package com.abasecode.opencode.pay.util;

import org.springframework.core.io.ClassPathResource;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */
public class BaseUtils {
    /**
     * 从resource读取文件字节
     *
     * @param path
     * @return 字节
     * @throws IOException
     */
    public static byte[] getBytes(String path) throws IOException {
        ClassPathResource classResource = new ClassPathResource(path);
        InputStream stream = classResource.getInputStream();
        byte[] bytes = IOUtils.toByteArray(stream);
        stream.read(bytes);
        stream.close();
        return bytes;
    }

    /**
     * 从resource读取文件流
     *
     * @param path
     * @return 流
     * @throws IOException
     */
    public static InputStream getStream(String path) throws IOException {
        return new ByteArrayInputStream(getBytes(path));
    }

    /**
     * 获取随机字符串
     *
     * @return 随机字符串
     */
    public static String getNonceStr() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取当前的Timestamp
     *
     * @return 时间戳
     */
    public static String getCurrentTimeStamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    /**
     * 获取当前的时间
     *
     * @return 时间
     */
    public static long getCurrentTimestampMs() {
        return System.currentTimeMillis();
    }

    /**
     * 生成订单号
     *
     * @return 订单号
     */
    public static String getOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        return sdf.format(new Date()) + makeUUID(16);
    }

    /**
     * 生成随机数
     *
     * @return 随机数
     */
    private static String makeUUID(int len) {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, len);
    }

    /**
     * 获取当前工程url
     *
     * @param request
     * @return URL
     */
    public static String getCurrentUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}