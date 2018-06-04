package com.hoyatod.applet.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hoyatod.applet.config.WechatSDK;
import com.hoyatod.applet.util.HttpsRequestUtils;
import com.hoyatod.appletclient.interfaces.IWechatDataInterface;
import com.hoyatod.appletclient.protocol.AccessToken;
import com.hoyatod.appletclient.protocol.WechatUserInfo;

import net.sf.json.JSONObject;

@Service("WechatDataServer")
public class WechatDataServer implements IWechatDataInterface{
	
	private static final Logger log = Logger.getLogger(WechatDataServer.class);
	
	@Override
	public AccessToken getAccessTokenByCode(String appid, String appsecret, String code) {
		AccessToken accessToken = new AccessToken();
		if(appid == null || appsecret == null || code == null){
			return null;
		}
		if(appid.trim().equals("")|| appid.trim().equals("null")){
			return null;
		}
		if(appsecret.trim().equals("")|| appsecret.trim().equals("null")){
			return null;
		}
		if(code.trim().equals("")|| code.trim().equals("null")){
			return null;
		}
		try {
			String requestUrl = WechatSDK.OAUTH_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret).replace("CODE",code);
			JSONObject httpsRequestReJson = HttpsRequestUtils.httpsRequestReJson(requestUrl, "GET", null);
			if(httpsRequestReJson != null){
				String access_token = httpsRequestReJson.getString("access_token");
				accessToken.setAccess_token(access_token);
				int expires_in = httpsRequestReJson.getInt("expires_in");
				accessToken.setExpires_in(expires_in);
				String refresh_token = httpsRequestReJson.getString("refresh_token");
				accessToken.setRefresh_token(refresh_token);
				String openid = httpsRequestReJson.getString("openid");
				accessToken.setOpenid(openid);
				String scope = httpsRequestReJson.getString("scope");
				accessToken.setScope(scope);
				accessToken.setErrcode("200");
				accessToken.setErrmsg("SUCCESS");
				return accessToken;
			}
			accessToken.setErrcode("500");
			accessToken.setErrmsg("FAIL");
			return accessToken;
		} catch (Exception e) {
			log.error("获取accessToken异常", e);
			return null;
		}
	}

	@Override
	public WechatUserInfo getWechatUserInfo(AccessToken accessToken) {
		WechatUserInfo userInfo = new WechatUserInfo();
		if(accessToken == null){
			return null;
		}
		if(accessToken.getAccess_token() ==null||accessToken.getAccess_token().equals("") || accessToken.getOpenid() == null||accessToken.getOpenid().equals("")){
			return null;
		}
		try {
			String requestUrl = WechatSDK.USERINFO_URL.replace("ACCESS_TOKEN", accessToken.getAccess_token()).replace("OPENID", accessToken.getOpenid());
			JSONObject httpsRequestReJson = HttpsRequestUtils.httpsRequestReJson(requestUrl, "POST", null);
			if(httpsRequestReJson != null){
				String openid = httpsRequestReJson.getString("openid");
				userInfo.setOpenid(openid);
				String nickname = httpsRequestReJson.getString("nickname");
				userInfo.setNickname(nickname);
				String sex = httpsRequestReJson.getString("sex");
				userInfo.setSex(sex);
				String province = httpsRequestReJson.getString("province");
				userInfo.setProvince(province);
				String city = httpsRequestReJson.getString("city");
				userInfo.setCity(city);
				String country = httpsRequestReJson.getString("country");
				userInfo.setCountry(country);
				String headimgurl = httpsRequestReJson.getString("headimgurl");
				userInfo.setHeadimgurl(headimgurl);
				String privilege = httpsRequestReJson.getString("privilege");
				userInfo.setPrivilege(privilege);
				String unionid = httpsRequestReJson.getString("unionid");
				userInfo.setUnionid(unionid);
				userInfo.setErrcode("200");
				userInfo.setErrmsg("SUCCESS");
				return userInfo;
			}
			userInfo.setErrcode("500");
			userInfo.setErrmsg("FAIL");
			return userInfo;
		} catch (Exception e) {
			log.error("获取用户基本信息异常", e);
			return null;
		}
	}

	@Override
	public WechatUserInfo getAppletUserInfo(String appid, String appsecret, String code) {
		WechatUserInfo userInfo = new WechatUserInfo();
		if(appid == null || appsecret == null || code == null){
			return null;
		}
		if(appid.trim().equals("")|| appid.trim().equals("null")){
			return null;
		}
		if(appsecret.trim().equals("")|| appsecret.trim().equals("null")){
			return null;
		}
		if(code.trim().equals("")|| code.trim().equals("null")){
			return null;
		}
		try {
			String requestUrl = WechatSDK.APPLET_OAUTH_USERINFO_URL.replace("APPID", appid).replace("APPSECRET", appsecret).replace("CODE",code);
			JSONObject httpsRequestReJson = HttpsRequestUtils.httpsRequestReJson(requestUrl, "GET", null);
			if(httpsRequestReJson != null){
				String openid = httpsRequestReJson.getString("openid");
				userInfo.setOpenid(openid);
				String session_key = httpsRequestReJson.getString("session_key");
				userInfo.setSession_key(session_key);
				userInfo.setErrcode("200");
				userInfo.setErrmsg("SUCCESS");
				return userInfo;
			}
			userInfo.setErrcode("500");
			userInfo.setErrmsg("FAIL");
			return userInfo;
		} catch (Exception e) {
			log.error("获取用户基本信息异常", e);
			return null;
		}
	}

	@Override
	public AccessToken getAppletAccessToken(String appid, String appsecret) {
		try {
			AccessToken accessToken = new AccessToken();
			if(appid == null || appsecret == null){
				return null;
			}
			if(appid.trim().equals("")|| appid.trim().equals("null")){
				return null;
			}
			if(appsecret.trim().equals("")|| appsecret.trim().equals("null")){
				return null;
			}
			String requestUrl = WechatSDK.APPLET_OAUTH_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
			JSONObject httpsRequestReJson = HttpsRequestUtils.httpsRequestReJson(requestUrl, "GET", null);
			if(httpsRequestReJson != null){
				String access_token = httpsRequestReJson.getString("access_token");
				accessToken.setAccess_token(access_token);
				int expires_in = httpsRequestReJson.getInt("expires_in");
				accessToken.setExpires_in(expires_in);
				accessToken.setErrcode("200");
				accessToken.setErrmsg("SUCCESS");
				return accessToken;
			}
			accessToken.setErrcode("500");
			accessToken.setErrmsg("FAIL");
			return accessToken;
		} catch (Exception e) {
			log.error("获取accessToken异常", e);
			return null;
		}
	}
	
}
