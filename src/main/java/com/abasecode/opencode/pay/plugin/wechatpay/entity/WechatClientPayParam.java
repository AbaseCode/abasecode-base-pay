package com.abasecode.opencode.pay.plugin.wechatpay.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * JSAPI客户端发起支付需要的参数（小程序，公众号）
 */
@Data
@Accessors(chain = true)
public class WechatClientPayParam implements Serializable {
    /**
     * AppID
     * string[1,32]
     * 必
     */
    private String appid;
    /**
     * 时间戳 10位数字
     * string[1,32]
     * 必
     */
    private String timeStamp;
    /**
     * 随机字符串
     * string[1,32]
     * 必
     */
    private String nonceStr;
    /**
     * 订单详情扩展字符串
     * string[1,128]
     * 必
     * 示例值：prepay_id=wx201410272009395522657a690389285100
     */
    private String packages;
    /**
     * 签名方式
     * string[1,32]
     * 必
     * 默认为RSA，仅支持RSA。
     * 示例值：RSA
     */
    private String signType;
    /**
     * 签名
     * string[1,512]
     * 必
     * 签名，使用字段appId、timeStamp、nonceStr、package计算得出的签名值
     */
    private String paySign;
}

