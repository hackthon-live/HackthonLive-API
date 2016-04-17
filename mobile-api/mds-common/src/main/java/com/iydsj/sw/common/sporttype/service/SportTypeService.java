package com.iydsj.sw.common.sporttype.service;

import com.iydsj.sw.common.sporttype.entity.SportType;
import com.iydsj.sw.common.sporttype.entity.SportTypeTree;

import java.util.List;
import java.util.Map;

/**
 * @author yanyan.wang
 * @date 2015-12-15 14:12
 */
public interface SportTypeService {

    /**
     * 运动类型
     *
     * @param id the id
     * @return
     */
    SportType get(int id);

    /**
     * 运动类型
     *
     * @return 获取根节点运动类型
     */
    List<SportType> getRootSportType();

    /**
     * 获取某些运动类型
     *
     * @param ids 运动类型ids
     * @return 运动类型
     */
    List<SportType> getSportTypes(List<Integer> ids);

    /**
     * 获取某些运动类型
     *
     * @param ids 运动ids
     * @return 运动类型Map
     */
    Map<Integer, SportType> getSportTypesMap(List<Integer> ids);


    /**
     * 获取某个运动类型的子运动类型
     *
     * @param parentId 父节点id
     * @return 运动类型
     */
    List<SportType> getSportTypes(int parentId);

    /**
     * 获取全部运动类型
     *
     * @return 运动类型
     */
    List<SportTypeTree> getSportTypeTrees();

    /**
     * 获取所有的体育项目
     *
     * @return 体育项目
     */
    List<SportType> getSportItems();
}
