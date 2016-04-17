package com.iydsj.sw.api.controller;

import com.iydsj.sw.api.service.LiveService;
import com.iydsj.sw.common.dto.AjaxMessage;
import com.iydsj.sw.common.dto.BaseController;
import com.iydsj.sw.common.utils.exceptions.BizException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by junyu-pc on 2016/4/16.
 */
@Controller
@RequestMapping("/live")
public class LiveController extends BaseController {
    @Resource
    private LiveService liveService;

    @ResponseBody
    @RequestMapping(value = "createChanel")
    public AjaxMessage createChanel(String name, String token) {

        AjaxMessage json = AjaxMessage.success("ok");

        try{
            json.setInfos(liveService.create(token ,name));
        }catch(BizException e){
            return AjaxMessage.error(e.getMessage());
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "exitChanel")
    public AjaxMessage exitChanel( String token ) {

        AjaxMessage json = AjaxMessage.success("ok");

        try{
            liveService.exitChanel(token);
        }catch(BizException e){
            return AjaxMessage.error(e.getMessage());
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "getChanelList")
    public AjaxMessage getChanelList(){
        AjaxMessage json = AjaxMessage.success("ok");

        try{
            json.setInfos(liveService.getLivingMap());
        }catch(BizException e){
            return AjaxMessage.error(e.getMessage());
        }
        return json;
    }
}
