package com.iydsj.sw.common.area.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author yanyan.wang
 * @date 2015-12-15 00:41
 */
@Data
public class Area {

    private int id;

    private String name;

    private int parentId;

    private String shortName;

    private int levelType;

    private double lng;

    private double lat;

    private String pinYin;

    private boolean isDeleted;

    private Date updateTime;

    private Date createTime;
}
