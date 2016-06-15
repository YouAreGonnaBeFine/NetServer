package ol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	Socket socket;
	InputStream inStream;
	OutputStream outStream;

	public static void main(String args[]) {
		Client client = new Client();
		client.conn();
	}

	public Client() {
		try {
			System.out.println("-------------客户端-----------------");
			socket = new Socket("127.0.0.1", 8090);
			inStream = socket.getInputStream();
			outStream = socket.getOutputStream();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void conn() {
		new Thread() {
			public void run() {
				try {
					// ---first write out---
					byte[] outArr1 = new byte[100];
					System.in.read(outArr1);
					outStream.write(outArr1);

					InputStreamReader inReader = new InputStreamReader(inStream);
					BufferedReader bReader = new BufferedReader(inReader);
					String inMessage = bReader.readLine();

					while (inMessage != "exit") {
						System.out.println("message：" + inMessage);
						byte[] outArr = new byte[100];
						System.in.read(outArr);
						outStream.write(outArr);
						// System.out.println("u say ---> " + new
						// String(outArr));

						inMessage = bReader.readLine();
						inMessage = inMessage.trim();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();

	}

}