package com.abasecode.opencode.pay.plugin.alipay.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 */
@Data
@Accessors(chain = true)
public class RefundChargeInfo implements Serializable {
    private static final long serialVersionUID = -7839760887731476148L;
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
    /**
     * 收单手续费trade，花呗分期手续hbfq，其他手续费charge
     */
    @JSONField(name = "charge_type")
    private String chargeType;
    /**
     * 组合支付退费明细
     */
    @JSONField(name = "refund_sub_fee_detail_list")
    private List<RefundSubFee> refundSubFeeDetailList;
}
