package ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ExClient {
	public static final String IP = "127.0.0.1";
//	public static final String IP = "115.159.152.136";	
	public static final int port = 8081;
	private Socket s;

	public ExClient() throws IOException {
		try {
			s = new Socket(IP, port);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void setConnection() throws IOException {

		InputStream is;

		try {
			is = s.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			System.out.println(br.readLine());

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void main(String args[]) throws IOException {

		ExClient mc = new ExClient();
		mc.setConnection();

	}

}
