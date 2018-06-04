package com.xul.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {

	private Socket socket;
	private BufferedReader sin;
	private BufferedReader in;
	private PrintWriter out;
	private String name;
	private Scanner scanner;

	public MyClient() {
		try {
			System.out.println("请为此客户端取一个名字：");
			scanner = new Scanner(System.in);
			name = scanner.next();
			socket = new Socket(InetAddress.getLocalHost(), 8899);
			sin = new BufferedReader(new InputStreamReader(System.in));
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			out.println(name + "进来了。");

			System.out.println("在下面输入:");

			while (true) {
				String line = sin.readLine();

				out.println(name + "说：" + line);
				out.flush();

				if ("exit".equals(line)) {
					out.println(name + "退出.");
					out.flush();
					break;
				}

				String serverMsg;
				while ((serverMsg = in.readLine()) != null) {
					System.out.println(serverMsg);
					break;
				}
			}

			System.out.println("客户端已退出。");
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
		}
	}

	public static void main(String[] args) {
		new MyClient();
	}
}
