package com.abasecode.opencode.pay.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */
public class BaseUtils {

    /**
     * 获得可访问网址
     *
     * @param domain 域名部分，形状如：https://www.abc.com
     * @param url    网址部分，可以是 /abc.html或者 https://www.abc.com/abc.html
     * @return 可访问网址
     */
    public static String getURI(String domain, String url) {
        final String HTTP = "http://";
        final String HTTPS = "https://";
        final String SLASH = "/";
        if (url.contains(HTTP) || url.contains(HTTPS)) {
            return url;
        }
        if (url.indexOf(SLASH) == 0) {
            return domain + url;
        }
        return domain + SLASH + url;
    }


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

    /**
     * 分转元字符串
     *
     * @param m 分
     * @return 元
     */
    public static String getYuanFromFen(Long m) {
        return BigDecimal.valueOf(m / 100.00).toString();
    }

    /**
     * 分转元字符串(int)
     *
     * @param m
     * @return
     */
    public static String getYuanFromFen(int m) {
        return BigDecimal.valueOf(m / 100.00).toString();
    }

    /**
     * 元字符串转分(long)
     *
     * @param m 元
     * @return 分
     */
    public static Long getFenFromYuanLong(String m) {
        BigDecimal b = new BigDecimal(m).multiply(BigDecimal.TEN).multiply(BigDecimal.TEN);
        return Long.parseLong(b.toString());
    }

    /**
     * 元字符串转分(int)
     *
     * @param m
     * @return
     */
    public static int getFenFromYuan(String m) {
        if (StringUtils.isNotBlank(getValue(m))) {
            return new BigDecimal(m).multiply(BigDecimal.valueOf(100)).intValue();
        }
        return 0;
    }

    /**
     * 格式化日期
     *
     * @param dateString RFC3339
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTimeStringFromRFC3339(String dateString) {
        if (StringUtils.isBlank(dateString)) {
            return "";
        }
        LocalDateTime date = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * @param d
     * @return
     */
    public static String getDateTimeStringFromRFC3339(Date d) {
        LocalDateTime date = LocalDateTime.parse(d.toString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * @param dateString
     * @return
     */
    public static LocalDateTime getDateTimeFromRFC3339(String dateString) {
        if (StringUtils.isBlank(dateString)) {
            return null;
        }
        LocalDateTime date = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return date;
    }

    /**
     * convert time to RFC3339
     *
     * @param date date
     * @return 2021-01-29T17:05:58+08:00
     */
    public static String getRFC3339(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String formatDate = simpleDateFormat.format(date);
        return formatDate;
    }

    /**
     * convert time to RFC3339 format
     *
     * @param datetime format：2021-01-29 17:05:58
     * @return datetime string
     */
    public static String getRFC3339(String datetime) {
        java.time.format.DateTimeFormatter formatter1 = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZoneId shanghaiZoneId = ZoneId.of("Asia/Shanghai");
        LocalDateTime localDateTime = LocalDateTime.parse(datetime, formatter1);
        ZonedDateTime zonedDateTime = localDateTime.atZone(shanghaiZoneId);
        java.time.format.DateTimeFormatter formatter2 = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        String formatDate = zonedDateTime.format(formatter2);
        return formatDate;
    }


    /**
     * 获取字符串值
     *
     * @param o
     * @return
     */
    public static String getValue(Object o) {
        if (null != o) {
            return String.valueOf(o);
        }
        return "";
    }
}