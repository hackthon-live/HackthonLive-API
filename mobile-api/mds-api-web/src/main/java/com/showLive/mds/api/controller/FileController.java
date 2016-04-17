package com.showLive.mds.api.controller;

import com.showLive.mds.common.dto.AjaxMessage;
import com.showLive.mds.common.utils.TokenUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by junyu-pc on 2016/4/17.
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @ResponseBody
    @RequestMapping(value = "upload" ,method = RequestMethod.POST)
    public AjaxMessage upload(MultipartFile file,HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName =file.getOriginalFilename();

        String fileKind = fileName.substring(fileName.lastIndexOf(".")+1);

        fileName = TokenUtils.getToken(fileName) + "." + fileKind;
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            return  AjaxMessage.error("上传失败");
        }
        Map map = new HashMap();
        map.put("fileUrl","/upload/"+fileName);

        AjaxMessage ajaxMessage = AjaxMessage.success("上传成功");

        ajaxMessage.setInfos(map);
        return ajaxMessage;

    }
}
