package com.abasecode.opencode.pay.plugin.wechatpay.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */
@Data
@Accessors(chain = true)
public class Payer implements Serializable {
    /**
     * 用户在直连商户appid下的唯一标识。 下单前需获取到用户的Openid
     * string[1,128]
     */
    private String openid;
}

