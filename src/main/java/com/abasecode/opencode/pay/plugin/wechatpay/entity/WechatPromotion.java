package com.abasecode.opencode.pay.plugin.wechatpay.entity;

import com.alibaba.fastjson2.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 */
public class WechatPromotion implements Serializable {
    private static final long serialVersionUID = -2070743460104004038L;
    /**
     * 券ID
     * 券或者立减优惠id
     */
    @JSONField(name ="promotion_id")
    private String promotionId;
    /**
     * 优惠范围
     * 枚举值：
     * GLOBAL：全场代金券
     * SINGLE：单品优惠
     */
    private String scope;
    /**
     * 优惠类型
     * 枚举值：
     * COUPON：代金券，需要走结算资金的充值型代金券
     * DISCOUNT：优惠券，不走结算资金的免充值型优惠券
     */
    private String type;
    /**
     * 优惠券面额
     * 用户享受优惠的金额（优惠券面额=微信出资金额+商家出资金额+其他出资方金额 ），单位为分
     */
    private int amount;
    /**
     * 优惠退款金额
     * 优惠退款金额 小于等于 退款金额，退款金额-代金券或立减优惠退款金额为用户支付的现金，说明详见代金券或立减优惠，单位为分
     */
    @JSONField(name ="refund_amount")
    private int refundAmount;

    /**
     * 商品列表
     * 优惠商品发生退款时返回商品信息
     */
    @JSONField(name ="goods_detail")
    private List<WechatGoods> goodsDetail;

}
