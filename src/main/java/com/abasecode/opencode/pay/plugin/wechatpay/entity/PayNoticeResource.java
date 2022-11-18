package com.abasecode.opencode.pay.plugin.wechatpay.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PayNoticeResource implements Serializable {
    /**
     * 加密算法类型
     * string[1,32]
     * 必
     */
    @JsonProperty("algorithm")
    private String algorithm;
    /**
     * 数据密文
     * string[1,1048576]
     * 必
     */
    @JsonProperty("ciphertext")
    private String ciphertext;
    /**
     * 附加数据
     * string[1,16]
     * 否
     */
    @JsonProperty("associated_data")
    private String associatedData;
    /**
     * 原始类型
     * string[1,16]
     * 必
     */
    @JsonProperty("original_type")
    private String originalType;
    /**
     * 随机串
     * string[1,16]
     * 必
     */
    @JsonProperty("nonce")
    private String nonce;
}