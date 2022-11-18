package com.abasecode.opencode.pay.plugin.wechatpay.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */
@Data
public class BaseReturn implements Serializable {
    private String code;
    private String message;
    private int statusCode;
}
