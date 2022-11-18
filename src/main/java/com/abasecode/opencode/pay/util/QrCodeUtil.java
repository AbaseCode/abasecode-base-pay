package com.abasecode.opencode.pay.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */
public class QrCodeUtil {
    /**
     * 二维码格式
     */
    private static final String UNICODE = "utf-8";
    /**
     * 图片格式
     */
    private static final String FORMAT = "JPG";
    /**
     * 宽度
     */
    private static final int QRCODE_WIDTH = 300;
    /**
     * 高度
     */
    private static final int QRCODE_HEIGHT = 300;
    /**
     * LOGO宽
     */
    private static final int LOGO_WIDTH = 100;
    /**
     * LOGO高
     */
    private static final int LOGO_HEIGHT = 100;

    /**
     *
     * @param content
     * @param logoPath
     * @return BufferedImage
     * @throws Exception
     */
    private static BufferedImage createImage(String content, String logoPath) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, UNICODE);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_WIDTH, QRCODE_HEIGHT,
                hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (logoPath == null || "".equals(logoPath)) {
            return image;
        }
        QrCodeUtil.addLogo(image, logoPath);
        return image;
    }

    /**
     * 添加LOGO
     * @param source 二维码
     * @param logoPath LOGO路径
     * @throws Exception
     */
    private static void addLogo(BufferedImage source, String logoPath) throws Exception {
        File file = new File(logoPath);
        if (!file.exists()) {
            throw new Exception("logo file not found.");
        }
        Image src = ImageIO.read(new File(logoPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (width > LOGO_WIDTH) {
            width = LOGO_WIDTH;
        }
        if (height > LOGO_HEIGHT) {
            height = LOGO_HEIGHT;
        }
        Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        src = image;
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_WIDTH - width) / 2;
        int y = (QRCODE_HEIGHT - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * 保存
     * @param content
     * @param logoPath
     * @param destPath
     * @return fileName
     * @throws Exception
     */
    public static String save(String content, String logoPath, String destPath) throws Exception {
        BufferedImage image = QrCodeUtil.createImage(content, logoPath);
        File file = new File(destPath);
        String path = file.getAbsolutePath();
        File filePath = new File(path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            filePath.mkdirs();
        }
        String fileName = file.getName();
        fileName = fileName.substring(0, fileName.indexOf(".")>0?fileName.indexOf("."):fileName.length())
                + "." + FORMAT.toLowerCase();
        System.out.println("destPath:"+destPath);
        ImageIO.write(image, FORMAT, new File(destPath));
        return fileName;
    }

    /**
     * 生成二维码
     * @param content
     * @param logoPath
     * @param output
     * @throws Exception
     */
    public static void encode(String content, String logoPath, OutputStream output)
            throws Exception {
        BufferedImage image = QrCodeUtil.createImage(content, logoPath);
        ImageIO.write(image, FORMAT, output);
    }

    /**
     * 读取二维码
     * @param path 路径
     * @return 文本
     * @throws Exception
     */
    public static String decode(String path) throws Exception {
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, UNICODE);
        result = new MultiFormatReader().decode(bitmap, hints);
        return result.getText();
    }
}
