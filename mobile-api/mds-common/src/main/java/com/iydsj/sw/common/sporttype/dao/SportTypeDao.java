package com.iydsj.sw.common.sporttype.dao;

import com.iydsj.sw.common.sporttype.entity.SportType;

import java.util.List;

/**
 * @author yanyan.wang
 * @date 2015-12-15 14:20
 */
public interface SportTypeDao {


    SportType find(int id);

    List<SportType> findSportTypes(List<Integer> ids);

    List<SportType> findSportTypes(int parentId);

    List<SportType> findSportTypes();

    List<SportType> findSportItems();
}
