package com.iydsj.sw.common.area.service;

import com.iydsj.sw.common.area.entity.Area;
import com.iydsj.sw.common.dto.PageParam;
import com.iydsj.sw.common.dto.Pager;

import java.util.List;
import java.util.Map;

/**
 * @author yanyan.wang
 * @date 2015-12-15 01:01
 */
public interface AreaService {

    /**
     * 获取某个id的区域
     *
     * @param id the id
     * @return 区域
     */
    Area get(int id);

    /**
     * 获取某些区域的信息
     *
     * @param ids the ids
     * @return 区域信息
     */
    Map<Integer, Area> getAreasMap(List<Integer> ids);

    /**
     * 获取某些区域信息
     *
     * @param ids the ids
     * @return 区域信息
     */
    List<Area> getAreas(List<Integer> ids);

    /**
     * 获取根节点的区域(省)
     *
     * @return 根节点区域
     */
    List<Area> getRootAreas();

    /**
     * 获取某个父节点下的子节点信息
     *
     * @param parentId 父节点id
     * @return 区域信息
     */
    List<Area> getAreasByParentId(int parentId);
}
