package com.iydsj.sw.common.sporttype.dao.impl;

import com.iydsj.sw.common.dao.AbstractBaseDao;
import com.iydsj.sw.common.dao.SqlBuilder;
import com.iydsj.sw.common.sporttype.dao.SportTypeDao;
import com.iydsj.sw.common.sporttype.entity.SportType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanyan.wang
 * @date 2015-12-15 14:22
 */
@Repository
public class SportTypeDaoImpl extends AbstractBaseDao implements SportTypeDao {

    @Override
    public SportType find(int id) {
        SqlBuilder sqlBuilder = sportTypeSqlBuilder();
        sqlBuilder.where(" Id=:id ").deleted(false);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(sqlBuilder.toString(), params, new SportTypeMapper());
    }

    @Override
    public List<SportType> findSportTypes(List<Integer> ids) {
        SqlBuilder sqlBuilder = sportTypeSqlBuilder();
        sqlBuilder.where().in("Id", ids).deleted(false);
        return jdbcTemplate.query(sqlBuilder.toString(), new SportTypeMapper());
    }

    @Override
    public List<SportType> findSportTypes(int parentId) {
        SqlBuilder sqlBuilder = sportTypeSqlBuilder();
        sqlBuilder.where(" parentId=:parentId ").deleted(false);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("parentId", parentId);
        return namedParameterJdbcTemplate.query(sqlBuilder.toString(), params, new SportTypeMapper());
    }

    @Override
    public List<SportType> findSportTypes() {
        SqlBuilder sqlBuilder = sportTypeSqlBuilder();
        sqlBuilder.where(" 1=1 ").deleted(false);
        return jdbcTemplate.query(sqlBuilder.toString(), new SportTypeMapper());
    }

    @Override
    public List<SportType> findSportItems() {
        SqlBuilder sqlBuilder = sportTypeSqlBuilder().where("ParentId!=0").deleted(false);
        return jdbcTemplate.query(sqlBuilder.toString(), new SportTypeMapper());
    }

    private class SportTypeMapper implements RowMapper<SportType> {
        @Override
        public SportType mapRow(ResultSet rs, int rowNum) throws SQLException {
            SportType sportType = new SportType();
            sportType.setId(rs.getInt("Id"));
            sportType.setName(rs.getString("name"));
            sportType.setParentId(rs.getInt("ParentId"));
            return sportType;
        }
    }

    private SqlBuilder sportTypeSqlBuilder() {
        SqlBuilder sqlBuilder = SqlBuilder.select("base_sporttype", "Id,Name,ParentId");
        return sqlBuilder;
    }
}
