package com.iydsj.sw.common.area.dao.impl;

import com.iydsj.sw.common.area.dao.AreaDao;
import com.iydsj.sw.common.area.entity.Area;
import com.iydsj.sw.common.dao.AbstractBaseDao;
import com.iydsj.sw.common.dao.SqlBuilder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanyan.wang
 * @date 2015-12-15 09:38
 */
@Repository("areaDao")
public class AreaDaoImpl extends AbstractBaseDao implements AreaDao {

    @Override
    public Area find(int id) {
        Assert.isTrue(id > 0);
        SqlBuilder sqlBuilder = areaSqlBuilder();
        sqlBuilder.where(" Id=:id ").deleted(false);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        try {
            return namedParameterJdbcTemplate.queryForObject(sqlBuilder.toString(), params, new AreaRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Area> findAreas(List<Integer> ids) {
        Assert.notEmpty(ids);
        SqlBuilder sqlBuilder = areaSqlBuilder();
        sqlBuilder.where().in("Id",ids).deleted(false);
        return jdbcTemplate.query(sqlBuilder.toString(), new AreaRowMapper());
    }

    @Override
    public List<Area> findAreas(int levelType) {
        Assert.isTrue(levelType >= 0);
        SqlBuilder sqlBuilder = areaSqlBuilder();
        sqlBuilder.where(" LevelType=:levelType ").deleted(false);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("levelType", levelType);
        return namedParameterJdbcTemplate.query(sqlBuilder.toString(), params, new AreaRowMapper());
    }

    @Override
    public List<Area> findAreasByParentId(int parentId) {
        Assert.isTrue(parentId > 0);
        SqlBuilder sqlBuilder = areaSqlBuilder();
        sqlBuilder.where(" ParentId=:parentId ").deleted(false);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("parentId", parentId);
        return namedParameterJdbcTemplate.query(sqlBuilder.toString(), params, new AreaRowMapper());
    }

    private class AreaRowMapper implements RowMapper<Area> {
        @Override
        public Area mapRow(ResultSet rs, int rowNum) throws SQLException {
            Area area = new Area();
            area.setId(rs.getInt("Id"));
            area.setName(rs.getString("Name"));
            area.setParentId(rs.getInt("ParentId"));
            area.setShortName(rs.getString("ShortName"));
            area.setLat(rs.getDouble("Lat"));
            area.setLng(rs.getDouble("Lng"));
            area.setPinYin(rs.getString("PinYin"));
            return area;
        }
    }

    private SqlBuilder areaSqlBuilder() {
        SqlBuilder sqlBuilder = SqlBuilder.select("base_area", "Id,Name,ParentId,ShortName,LevelType,Lng,Lat,PinYin");
        return sqlBuilder;
    }
}
