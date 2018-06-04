package com.xul.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang.StringUtils;

public class Server {

	@SuppressWarnings("unused")
	private static String ip = "127.0.0.1";
	private static int port = 8899;
	private static Map<String, Socket> clientMap = new HashMap<String, Socket>();
	private static Map<String, Task> clientThreadMap = new HashMap<String, Task>();
	private static int msgNO = 1;

	public static void main(String[] args) throws IOException {
		// 假的定时器，模拟学生行为，给所有的客户端发送学生事件。
		setTimeTaskMsg();

		// 为了简单起见，所有的异常信息都往外抛
		// int port = 8899;
		// 定义一个ServerSocket监听在端口8899上
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(port);
		while (true) {
			Socket socket = server.accept();

			Task t = new Task(socket);
			new Thread(t).start();

			clientMap.put(socket.toString(), socket);
			clientThreadMap.put(socket.toString(), t);

			System.out.println();
			System.out.println("server.accept new client socket.");
			System.out.println(socket.toString());
			System.out.println(clientMap.keySet());

		}

	}

	/**
	 * 定时任务，模拟发送学生事件和心跳。
	 */
	static void setTimeTaskMsg() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			public void run() {
				if (msgNO % 3 == 0) {
					// 两次心跳，一次事件
					MessageBean msg = new MessageBean();
					msg.setName(MessageConstant.NAME_EVENT);
					msg.setNo(String.valueOf(msgNO));

					msg.setContent("NULL");
					msg.setType(MessageConstant.TYPE_RSP);
					msg.setEventName(MessageConstant.EVENTNAME_EAT);
					msg.setEventName("学生吃饭了");
					msg.setEventNo("001");
					msg.setMobilePhone("1383838438");

					System.out.println();
					System.out.println("server sendMsg.  str =\n" + MessageXmlUtil.toXml(msg) + "\n");

					for (String key : clientThreadMap.keySet()) {
						Task tTemp = clientThreadMap.get(key);
						tTemp.sendMsg(MessageXmlUtil.toXml(msg));
					}
				} else {
					// 两次心跳，一次事件
					MessageBean msg = new MessageBean();
					msg.setName(MessageConstant.NAME_ACTIVE);
					msg.setNo(String.valueOf(msgNO));

					msg.setContent("NULL");
					msg.setType(MessageConstant.TYPE_RSP);

					System.out.println();
					System.out.println("server sendMsg.  str =\n" + MessageXmlUtil.toXml(msg) + "\n");
					for (String key : clientThreadMap.keySet()) {
						Task tTemp = clientThreadMap.get(key);
						tTemp.sendMsg(MessageXmlUtil.toXml(msg));
					}
				}

				msgNO++;
			}
		}, 0, 30 * 1000);
	}

	static class Task implements Runnable {
		private Socket socket;

		public Task(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {

				handleSocket();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * 跟客户端Socket进行通信
		 * 
		 * @throws Exception
		 */
		private void handleSocket() throws Exception {
			System.out.println();
			System.out.println("server handleSocket");
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String temp;
			@SuppressWarnings("unused")
			int index;
			while ((temp = br.readLine()) != null) {
				if (isEndMsg(temp)) {
					// 一直等待处理客户端结果。
					doRes(sb.toString());
					sb = new StringBuffer();
				} else {
					sb.append(temp);
				}
			}

			// 到这里就说明客户端断开了。
			clientMap.remove(socket.toString());
			clientThreadMap.remove(socket.toString());

			System.out.println();
			System.out.println("socket disconnected");
			System.out.println(clientMap.keySet());
		}

		private void sendMsg(String str) {
			Writer writer;
			try {
				writer = new OutputStreamWriter(socket.getOutputStream());
				str = str + "\n";
				writer.write(str);
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		private void doRes(String str) {
			System.out.println();
			System.out.println("server doRes, 处理客户端的请求。 如果为心跳不处理，如果为认证请求，则验证密码是否合法等等。 str=\n" + str);
		}

		/**
		 * 判断是否为 message的结束标识
		 * 
		 * @param str
		 * @return
		 */
		private static boolean isEndMsg(String str) {
			if (StringUtils.isNotBlank(str) && str.endsWith("</MESSAGE>")) {
				return true;
			} else {
				return false;
			}
		}

	}
}
