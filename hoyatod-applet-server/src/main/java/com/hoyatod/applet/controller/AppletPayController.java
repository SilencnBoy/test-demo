package com.hoyatod.applet.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hoyatod.applet.config.WechatConfig;
import com.hoyatod.applet.pay.WechatPayUtils;
import com.hoyatod.member.client.interfaces.FinanceInterface;
import com.hoyatod.xmktrade.client.interfaces.TradeInterface;
import com.hoyatod.xmktrade.client.protocol.OrderData;
import com.hoyatod.xmktrade.client.protocol.SearchOrderData;
import com.hoyatod.xmktrade.client.protocol.SearchOrderRespData;
import com.hoyatod.xmktrade.client.protocol.wechat.WechatOrderData;

/**
 * TODO 微信支付回调
 * 
 * @author xul
 *
 */
@Controller
public class AppletPayController {
	
	private static Logger log = Logger.getLogger(AppletPayController.class);
	
	@Autowired
	private FinanceInterface PCFinanceDao;
	
	@Autowired
	private TradeInterface tradeDao;
	
	@Autowired
	private WechatPayUtils wechatUtil;
	
	/**
	 * pc回调函数
	 * 
	 * @param request response
	 * @return code_url   支付链接
	 */
	@RequestMapping(value = "/sweepCallBack", method = RequestMethod.POST)
	public void sweepCallBack(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/xml;charset=UTF-8");
			InputStream is = request.getInputStream();
			String result;
			StringBuffer sb = new StringBuffer();
			BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			while ((result = in.readLine()) != null) {
				sb.append(result);
			}
			if ("".equals(sb.toString())) {
				System.out.println("参数错误!");
				response.getWriter().write(wechatUtil.setXML("FAIL", "参数错误!"));
				return;
			} 
			SortedMap<Object, Object> map = wechatUtil.doXMLParse(sb.toString());
            String out_trade_no = (String) map.get("out_trade_no");
            String return_code = (String) map.get("return_code");
            String total_fee = (String) map.get("total_fee");
            String transaction_id = (String) map.get("transaction_id");
            System.out.println("回调中处理:*****支付成功订单号:"+out_trade_no);
           	 if(return_code.equals("SUCCESS")){
				// 业务逻辑(先判断数据库中订单号是否存在，并且订单状态为未支付状态)
           		System.out.println("runing");
           		Map<String, Object> addPayRecordFromXMK = PCFinanceDao.addPayRecordFromXMK("hoyatod", "hoyatod", 1, Integer.parseInt(total_fee), out_trade_no, transaction_id);
           		if(addPayRecordFromXMK != null){
           			Integer code = (Integer)addPayRecordFromXMK.get("code");//code	结果代码，不会为null。
           			System.out.println("**********" +code + "**********");
           			if(code == -1){//-1-授权失败
						response.getWriter().flush();
						response.getWriter().close();
						response.getWriter().write(wechatUtil.setXML("FAIL", "交易失败"));
						return;
           			}else if(code == 0){//0-新增成功
           				//查询订单接口
						 SearchOrderData searchData = new SearchOrderData();
						 searchData.setOrderNo(out_trade_no);
						 SearchOrderRespData listOrder = tradeDao.listOrder("hoyatod", "hoyatod",searchData);
						 System.out.println("查询订单个数:"+listOrder.getTotalCount());
						if(listOrder != null){
							 List<OrderData> orderDatas = listOrder.getOrderData();
							 if(orderDatas != null && orderDatas.size() > 0){
								 WechatOrderData orderData = (WechatOrderData) orderDatas.get(0);
								 orderData.setOrderStatus((byte)5);
								 int updateOrder = tradeDao.updateOrder("hoyatod", "hoyatod",orderData);
								 System.out.println("****************"+updateOrder+"****************");
								 if(updateOrder == 0){
									 System.out.println("订单更新成功。。。");
									 response.getWriter().write(wechatUtil.setXML("SUCCESS","OK"));
								 }else {
									  System.out.println("订单更新失败。。。");
									  response.getWriter().write(wechatUtil.setXML("FAIL","交易失败"));
								}
							 }
						 }
           				response.getWriter().write(wechatUtil.setXML("SUCCESS","OK"));
           			}else if(code == 1){//1-参数缺失
           				System.out.println("参数缺失");
           				response.getWriter().write(wechatUtil.setXML("FAIL","交易失败"));
           			}else if(code == 2){//2-订单不存在
           				System.out.println("订单不存在");
           				response.getWriter().write(wechatUtil.setXML("FAIL","交易失败"));
           			}else if(code == 3){//3-传参type值不正确
           				System.out.println("传参type值不正确");
           				response.getWriter().write(wechatUtil.setXML("FAIL","交易失败"));
           			}else if(code == 9){//9-系统异常 errorMsg	异常信息。当code=9时才返回的信息，可能为空字符串，code!=9时为null
           				String errorMsg = (String)addPayRecordFromXMK.get("errorMsg");
           				System.out.println(errorMsg);
           				response.getWriter().write(wechatUtil.setXML("FAIL","交易失败"));
           			}
           		}
				response.getWriter().write(wechatUtil.setXML("SUCCESS", "ok"));// 通知微信.异步确认成功.必写.不然会一直通知后台.五次之后就认为交易失败了.
           	 } 
			response.getWriter().flush();
			response.getWriter().close();
			return;
		} catch (IOException e) {
			log.info("系统异常", e);
		}
	}
	
	//小程序支付回调
	@RequestMapping(value = "/smallCallBack", method=RequestMethod.POST)
	public void smallCallBack(HttpServletRequest request, HttpServletResponse response){
		try {
			response.setContentType("text/xml;charset=UTF-8");
			InputStream is = request.getInputStream();
			String result;
			StringBuffer sb = new StringBuffer();
			BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			while ((result = in.readLine()) != null) {
				sb.append(result);
			}
			if("".equals(sb.toString())){
                response.getWriter().write(wechatUtil.setXML("FAIL", "参数错误!"));
                log.error("参数错误!");
                return ;
            }
			System.out.println("sb:"+sb);
			SortedMap<Object, Object> map = wechatUtil.doXMLParse(sb.toString());
			String appid = (String) map.get("appid");
			String attach =(String) map.get("attach");
			String bank_type =(String) map.get("bank_type");
			String cash_fee =(String) map.get("cash_fee");
			String fee_type =(String) map.get("fee_type");
			String is_subscribe =(String) map.get("is_subscribe");
			String mch_id =(String) map.get("mch_id");
      		String nonce_str = (String) map.get("nonce_str");
      		String openid = (String) map.get("openid");
            String out_trade_no = (String) map.get("out_trade_no");
            String result_code = (String) map.get("result_code");
            String return_code = (String) map.get("return_code");
            String sign = (String) map.get("sign");
            String time_end = (String) map.get("time_end");
            String total_fee = (String) map.get("total_fee");
            String trade_type = (String) map.get("trade_type");
            String transaction_id = (String) map.get("transaction_id");
            log.info("支付成功订单号:"+out_trade_no);
            //签名验证
            SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
            parameters.put("appid",appid);
            parameters.put("attach",attach);
            parameters.put("bank_type",bank_type);
            parameters.put("cash_fee",cash_fee);
            parameters.put("fee_type",fee_type);
            parameters.put("is_subscribe",is_subscribe);
            parameters.put("mch_id",mch_id);
            parameters.put("nonce_str",nonce_str);
            parameters.put("openid",openid);
            parameters.put("out_trade_no",out_trade_no);
            parameters.put("return_code",return_code);
            parameters.put("result_code",result_code);
            parameters.put("time_end",time_end);
            parameters.put("total_fee",total_fee);
            parameters.put("trade_type",trade_type);
            parameters.put("transaction_id",transaction_id);
            String createSign = wechatUtil.createSign(parameters,WechatConfig.API_KEY);
            log.info("第一次签名:"+sign);
            log.info("第二次签名:"+createSign);
            if(return_code.equals("SUCCESS") && result_code.equals("SUCCESS")){
				// 业务逻辑(先判断数据库中订单号是否存在，并且订单状态为未支付状态)
            	log.info("SUCCESS");
								
            	 response.getWriter().write(wechatUtil.setXML("SUCCESS","OK"));
             }else{
            	 log.info("签名校验失败!");
				response.getWriter().write(wechatUtil.setXML("FAIL", "签名校验失败失败"));
             }
             response.getWriter().flush();
             response.getWriter().close();
             return ;
		} catch (IOException e) {
			log.error("系统异常", e);
			e.printStackTrace();
		}
	
	}
}
