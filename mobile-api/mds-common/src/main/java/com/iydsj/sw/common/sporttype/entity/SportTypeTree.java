package com.iydsj.sw.common.sporttype.entity;

import lombok.Data;

import java.util.List;

/**
 * @author yanyan.wang
 * @date 2015-12-15 14:50
 */
@Data
public class SportTypeTree {

    private int id;

    private String name;

    private List<SportTypeTree> children;

}
