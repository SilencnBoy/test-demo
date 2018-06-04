package com.xul.mail;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * TODO 发送带附件的邮件
 * 
 * @author xl
 * 
 */
@Component
public class AttachEmailSender {

	@Autowired
	private JavaMailSender javaMailSender;

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmailWithAttach(String from, String[] to, String subject, String text, String[] attachPath)
			throws MessagingException, IOException {

		// 1.创建一个多媒体邮件对象
		MimeMessage message = javaMailSender.createMimeMessage();
		// 2.创建多媒体邮件写入助手
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		// 3.为多媒体邮件设置属性添加内容
		helper.setFrom(from);// 设置发件人
		helper.setTo(to);// 设置收件人
		helper.setSubject(subject);// 设置主题
		helper.setText(text);// 设置邮件内容
		// 如何添加附件
		for (String path : attachPath) {
			ClassPathResource classPathResource = new ClassPathResource(path);
			helper.addAttachment(classPathResource.getFilename(), classPathResource.getFile());
		}
		javaMailSender.send(message);
		System.out.println("多媒体邮件发送成功");
	}

}
