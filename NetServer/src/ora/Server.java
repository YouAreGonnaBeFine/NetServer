package ora;

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
			return;
		}
			try {
				Socket remoteSocket = server.accept();
				System.out.println("remoteSocket accpet!");
				Socket localSocket = server.accept();
				System.out.println("localSocket  accpet!");
				remoteSocket.setSoTimeout(0);
				localSocket.setSoTimeout(0);

				new TransferUp(remoteSocket, localSocket);
				new TransferDown(remoteSocket, localSocket);

			} catch (Exception e) {
			}
	}

}
