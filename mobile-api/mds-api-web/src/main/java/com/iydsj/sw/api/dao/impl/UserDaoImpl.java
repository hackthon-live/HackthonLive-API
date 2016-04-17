package com.iydsj.sw.api.dao.impl;

import com.iydsj.sw.api.dao.UserDao;
import com.iydsj.sw.api.entity.User;
import com.iydsj.sw.common.dao.AbstractBaseDao;
import com.iydsj.sw.common.dao.SqlBuilder;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by junyu-pc on 2016/4/16.
 */

@Repository("userDao")
public class UserDaoImpl extends AbstractBaseDao implements UserDao {
    private static final String table_name = "user";
    private static final String table_name2 = "actor_check";
    @Override
    public List<User> find() {
        SqlBuilder sqlBuilder = SqlBuilder.select(table_name,"*");
        HashMap map = new HashMap();
        return namedParameterJdbcTemplate.query(sqlBuilder.toString(),map,new UserMapper());
    }

    @Override
    public User login(String userName,String passWord) {
        SqlBuilder sqlBuilder = SqlBuilder.select(table_name,"*");
        HashMap map = new HashMap();
        map.put("userName", userName);
        map.put("passWord", passWord);
        return namedParameterJdbcTemplate.queryForObject(sqlBuilder.toString(),map,new UserMapper());
    }

    @Override
    public User getUserByToken(String token) {
        SqlBuilder sqlBuilder = SqlBuilder.select(table_name,"*");
        HashMap map = new HashMap();
        map.put("token", token);
        return namedParameterJdbcTemplate.queryForObject(sqlBuilder.toString(),map,new UserMapper());
    }

    @Override
    public int updateToken(int id, String token) {
        SqlBuilder sqlBuilder = SqlBuilder.update(table_name);
        List<String> items = new ArrayList<>();
        HashMap map = new HashMap();
        if( token != null){
            items.add("token=:token");
            map.put("token",token);
        }
        map.put("id",id);
        sqlBuilder.set(items).where("Id=:id");
        return namedParameterJdbcTemplate.update(sqlBuilder.toString(), map);
    }

    @Override
    public int updateIsActor(String token,String fileUrl) {
        User user = getUserByToken(token);
        SqlBuilder sqlBuilder = SqlBuilder.insert(table_name2,"userid,username",":userId,:userName");

        Map<String, Object> params = new HashMap<String, Object>();

        params.put( "userId",user.getId() );
        params.put( "userName",user.getUserName() );
        params.put( "icon", fileUrl);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sqlBuilder.toString(), new MapSqlParameterSource(params), keyHolder);
        return keyHolder.getKey().intValue();

    }

    @Override
    public int isChecking(String token) {
        User user = getUserByToken(token);
        SqlBuilder sqlBuilder = SqlBuilder.select(table_name2,"count(id)");
        sqlBuilder.where("userId="+user.getId());
        return namedParameterJdbcTemplate.queryForObject(sqlBuilder.toString(),new HashMap<String, Object>(),Integer.class);
    }


    private class UserMapper implements RowMapper<User>{
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUserName(resultSet.getString("username"));
            user.setLogoUrl(resultSet.getString("logo"));
            user.setPassWord(resultSet.getString("password"));
            user.setFans(resultSet.getInt("fans"));
            user.setFollow(resultSet.getInt("follow"));
            user.setIcon(resultSet.getString("icon"));
            return user;
        }
    }
}
