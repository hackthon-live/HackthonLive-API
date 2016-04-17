package com.iydsj.sw.api.dao.impl;

import com.iydsj.sw.api.DTO.LivingDTO;
import com.iydsj.sw.api.dao.LivingDao;
import com.iydsj.sw.common.dao.AbstractBaseDao;
import com.iydsj.sw.common.dao.SqlBuilder;
import com.iydsj.sw.common.utils.DateUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by junyu-pc on 2016/4/16.
 */
@Repository("livingDao")
public class LivingDaoImpl extends AbstractBaseDao implements LivingDao{
    private final static String table_name = "living";

    @Override
    public List<LivingDTO> getPlayingList() {
        SqlBuilder sqlBuilder = SqlBuilder.select(table_name,"*");
        sqlBuilder.where("isStart=:isStart");
        HashMap map = new HashMap();
        map.put("isStart",1);

        return namedParameterJdbcTemplate.query(sqlBuilder.toString() , map , new LivingMapper());
    }

    @Override
    public int createChanel(Map info) {
        SqlBuilder sqlBuilder = SqlBuilder.insert(table_name,"name,isStart,cId,cTime,pushUrl,rtmpPullUrl,uId",":name,:isStart,:cId,:cTime,:pushUrl,:rtmpPullUrl,:uId");

        Map<String, Object> params = new HashMap<String, Object>();

        params.put( "name",(String) info.get("name") );
        params.put( "isStart", 1);
        params.put( "cId",(String) info.get("cId")) ;
        params.put("cTime",(Date) DateUtils.timestampToDate((String) info.get("cTime")));
        params.put("pushUrl",(String) info.get("pushUrl"));
        params.put("rtmpPullUrl",(String) info.get("rtmpPullUrl"));
        params.put("uId",(int) info.get("uId"));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sqlBuilder.toString(), new MapSqlParameterSource(params), keyHolder);
        return keyHolder.getKey().intValue();
    }
    @Override
    public int exitChanel(int id) {
        SqlBuilder sqlBuilder = SqlBuilder.update(table_name).set("isStart=:isStart").where("uId=:uId");

        Map<String, Object> params = new HashMap<String, Object>();

        params.put( "uId",id );
        params.put("isStart",0);
        return namedParameterJdbcTemplate.update(sqlBuilder.toString(),params );
    }
    public class LivingMapper implements RowMapper<LivingDTO> {

        @Override
        public LivingDTO mapRow(ResultSet resultSet, int i) throws SQLException {
            LivingDTO living = new LivingDTO();
            living.setId(resultSet.getInt("id"));
            living.setCId(resultSet.getString("cId"));
            living.setCTime(resultSet.getDate("cTime"));
            living.setIsStart(resultSet.getInt("isStart"));
            living.setName(resultSet.getString("name"));
            living.setPushUrl(resultSet.getString("pushUrl"));
            living.setRtmpPullUrl(resultSet.getString("rtmpPullUrl"));
            living.setTagId(resultSet.getInt("tagId"));
            living.setUId(resultSet.getInt("uId"));
            return living;
        }
    }

}
