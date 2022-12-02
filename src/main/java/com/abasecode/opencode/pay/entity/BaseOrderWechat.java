package com.abasecode.opencode.pay.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 */
@Data
@Accessors(chain = true)
public class BaseOrderWechat implements Serializable {
    private static final long serialVersionUID = -1986039082025305864L;
    /**
     * 微信：单优惠标记
     * 非必须
     */
    private String goodsTag;
    /**
     * 微信：电子发票入口开放标识
     * 非必须
     */
    private boolean invoice = false;
    /**
     * 微信：商品描述
     */
    private String 	description;

}
