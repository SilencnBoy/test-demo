package com.hoyatod.applet.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoyatod.applet.config.WechatConfig;
import com.hoyatod.applet.config.WechatSDK;
import com.hoyatod.applet.util.HttpsRequestUtils;
import com.hoyatod.appletclient.interfaces.TemplateMessageInterface;
import com.hoyatod.appletclient.protocol.AccessToken;
import com.hoyatod.wechatclient.interfaces.SaasWechatGoodsInterface;
import com.hoyatod.wechatclient.protocol.wechat.GoodsClaimTemplateData;

import net.sf.json.JSONObject;

@Service("TemplateMessageServer")
public class TemplateMessageServer implements TemplateMessageInterface {
	
	@Autowired
	private SaasWechatGoodsInterface wechatGoods;
	
	@Autowired
	private WechatDataServer wechatDataServer;

	@Override
	public Map<String, Object> sendStoreClaimTemplateMessage(String appid, String appsecret, Integer claimId,Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> findGoodsClaimTemplate = wechatGoods.findGoodsClaimTemplate(appid, appsecret, claimId,userId);
			if (findGoodsClaimTemplate != null) {
				Integer code = (Integer) findGoodsClaimTemplate.get("code");
				if (code == -1) {
					System.out.println("-1-授权失败");
					map.put("code", -1);
					return map;
				} else if (code == 0) {
					GoodsClaimTemplateData goodsClaimTemplateData = (GoodsClaimTemplateData) findGoodsClaimTemplate.get("data");
					/**
					 * 获取accesstoken
					 */
					AccessToken appletAccessToken = wechatDataServer.getAppletAccessToken(WechatConfig.APPID, WechatConfig.APPSECRET);
					String access_token = appletAccessToken.getAccess_token();
					
					String requestUrl = WechatSDK.APPLET_TEMPLATE_NEWS.replace("ACCESS_TOKEN", access_token);
					JSONObject fromObject = JSONObject.fromObject(goodsClaimTemplateData);
					JSONObject httpsRequestReJson = HttpsRequestUtils.httpsRequestReJson(requestUrl, "POST", fromObject.toString());
					
					String errorCode = null;
					if (null != httpsRequestReJson) {
						errorCode = httpsRequestReJson.getString("errcode");
						if (errorCode.equals("0")) {
							map.put("code", 0);
							return map;
						} else {
							map.put("code", 4);
							System.out.println("参数失效");
							return map;
						}
					}
					map.put("code", 3);
					System.out.println("3-发送失败");
					return map;
				} else if (code == 1) {
					System.out.println("1-参数格式不正确");
					map.put("code", 1);
					return map;
				} else if (code == 2) {
					System.out.println("2-模版数据不存在");
					map.put("code", 2);
					return map;
				} else if (code == 9) {
					String errorMsg = (String) findGoodsClaimTemplate.get("errorMsg");
					System.out.println();
					map.put("errorMsg", errorMsg);
					map.put("code", 9);
					return map;
				}
			} 
			return map;
		} catch (Exception e) {
			map.put("code", 9);
			map.put("errMsg", e);
			return map;
		}
		
	}

}
