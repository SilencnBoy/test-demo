package com.xul.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.lang.StringUtils;



public class Client {

	private static Socket socket = null;
	private static String ip = "127.0.0.1";
	private static int port = 8899;
	
	
	
	public static Socket init() {
		try {
			socket = new Socket(ip, port);
			socket.setSoTimeout(MessageConstant.time_timeout * 1000);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return socket;
	}
	
	public static void sendMsg(String str){
		Writer writer;
		try {
			writer = new OutputStreamWriter(socket.getOutputStream());
			str = str + "\n";
			System.out.println("client sendMsg=\n" +  str);
			writer.write(str);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void handleSocket() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String temp;
		@SuppressWarnings("unused")
		int index;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
			if(isEndMsg(temp)){
				//处理结果。 如果为心跳，打印，如果为事件，则入库。记录同学轨迹
				doRes(sb.toString());
				sb = new StringBuffer();
			}
		}
		
		//一旦到这里，说明socket断开了，抛异常。 重新连接
	}
	
	public static void doRes(String str) {
		System.out.println("client doRes, 处理结果。  如果为心跳不处理， 事件的话，记录数据库。 str=\n" + str);
	}

	/**
	 * 判断是否为 message的结束标识
	 * @param str
	 * @return
	 */
	private static boolean isEndMsg(String str){
		if(StringUtils.isNotBlank(str) && str.endsWith("</MESSAGE>")){
			return true;
		}else{
			return false;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		MessageBean msg = new MessageBean();
		msg.setName(MessageConstant.NAME_ACTIVE);
		msg.setContent("NULL");
		msg.setType(MessageConstant.TYPE_REQ);
		
		Client.init();
		Client.sendMsg(MessageXmlUtil.toXml(msg));
		
		//一直等待处理服务器端的结果
		Client.handleSocket();
		
	}
}
