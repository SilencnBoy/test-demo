package com.xul.mail;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * TODO 邮件发送工具类
 * 
 * @author xl
 */
@Component
public class EMailSender {
	
	@Autowired
	private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	// 发送简单邮件的方法
	public void sendEmail(String from, String to, String subject, String text) throws UnsupportedEncodingException {
		// 简单邮件对象
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		// 给邮件对象设置参数
		mailMessage.setFrom(from);// 设置发件人
		mailMessage.setTo(to);// 设置收件人
		mailMessage.setSubject(subject);// 设置邮件主题
		mailMessage.setText(text);// 设置邮件文本内容
		mailSender.send(mailMessage);// 执行邮件发送s
		System.out.println("普通发送成功.......................");
	}
}
