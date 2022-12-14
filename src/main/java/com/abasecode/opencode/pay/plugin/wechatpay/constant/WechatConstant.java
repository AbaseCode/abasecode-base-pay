package com.abasecode.opencode.pay.plugin.wechatpay.constant;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */
public class WechatConstant {

    public final static String URL_JSAPI_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={APPID}&redirect_uri={REDIRECT_URI}&response_type=code&scope={SCOPE}&state={STATE}#wechat_redirect";
    public final static String URL_JSAPI_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={APPID}&secret={SECRET}&code={CODE}&grant_type=authorization_code";
    public final static String URL_JSAPI_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid={APPID}&grant_type=refresh_token&refresh_token={REFRESH_TOKEN}";
    public final static String URL_JSAPI_ORDER = "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi";
    public final static String URL_ORDER_QUERY = "https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/{out_trade_no}?mchid={mchid}";
    public final static String URL_JSAPI_REFUND = "https://api.mch.weixin.qq.com/v3/refund/domestic/refunds";
    public final static String URL_REFUND_QUERY = "https://api.mch.weixin.qq.com/v3/refund/domestic/refunds/{out_refund_no}";

    public final static String URL_ORDER_CLOSE = "https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/{out_trade_no}/close";
    public final static String URL_CERTIFICATES = "https://api.mch.weixin.qq.com/v3/certificates";
    public final static String URL_CODE2SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid={APPID}&secret={SECRET}&js_code={JSCODE}&grant_type=authorization_code";
    public final static String CURRENCY = "CNY";
    public final static String ORDER_NOTICE_SUCCESS = "TRANSACTION.SUCCESS";
    public final static String ORDER_NOTICE_SUCCESS_CN = "????????????";
    public final static String REFUND_NOTICE_SUCCESS = "REFUND.SUCCESS";
    public final static String REFUND_NOTICE_SUCCESS_CN = "????????????";
    public final static String SUCCESS = "SUCCESS";
    public final static String FAIL = "FAIL";
    public final static String SUCCESS_CN = "??????";
    public final static String FAIL_CN = "??????";
    public final static String SIGN_TYPE = "RSA";
    public final static String SIGN_TYPE_RSA = "RSA";
    public final static String KEY_ALIAS = "tenpay certificate";
    public final static String REFUND_ACCOUNT = "AVAILABLE";
    public final static String CONTENT_TYPE_NAME = "Content-Type";
    public final static String CONTENT_TYPE_VALUE = "application/json;charset=UTF-8";
    public final static String ACCEPT = "Accept";
    public final static String ACCEPT_JSON = "application/json";
    public final static String ACCEPT_HTML_XML = "text/html, application/xhtml+xml, image/jxr, */*";
    public final static String AUTHORIZATION = "Authorization";
    public final static String POST = "POST";
    public final static String GET = "GET";
    public final static String RSA = "SHA256withRSA";
    public final static String AES_NAME = "AES";
    public final static String AES_SETTING = "AES/GCM/NoPadding";
    public final static int SECOND = 5000000;
    public final static int STATUS_CODE_OK = 200;
    public final static int GCM_LENGTH = 128;
    public final static String DATE_FORMAT_WITH_3339 = "yyyy-MM-dd'T'HH:mm:ss";
    public final static String CERTIFICATE_TYPE = "X509";
    /**
     * ?????????appid
     */
    public static String wechatMicroAppid;
    /**
     * ?????????appid
     */
    public static String wechatMpAppid;
    /**
     * APP???appid
     */
    public static String wechatAppAppid;

    /**
     * ????????????AppSecret
     */
    public static String wechatMicroSecret;
    /**
     * ????????????AppSecret
     */
    public static String wechatMpSecret;
    /**
     * App???AppSecret
     */
    public static String wechatAppSecret;
    /**
     * ????????????
     */
    public static String wechatBaseDomain;
    /**
     * ?????????JSAPI??????code???????????????(????????????)
     */
    public static String wechatCodeReturnUrl;

    /**
     * ???????????????
     */
    public static String wechatMchid;

    /**
     * ????????????
     */
    public static String wechatCertUrl;

    /**
     * ??????????????????
     */
    public static String wechatCertKey;

    /**
     * v3Key
     */
    public static String wechatV3key;

    /**
     * ??????
     */
    public static PrivateKey wechatPrivateKey;

    /**
     * ???????????????
     */
    public static String wechatSerialNo;
    /**
     * ??????
     */
    public static Map<String, X509Certificate> wechatCertificateMap = new ConcurrentHashMap<>();

    /**
     * ????????????
     */
    public static String wechatPayNotifyUrl;

    /**
     * ????????????
     */
    public static String wechatRefundNotifyUrl;
}

