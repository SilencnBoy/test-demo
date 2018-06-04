package com.xul.test;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

public class SendMsg_webchinese {

	@Test
	public void sendMsg() throws Exception{
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");
		
		//PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
		// 在头文件中设置转码
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");
		// 注册的用户名
		NameValuePair[] data = { new NameValuePair("Uid", "xl19890926"), 
				// 注册成功后,登录网站使用的密钥
				new NameValuePair("Key", "e929278fcf20dd9805e9"), 
				// 手机号码
				new NameValuePair("smsMob", "13034448623"), 
				// 设置短信内容
				new NameValuePair("smsText", "造化设。。辣萝卜。。瓦克朗。。 风一样的男人") };
		post.setRequestBody(data);  
		  
	    client.executeMethod(post);  
	    Header[] headers = post.getResponseHeaders();  
	    int statusCode = post.getStatusCode();  
	    System.out.println("statusCode:" + statusCode);  
	    for (Header h : headers) {  
	        System.out.println(h.toString());  
	    }  
	    String result = new String(post.getResponseBodyAsString().getBytes("gbk"));  
	    System.out.println(result);  
	    post.releaseConnection();  
	}
}
