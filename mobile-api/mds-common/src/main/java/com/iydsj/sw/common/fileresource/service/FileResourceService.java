package com.iydsj.sw.common.fileresource.service;

import com.iydsj.sw.common.fileresource.enums.BizType;
import com.iydsj.sw.common.fileresource.enums.FileType;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/**
 * @author yanyan.wang
 * @date 2015-12-15 00:54
 */
public interface FileResourceService {

    /**
     * 上传。
     * <p><pre>
     * stream需关闭。
     * </pre>
     * @param bizType 文件业务类型
     * @param originalName 文件原始名称
     * @param stream 内容输入流
     * @return 附件的Id
     */
    int upload(BizType bizType, FileType fileType, String originalName, InputStream stream);

    /**
     * 上传。
     * <p><pre>
     * stream需关闭。
     * </pre>
     * @param fileType 文件类型
     * @param originalName 文件名称
     * @param buffer 内容输入流
     * @return 附件id
     */
    int upload(BizType bizType, FileType fileType, String originalName, ByteBuffer buffer);


    /**
     * 获取文件地址
     * <p>
     *     全路径如http://baidu.com/img.png
     * </p>
     * @param fileId 文件id
     * @return 文件地址
     */
    String getUrl(int fileId);

    /**
     * 获取文件地址
     * <p>
     *     仅适用于图片
     *     fileStyle可以在云厂商设置需要的缩略图尺寸，一般是宽度限定 高度自定义
     *     自定义的设置  由FileResource设定，可以在字典表中设定
     *     若fileStyle 不支持 则抛出UnSupportException;
     * </p>
     * @param fileId 文件id
     * @param fileStyle 文件样式
     * @return 文件地址
     */
    String getUrl(int fileId, String fileStyle);

    /**
     * 批量获取文件地址
     * <p>
     *     key为文件id,
     *     value为文件地址
     * </p>
     * @param fileIds 文件id
     * @return 文件地址
     */
    Map<Integer,String> getUrls(List<Integer> fileIds);

    /**
     * 批量获取文件地址
     * <p>
     *     key为文件id
     *     value为文件地址
     *     仅适用于图片
     *     fileStyle可以在云厂商设置需要的缩略图尺寸，一般是宽度限定 高度自定义
     *     自定义的设置  由FileResource设定，可以在字典表中设定
     *     若fileStyle 不支持 则抛出UnSupportException;
     * </p>
     * @param fileIds 文件id
     * @param fileStyle fileStyle
     * @return 文件地址
     */
    Map<Integer,String> getUrls(List<Integer> fileIds, String fileStyle);

    /**
     * 下载。
     * <p><pre>
     * outStream不会close。
     * </pre>
     * @param id 附件Id
     * @param outStream 输出流
     */
    void download(int id,OutputStream outStream);

    /**
     *
     * @param id
     * @param printWriter
     */
    void download(int id, PrintWriter printWriter);

}
