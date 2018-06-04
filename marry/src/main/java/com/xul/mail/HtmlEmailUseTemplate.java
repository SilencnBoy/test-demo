package com.xul.mail;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * TODO 支持发送带附件和内容为HTML邮件
 * 
 * @author Administrator
 * 
 */
@Component
public class HtmlEmailUseTemplate {

	@Autowired
	private JavaMailSender javaMailSender;

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendMultiPartEmail(String from, String[] to, String subject,
			Map<String, Object> templateData, String[] attachPath) throws MessagingException,
			IOException {

		// 1.创建一个多媒体邮件对象
		MimeMessage message = javaMailSender.createMimeMessage();
		// 2.创建多媒体邮件写入助手
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		// 3.为多媒体邮件设置属性添加内容
		helper.setFrom(from);// 设置发件人
		helper.setTo(to);// 设置收件人
		helper.setSubject(subject);// 设置主题
		helper.setText(getHtml(templateData), true);// 设置邮件内容 true:内容为多媒体内容
		// 如何添加附件
		for (String path : attachPath) {
			ClassPathResource classPathResource = new ClassPathResource(path);
			helper.addAttachment(classPathResource.getFilename(),classPathResource.getFile());
		}
		javaMailSender.send(message);
		System.out.println("多媒体邮件发送成功");
	}

	@Autowired
	private Configuration configuration;
	
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	//通过模板解析模板为一个HTML字符串
	public String getHtml(Map<String, Object> templateData){
		String str = "";
		try {
			Template template = configuration.getTemplate("registerTemplate.ftl");
			
			//给模板设置动态参数
			 str = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateData);
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}//获取模板s
		
		return str;
		
	}
}
