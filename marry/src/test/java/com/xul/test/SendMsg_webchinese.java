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
		// ��ͷ�ļ�������ת��
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");
		// ע����û���
		NameValuePair[] data = { new NameValuePair("Uid", "xl19890926"), 
				// ע��ɹ���,��¼��վʹ�õ���Կ
				new NameValuePair("Key", "e929278fcf20dd9805e9"), 
				// �ֻ�����
				new NameValuePair("smsMob", "13034448623"), 
				// ���ö�������
				new NameValuePair("smsText", "�컯�衣�����ܲ������߿��ʡ��� ��һ��������") };
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
