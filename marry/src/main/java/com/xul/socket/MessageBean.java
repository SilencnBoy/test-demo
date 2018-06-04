package com.xul.socket;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("MESSAGE")
public class MessageBean {
	
	//为了避免<MESSAGE NAME="ACTIVE"/>
	@XStreamAlias("CONTENT")
	private String content;
	
	//	1.ACTIVE 心跳 2.EVENT 事件(上课 下课 吃饭 等等)
	@XStreamAsAttribute
	@XStreamAlias("NAME")
	private String name;//<MESSAGE NAME=
	
	//消息序列号
	@XStreamAsAttribute
	@XStreamAlias("NO")
	private String no;//<MESSAGE NO=
	
	@XStreamAsAttribute
	@XStreamAlias("TYPE")
	private String type;//<MESSAGE TYPE="REQ/RSP"

	//事件No 1 进入教室 2 离开教室 3 吃饭 4出校门 5进入校门 
	@XStreamAlias("EventNo")
	private String eventNo;
	
	//事件名称
	@XStreamAlias("EventName")
	private String eventName;
	
	//学生编号	00000001
	@XStreamAlias("stuNo")
	private String stuNo;
	
	//学生姓名
	@XStreamAlias("stuName")
	private String stuName;
	
	//性别 0未知 1男 2女
	@XStreamAlias("Sex")
	private String sex;
	
	//联系电话 13838386666
	@XStreamAlias("MobilePhone")
	private String mobilePhone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEventNo() {
		return eventNo;
	}

	public void setEventNo(String eventNo) {
		this.eventNo = eventNo;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	
	
}