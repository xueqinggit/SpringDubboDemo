package com.xueqing.demo.springdubbo.controller;

import com.xueqing.demo.springdubbo.entity.Email;
import com.xueqing.demo.springdubbo.entity.Result;
import com.xueqing.demo.springdubbo.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {

    @Autowired
    private IMailService mailService;

    @RequestMapping("/email")
    @ResponseBody
    public Result send() {
        try {
            Email email = new Email();
            email.setEmail("17621152225@163.com");
            email.setSubject("测试");
            email.setContent("测试");
            email.setTemplate("freemarker");
            mailService.sendHtml(email);
        } catch (Exception e) {
            e.printStackTrace();
            return  Result.error();
        }
        return  Result.ok();
    }

}
