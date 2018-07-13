package com.xueqing.demo.springdubbo.controller;

import com.xueqing.demo.springdubbo.entity.Email;
import com.xueqing.demo.springdubbo.entity.Result;
import com.xueqing.demo.springdubbo.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class UploadController {

    @Autowired
    private IMailService mailService;

    @RequestMapping("/upload")
    public String upload() {
        return  "upload";
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

            File file2 = new File(contextPath + "/uploadFiles/");
            if(!file2.exists()){
                file2.mkdirs();
            }
            File file1 = new File(contextPath + "/uploadFiles/" +name+type );
            if(!file1.exists()){
                boolean newFile = file1.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  Result.error();
        }
        return  Result.ok();
    }

}
