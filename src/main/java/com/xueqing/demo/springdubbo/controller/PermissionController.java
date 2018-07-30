package com.xueqing.demo.springdubbo.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@Api(value = "权限规则控制层", tags = {"接口:权限规则控制Controller"}, description = "用于配置权规则限的Controller")
public class PermissionController {

    @Autowired
    Configuration configuration;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/config")
    @ResponseBody
    public String freemarker(Map<String, Object> map) throws Exception {
        map.put("content", "freemarker哈哈哈哈");
        Template template = configuration.getTemplate("config.ftl");
        String s = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        logger.info("config", s);
        return s;

    }
}
