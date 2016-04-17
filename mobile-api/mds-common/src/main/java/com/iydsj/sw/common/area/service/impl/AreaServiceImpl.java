package com.iydsj.sw.common.area.service.impl;

import com.iydsj.sw.common.area.dao.AreaDao;
import com.iydsj.sw.common.area.entity.Area;
import com.iydsj.sw.common.area.service.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanyan.wang
 * @date 2015-12-15 11:17
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Resource
    private AreaDao areaDao;

    @Override
    public Area get(int id) {
        return areaDao.find(id);
    }

    @Override
    public Map<Integer, Area> getAreasMap(List<Integer> ids) {
        Map<Integer, Area> areaMap = new HashMap<Integer, Area>();
        List<Area> areas = getAreas(ids);
        for (Area area : areas) {
            areaMap.put(area.getId(), area);
        }
        return areaMap;
    }

    @Override
    public List<Area> getAreas(List<Integer> ids) {
        return areaDao.findAreas(ids);
    }

    @Override
    public List<Area> getRootAreas() {
        return areaDao.findAreas(1);
    }

    @Override
    public List<Area> getAreasByParentId(int parentId) {
        return areaDao.findAreasByParentId(parentId);
    }
}
