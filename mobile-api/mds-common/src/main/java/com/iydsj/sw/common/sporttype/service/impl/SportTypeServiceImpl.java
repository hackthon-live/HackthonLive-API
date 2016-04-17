package com.iydsj.sw.common.sporttype.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.iydsj.sw.common.sporttype.dao.SportTypeDao;
import com.iydsj.sw.common.sporttype.entity.SportType;
import com.iydsj.sw.common.sporttype.entity.SportTypeTree;
import com.iydsj.sw.common.sporttype.service.SportTypeService;
import net.sf.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author yanyan.wang
 * @date 2015-12-15 14:18
 */
@Service
public class SportTypeServiceImpl implements SportTypeService {

    @Resource
    private SportTypeDao sportTypeDao;

    private BeanCopier sportTypeTreeCopier = BeanCopier.create(SportType.class, SportTypeTree.class, false);

    @Override
    public SportType get(int id) {
        return sportTypeDao.find(id);
    }

    @Override
    public List<SportType> getRootSportType() {
        return sportTypeDao.findSportTypes(0);
    }

    @Override
    public List<SportType> getSportTypes(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return sportTypeDao.findSportTypes(ids);
    }

    @Override
    public Map<Integer, SportType> getSportTypesMap(List<Integer> ids) {
        List<SportType> sportTypes = getSportTypes(ids);
        Map<Integer, SportType> sportTypeMap = Maps.uniqueIndex(sportTypes, new Function<SportType, Integer>() {
            @Override
            public Integer apply(SportType sportType) {
                return sportType.getId();
            }
        });
        return sportTypeMap;
    }

    @Override
    public List<SportType> getSportTypes(int parentId) {
        return sportTypeDao.findSportTypes(parentId);
    }

    @Override
    public List<SportTypeTree> getSportTypeTrees() {
        List<SportType> sportTypes = sportTypeDao.findSportTypes();
        Map<Integer, SportTypeTree> sportTypeTreeMap = new HashMap<Integer, SportTypeTree>();
        for (SportType sportType : sportTypes) {
            if (sportType.getParentId() == 0) {
                SportTypeTree sportTypeTree = getSportTypeTree(sportType);
                sportTypeTreeMap.put(sportType.getId(), sportTypeTree);
            }
        }
        for (SportType sportType : sportTypes) {
            if (sportType.getParentId() != 0) {
                SportTypeTree sportTypeTree = sportTypeTreeMap.get(sportType.getParentId());
                List<SportTypeTree> sportTypeTrees;
                if (sportTypeTree.getChildren() == null) {
                    sportTypeTrees = new ArrayList<SportTypeTree>();
                } else {
                    sportTypeTrees = sportTypeTree.getChildren();
                }
                SportTypeTree sport = getSportTypeTree(sportType);
                sportTypeTrees.add(sport);
                sportTypeTree.setChildren(sportTypeTrees);
                sportTypeTreeMap.put(sportType.getId(), sportTypeTree);
            }
        }
        List<SportTypeTree> sportTypeTrees = new ArrayList<SportTypeTree>();
        Iterator iterator = sportTypeTreeMap.keySet().iterator();
        while (iterator.hasNext()) {
            SportTypeTree sportTypeTree = sportTypeTreeMap.get(iterator.next());
            sportTypeTrees.add(sportTypeTree);
        }
        return sportTypeTrees;
    }

    private SportTypeTree getSportTypeTree(SportType sportType) {
        SportTypeTree sportTypeTree = new SportTypeTree();
        sportTypeTreeCopier.copy(sportType, sportTypeTree, null);
        return sportTypeTree;
    }

    @Override
    public List<SportType> getSportItems() {
        return sportTypeDao.findSportItems();
    }
}
