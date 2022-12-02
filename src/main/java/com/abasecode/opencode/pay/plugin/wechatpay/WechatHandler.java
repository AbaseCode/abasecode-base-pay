package com.abasecode.opencode.pay.plugin.wechatpay;

import com.abasecode.opencode.pay.entity.BaseOrder;
import com.abasecode.opencode.pay.entity.PayType;
import com.abasecode.opencode.pay.plugin.wechatpay.constant.WechatConstant;
import com.abasecode.opencode.pay.plugin.wechatpay.constant.WechatMessage;
import com.abasecode.opencode.pay.plugin.wechatpay.entity.*;
import com.abasecode.opencode.pay.plugin.wechatpay.util.WechatHttp;
import com.abasecode.opencode.pay.plugin.wechatpay.util.WechatUtils;
import com.abasecode.opencode.pay.util.BaseUtils;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */
@Component
@Slf4j
public class WechatHandler {

    /**
     * 付款流程
     * 公众号支付第一步
     * 生成公众号JSAPI获取code的URL，通过微信浏览器打开URL，等待微信回调返回code
     *
     * @param state 可不传
     * @return url
     */
    public String createJsapiCodeUrl(String state) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(state)) {
            state = "STATE";
        }
        String url = URLEncoder.encode(
                WechatConstant.wechatCodeReturnUrl,
                StandardCharsets.UTF_8.toString()
        );
        String fullUrl = WechatConstant.URL_JSAPI_CODE.replace("{APPID}", WechatConstant.wechatMpAppid)
                .replace("{REDIRECT_URI}", url)
                .replace("{SCOPE}", "snsapi_base")
                .replace("{STATE}", state);
        return fullUrl;
    }

    /**
     * 付款流程，发给客户端用
     * 封装获取openId的URL（小程序）
     *
     * @param code
     * @return url
     */
    public String createJsapiOpenIdUrl(String code) {
        String url = WechatConstant.URL_CODE2SESSION.replace("{APPID}", WechatConstant.wechatMicroAppid)
                .replace("{SECRET}", WechatConstant.wechatMicroSecret)
                .replace("{JSCODE}", code);
        return url;
    }

    /**
     * 付款流程：公众号，服务端发起并获得付款参数
     * 公众号支付第三步，生成客户端需要的参数
     * 再由客户端将参数发送给微信
     *
     * @param payType 支付类型
     * @param order   订单
     * @param code    微信返回的code
     * @return ClientPayParam
     * @throws Exception
     */
    public WechatClientPayParam prePayJsapiMp(PayType payType, BaseOrder order, String code) throws Exception {
        AccessToken accessToken = getAccessToken(code);
        Payer payer = new Payer().setOpenid(accessToken.getOpenid());
        return createClientPayParam(payType, order, payer);
    }

    /**
     * 付款流程：小程序，服务端发起并获得参数
     * 小程序支付第三步，生成客户端需要的参数
     * 再由客户端将参数发送给微信
     *
     * @param payType 支付类型
     * @param order   订单
     * @param openId  支付者openId（通过微信客户端获取）
     * @return ClientPayParam
     * @throws Exception
     */
    public WechatClientPayParam prePayJsapiMicro(PayType payType, BaseOrder order, String openId) throws Exception {
        Payer payer = new Payer().setOpenid(openId);
        return createClientPayParam(payType, order, payer);
    }

    /**
     * 付款流程：通知处理
     * 处理支付成功通知(解密)
     *
     * @param notice 微信发起的通知
     * @return 通知体
     */
    public PayNotice payNotify(PayNotice notice) throws Exception {
        PayNoticeResourceOrigin origin = null;
        if (notice != null) {
            if (notice.getEventType().equals(WechatConstant.ORDER_NOTICE_SUCCESS)) {
                String s = WechatUtils.decryptToString(notice.getResource().getAssociatedData(),
                        notice.getResource().getNonce(), notice.getResource().getCiphertext());
                origin = JSONObject.parseObject(s, PayNoticeResourceOrigin.class);
                notice.setOrigin(origin);
            }
        }
        return notice;
    }

    /**
     * 退款流程：发起退款
     * 发起退款并获得退款结果
     *
     * @param outTradeNo  商户订单号
     * @param outRefundNo 商户退单单号
     * @param reason      退款原因
     * @param refund      退款金额
     * @param total       订单金额
     * @return 退款返回参数
     */
    public RefundCreateReturn payRefund(String outTradeNo, String outRefundNo, String reason, int refund, int total) throws Exception {
        RefundCreate refundCreate = createRefundParam(outTradeNo, outRefundNo, reason, refund, total);
        String s = JSON.toJSONString(refundCreate);
        RefundCreateReturn r = WechatHttp.httpPost(WechatConstant.URL_JSAPI_REFUND, s, RefundCreateReturn.class);
        if (r.getStatusCode() != WechatConstant.STATUS_CODE_OK) {
            r.setOutTradeNo(outTradeNo);
            r.getAmount().setRefund(refund);
            r.getAmount().setTotal(total);
            r.setOutRefundNo(refundCreate.getOutRefundNo());
        } else {
            r.setStatusCode(0);
            r.setMessage(WechatConstant.SUCCESS);
        }
        return r;
    }

    /**
     * 退款流程：处理退款通知
     * 处理退款通知（并解密）
     *
     * @param notice 退款通知
     * @return 解密
     */
    public RefundNotice payRefundNotify(RefundNotice notice) throws Exception {
        RefundNoticeResourceOrigin origin = null;
        if (notice != null) {
            if (notice.getEventType().equals(WechatConstant.REFUND_NOTICE_SUCCESS)) {
                String s = WechatUtils.decryptToString(notice.getResource().getAssociatedData(),
                        notice.getResource().getNonce(), notice.getResource().getCiphertext());
                origin = JSONObject.parseObject(s, RefundNoticeResourceOrigin.class);
                notice.setOrigin(origin);
            }
        }
        return notice;
    }

    /**
     * 关闭流程
     * 关闭订单
     *
     * @param outTradeNo 订单编号
     * @return 关闭情况
     */
    public Object payClose(String outTradeNo) throws Exception {
        if (StringUtils.isNotEmpty(outTradeNo)) {
            String url = WechatConstant.URL_ORDER_CLOSE.replace("{out_trade_no}", outTradeNo);
            PayClose close = new PayClose().setMchid(WechatConstant.wechatMchid);
            Object o = WechatHttp.httpPost(url, JSON.toJSONString(close), Object.class);
            return o;
        }
        throw new Exception(WechatMessage.WECHAT_ERROR_MESSAGE_MISSING_OUT_TRADE_NO);
    }

    /**
     * 查询流程：查询支付
     * 查询订单
     *
     * @param outTradeNo 订单编号
     * @return 查询结果
     */
    public PayQueryReturn payQuery(String outTradeNo) throws Exception {
        if (StringUtils.isNotEmpty(outTradeNo)) {
            String url = WechatConstant.URL_ORDER_QUERY
                    .replace("{out_trade_no}", outTradeNo)
                    .replace("{mchid}", WechatConstant.wechatMchid);
            PayQueryReturn payQueryReturn = WechatHttp.httpGet(url, "", PayQueryReturn.class);
            return payQueryReturn;
        }
        throw new Exception(WechatMessage.WECHAT_ERROR_MESSAGE_MISSING_OUT_TRADE_NO);
    }

    /**
     * 查询流程：查询退款
     * 查询退款
     *
     * @param outRefundNo 退款单号
     * @return 退款内容
     */
    public RefundQueryReturn payRefundQuery(String outRefundNo) throws Exception {
        if (StringUtils.isNotEmpty(outRefundNo)) {
            String url = WechatConstant.URL_REFUND_QUERY
                    .replace("{out_refund_no}", outRefundNo);
            RefundQueryReturn orderQueryReturn = WechatHttp.httpGet(url, "", RefundQueryReturn.class);
            return orderQueryReturn;
        }
        throw new Exception(WechatMessage.WECHAT_ERROR_MESSAGE_MISSING_REFUND_NO);
    }

    /**
     * 付款流程，参数封装
     * 封装JSAPI订单信息（小程序，公众号）
     *
     * @param payType   支付类型
     * @param baseOrder 订单信息
     * @param payer     payer
     * @return JSAPI订单详情
     */
    private Pay4Jsapi createJsapiOrderParams(PayType payType, BaseOrder baseOrder, Payer payer) {
        AmountOrder amount = new AmountOrder()
                .setCurrency(WechatConstant.CURRENCY)
                .setTotal(baseOrder.getAmount());
        Pay4Jsapi order = new Pay4Jsapi()
                .setAppid(getAppId(payType))
                .setMchid(WechatConstant.wechatMchid)
                .setOutTradeNo(baseOrder.getOutTradeNo())
                .setDescription(baseOrder.getSubject())
                .setNotifyUrl(WechatConstant.wechatPayNotifyUrl)
                .setAmount(amount)
                .setPayer(payer);
        if (StringUtils.isNotEmpty(baseOrder.getOtherParams())) {
            order.setAttach(baseOrder.getOtherParams());
        }
        if (StringUtils.isNotEmpty(baseOrder.getTimeExpire())) {
            order.setTimeExpire(baseOrder.getTimeExpire());
        }
//        if(baseOrder.getBaseOrderWechat()!=null){
//            BaseOrderWechat baseOrderWechat = baseOrder.getBaseOrderWechat();
//            order.setInvoice(baseOrderWechat.isInvoice());
//            if(StringUtils.isNotEmpty(baseOrderWechat.getGoodsTag())){
//                order.setGoodsTag(baseOrderWechat.getGoodsTag());
//            }
//            if(StringUtils.isNotEmpty(baseOrderWechat.getDescription())){
//                order.setDescription(baseOrderWechat.getDescription());
//            }
//        }
        return order;
    }

    /**
     * 退款流程，参数封装
     * 生成退款信息
     *
     * @param outTradeNo
     * @param outRefundNo
     * @param reason
     * @param refund
     * @param total
     * @return RefundCreate
     */
    private RefundCreate createRefundParam(String outTradeNo, String outRefundNo, String reason, int refund, int total) {
        AmountRefund amount = new AmountRefund()
                .setRefund(refund)
                .setTotal(total)
                .setCurrency(WechatConstant.CURRENCY);
        RefundCreate refundCreate = new RefundCreate()
                .setOutRefundNo(outRefundNo)
                .setAmount(amount)
                .setNotifyUrl(WechatConstant.wechatRefundNotifyUrl)
                .setOutTradeNo(outTradeNo)
                .setReason(reason)
                .setFundsAccount(WechatConstant.REFUND_ACCOUNT);
        return refundCreate;
    }

    /**
     * 根据微信支付类型返回AppID
     *
     * @param payType
     * @return appid
     */
    private String getAppId(PayType payType) {
        switch (payType) {
            case WECHAT_APP:
                return WechatConstant.wechatAppAppid;
            case WECHAT_JSAPI_MICRO:
                return WechatConstant.wechatMicroAppid;
            default:
                return WechatConstant.wechatMpAppid;
        }
    }

    /**
     * 付款流程
     * 公众号支付第二步
     * 通过code获取AccessToken
     *
     * @param code 第一步获取的code
     * @return AccessToken
     */
    private AccessToken getAccessToken(String code) throws Exception {
        String accessUrl = WechatConstant.URL_JSAPI_ACCESS_TOKEN.replace("{APPID}", WechatConstant.wechatMpAppid)
                .replace("{SECRET}", WechatConstant.wechatMpSecret)
                .replace("{CODE}", code);
        JSONObject object = WechatHttp.httpGet(accessUrl, "", JSONObject.class);
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(object.getString("access_token"))
                .setExpiresIn(object.getLong("expires_in"))
                .setRefreshToken(object.getString("refresh_token"))
                .setOpenid(object.getString("openid"))
                .setScope(object.getString("scope"))
                .setIsSnapshotuser(object.getInteger("is_snapshotuser"))
                .setErrcode(object.getString("errcode"))
                .setErrmsg(object.getString("errmsg"));
        return accessToken;
    }

    /**
     * 付款流程：参数组装
     * 公众号支付第一步
     * 创建JSAPI客户端必须的参数
     * 付款流程：发起JSAPI预支付（向微信发送订单信息），获得返回的prepay_id后封装客户端支付参数包
     *
     * @param payType   支付类型
     * @param baseOrder 基础订单
     * @param payer     支付者
     * @return 客户端需要的参数
     * @throws Exception
     */
    private WechatClientPayParam createClientPayParam(PayType payType, BaseOrder baseOrder, Payer payer) throws Exception {
        String appId = getAppId(payType);
        Pay4Jsapi pay4Jsapi = createJsapiOrderParams(payType, baseOrder, payer);
        String s = JSON.toJSONString(pay4Jsapi);
        Pay4JsapiReturn r = WechatHttp.httpPost(WechatConstant.URL_JSAPI_ORDER, s, Pay4JsapiReturn.class);
        WechatClientPayParam param = new WechatClientPayParam()
                .setAppid(appId)
                .setPackages("prepay_id=" + r.getPrepayId())
                .setTimeStamp(BaseUtils.getCurrentTimeStamp())
                .setNonceStr(BaseUtils.getNonceStr())
                .setSignType(WechatConstant.SIGN_TYPE);
        String jsapiSign = WechatHttp.getJsapiSign(appId, param.getTimeStamp(), param.getNonceStr(), param.getPackages());
        param.setPaySign(jsapiSign);
        return param;
    }

}
