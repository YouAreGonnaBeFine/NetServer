package ol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	ServerSocket ss;
	Socket serverSocket;
	InputStream inStream;
	OutputStream outStream;

	public Server() {
		try {
			System.out.println("====================服务端==================");
			ss = new ServerSocket(8090);
			serverSocket = ss.accept();
			System.out.println("收到连接请求");
			inStream = serverSocket.getInputStream();
			outStream = serverSocket.getOutputStream();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void conn() {
		new Thread() {
			public void run() {
				try {
					InputStreamReader iReader = new InputStreamReader(inStream);
					BufferedReader iBufferStream = new BufferedReader(iReader);
					String inMessage = iBufferStream.readLine();

					while (inMessage != "exit") {
						System.out.println("message：" + inMessage);
						// System.out.print(" u say --->>  ");
						byte[] outArr = new byte[100];
						System.in.read(outArr);
						outStream.write(outArr);
						// System.out.println("====== " + new
						// String(outArr).toString());

						inMessage = iBufferStream.readLine();
						inMessage = inMessage.trim();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public static void main(String args[]) {
		Server server = new Server();
		server.conn();
	}
}