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

	 public static void main(String args[]){
		Client client = new Client();
		client.conn();
	}

	public Client() {
		try {
			System.out.println("-------------This is client-----------------");
			socket = new Socket("115.159.152.136", 8090);
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

					System.out
							.println("-------------got server response-----------------");

					while (inMessage != "exit") {
						System.out.println("h say <-- " + inMessage);
						byte[] outArr = new byte[100];
						System.in.read(outArr);
						outStream.write(outArr);
						System.out.println("u say ---> " + new String(outArr));

						inMessage = bReader.readLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();

	}

}