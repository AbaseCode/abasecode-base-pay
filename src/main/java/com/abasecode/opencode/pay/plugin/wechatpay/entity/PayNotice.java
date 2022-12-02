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
public class PayNotice implements Serializable {
    /**
     * 通知ID
     * string[1,36]
     * 必
     */
    @JsonProperty("id")
    private String id;
    /**
     * 通知创建时间
     * string[1,32]
     * 必
     */
    @JsonProperty("create_time")
    private String createTime;
    /**
     * 通知类型
     * string[1,32]
     * 必
     */
    @JsonProperty("event_type")
    private String eventType;
    /**
     * 通知数据类型
     * string[1,32]
     * 必
     */
    @JsonProperty("resource_type")
    private String resourceType;
    /**
     * 通知数据类型
     * string[1,32]
     * 必
     */
    @JsonProperty("resource")
    private PayNoticeResource resource;
    /**
     * 回调摘要
     * string[1,64]
     * 必
     */
    @JsonProperty("summary")
    private String summary;

    /**
     * 解密数据
     */
    @JsonProperty("origin")
    private PayNoticeResourceOrigin origin;
}

