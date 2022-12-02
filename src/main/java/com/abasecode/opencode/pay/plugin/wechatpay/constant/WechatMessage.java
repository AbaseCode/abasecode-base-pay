package com.abasecode.opencode.pay.plugin.wechatpay.constant;

/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */
public class WechatMessage {
    public static final String WECHAT_EXCEPTION_HTTP_FAIL = "执行http请求失败！";
    public static final String WECHAT_EXCEPTION_URL_ERROR = "URL错误！";
    public static final String WECHAT_EXCEPTION_SIGN_FAIL = "获得RSA签名失败!";
    public static final String WECHAT_EXCEPTION_RSA_ERROR = "当前Java环境不支持RSA!";
    public static final String WECHAT_EXCEPTION_KEY_SPEC_ERROR = "无效的密钥格式!";
    public static final String WECHAT_EXCEPTION_AES_FAIL = "执行AES解密请求失败！";
    public static final String WECHAT_ERROR_MESSAGE_MISSING_OUT_TRADE_NO = "商户订单号不能为空！";
    public static final String WECHAT_ERROR_MESSAGE_MISSING_REFUND_NO = "商户退款订单号不能为空！";
}
