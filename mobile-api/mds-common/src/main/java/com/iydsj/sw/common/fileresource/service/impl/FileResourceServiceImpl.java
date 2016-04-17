package com.iydsj.sw.common.fileresource.service.impl;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.iydsj.sw.common.utils.KeyHolder;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.iydsj.sw.common.fileresource.dao.FileResourceDao;
import com.iydsj.sw.common.fileresource.entity.FileResource;
import com.iydsj.sw.common.fileresource.enums.BizType;
import com.iydsj.sw.common.fileresource.enums.FileType;
import com.iydsj.sw.common.fileresource.service.FileResourceService;
import com.iydsj.sw.common.utils.exceptions.SysException;
import com.iydsj.sw.common.utils.exceptions.UnSupportException;

/**
 * @author yanyan.wang
 * @date 2015-12-24 16:14
 */
@Service("fileResourceService")
public class FileResourceServiceImpl implements FileResourceService {


    /**
     * 附件存储根路径。
     */
    @Autowired(required = false)
    @Qualifier("attachFileRootPath")
    private String attachFileRootPath;

    @Autowired(required = false)
    @Qualifier("basePath")
    private String basePath;

    /**
     * 字节大小
     */
    public static final int BYTESIZE = 1024;

    /**
     * 斜杠标识
     */
    public static final String SLASH = "/";

    @Resource
    private FileResourceDao fileResourceDao;

    @Override
    public int upload(BizType bizType, FileType fileType, String originalName, InputStream stream) {
        FileResource file = new FileResource();
        Calendar cal = Calendar.getInstance();
        Object[] paths = new Object[3];
        paths[0] = cal.get(Calendar.YEAR) + SLASH;
        paths[1] = (cal.get(Calendar.MONTH) + 1) + SLASH;
        paths[2] = cal.get(Calendar.DAY_OF_MONTH) + SLASH;
        String path = String.format("%s%s%s", paths);
        String storeName = KeyHolder.getKey() + "." + originalName.substring(originalName.lastIndexOf(".") + 1);
        int fileSize = 0;

        FileOutputStream os = null;
        BufferedOutputStream bos = null;
        try {
            File filePath = new File(attachFileRootPath + path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            os = new FileOutputStream(attachFileRootPath + path
                    + storeName);
            bos = new BufferedOutputStream(os);
            byte[] buf = new byte[BYTESIZE];
            fileSize = stream.available();
            int size = 0;
            while ((size = stream.read(buf)) != -1) {
                bos.write(buf, 0, size);
            }
        } catch (IOException e) {
            throw new SysException("写文件时报错.", e);
        } finally {
            IOUtils.closeQuietly(bos);
            //关闭流
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(stream);
        }

        file.setPath(path + storeName);
        file.setOriginName(originalName);
        file.setIsDeleted(false);
        file.setFileSize(fileSize);
        file.setBizType(bizType.getValue());
        file.setResType(fileType.getValue());
        file.setBranch("");
        return fileResourceDao.insert(file);
    }

    @Override
    public int upload(BizType bizType, FileType fileType, String originalName, ByteBuffer buffer) {
        InputStream inputStream = new ByteArrayInputStream(buffer.array());
        return upload(bizType, fileType, originalName, inputStream);
    }

    @Override
    public String getUrl(int fileId) {
        return basePath + "/" + fileId;
    }

    @Override
    public String getUrl(int fileId, String fileStyle) {
        throw new UnSupportException("不支持按缩略图尺寸搜索");
    }

    @Override
    public Map<Integer, String> getUrls(List<Integer> fileIds) {
        Map<Integer, String> fileMap = new HashMap<Integer, String>();
        List<FileResource> files = fileResourceDao.findFileResources(fileIds);
        for (FileResource file : files) {
            fileMap.put(file.getId(), basePath + "/" + file.getId());
        }
        return fileMap;
    }

    @Override
    public Map<Integer, String> getUrls(List<Integer> fileIds, String fileStyle) {
        return null;
    }

    @Override
    public void download(int id, OutputStream outStream) {
        FileResource fileResource = fileResourceDao.find(id);
        read(fileResource, outStream);
    }

    @Override
    public void download(int id, PrintWriter printWriter) {
        FileResource fileResource = fileResourceDao.find(id);
        FileInputStream is = null;
        try {
            is = new FileInputStream(attachFileRootPath + fileResource.getPath());
            IOUtils.copy(is, printWriter);
            printWriter.flush();
        } catch (Throwable e) {
            throw new SysException("读文件时报错.", e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }


    /**
     * 读取文件资源信息
     *
     * @param file
     * @param outStream
     */
    private void read(FileResource file, OutputStream outStream) {

            BufferedInputStream bis = null;
            FileInputStream is = null;
            try {
            is = new FileInputStream(attachFileRootPath + file.getPath());
            bis = new BufferedInputStream(is);
            byte[] buf = new byte[BYTESIZE];
            int size = 0;
            while ((size = bis.read(buf)) != -1) {
                outStream.write(buf, 0, size);
            }
        } catch (Throwable e) {
            throw new SysException("读文件时报错.", e);
        } finally {
            //关闭流
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(is);
        }
    }

}
