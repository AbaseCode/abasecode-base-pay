package com.abasecode.opencode.pay.plugin.wechatpay.entity;

/**
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 */
public enum WechatCode {
    /**
     * 系统超时
     */
    SYSTEM_ERROR(500,"系统超时","请不要更换商户退款单号，请使用相同参数再次调用API。"),
    /**
     * 退款请求失败
     */
    USER_ACCOUNT_ABNORMAL(403,"退款请求失败","此状态代表退款申请失败，商户可自行处理退款。"),
    /**
     * 余额不足
     */
    NOT_ENOUGH(403,"余额不足","此状态代表退款申请失败，商户可根据具体的错误提示做相应的处理。"),
    /**
     * 参数错误
     */
    PARAM_ERROR(400,"参数错误","请求参数错误，请重新检查再调用申请退款接口"),
    /**
     * MCHID不存在
     */
    MCH_NOT_EXISTS(404,"MCHID不存在","请检查MCHID是否正确");
    private int code;
    private String msg;
    private String desc;
    private WechatCode(int code,String msg,String desc){
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }
}
