package com.abasecode.opencode.pay.entity;


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
public class PayRefundResultAlipay implements Serializable {
    private static final long serialVersionUID = -2592163947404044717L;
    /**
     * 交易在支付时候的门店名称
     */
    private String storeName;
    /**
     * 买家在支付宝的用户id
     */
    private String buyerUserId;
    /**
     * 本次商户实际退回金额。
     * 说明：如需获取该值，需在入参query_options中传入 refund_detail_item_list。
     */
    private String sendBackFee;
    /**
     * 本次请求退惠营宝金额（小数格式）
     */
    private String refundHybAmount;

    /**
     * 用户的登录id
     */
    private String buyerLogonId;
    /**
     * 本次退款是否发生了资金变化
     */
    private String fundChange;

//    /**
//     * 退款使用的资金渠道。
//     * 只有在签约中指定需要返回资金明细，或者入参的query_options中指定时才返回该字段信息。
//     */
//    @JSONField(name = "refund_detail_item_list")
//    private List<TradeFundBill> refundDetailItemList;
//    /**
//     * 退费信息
//     */
//    @JSONField(name = "refund_charge_info_list")
//    private List<RefundChargeInfo> refundChargeInfoList;

}
