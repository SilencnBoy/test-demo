package com.xul.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;

public class MyServer {

	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private static HashSet<PrintWriter> socketSet = new HashSet<PrintWriter>();

	public MyServer() throws IOException {
		try {
			System.out.println("服务器启动中.............");
			serverSocket = new ServerSocket(8899);
			while (true) {
				socket = serverSocket.accept();
				new CreateServerThread(socket);
			}
		} catch (IOException e) {
		} finally {
			serverSocket.close();
		}
	}

	// --- CreateServerThread
	class CreateServerThread extends Thread {
		private Socket client;
		private BufferedReader in;
		private PrintWriter out;

		public CreateServerThread(Socket s) throws IOException {
			client = s;
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream());
			socketSet.add(out);
			start();
		}

		public void run() {
			try {
				String line;
				while ((line = in.readLine()) != null) {
					distributeMsg(line);
				}
			} catch (IOException e) {
			} finally {
				try {
					in.close();
					out.close();
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void distributeMsg(String msg) {
		for (Iterator<PrintWriter> iterator = socketSet.iterator(); iterator.hasNext();) {
			PrintWriter pw = iterator.next();
			pw.println(msg);
			pw.flush();
		}
	}

	public static void main(String[] args) throws IOException {
		new MyServer();
	}
}
