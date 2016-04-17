package com.iydsj.sw.common.fileresource.enums;


/**
 * 文件类型
 */
public enum FileType {

    /**
     * 图片
     */
    Image(10),

    /**
     * 压缩包
     */
    ZipFile(20),

    /**
     * 文件路径
     */
    Video(30),

    /**
     * 其他
     */
    OTHER(40);

    /**
     * 文件类型
     */
    private int value;

    FileType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
