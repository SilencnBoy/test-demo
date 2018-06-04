package com.hoyatod.appletclient.protocol;

import java.io.Serializable;

public class WechatUserInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String openid; // 用户唯一标识
	private String session_key; // 会话密钥(小程序特有)

	private String nickname; // 用户昵称
	private String sex; // 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private String province; // 用户个人资料填写的省份
	private String city; // 普通用户个人资料填写的城市
	private String country; // 国家，如中国为CN
	private String headimgurl; // 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	private String privilege; // 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
	private String unionid; // 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。

	private String errcode;// 错误码
	private String errmsg;// 错误信息

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserInfo [openid=" + openid + ", session_key=" + session_key + ", nickname=" + nickname + ", sex=" + sex
				+ ", province=" + province + ", city=" + city + ", country=" + country + ", headimgurl=" + headimgurl
				+ ", privilege=" + privilege + ", unionid=" + unionid + ", errcode=" + errcode + ", errmsg=" + errmsg
				+ "]";
	}
	
}
