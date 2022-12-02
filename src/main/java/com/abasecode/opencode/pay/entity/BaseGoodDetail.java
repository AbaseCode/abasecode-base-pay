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
public class BaseGoodDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 商品ID
     */
    private String goodId;
    /**
     * 商品名称
     */
    private String goodName;
    /**
     * 商品数量
     */
    private Integer quantity;
    /**
     * 商品价格(分)
     */
    private int price;
    /**
     * 商品类目
     */
    private String category;
    /**
     * 商品类目树
     */
    private String contentTree;
    /**
     * 商品的展示地址
     */
    private String showUrl;
}
