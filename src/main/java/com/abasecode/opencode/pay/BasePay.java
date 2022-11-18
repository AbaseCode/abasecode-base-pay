package com.abasecode.opencode.pay;

import com.abasecode.opencode.base.code.CodeException;
import com.abasecode.opencode.pay.entity.BaseOrder;
import com.abasecode.opencode.pay.entity.PayType;
import com.abasecode.opencode.pay.plugin.alipay.AlipayHandler;
import com.abasecode.opencode.pay.plugin.wechatpay.WechatHandler;
import com.abasecode.opencode.pay.plugin.wechatpay.entity.*;
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
     * 支付宝支付
     */
    public void createAliPay(){
        //todo
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
    public WechatClientPayParam createWechatPay(PayType payType, BaseOrder baseOrder, String codeOrOpenId) throws Exception {
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
