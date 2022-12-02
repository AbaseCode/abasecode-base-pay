package com.abasecode.opencode.pay.plugin.alipay.form;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 */
@Data
@Accessors(chain = true)
public class AlipayRefundQueryForm implements Serializable {
    private static final long serialVersionUID = -6663404660376537193L;
    /**
     * 支付宝交易号。
     * 和商户订单号不能同时为空
     */
    private String tradeNo;
    /**
     * 商户订单号。
     * 订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no
     */
    private String outTradeNo;
    /**
     * 退款请求号。
     * 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的商户订单号。
     */
    @NotEmpty
    private String outRequestNo;
    /**
     * 查询选项，商户通过上送该参数来定制同步需要额外返回的信息字段，数组格式。枚举支持：
     * refund_detail_item_list：本次退款使用的资金渠道；
     * gmt_refund_pay：退款执行成功的时间；
     * deposit_back_info：银行卡冲退信息；
     */
    private List<String> queryOptions;
}
