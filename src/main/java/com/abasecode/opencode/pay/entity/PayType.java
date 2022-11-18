package com.abasecode.opencode.pay.entity;

import lombok.Getter;

/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */
@Getter
public enum PayType {
    /**
     * 支付宝-app
     */
    ALIPAY_APP(1,"ALIPAY_APP",1),
    /**
     * 支付宝-wap
     */
    ALIPAY_WAP(2,"ALIPAY_WAP",1),
    /**
     * 支付宝-网站
     */
    ALIPAY_PAGE(3,"ALIPAY_PAGE",1),
    /**
     * 微信-公众号
     */
    WECHAT_JSAPI_MP(4,"WECHAT_JSAPI_MP",2),
    /**
     * 微信-小程序
     */
    WECHAT_JSAPI_MICRO(5,"WECHAT_JSAPI_MICRO",2),
    /**
     * 微信-app
     */
    WECHAT_APP(6,"WECHAT_APP",2),
    /**
     * 微信-native
     */
    WECHAT_NATIVE(7,"WECHAT_NATIVE",2);

    private Integer code;
    private String name;
    private Integer channelId;

    private PayType(int code,String name, int channelId){
        this.code=code;
        this.name = name;
        this.channelId = channelId;
    }

    public static PayType getPayType(Integer code,Integer channelId){
        for (PayType type: PayType.values()){
            if(type.getChannelId().equals(channelId)){
                if(type.getCode().equals(type.getCode())){
                    return type;
                }
            }
        }
        return null;
    }

}
