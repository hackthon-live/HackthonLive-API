package com.iydsj.sw.common.sporttype.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author yanyan.wang
 * @date 2015-12-15 09:33
 */
@Data
public class SportType {

    private int id;

    private String name;

    private int Sort;

    private int parentId;

    private boolean isDeleted;

    private Date createTime;

    private Date updateTime;
}
