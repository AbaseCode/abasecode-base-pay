package com.abasecode.opencode.pay.plugin.alipay.entity;

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
public class RefundSubFee implements Serializable {
    private static final long serialVersionUID = 4019628813337436556L;
    /**
     * 实退费用
     */
    @JSONField(name = "refund_charge_fee")
    private String refundChargeFee;
    /**
     * 签约费率
     */
    @JSONField(name = "switch_fee_rate")
    private String switchFeeRate;
}
