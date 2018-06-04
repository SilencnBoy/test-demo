package com.hoyatod.appletclient.interfaces;

import com.hoyatod.appletclient.protocol.AccessToken;
import com.hoyatod.appletclient.protocol.WechatUserInfo;

public interface IWechatDataInterface {
	
	/**
	 * 	获取网页授权调用凭证
	 * 
	 *  @param appid 公众号的唯一标识
	 *  @param appsecret 公众号密钥
	 *  @param code 用户同意授权才能获取(code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期)
	 *  @return AccessToken (实体对象获取 access_token:网页授权接口调用凭证)
	 */
	public AccessToken getAccessTokenByCode(String appid,String appsecret,String code);
	
	/**
	 * 	拉取用户信息(需scope为 snsapi_userinfo)
	 * 
	 *  @param accessToken 票据对象
	 *  @return UserInfo  (用户基本信息)
 	 */
	public WechatUserInfo getWechatUserInfo(AccessToken accessToken);
	
	/**
	 * 	获取小程序用户信息(只针对小程序授权用户)	
	 * 
	 *  @param appid 小程序唯一标识
	 *  @param appsecret 小程序密钥
	 *  @param code 用户同意授权才能获取
	 *  @return UserInfo (用户基本信息)
	 */
	public WechatUserInfo getAppletUserInfo(String appid,String appsecret,String code);
	
	/**
	 * 	获取小程序	AccessToken
	 * 
	 *  @param appid  小程序唯一标识
	 *  @param appsecret 小程序密钥
	 *  @return AccessToken
	 */
	public AccessToken getAppletAccessToken(String appid,String appsecret);
}
