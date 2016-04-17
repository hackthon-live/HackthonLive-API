package com.iydsj.sw.common.test;

import com.iydsj.sw.common.area.entity.Area;
import com.iydsj.sw.common.area.service.AreaService;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yanyan.wang
 * @date 2015-12-21 23:31
 */
@ContextConfiguration(locations = "classpath:config/spring/application-*.xml")
public class AreaServiceTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private AreaService areaService;

    @Test
    public void testGet(){
       Area area=areaService.get(110000);
    }

    @Test
    public void testGetAreasMap(){
        List<Integer> ids=new ArrayList<Integer>();
        ids.add(110000);
        ids.add(110105);
        ids.add(110106);
        Map<Integer,Area> areaMap=areaService.getAreasMap(ids);
    }

//    @Resource
//    private ArrayList dbResources;
//
//    private
//
//    @Test
//    public void test(){
//        int index=0;
//    }
}
