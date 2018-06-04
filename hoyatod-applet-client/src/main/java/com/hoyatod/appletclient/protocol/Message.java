package com.hoyatod.appletclient.protocol;

import java.io.Serializable;

/**
 * 基本消息实体类
 * 
 * @author xul
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msgeHeader;
	private String msgContent;
	private boolean msgstatus;

	public String getMsgeHeader() {
		return msgeHeader;
	}

	public void setMsgeHeader(String msgeHeader) {
		this.msgeHeader = msgeHeader;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public boolean isMsgstatus() {
		return msgstatus;
	}

	public void setMsgstatus(boolean msgstatus) {
		this.msgstatus = msgstatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Message [msgeHeader=" + msgeHeader + ", msgContent=" + msgContent + ", msgstatus=" + msgstatus + "]";
	}
}
