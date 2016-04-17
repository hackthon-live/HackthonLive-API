package com.iydsj.sw.api.service.impl;

import com.iydsj.sw.api.dao.UserDao;
import com.iydsj.sw.api.entity.User;
import com.iydsj.sw.api.service.UserService;
import com.iydsj.sw.common.utils.TokenUtils;
import com.iydsj.sw.common.utils.exceptions.BizException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by junyu-pc on 2016/4/16.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public String login(String userName,String passWorld) {
        String token;
        User user = userDao.login(userName, passWorld);
        if(user == null) {
            throw new BizException("用户名或密码错误");
        }
        token = TokenUtils.getToken(user.getUserName());
        userDao.updateToken(user.getId(),token);
        return token;
    }

    @Override
    public User getUser(String token) {
        return userDao.getUserByToken(token);
    }

    @Override
    public int tobeActor(String token,String fileUrl) {
        System.out.println(userDao.isChecking(token));
        if(userDao.isChecking(token) >= 1){
            throw new BizException("请等待审核");
        }
        return userDao.updateIsActor(token,fileUrl);
    }

}
