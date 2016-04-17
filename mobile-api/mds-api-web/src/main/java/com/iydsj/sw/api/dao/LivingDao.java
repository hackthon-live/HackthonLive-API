package com.iydsj.sw.api.dao;

import com.iydsj.sw.api.DTO.LivingDTO;

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
