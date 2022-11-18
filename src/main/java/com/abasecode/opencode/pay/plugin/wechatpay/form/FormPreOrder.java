package com.abasecode.opencode.pay.plugin.wechatpay.form;

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
public class FormPreOrder implements Serializable {
    private int goodsId;
    private int num;
    private String openid;
}

