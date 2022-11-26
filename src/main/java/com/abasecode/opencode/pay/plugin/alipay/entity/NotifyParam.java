package com.abasecode.opencode.pay.plugin.alipay.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 */
@Data
@Accessors(chain = true)
public class NotifyParam implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 通知的发送时间。格式为 yyyy-MM-dd HH:mm:ss。
     */
    @JSONField(name = "notify_time")
    private String notifyTime;
    /**
     * 通知的类型。
     */
    @JSONField(name = "notify_type")
    private String notifyType;
    /**
     *
     * 通知校验 ID。
     */
    @JSONField(name = "notify_id")
    private String notifyId;
    /**
     * 支付宝分配给开发者的应用 ID。
     */
    @JSONField(name = "app_id")
    private String appId;
    /**
     * 编码格式，如 utf-8、gbk、gb2312 等。
     */
    private String charset;
    /**
     * 调用的接口版本，固定为：1.0。
     */
    private String version;
    /**
     * 商家生成签名字符串所使用的签名算法类型，目前支持 RSA2 和 RSA，推荐使用 RSA2。
     */
    @JSONField(name = "sign_type")
    private String signType;
    /**
     * 签名。详情可查看 异步返回结果的验签。
     */
    @JSONField(name = "")
    private String sign;
    /**
     * 支付宝交易凭证号。
     */
    @JSONField(name = "trade_no")
    private String tradeNo;
    /**
     * 原支付请求的商户订单号。
     */
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    /**
     * 商户业务 ID，主要是退款通知中返回退款申请的流水号。
     */
    @JSONField(name = "out_biz_no")
    private String outBizNo;
    /**
     * 买家支付宝账号对应的支付宝唯一用户号。以 2088 开头的纯 16 位数字。
     */
    @JSONField(name = "buyer_id")
    private String buyerId;
    /**
     * 买家支付宝账号。
     */
    @JSONField(name = "buyer_logon_id")
    private String buyerLogonId;
    /**
     * 卖家支付宝用户号。
     */
    @JSONField(name = "seller_id")
    private String sellerId;
    /**
     * 卖家支付宝账号。
     */
    @JSONField(name = "seller_email")
    private String sellerEmail;
    /**
     * 交易目前所处的状态。详情可查看 交易状态说明。
     */
    @JSONField(name = "trade_status")
    private String tradeStatus;
    /**
     * 本次交易支付的订单金额，单位为人民币（元）。
     */
    @JSONField(name = "total_amount")
    private String totalAmount;
    /**
     * 商家在收益中实际收到的款项，单位人民币（元）。
     */
    @JSONField(name = "receipt_amount")
    private String receiptAmount;
    /**
     *
     * 用户在交易中支付的可开发票的金额。
     */
    @JSONField(name = "invoice_amount")
    private String invoiceAmount;
    /**
     * 用户在交易中支付的金额。
     */
    @JSONField(name = "buyer_pay_amount")
    private String buyerPayAmount;
    /**
     * 使用集分宝支付的金额。
     */
    @JSONField(name = "point_amount")
    private String pointAmount;
    /**
     *
     * 退款通知中，返回总退款金额，单位为人民币（元），支持两位小数。
     */
    @JSONField(name = "refund_fee")
    private String refundFee;
    /**
     * 商品的标题/交易标题/订单标题/订单关键字等，是请求时对应的参数，原样通知回来。
     */
    private String subject;
    /**
     * 订单的备注、描述、明细等。对应请求时的 body 参数，原样通知回来。
     */
    private String body;
    /**
     * 该笔交易创建的时间。格式为 yyyy-MM-dd HH:mm:ss。
     */
    @JSONField(name = "gmt_create")
    private String gmtCreate;
    /**
     * 该笔交易 的买家付款时间。格式为 yyyy-MM-dd HH:mm:ss。
     */
    @JSONField(name = "gmt_payment")
    private String gmtPayment;
    /**
     * 该笔交易的退款时间。格式 yyyy-MM-dd HH:mm:ss.S。
     */
    @JSONField(name = "gmt_refund")
    private String gmtRefund;
    /**
     * 该笔交易结束时间。格式为 yyyy-MM-dd HH:mm:ss。
     */
    @JSONField(name = "gmt_close")
    private String gmtClose;
    /**
     * 支付成功的各个渠道金额信息。详情可查看 资金明细信息说明。
     */
    @JSONField(name = "fund_bill_list")
    private String fundBillList;
    /**
     * 公共回传参数，如果请求时传递了该参数，则返回给商家时会在异步通知时将该参数原样返回。本参数必须进行 UrlEncode 之后才可以发送给支付宝。
     */
    @JSONField(name = "passback_params")
    private String passbackParams;
    /**
     * 本交易支付时所有优惠券信息，详情可查看 优惠券信息说明。
     */
    @JSONField(name = "voucher_detail_list")
    private String voucherDetailList;
}
