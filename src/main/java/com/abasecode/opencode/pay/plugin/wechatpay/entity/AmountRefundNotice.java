package com.abasecode.opencode.pay.plugin.wechatpay.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AmountRefundNotice implements Serializable {
    /**
     * 订单金额
     * 必
     */
    private int total;
    /**
     * 退款金额
     * 必
     */
    private int refund;
    /**
     * 用户支付金额
     * 必
     */
    @JsonProperty("payer_total")
    private int payerTotal;
    /**
     * 用户退款金额
     * 必
     */
    @JsonProperty("payer_refund")
    private int payerRefund;
}
