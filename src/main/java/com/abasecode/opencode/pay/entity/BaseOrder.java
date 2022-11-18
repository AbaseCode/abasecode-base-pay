package com.abasecode.opencode.pay.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
public class BaseOrder implements Serializable {
    private static final long serialVersionUID = -1L;
    /**
     * 订单号
     */
    private String outTradeNo;
    /**
     * 单价（分）
     */
    private Integer price;
    /**
     * 总价（分）
     */
    private Integer amount;
    /**
     * 商品标题
     */
    private String subject;
    /**
     * 商品详情，可空。支付宝或微信均适用
     */
    private List<BaseGoodDetail> details;
}