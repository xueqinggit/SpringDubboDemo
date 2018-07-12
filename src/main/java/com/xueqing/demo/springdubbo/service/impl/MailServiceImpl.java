package com.xueqing.demo.springdubbo.service.impl;


import com.xueqing.demo.springdubbo.entity.Email;
import com.xueqing.demo.springdubbo.service.IMailService;
import com.xueqing.demo.springdubbo.utils.MailUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.ResourceUtils;
import redis.clients.jedis.JedisPool;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements IMailService {
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	@Autowired
	private JavaMailSender mailSender;//执行者
	@Autowired
	public Configuration configuration;//freemarker
	@Value("${spring.mail.username}")
	public String USER_NAME;//发送者


	@Autowired
    private JedisPool jedisPool;

	MailUtil mailUtil = new MailUtil();
	
	static {
		 System.setProperty("mail.mime.splitlongparameters","false");
	}

	@Override
	public void send(Email mail) throws Exception {
		logger.info("发送邮件：{}",mail.getContent());
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(USER_NAME);
		message.setTo(mail.getEmail());
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent());
//		mailSender.send(message);
		mailUtil.start(mailSender,message);
	}

	@Override
	public void sendHtml(Email mail) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(USER_NAME);
		helper.setTo(mail.getEmail());
		helper.setSubject(mail.getSubject());
		helper.setText(
				"<html><body><img src=\"我来测试发送邮件\" ></body></html>",
				true);
		// 发送图片
		File file = ResourceUtils.getFile("classpath:static/images/1.jpg");
		helper.addInline("springboot", file);
		helper.addAttachment("XueQing Wang", file);
//		mailSender.send(message);
		mailUtil.startHtml(mailSender,message);
	}

	@Override
	public void sendFreemarker(Email mail) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(USER_NAME);
		helper.setTo(mail.getEmail());
		helper.setSubject(mail.getSubject());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("mail", mail);
		Template template = configuration.getTemplate(mail.getTemplate()+".flt");
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(
				template, model);
		helper.setText(text, true);
		mailSender.send(message);
	}

	@Override
	public void sendRedisQueue(Email mail) throws Exception {
	}

}
