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
public class AmountRefund implements Serializable {
    /**
     * 退款金额
     * int
     * 必
     */
    private int refund;
    /**
     * 退款币种
     * string[1,16]
     * 是：CNY
     */
    private String currency;

    /**
     * 原订单金额
     * long
     * 必
     */
    private int total;

    // todo 要增加 from

}
