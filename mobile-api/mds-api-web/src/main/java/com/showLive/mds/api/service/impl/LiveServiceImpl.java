package com.showLive.mds.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.showLive.mds.api.dao.LivingDao;
import com.showLive.mds.api.dao.UserDao;
import com.showLive.mds.api.entity.User;
import com.showLive.mds.api.service.LiveService;
import com.showLive.mds.common.utils.CheckSumBuilder;
import com.showLive.mds.common.utils.exceptions.BizException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by junyu-pc on 2016/4/16.
 */
@Service("LiveService")
public class LiveServiceImpl implements LiveService {
    @Resource
    private String appKey;

    @Resource
    private String appSecret;

    @Resource
    private LivingDao livingDao;

    @Resource
    private UserDao userDao;
    @Override
    public Map create(String token ,String name) {
        User user = userDao.getUserByToken(token);

        Map info = new HashMap();
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://vcloud.163.com/app/channel/create";
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;
        HttpResponse response = null;

        String nonce =  "1";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码


        // 设置请求的header
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");

        // 设置请求的参数
        StringEntity params = null;
        try {
            params = new StringEntity("{\"name\":\"" + name + "\", \"type\":0}");
            httpPost.setEntity(params);
            // 执行请求
            response = httpClient.execute(httpPost);
            jsonObject = JSON.parseObject(EntityUtils.toString(response.getEntity())).getJSONObject("ret");
        } catch (UnsupportedEncodingException e) {
            throw new BizException("拼装json失败");
        } catch (IOException e) {
            throw new BizException("网络错误");
        }
        if(jsonObject == null){
            throw new BizException("频道名已存在");
        }
        info.put( "rtmpPullUrl" , jsonObject.getString("rtmpPullUrl") );
        info.put( "name" , jsonObject.getString("name") );
        info.put( "cId" , jsonObject.getString("cid") );
        info.put( "cTime" , jsonObject.getString("ctime") );
        info.put( "pushUrl" , jsonObject.getString("pushUrl") );
        info.put( "uId" , user.getId());
        livingDao.createChanel(info);
        return info;
    }

    @Override
    public Map getLivingMap() {
        Map livingMap = new HashMap();
        livingMap.put("chanelList",livingDao.getPlayingList());
        return livingMap;
    }

    @Override
    public int exitChanel(String token) {
        User user = userDao.getUserByToken(token);

        return livingDao.exitChanel(user.getId());
    }
}
