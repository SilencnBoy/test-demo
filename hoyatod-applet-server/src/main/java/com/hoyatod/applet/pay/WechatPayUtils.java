package com.hoyatod.applet.pay;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.hoyatod.applet.util.MD5Util;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class WechatPayUtils {
	
	/**
	 * 	@Description：签名计算
	 *  @param map 签名所需要的参数
	 *  @param api_key
	 *  @return String
	 */
	public String createSign(SortedMap<Object,Object> map,String api_key) {
		StringBuffer sb = new StringBuffer();
		Set es = map.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + api_key);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		return sign;
	}
	
	/**
	 * 	@Description：商户订单号
	 *  @return String 返回一个纯数字的24位字符串
	 */
	public String getOut_trade_no() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		char start = '0';
		char end = '9';
		Random rnd = new Random();
		int count = 7;
		char[] result = new char[count];
		int len = end - start + 1;
		while (count-- > 0) {
			result[count] = (char) (rnd.nextInt(len) + start);
		}
		return df.format(new Date()) + new String(result);
	}
	
	/**
	 * 	@Description：生成时间戳
	 *  @return String 
	 */
	public String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
	
	/**
	 * 	@Description：获取预支付ID时 获取随机码 微信支付API接口协议中包含字段nonce_str,主要保证签名不可预测。调用随机数函数生成，将得到的值转换为字符串.
	 *  @return  String
	 */
	public String create_nonce_str() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String res = "";
		for (int i = 0; i < 16; i++) {
			Random rd = new Random();
			res += chars.charAt(rd.nextInt(chars.length() - 1));
		}
		return res;
	}
	
	/**
	 * 	@Description：map转成xml
	 *  @param map
	 *  @return  String
	 */
	public String MapToXml(SortedMap<Object,Object> map) {
		String xml = "<xml>";
		Iterator<Entry<Object, Object>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Object, Object> entry = iter.next();
			String key = (String) entry.getKey();
			String val = (String) entry.getValue();
			xml += "<" + key + ">" + val + "</" + key + ">";
		}
		xml += "</xml>";
		return xml;
	}
	
	/**
	 * 	@Description：xml字符串转成map
	 *  @param xml
	 *  @return SortedMap<Object,Object>
	 */
	public SortedMap<Object,Object> doXMLParse(String xml) {
		SortedMap<Object,Object> map = new TreeMap<Object,Object>(); 
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节点
			List<Element> list = rootElt.elements();// 获取根节点下所有节点
			for (Element element : list) { // 遍历节点
				map.put(element.getName(), element.getText()); // 节点的name为map的key，text为map的value
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * @Description：返回给微信的参数
	 * @param return_code 返回编码
	 * @param return_msg  返回信息
	 */
	public String setXML(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg+ "]]></return_msg></xml>";
	}
	
	/**
	 * 获得随机字符串
	 */
	public String getNonceStr() {
		Random random = new Random();
		return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
	}
}
