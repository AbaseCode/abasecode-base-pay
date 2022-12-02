package com.abasecode.opencode.pay.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 */
@Data
@Accessors(chain = true)
public class PrepayResult implements Serializable {
    private static final long serialVersionUID = -7401634860330043604L;
    /**
     * 预支付信息
     * 支付宝和微信都是在客户端浏览器直接调用
     */
    private String prePayResult;
}
