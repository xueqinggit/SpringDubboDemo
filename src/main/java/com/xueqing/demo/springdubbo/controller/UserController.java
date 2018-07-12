package com.xueqing.demo.springdubbo.controller;

import com.github.pagehelper.PageHelper;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.xueqing.demo.springdubbo.annotation.SysLog;
import com.xueqing.demo.springdubbo.entity.Result;
import com.xueqing.demo.springdubbo.service.UserService;
import com.xueqing.demo.springdubbo.utils.ShiroUtils;
import com.xueqing.demo.springdubbo.utils.SpringUtil;
import com.xueqing.demo.springdubbo.entity.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import redis.clients.jedis.JedisPool;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@Api(value = "用户Controller", tags = {"演示接口:用户Controller"}, description = "用于演示的用户Controller")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    Configuration configuration;

    @Autowired
    JedisPool jedisPool;

    @Autowired
    UserService userService;

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping(value = "/getUser")
    @ResponseBody
    @SysLog("查询用户列表")
    @ApiOperation(value = "获取用户列表", notes = "暂无")
    public List<User> getUser() {
        Map<Object, Element> elements = userService.getCache();
        User user = SpringUtil.getBean(User.class);
        /*
         *  PageHelper.startPage(需要显示的第几个页面，每个页面显示的数量);
         *   下一行紧跟查询语句，不可以写其他的，否则没有效果。
         */
        PageHelper.startPage(1, 3);
        List<User> users = userService.getUsers();
        logger.info("users", users);
        jedisPool.getResource().set("user", "user");
        String user1 = jedisPool.getResource().get("user");
        return users;
    }

    @RequestMapping("/findUserById")
    @ResponseBody
    public Result findUserById(String id) {
        try {
            Map<Object, Element> elements = userService.getCache();
            User user = userService.findUserById(id);
            return Result.ok().put("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @RequestMapping("/deleteUserById")
    @ResponseBody
    public Result deleteUserById(String id) {
        try {
            userService.deleteUserById(id);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }


    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response)throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = defaultKaptcha.createText();
        //生成图片验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(ShiroUtils.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    @RequestMapping("/startLogin")
    public String startLogin(String username,String password,String kaptcha) {
        try {

            String kaptcha1 = ShiroUtils.getKaptcha(ShiroUtils.KAPTCHA_SESSION_KEY);
            if(!kaptcha1.equalsIgnoreCase(kaptcha)){
                throw new AuthenticationException("验证码不正确");
            }
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            usernamePasswordToken.setRememberMe(true);
            Subject subject = ShiroUtils.getSubject();
            subject.login(usernamePasswordToken);
            return "index";
        }catch (AuthenticationException e){
            return "redirect:login";
        }catch (Exception e) {
            e.printStackTrace();
            return "redirect:login";
        }
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }


    @RequestMapping("/freemarker")
    @ResponseBody
    public String freemarker(Map<String, Object> map) throws Exception {
        map.put("content", "freemarker哈哈哈哈");
        Template template = configuration.getTemplate("freemarker.ftl");
        String s = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        logger.info("freemarker", s);
        return s;

    }
}
