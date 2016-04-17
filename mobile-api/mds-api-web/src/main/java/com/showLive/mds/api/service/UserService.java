package com.showLive.mds.api.service;

import com.showLive.mds.api.entity.User;

/**
 * Created by junyu-pc on 2016/4/16.
 */

public interface UserService {
    String login(String userName,String passWorld);

    User getUser(String token);

    int tobeActor(String token,String fileUrl);

}
