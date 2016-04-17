package com.iydsj.sw.common.area.dao;

import com.iydsj.sw.common.area.entity.Area;

import java.util.List;

/**
 * @author yanyan.wang
 * @date 2015-12-15 09:36
 */
public interface AreaDao {

    Area find(int id);

    List<Area> findAreas(List<Integer> ids);

    List<Area> findAreas(int levelType);

    List<Area> findAreasByParentId(int parentId);
}
