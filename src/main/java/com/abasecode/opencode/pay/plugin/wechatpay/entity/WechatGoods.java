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
public class WechatGoods implements Serializable {
    private static final long serialVersionUID = 593978662333134485L;
    /**
     * 由半角的大小写字母、数字、中划线、下划线中的一种或几种组成
     */
    @JSONField(name = "merchant_goods_id")
    private String merchantGoodsId;
    /**
     * 微信支付定义的统一商品编号（没有可不传）
     */
    @JSONField(name = "wechatpay_goods_id")
    private String wechatpayGoodsId;
    /**
     * 商品的实际名称
     */
    @JSONField(name = "goods_name")
    private String goodsName;
    /**
     * 商品单价金额，单位为分
     */
    @JSONField(name = "unit_price")
    private int unitPrice;
    /**
     * 商品退款金额，单位为分
     */
    @JSONField(name = "refund_amount")
    private int refundAmount;
    /**
     * 单品的退款数量
     */
    @JSONField(name = "refund_quantity")
    private int refundQuantity;

}
