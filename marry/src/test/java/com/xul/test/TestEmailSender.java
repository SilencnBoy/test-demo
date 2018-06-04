package com.xul.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xul.mail.AttachEmailSender;
import com.xul.mail.EMailSender;
import com.xul.mail.HtmlEmail;
import com.xul.mail.HtmlEmailUseTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:applicationEmail.xml", "classpath:applicationFreemarker.xml" })
public class TestEmailSender {
	
	//普通邮件
	@Autowired
	private EMailSender eMailSender;

	@Test
	public void testSendEmail() {
		for (int i = 0; i < 1; i++) {
			try {
				eMailSender.sendEmail("xuliang@hoyatod.com","15897899920@163.com","第一封", "你好！Silencn");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	// Html邮件
	@Autowired
	private HtmlEmail htmlEmail;

	public void setHtmlEmail(HtmlEmail htmlEmail) {
		this.htmlEmail = htmlEmail;
	}

	@Test
	public void testSendEmailWithAttachAndHtml() {

		StringBuffer buffer = new StringBuffer();
		buffer.append("<html>");
		buffer.append("<body style='color:red'>");
		buffer.append("<marquee><h1>Hello World!</h1><a href ='http://www.baidu.com'>点击</a></marquee>");
		buffer.append("</body>");
		buffer.append("</html>");

		try {
			for (int i = 0; i < 2; i++) {
			
				htmlEmail.sendMultiPartEmail("xuliang@hoyatod.com", new String[] { "15897899920@163.com" }, "******"+i, buffer.toString(),new String[] { "com/xul/mail/attach/sb.torrent" });
			}
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
	}
	
	//带附件邮件
	@Autowired
	private AttachEmailSender attachEmailSender;

	public void setAttachEmailSender(AttachEmailSender attachEmailSender) {
		this.attachEmailSender = attachEmailSender;
	}
	
	@Test
	public void testSendEmailWithAttach() {
		try {
			attachEmailSender.sendEmailWithAttach("15897899920@163.com",new String[] { "15623032112@163.com" },".....",".....",new String[] { "com/xul/mail/attach/sb.torrent" });
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	private HtmlEmailUseTemplate emailUseTemplate;

	public void setEmailUseTemplate(HtmlEmailUseTemplate emailUseTemplate) {
		this.emailUseTemplate = emailUseTemplate;
	}

	@Test
	public void testSendEmaiUseTemplate() {
		
		Map<String, Object> templateData = new HashMap<>();
		templateData.put("name", "凤姐");
		templateData.put("site_name", "王宝强");
		templateData.put("url", "http://www.baidu.com");
		
		try {
			emailUseTemplate.sendMultiPartEmail("15897899920@163.com",new String[] { "xuliang@hoyatod.com" }, "这是一个模板引擎",templateData, new String[]{});
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
