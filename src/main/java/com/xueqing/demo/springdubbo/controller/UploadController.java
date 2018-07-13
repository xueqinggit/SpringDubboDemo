package com.xueqing.demo.springdubbo.controller;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.xueqing.demo.springdubbo.entity.Email;
import com.xueqing.demo.springdubbo.entity.Result;
import com.xueqing.demo.springdubbo.service.IMailService;
import com.xueqing.demo.springdubbo.service.MongoService;
import com.xueqing.demo.springdubbo.service.impl.MongoServiceImpl;
import com.xueqing.demo.springdubbo.utils.MongoDBUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class UploadController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MongoService mongoService;

    @RequestMapping("/upload")
    public String upload() {
        return "upload";
    }

    @RequestMapping("/uploadFile")
    @ResponseBody
    public Result uploadFile(MultipartFile files, HttpServletRequest request) {
        try {
            String contextPath = request.getServletContext().getRealPath("/");
            String originalFilename = files.getOriginalFilename();
            String[] split = originalFilename.split("\\.");
            String type = split[1];
            String name = split[0];

            File fileDir = new File(contextPath + "/uploadFiles/");
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            File file1 = new File(contextPath + "/uploadFiles/" + name + "." + type);
            if (!file1.exists()) {
                boolean newFile = file1.createNewFile();
            }
            files.transferTo(file1);
            String s = mongoService.uploadFile(file1);
            logger.info(s);

            return Result.ok().put(s, s);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @RequestMapping("/showPic")
    public void showPic(HttpServletRequest request, HttpServletResponse response, String id) {
        try {
            OutputStream out = response.getOutputStream();
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            mongoService.getFile(id, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置图片无缓存

    }

    @RequestMapping("/delFile")
    @ResponseBody
    public Result delFile(String id) {
        try {
            mongoService.delFile(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.ok();
    }

}
