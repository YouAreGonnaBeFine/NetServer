package ora;

import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

	private Route route;

	public Server(Route route) {
		this.route = route;
		start();
	}

	public void run() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(route.getLocalPort());
			System.out.println("System Online!");
		} catch (Exception e) {
			return;
		}
			try {
				Socket sock1 = server.accept();
				System.out.println("socket1 accpet!");
				Socket sock2 = server.accept();
				System.out.println("socket2 accpet!");
				sock1.setSoTimeout(0);
				sock2.setSoTimeout(0);

				new Transfer(sock1, sock2);
				new Transfer2(sock1, sock2);

			} catch (Exception e) {
			}
	}

}
