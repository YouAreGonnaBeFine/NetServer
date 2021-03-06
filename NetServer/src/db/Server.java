package db;

import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

	private LinkInfo link;

	public Server(LinkInfo link) {
		this.link = link;
		start();
	}

	public void run() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(link.getSerPort());
			System.out.println("System Online!");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
			try {
				Socket remoteSocket = server.accept();
				System.out.println("remoteSocket accpet!");
				Socket localSocket = server.accept();
				System.out.println("localSocket  accpet!");
				remoteSocket.setSoTimeout(0);
				localSocket.setSoTimeout(0);
				
				remoteSocket.setTcpNoDelay(true);
				localSocket.setTcpNoDelay(true);

				new TransferDown(remoteSocket, localSocket,"ToCZ");
				new TransferUp(remoteSocket, localSocket,"ToYonYou");

			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}