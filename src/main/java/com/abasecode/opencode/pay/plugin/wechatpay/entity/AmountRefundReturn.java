package com.abasecode.opencode.pay.plugin.wechatpay.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */
@Data
@Accessors(chain = true)
public class AmountRefundReturn {
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
    @JSONField(name = "payer_total")
    private int payerTotal;
    /**
     * 用户退款金额
     * 必
     */
    @JSONField(name = "payer_refund")
    private int payerRefund;
    /**
     * 应结退款金额
     * 必
     */
    @JSONField(name = "settlement_refund")
    private int settlementRefund;
    /**
     * 应结订单金额
     * 必
     */
    @JSONField(name = "settlement_total")
    private int settlementTotal;
    /**
     * 优惠退款金额
     * 必
     */
    @JSONField(name = "discount_refund")
    private int discountRefund;
    /**
     * 退款币种
     * 必
     */
    private String currency;

    /**
     * 退款出资账户及金额
     */
    private List<WechatAccount> from;

}
