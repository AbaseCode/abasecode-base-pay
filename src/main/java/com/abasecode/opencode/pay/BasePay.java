package com.abasecode.opencode.pay;

import com.abasecode.opencode.base.code.CodeException;
import com.abasecode.opencode.pay.entity.BaseOrder;
import com.abasecode.opencode.pay.entity.PayType;
import com.abasecode.opencode.pay.plugin.alipay.AlipayHandler;
import com.abasecode.opencode.pay.plugin.alipay.entity.CloseOrderParam;
import com.abasecode.opencode.pay.plugin.alipay.form.AlipayQueryForm;
import com.abasecode.opencode.pay.plugin.alipay.form.AlipayRefundForm;
import com.abasecode.opencode.pay.plugin.wechatpay.WechatHandler;
import com.abasecode.opencode.pay.plugin.wechatpay.entity.*;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */
@Component
public class BasePay {

    @Autowired
    private WechatHandler wechatHandler;
    @Autowired
    private AlipayHandler alipayHandler;

    /**
     * 创建支付单
     * @param payType 支付类型
     * @param order 订单
     * @return 支付响应
     * @throws AlipayApiException
     */
    public AlipayTradeWapPayResponse alipayCreate(PayType payType, BaseOrder order) throws AlipayApiException {
        AlipayTradeWapPayResponse response = alipayHandler.handlerPrepayWap(payType, order);
        return response;
    }

    /**
     * 支付宝查询
     * @param form
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeQueryResponse alipayQuery(AlipayQueryForm form) throws AlipayApiException {
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(form.getOutTradeNo());
        model.setQueryOptions(form.getQueryOptions());
        model.setTradeNo(form.getTradeNo());
        return alipayHandler.queryPay(model);
    }

    /**
     * 退款
     * @param form
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeRefundResponse alipayRefund(AlipayRefundForm form) throws AlipayApiException {
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(form.getOutTradeNo());
        model.setTradeNo(form.getTradeNo());
        model.setRefundAmount(form.getRefundAmount());
        model.setRefundReason(form.getRefundReason());
        model.setOutRequestNo(form.getOutRequestNo());
        return alipayHandler.refund(model);
    }

    /**
     * 退款查询
     * @param param
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeFastpayRefundQueryResponse alipayRefundQuery(AlipayTradeQueryModel param) throws AlipayApiException {
        return alipayHandler.refundQuery(param);
    }

    /**
     * 关闭订单
     * @param param
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeCloseResponse alipayClose(CloseOrderParam param) throws AlipayApiException {
        return alipayHandler.close(param);
    }


    /**
     * 公众号获取Code的Url
     * 前端获取
     * @return url
     * @throws UnsupportedEncodingException
     */
    public String wechatMpCodeUrl() throws UnsupportedEncodingException {
        return wechatHandler.createJsapiCodeUrl(null);
    }

    /**
     * 公众号获取Code的Url
     * @param state 要传递的参数
     * @return url
     * @throws UnsupportedEncodingException
     */
    public String wechatMpCodeUrl(String state) throws UnsupportedEncodingException {
        return wechatHandler.createJsapiCodeUrl(state);
    }

    /**
     * 小程序通过Code获取OpenId
     * 前端获取
     * @param code 前端获取的code
     * @return url
     */
    public String wechatMicroOpenIdUrl(String code){
        return wechatHandler.createJsapiOpenIdUrl(code);
    }

    /**
     * 微信支付
     * @param payType 支付类型
     * @param baseOrder 订单
     * @param codeOrOpenId 公众号必传code，小程序传openId
     * @return WechatClientPayParam
     * @throws Exception
     */
    public WechatClientPayParam wechatPayCreate(PayType payType, BaseOrder baseOrder, String codeOrOpenId) throws Exception {
        switch (payType){
            case WECHAT_JSAPI_MP:
                return wechatHandler.handlerPrePayJsapiMp(payType, baseOrder, codeOrOpenId);
            case WECHAT_JSAPI_MICRO:
                return wechatHandler.handlerPrePayJsapiMicro(payType, baseOrder, codeOrOpenId);
                default: throw new CodeException("暂时只支持微信支付");
        }
    }

    /**
     * 微信通知处理
     * @param pay4Notice 原始通知
     * @return 解密通知
     */
    public Pay4Notice wechatPayNotify(Pay4Notice pay4Notice){
        return wechatHandler.handlePayNotify(pay4Notice);
    }

    /**
     * 微信关闭订单
     * @param outTradeNo 订单号
     */
    public void wechatClose(String outTradeNo){
        wechatHandler.handleClosePay(outTradeNo);
    }

    /**
     * 微信申请退款（系统自动生成退款单号）
     * @param outTradeNo 订单号
     * @param reason 退款原因
     * @param refund 退款金额
     * @param total 原订单金额
     * @return 退款返回参数
     */
    public RefundCreateReturn wechatRefund(String outTradeNo,String reason,int refund,int total){
        RefundCreateReturn refundCreateReturn = wechatHandler.handleRefund(outTradeNo, reason, refund, total);
        return refundCreateReturn;
    }

    /**
     * 微信退款通知
     * @param notice 退款通知
     * @return 解密结果
     */
    public RefundNotice wechatRefundNotify(RefundNotice notice){
        RefundNotice refundNotice = wechatHandler.handleRefundNotify(notice);
        return refundNotice;
    }

    /**
     * 微信查询支付
     * @param outTradeNo 支付单号
     * @return 查询结果
     */
    public Pay4QueryReturn wechatQueryPay(String outTradeNo){
        Pay4QueryReturn pay4QueryReturn = wechatHandler.handlerQueryPay(outTradeNo);
        return pay4QueryReturn;
    }

    /**
     * 微信查询退款
     * @param returnTradeNo 退款单号
     * @return 退款结果
     */
    public RefundQueryReturn wechatQueryRefund(String returnTradeNo){
        RefundQueryReturn refundQueryReturn = wechatHandler.handlerQueryRefund(returnTradeNo);
        return refundQueryReturn;
    }


}
