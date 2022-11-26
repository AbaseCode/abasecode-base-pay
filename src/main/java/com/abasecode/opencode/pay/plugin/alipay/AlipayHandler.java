package com.abasecode.opencode.pay.plugin.alipay;

import com.abasecode.opencode.pay.entity.BaseGoodDetail;
import com.abasecode.opencode.pay.entity.BaseOrder;
import com.abasecode.opencode.pay.entity.PayType;
import com.abasecode.opencode.pay.plugin.alipay.constant.AliConstant;
import com.abasecode.opencode.pay.plugin.alipay.entity.CloseOrderParam;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * @param payType 支付类别
     * @param order 订单信息
     * @return 返回JS需要的表单内容
     * @throws AlipayApiException
     */
    public AlipayTradeWapPayResponse handlerPrepayWap(PayType payType, BaseOrder order) throws AlipayApiException {
        if(payType==PayType.ALIPAY_WAP){
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

    public void handlePayNotify(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> map = getReceiveMap(request);

    }

    public boolean paynotify(Map<String, String> receiveMap) throws Exception {
        boolean signVerified = AlipaySignature.rsaCheckV2(receiveMap,AliConstant.alipayPublicKey,AliConstant.CHARSET,AliConstant.SIGN_TYPE);
        return signVerified;
//        if (signVerified) {
//            String tradeStatus = receiveMap.get("trade_status");
////            if ("TRADE_FINISHED".equals(tradeStatus) || "TRADE_SUCCESS".equals(tradeStatus)) {
//            if(TradeStatus.TRADE_FINISHED.name().equals(tradeStatus) || TradeStatus.TRADE_SUCCESS.name().equals(tradeStatus)){
//                String orderNoStr = receiveMap.get("out_trade_no").toString();
////                BigDecimal orderNo = new BigDecimal(orderNoStr);
//
////                XqlOrder xqlOrder = xqlOrderServiceImpl
////                        .selectXqlOrderByOrderNo(orderNo);
//                // 订单不存在
//                if (xqlOrder == null) {
//                    logger.info("订单号" + orderNoStr + "不存在");
//                    return false;
//                }
//
//                // 订单已经支付
//                if (xqlOrder.getOrderStatus() != 101
//                        && xqlOrder.getPayStatus() == 1) {
//                    logger.info("订单号" + orderNoStr + "已经支付");
//                    return true;
//                }
//
//                // 判断电商订单还是门店订单
//                Short deliveryType = xqlOrder.getDeliveryType();
//                String trade_no = xqlOrder.getPayNo();
//                Long orderAmountL = xqlOrder.getOrderAmount();
//                BigDecimal orderAmountB = new BigDecimal(orderAmountL);
//                BigDecimal d100 = new BigDecimal(100);
//                BigDecimal orderAmount = orderAmountB.divide(d100, 2, 2);
//
//                // 根据单据类型进行补单，成功则更新单据支付信息，失败则进行退款
//                ResponseJson responseJson = weChatPayServiceImpl.fullorder(deliveryType, orderNo);
//                if (responseJson.getCode().equalsIgnoreCase(CodeMsg.SUCCESS_CODE)) {
//                    XqlOrder xqlOrder1 = new XqlOrder();
//
//                    xqlOrder1.setOrderNo(orderNo);
//                    if(xqlOrder.getDeliveryType() == 0){
//                        xqlOrder1.setOrderStatus(302);
//                        xqlOrder1.setSelfDeliveryStatus((short) 0);
//                    }else{
//                        xqlOrder1.setOrderStatus(201);
//                    }
//                    xqlOrder1.setPayStatus((short) 1);
//                    xqlOrder1.setPayType((short) 1);
//                    xqlOrder1.setPayAccount(receiveMap.get("trade_no"));
//                    xqlOrder1.setPayNo(receiveMap.get("trade_no"));
//                    xqlOrder1.setPaidAmount(Math.round(Double.parseDouble(receiveMap.get("total_amount").toString()) * 100));
//                    xqlOrder1.setPayTime(new Date());
//
//                    int returnResult = xqlOrderServiceImpl
//                            .updateXqlOrderByOrderNo(xqlOrder1);
//
//                    if (returnResult > 0) {
//                        return true;
//                    } else {
//                        logger.info("订单号" + orderNoStr + "更新支付信息失败");
//                        return false;
//                    }
//                } else {
//                    // 退单
//                    refund(trade_no, orderAmount);
//                }
//            }
//        }
//        return signVerified;
    }

    private static Map<String,String> getReceiveMap(HttpServletRequest request){
        Map<String,String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        return params;
    }


    /**
     * 用BaseOrder组装支付宝参数
     * @param order BaseOrder
     * @return AlipayTradeWapPayModel
     */
    private AlipayTradeWapPayModel createPayParam(BaseOrder order){
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(order.getOutTradeNo());
        model.setSubject(order.getSubject());
        model.setTotalAmount(getYuanFromFen(order.getAmount()));
        if(order.getDetails().size() > 0){
            List<GoodsDetail> list = new ArrayList<>();
            for (int i = 0; i < order.getDetails().size(); i++) {
                BaseGoodDetail bd = order.getDetails().get(i);
                GoodsDetail d=new GoodsDetail();
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
     * @param param
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeQueryResponse queryPay(AlipayTradeQueryModel param) throws AlipayApiException {

        AlipayClient alipayClient = createAlipayClient();
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizModel(param);
        request.setNeedEncrypt(AliConstant.hasEncrypt);
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        return response;
    }

    /**
     * 退款
     * @param param
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeRefundResponse refund(AlipayTradeRefundModel param) throws AlipayApiException {
        AlipayClient alipayClient = createAlipayClient();
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizModel(param);
        request.setNeedEncrypt(AliConstant.hasEncrypt);
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        return response;
    }

    /**
     * 退款查询
     * @param param
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeFastpayRefundQueryResponse refundQuery(AlipayTradeQueryModel param) throws AlipayApiException {
        AlipayClient alipayClient = createAlipayClient();
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizModel(param);
        request.setNeedEncrypt(AliConstant.hasEncrypt);
        AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
        return response;
    }


    /**
     * 关闭订单
     * @param param
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeCloseResponse close(CloseOrderParam param) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key","RSA2");
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        JSONObject bizContent = new JSONObject();
        if(StringUtils.isNotBlank(param.getTradeNo())){
            bizContent.put("trade_no", param.getTradeNo());
        }
        if(StringUtils.isNotBlank(param.getOutTradeNo())){
            bizContent.put("out_trade_no", param.getOutTradeNo());
        }
        if(StringUtils.isNotBlank(param.getOperatorId())){
            bizContent.put("operator_id", param.getOperatorId());
        }
        request.setNeedEncrypt(AliConstant.hasEncrypt);
        request.setBizContent(bizContent.toString());
        AlipayTradeCloseResponse response = alipayClient.execute(request);
        return response;
    }


    /**
     * 封装支付宝客户端
     * @return
     */
    public AlipayClient createAlipayClient(){
        AlipayClient alipayClient = new DefaultAlipayClient(AliConstant.URL_GATEWAY,AliConstant.appid,
                AliConstant.appPrivateKey,AliConstant.FORMAT,AliConstant.CHARSET,AliConstant.alipayPublicKey,
                AliConstant.SIGN_TYPE,AliConstant.encryptKey,AliConstant.encryptType);
        return alipayClient;
    }

}
