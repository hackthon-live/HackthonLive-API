package com.iydsj.sw.common.test;

import com.iydsj.sw.common.fileresource.enums.BizType;
import com.iydsj.sw.common.fileresource.enums.FileType;
import com.iydsj.sw.common.fileresource.service.FileResourceService;
import org.apache.tools.ant.types.resources.FileResource;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author yanyan.wang
 * @date 2016-01-03 20:02
 */
@ContextConfiguration(locations = "classpath:config/spring/application-*.xml")
public class FileResourceServiceTest  extends AbstractJUnit4SpringContextTests {

    @Resource
    private FileResourceService fileResourceService;

    @Test
    public void testUpload(){
        File file=new File("/data/1.png");
        try {
            InputStream inputStream=new FileInputStream(file);
           int id=fileResourceService.upload(BizType.Venue, FileType.Image,file.getName(),inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
