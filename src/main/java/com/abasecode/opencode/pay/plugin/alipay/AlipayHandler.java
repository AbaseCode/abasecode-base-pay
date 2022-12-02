package com.abasecode.opencode.pay.plugin.alipay;

import com.abasecode.opencode.pay.entity.BaseGoodDetail;
import com.abasecode.opencode.pay.entity.BaseOrder;
import com.abasecode.opencode.pay.entity.PayType;
import com.abasecode.opencode.pay.plugin.alipay.constant.AliConstant;
import com.abasecode.opencode.pay.plugin.alipay.constant.AliMessage;
import com.abasecode.opencode.pay.plugin.alipay.entity.TradeStatus;
import com.abasecode.opencode.pay.util.BaseUtils;
import com.alibaba.fastjson2.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.domain.GoodsDetail;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.abasecode.opencode.pay.util.BaseUtils.getYuanFromFen;

/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */
@Component
@Slf4j
public class AlipayHandler {
    /**
     * 支付宝WAP支付
     *
     * @param payType 支付类别
     * @param order   订单信息
     * @return 返回JS需要的表单内容
     * @throws AlipayApiException
     */
    public AlipayTradeWapPayResponse prepayWap(PayType payType, BaseOrder order) throws AlipayApiException {
        if (payType == PayType.ALIPAY_WAP) {
            AlipayClient alipayClient = createAlipayClient();
            AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
            request.setNotifyUrl(AliConstant.payNotifyUrl);
            request.setReturnUrl(AliConstant.payReturnUrl);
            request.setBizModel(createPayParam(order));
            request.setNeedEncrypt(AliConstant.hasEncrypt);
            AlipayTradeWapPayResponse response = alipayClient.pageExecute(request);
            return response;
        }
        return null;
    }

    /**
     * 处理支付宝回调 map
     *
     * @param map 回调map
     * @return TradeStatus
     * @throws Exception
     */
    public TradeStatus getTradeStatus(Map<String, String> map) throws Exception {
        boolean b = AlipaySignature.rsaCheckV1(map, AliConstant.alipayPublicKey, AliConstant.CHARSET, AliConstant.SIGN_TYPE);
        if (b) {
            String trade_status = map.get("trade_status");
            if (trade_status.equals(TradeStatus.TRADE_CLOSED.name())) {
                return TradeStatus.TRADE_CLOSED;
            }
            if (trade_status.equals(TradeStatus.TRADE_FINISHED.name())) {
                return TradeStatus.TRADE_FINISHED;
            }
            if (trade_status.equals(TradeStatus.TRADE_SUCCESS.name())) {
                return TradeStatus.TRADE_SUCCESS;
            }
            if (trade_status.equals(TradeStatus.WAIT_BUYER_PAY.name())) {
                return TradeStatus.WAIT_BUYER_PAY;
            }
        }
        throw new Exception(AliMessage.ALIPAY_EXCEPTION_RSA_FAIL);
    }

    /**
     * 验签
     *
     * @param map Map
     * @return bool
     * @throws AlipayApiException
     */
    private boolean checkMap(Map<String, String> map) throws AlipayApiException {
        return AlipaySignature.rsaCheckV1(map, AliConstant.alipayPublicKey, AliConstant.CHARSET, AliConstant.SIGN_TYPE);
    }

    /**
     * 获取回调map
     *
     * @param requestParams Map
     * @return Map
     * @throws Exception
     */
    private Map<String, String> getPayNotifyMap(Map<String, String[]> requestParams) throws Exception {
        Map<String, String> params = new HashMap<>();
        for (Object o : requestParams.keySet()) {
            String name = (String) o;
            String[] values = (String[]) requestParams.get(name);
            String value = "";
            for (int i = 0; i < values.length; i++) {
                value = (i == values.length - 1) ? value + values[i]
                        : value + values[i] + ",";
            }
            params.put(name, value);
        }
        return params;
    }

    /**
     * 支付宝回调map
     *
     * @param requestParams 请求内容
     * @return map Map
     * @throws Exception
     */
    public Map<String, String> getNotifyMaps(Map<String, String[]> requestParams) throws Exception {
        Map<String, String> map = getPayNotifyMap(requestParams);
        if (checkMap(map)) {
            return map;
        }
        throw new Exception(AliMessage.ALIPAY_EXCEPTION_RSA_FAIL);
    }


    /**
     * 用BaseOrder组装支付宝参数
     *
     * @param order BaseOrder
     * @return AlipayTradeWapPayModel
     */
    private AlipayTradeWapPayModel createPayParam(BaseOrder order) {
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(order.getOutTradeNo());
        model.setSubject(order.getSubject());
        model.setTotalAmount(getYuanFromFen(order.getAmount()));
        if (StringUtils.isNotEmpty(order.getOtherParams())) {
            model.setPassbackParams(order.getOtherParams());
        }

        if (order.getDetails().size() > 0) {
            List<GoodsDetail> list = new ArrayList<>();
            for (int i = 0; i < order.getDetails().size(); i++) {
                BaseGoodDetail bd = order.getDetails().get(i);
                GoodsDetail d = new GoodsDetail();
                d.setGoodsId(bd.getGoodId());
                d.setBody(bd.getGoodName());
                d.setCategoriesTree(bd.getContentTree());
                d.setGoodsCategory(bd.getCategory());
                d.setPrice(BaseUtils.getYuanFromFen(bd.getPrice()));
                d.setQuantity(Long.parseLong(bd.getQuantity().toString()));
                d.setShowUrl(bd.getShowUrl());
                d.setGoodsName(bd.getGoodName());
                list.add(d);
            }
            model.setGoodsDetail(list);
        }
        return model;
    }

    /**
     * 查询支付情况
     *
     * @param param
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeQueryResponse payQuery(AlipayTradeQueryModel param) throws AlipayApiException {

        AlipayClient alipayClient = createAlipayClient();
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizModel(param);
        request.setNeedEncrypt(AliConstant.hasEncrypt);
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        return response;
    }

    /**
     * 退款
     *
     * @param param
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeRefundResponse payRefund(AlipayTradeRefundModel param) throws AlipayApiException {
        AlipayClient alipayClient = createAlipayClient();
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizModel(param);
        request.setNeedEncrypt(AliConstant.hasEncrypt);
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        return response;
    }

    /**
     * 退款
     *
     * @param bizContent
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeRefundResponse payRefund(JSONObject bizContent) throws AlipayApiException {
        AlipayClient alipayClient = createAlipayClient();
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent(bizContent.toString());
        request.setNeedEncrypt(AliConstant.hasEncrypt);
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        return response;
    }

    /**
     * 退款查询
     *
     * @param bizContent 退款查询信息(JSONObject)
     * @return AlipayTradeFastpayRefundQueryResponse
     * @throws Exception
     */
    public AlipayTradeFastpayRefundQueryResponse refundQuery(JSONObject bizContent) throws Exception {
        AlipayClient alipayClient = createAlipayClient();
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizContent(bizContent.toString());
        request.setNeedEncrypt(AliConstant.hasEncrypt);
        AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
        return response;
    }


    /**
     * 关闭订单
     *
     * @param outTradeNo 商户订单号
     * @return AlipayTradeCloseResponse
     * @throws AlipayApiException
     */
    public AlipayTradeCloseResponse payClose(String outTradeNo) throws AlipayApiException {
        AlipayClient alipayClient = createAlipayClient();
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", outTradeNo);
        request.setBizContent(bizContent.toString());
        request.setNeedEncrypt(AliConstant.hasEncrypt);
        request.setBizContent(bizContent.toString());
        AlipayTradeCloseResponse response = alipayClient.execute(request);
        return response;
    }

    /**
     * 关闭订单
     *
     * @param bizContent 商户订单号
     * @return AlipayTradeCloseResponse
     * @throws AlipayApiException
     */
    public AlipayTradeCloseResponse payClose(JSONObject bizContent) throws AlipayApiException {
        AlipayClient alipayClient = createAlipayClient();
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        request.setBizContent(bizContent.toString());
        request.setNeedEncrypt(AliConstant.hasEncrypt);
        request.setBizContent(bizContent.toString());
        AlipayTradeCloseResponse response = alipayClient.execute(request);
        return response;
    }


    /**
     * 封装支付宝客户端
     *
     * @return
     */
    private AlipayClient createAlipayClient() {
        AlipayClient alipayClient = new DefaultAlipayClient(AliConstant.URL_GATEWAY, AliConstant.appid,
                AliConstant.appPrivateKey, AliConstant.FORMAT, AliConstant.CHARSET, AliConstant.alipayPublicKey,
                AliConstant.SIGN_TYPE, AliConstant.encryptKey, AliConstant.encryptType);
        return alipayClient;
    }

}
