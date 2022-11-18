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
public class FormRefund implements Serializable {
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 退款原因
     */
    private String reason;
}
