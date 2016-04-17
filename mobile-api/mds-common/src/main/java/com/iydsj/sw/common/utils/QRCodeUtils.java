package com.iydsj.sw.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.iydsj.sw.common.utils.exceptions.SysException;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyan
 * @date 2014-11-02 17:04
 */
public final class QRCodeUtils {

    /**
     * 生成二维码
     * @param content 内容
     * @param width 宽度
     * @param height 高度
     * @param format 图片类型如png,jpg等
     * @param outputStream 输出流
     */
    public static void generate(String content,Integer width,Integer height,String format,OutputStream outputStream){
        Assert.notNull(content, "content is required!");
        Assert.isTrue(width!=null&&width>0," width is required and must more than 0");
        Assert.isTrue(height!=null&&height>0," height is required and must more than 0");
        Assert.notNull(outputStream," outputStream is required!");
        MultiFormatWriter formatWriter=new MultiFormatWriter();
        Map<EncodeHintType,Object> hints=new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 1);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        try {
            BitMatrix bitMatrix=formatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream);
        } catch (WriterException e) {
            throw new SysException("QRCode generate failed!", e);
        } catch (IOException e) {
            throw new SysException("QRCode generate failed!", e);
        }
    }
}
