package com.iydsj.sw.api.controller;

import com.iydsj.sw.api.entity.User;
import com.iydsj.sw.api.service.UserService;
import com.iydsj.sw.common.dto.AjaxMessage;
import com.iydsj.sw.common.utils.exceptions.BizException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by junyu-pc on 2016/4/16.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value="/login")
    public AjaxMessage login(String name,String passWord){
        try{
            AjaxMessage success = AjaxMessage.success("ok");
            String token = userService.login(name,passWord);
            Map<String , Object> hashMap = new HashMap<>();
            hashMap.put("token", token);
            success.setInfos(hashMap);
            return success;
        }catch(BizException e){
            return AjaxMessage.error(e.getMessage());
        }
    }
    @ResponseBody
    @RequestMapping(value = "info")
    public AjaxMessage getInfo(String token){
        try{
            AjaxMessage success = AjaxMessage.success("ok");
            User user = userService.getUser(token);
            Map<String , Object> hashMap = new HashMap<>();
            hashMap.put("user", user);
            success.setInfos(hashMap);
            return success;
        }catch(BizException e){
            return AjaxMessage.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "beActor")
    public AjaxMessage beActor(String token,String fileUrl){
        try{
            AjaxMessage success = AjaxMessage.success("申请主播成功，请等后台审核待");
            int res = userService.tobeActor(token,fileUrl);
            if(res == 1){
                return success;
            }else{
                throw new BizException("申请主播失败");
            }
        }catch(BizException e){
            return AjaxMessage.error(e.getMessage());
        }
    }
}
