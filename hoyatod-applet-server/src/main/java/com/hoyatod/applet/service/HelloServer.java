package com.hoyatod.applet.service;

import org.springframework.stereotype.Service;

import com.hoyatod.appletclient.interfaces.IHelloInterface;
import com.hoyatod.appletclient.protocol.Message;

@Service("HelloServer")
public class HelloServer implements IHelloInterface{

	public String sayHello(Message message) {
		message.setMsgeHeader("这是一个消息头");
		message.setMsgContent("第一个应用服务");
		message.setMsgstatus(true);
		return message.toString();
	}

}
