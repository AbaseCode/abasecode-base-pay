package com.abasecode.opencode.pay.plugin.alipay.form;

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
public class AlipayQueryForm implements Serializable {
    /**
     * 订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no
     */
    private String tradeNo;
    /**
     * 订单号
     */
    private String outTradeNo;
    /**
     * 查询选项，商户传入该参数可定制本接口同步响应额外返回的信息字段，数组格式。支持枚举如下：
     * trade_settle_info：返回的交易结算信息，包含分账、补差等信息；
     * fund_bill_list：交易支付使用的资金渠道；
     * voucher_detail_list：交易支付时使用的所有优惠券信息；
     * discount_goods_detail：交易支付所使用的单品券优惠的商品优惠信息；
     * mdiscount_amount：商家优惠金额；
     */
    private List<String> queryOptions;
}