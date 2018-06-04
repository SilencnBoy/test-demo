package com.hoyatod.applet.service;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoyatod.applet.config.WechatConfig;
import com.hoyatod.applet.config.WechatSDK;
import com.hoyatod.applet.pay.PayCheckUtil;
import com.hoyatod.applet.pay.WechatPayUtils;
import com.hoyatod.applet.util.HttpsRequestUtils;
import com.hoyatod.appletclient.interfaces.IWechatDataInterface;
import com.hoyatod.appletclient.interfaces.IWechatPayInterface;
import com.hoyatod.appletclient.protocol.AccessToken;
import com.hoyatod.appletclient.protocol.WechatAppletRequestData;
import com.hoyatod.appletclient.protocol.WechatData;
import com.hoyatod.appletclient.protocol.WechatPayResponseData;
import com.hoyatod.appletclient.protocol.WechatScanCodeRequestData;
import com.hoyatod.appletclient.protocol.WechatUserInfo;

@Service("WechatPayServer")
@SuppressWarnings("unused")
public class WechatPayServer implements IWechatPayInterface{
	
	private static Logger log = Logger.getLogger(WechatDataServer.class);
	
	@Autowired
	private WechatPayUtils wechatUtil;
	
	@Autowired
	private IWechatDataInterface wechatDao;

	@Override
	public Map<String, Object> wechatAppletUnifiedOrder(WechatAppletRequestData wechatAppletRequestData) {
		Map<String, Object> maps = new HashMap<String,Object>();
		try {
			String openid = null;
			if(wechatAppletRequestData == null){
				maps.put("returnState", 2);
				return maps;
			}
			String code = wechatAppletRequestData.getCode();
			WechatUserInfo appletUserInfo = wechatDao.getAppletUserInfo(WechatConfig.APPID, WechatConfig.APPSECRET, code);
			if(appletUserInfo != null){
				openid = appletUserInfo.getOpenid();
			}else{
				maps.put("returnState", -1);
				return maps;
			}
			
			String body = wechatAppletRequestData.getBody();
			String attach = wechatAppletRequestData.getAttach();
			Integer total_fee = wechatAppletRequestData.getTotal_fee();
			String spbill_create_ip = wechatAppletRequestData.getSpbill_create_ip();
			
			if(PayCheckUtil.isNull(body) && body.trim().length() < 128){
				maps.put("returnState", 4);
				return maps;
			}
			if(PayCheckUtil.isNull(attach) && attach.trim().length() < 127){
				maps.put("returnState", 4);
				return maps;
			}
			
			if(PayCheckUtil.isNull(spbill_create_ip)){
				maps.put("returnState", 4);
				return maps;
			}
			
			if(total_fee == null){
				maps.put("returnState", 4);
				return maps;
			}
			
			if(total_fee != null && total_fee != WechatConfig.TOTAL_FEE){
				maps.put("returnState", 5);
				return maps;
			}
			
			SortedMap<Object, Object> map = new TreeMap<Object, Object>();
			map.put("appid", WechatConfig.APPID);
			map.put("mch_id", WechatConfig.MCH_ID);
			map.put("nonce_str", wechatUtil.getNonceStr()); 
			map.put("sign_type", "MD5"); 
			map.put("body", body);
			map.put("attach",attach);
			map.put("out_trade_no", wechatUtil.getOut_trade_no()); 
			map.put("fee_type", "CNY"); 
			map.put("total_fee", total_fee); 
			map.put("spbill_create_ip", spbill_create_ip); 
			map.put("notify_url", WechatConfig.NOTIFY_URL);
			map.put("trade_type", "JSAPI");
			map.put("openid", openid);
			String createSign = wechatUtil.createSign(map, WechatConfig.API_KEY);
			map.put("sign", createSign);
			String mapToXml = wechatUtil.MapToXml(map);
			String RequestResult = HttpsRequestUtils.httpsRequestReStr(WechatSDK.WECHAT_ORDER_URL, "POST", mapToXml);
			if(RequestResult.indexOf("SUCCESS") != -1){
				SortedMap<Object, Object> doXMLMap = wechatUtil.doXMLParse(RequestResult);
				String return_code = (String) doXMLMap.get("return_code");
				
				if(return_code != null && return_code.equals("SUCCESS")){
					WechatPayResponseData wechatPayResponseData = new WechatPayResponseData();
					String result_code = (String) doXMLMap.get("result_code");
					
					if(result_code !=null && result_code.equals("SUCCESS")){
						SortedMap<Object, Object> payMap = new TreeMap<Object, Object>();
						
						String timeStamp = wechatUtil.create_timestamp();
					    payMap.put("timeStamp", timeStamp);
					    wechatPayResponseData.setTimeStamp(timeStamp);
					    
					    String nonceStr = (String) doXMLMap.get("nonceStr");
					    payMap.put("nonceStr",nonceStr);
					    wechatPayResponseData.setNonce_str(nonceStr);
					    
					    String prepay_id = "prepay_id=" + doXMLMap.get("prepay_id");
					    payMap.put("package", prepay_id);
					    wechatPayResponseData.setPrepay_id(prepay_id); 
					    
					    String signType = "MD5";
					    payMap.put("signType", signType);
					    wechatPayResponseData.setSignType(signType);
					    
					    String appid = (String) doXMLMap.get("appid");
					    payMap.put("appId", appid);
					    wechatPayResponseData.setAppid(appid);
					    
					    String paySign = wechatUtil.createSign(payMap, WechatConfig.API_KEY);
					    payMap.put("paySign", paySign);
					    wechatPayResponseData.setSign(paySign);
					    
					    maps.put("wechatPayResponseData", wechatPayResponseData);
					    maps.put("returnState", 0);
					    return maps;
					}
					maps.put("returnState", 1);
					return maps;
				}
				maps.put("returnState", 2);
				return maps;
			}
			maps.put("returnState", 3);
			return maps;
		} catch (Exception e) {
			log.error("系统异常", e);
			maps.put("returnState", 9);
			return maps;
		}
	}

	@Override
	public Map<String, Object> wechatScanCodeUnifiedOrder(WechatScanCodeRequestData wechatScanCodeRequestData) {
		Map<String, Object> maps = new HashMap<String,Object>();
		try {
			if(wechatScanCodeRequestData == null){
				maps.put("returnState", 2);
				return maps;
			}
			String body = wechatScanCodeRequestData.getBody();
			String spbill_create_ip = wechatScanCodeRequestData.getSpbill_create_ip();
			Integer total_fee = wechatScanCodeRequestData.getTotal_fee();
			
			if(PayCheckUtil.isNull(body) && body.trim().length() < 128){
				maps.put("returnState", 4);
				return maps;
			}
			
			if(PayCheckUtil.isNull(spbill_create_ip)){
				maps.put("returnState", 4);
				return maps;
			}
			
			if(total_fee == null){
				maps.put("returnState", 4);
				return maps;
			}
			
			if(total_fee != null && total_fee != WechatConfig.TOTAL_FEE){
				maps.put("returnState", 5);
				return maps;
			}
			
			SortedMap<Object,Object> map = new TreeMap<Object,Object>(); 
			map.put("appid", WechatConfig.APP_ID);
			map.put("mch_id", WechatConfig.MCH_ID);
			map.put("nonce_str", wechatUtil.getNonceStr()); 
			map.put("sign_type", "MD5"); 
			map.put("body", body);
			map.put("out_trade_no", wechatUtil.getOut_trade_no()); 
			map.put("fee_type", "CNY");
			map.put("total_fee", total_fee); 
			map.put("spbill_create_ip", spbill_create_ip); 
			map.put("notify_url", WechatConfig.SCANCODE_NOTIFY_URL);
			map.put("trade_type", "NATIVE");
			String createSign = wechatUtil.createSign(map, WechatConfig.API_KEY);
			map.put("sign", createSign);
			String mapToXml = wechatUtil.MapToXml(map);
			String resultXml = HttpsRequestUtils.httpsRequestReStr(WechatSDK.WECHAT_ORDER_URL, "POST", mapToXml);
			if (resultXml.indexOf("SUCCESS") != -1) {
				SortedMap<Object, Object> payMap = wechatUtil.doXMLParse(resultXml);
				String return_code = (String) payMap.get("return_code");
				
				if (return_code != null && return_code.equals("SUCCESS")) {
					String result_code = (String) payMap.get("result_code");
					
					if (result_code != null && result_code.equals("SUCCESS")) {
						String code_url = (String) payMap.get("code_url");
						maps.put("returnState", 0);
						maps.put("code_url", code_url);
						return maps;
					}
					maps.put("returnState", 1);
					return maps;
				}
				maps.put("returnState", 2);
				return maps;
			}
			maps.put("returnState", 3);
			return maps;
		} catch (Exception e) {
			log.error("系统异常", e);
			maps.put("returnState", 9);
			return maps;
		}
	}

	@Override
	public Map<String, Object> wechatWebpageUnifiedOrder(WechatData wechatData) {
		Map<String, Object> maps = new HashMap<String,Object>();
		try {
			WechatUserInfo wechatUserInfo = null;
			if (wechatData == null) {
				maps.put("returnState", 2);
				return maps;
			}
			String code = wechatData.getCode();
			AccessToken accessToken = wechatDao.getAccessTokenByCode(WechatConfig.APP_ID, WechatConfig.APP_SECRET, code);
			if(accessToken != null && accessToken.getErrcode().equals("200")){
				String access_token = accessToken.getAccess_token();
				if (access_token != null) {
					wechatUserInfo = wechatDao.getWechatUserInfo(accessToken);
				}
			}
			if (wechatUserInfo == null || wechatUserInfo.getErrcode().equals("500")) {
				maps.put("returnState", -1);
				return maps;
			}
			String openid = wechatUserInfo.getOpenid();
			String attach = wechatData.getAttach();
			String body = wechatData.getBody();
			Integer total_fee = wechatData.getTotal_fee();
			String spbill_create_ip = wechatData.getSpbill_create_ip();
			
			if(PayCheckUtil.isNull(body) && body.trim().length() < 128){
				maps.put("returnState", 4);
				return maps;
			}
			
			if(PayCheckUtil.isNull(attach) && attach.trim().length() < 127){
				maps.put("returnState", 4);
				return maps;
			}
			
			if(PayCheckUtil.isNull(spbill_create_ip)){
				maps.put("returnState", 4);
				return maps;
			}
			
			if(total_fee == null){
				maps.put("returnState", 4);
				return maps;
			}
			
			if(total_fee != null && total_fee != WechatConfig.TOTAL_FEE){
				maps.put("returnState", 5);
				return maps;
			}
			
			SortedMap<Object, Object> map = new TreeMap<Object, Object>();
			map.put("appid", WechatConfig.APPID);
			map.put("mch_id", WechatConfig.MCH_ID);
			map.put("attach",attach);
			map.put("device_info", "WEB"); 
			map.put("nonce_str", wechatUtil.getNonceStr());
			map.put("body", body);
			map.put("out_trade_no", wechatUtil.getOut_trade_no()); 
		    map.put("total_fee", total_fee); 
		    map.put("spbill_create_ip", spbill_create_ip); 
		    map.put("trade_type", "JSAPI");
		    map.put("notify_url", WechatConfig.NOTIFY_URL);
		    map.put("openid", openid);
		    String createSign = wechatUtil.createSign(map, WechatConfig.API_KEY);
		    map.put("sign", createSign); 
		    String mapToXml = wechatUtil.MapToXml(map);
			String RequestResult = HttpsRequestUtils.httpsRequestReStr(WechatSDK.WECHAT_ORDER_URL, "POST", mapToXml);
			
			if(RequestResult.indexOf("SUCCESS") != -1){
				SortedMap<Object, Object> doXMLMap = wechatUtil.doXMLParse(RequestResult);
				String return_code = (String) doXMLMap.get("return_code");
				
				if(return_code != null && return_code.equals("SUCCESS")){
					WechatPayResponseData wechatPayResponseData = new WechatPayResponseData();
					String result_code = (String) doXMLMap.get("result_code");
					
					if(result_code !=null && result_code.equals("SUCCESS")){
						WechatData wechatDatas = new WechatData();
						String trade_type = (String) doXMLMap.get("trade_type");
						wechatDatas.setTrade_type(trade_type);
						String prepay_id = (String) doXMLMap.get("prepay_id");
						wechatDatas.setPrepay_id("prepay_id="+prepay_id);
						String mweb_url = (String) doXMLMap.get("mweb_url");
						wechatDatas.setMweb_url(mweb_url);
						maps.put("returnState", 0);
						maps.put("wechatData", wechatDatas);
					}
					maps.put("returnState", 1);
					return maps;
				}
				maps.put("returnState", 2);
				return maps;
			}
			maps.put("returnState", 3);
			return maps;
		} catch (Exception e) {
			log.error("系统异常", e);
			maps.put("returnState", 9);
			return maps;
		}
		
	}

}
