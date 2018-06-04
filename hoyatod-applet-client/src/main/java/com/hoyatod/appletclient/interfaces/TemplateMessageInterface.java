package com.hoyatod.appletclient.interfaces;

import java.util.Map;

public interface TemplateMessageInterface {
	
	/**
	 * 
	 *  @param appid    	必须
	 *  @param appsecret 	必须
	 *  @param claimId 		必须 认领申请id
	 *  @param userId 		必须 用户登陆id
	 *  @return Map<String,Object> 
	 */
	public Map<String, Object> sendStoreClaimTemplateMessage(String appid,String appsecret,Integer claimId,Integer userId);

}
