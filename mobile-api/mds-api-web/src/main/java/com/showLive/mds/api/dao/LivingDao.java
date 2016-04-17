package com.showLive.mds.api.dao;

import com.showLive.mds.api.DTO.LivingDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by junyu-pc on 2016/4/16.
 */
public interface LivingDao {

    List<LivingDTO> getPlayingList();

    int createChanel(Map map);

    int exitChanel(int id);
}
