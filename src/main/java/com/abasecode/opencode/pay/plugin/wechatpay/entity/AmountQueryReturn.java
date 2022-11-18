package com.abasecode.opencode.pay.plugin.wechatpay.entity;

import com.alibaba.fastjson2.annotation.JSONField;
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
public class AmountQueryReturn implements Serializable {
    /**
     * 订单总金额，单位为分。
     * int
     * 否
     */
    private int total;
    /**
     * CNY：人民币，境内商户号仅支持人民币。
     * string[1,16]
     * 否
     */
    private String currency;

    /**
     * 用户支付金额
     * int
     * 否
     */
    @JSONField(name = "payer_total")
    private int payerTotal;

    /**
     * 用户支付币种
     * string[1,16]
     * 否
     */
    @JSONField(name = "payer_currency")
    private String payerCurrency;

}
