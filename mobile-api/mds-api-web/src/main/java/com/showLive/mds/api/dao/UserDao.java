package com.showLive.mds.api.dao;

import com.showLive.mds.api.entity.User;

import java.util.List;

/**
 * Created by junyu-pc on 2016/4/16.
 */
public interface UserDao {
    List<User> find();

    User login(String userName,String passWord);

    User getUserByToken(String token);

    int updateToken(int id ,String token);

    int updateIsActor(String token,String fileUrl);

    int isChecking(String token);

}
