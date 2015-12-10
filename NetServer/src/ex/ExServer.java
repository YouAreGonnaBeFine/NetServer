package ex;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ExServer {


	private ServerSocket ss;

	public static final int port = 8081;

	public ExServer() {

		try {

			ss = new ServerSocket(port);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void setConnection() throws IOException {
		Socket s;
		OutputStream os;
		try { 

			s = ss.accept();
			os = s.getOutputStream();
			os.write("miss my world".getBytes());
			os.close();
			s.close();
			System.out.println(s.getInetAddress()+"connected !");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {

		ExServer ms = new ExServer();
		ms.setConnection();

	}
}
