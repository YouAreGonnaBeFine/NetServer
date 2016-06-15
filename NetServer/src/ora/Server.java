package ora;

import java.net.Inet4Address;
import java.net.InetAddress;
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
			InetAddress ip = Inet4Address.getByName(route.getLocalIP());
			server = new ServerSocket(route.getLocalPort(), 4, ip);
			System.out.println("System Online!");
		} catch (Exception e) {
			return;
		}
		while (true) {
			try {
				Socket sock1 = server.accept();
				System.out.println("socket1 accpet!");
				Socket sock2 = server.accept();
				System.out.println("socket2 accpet!");
				sock1.setSoTimeout(0);
				sock2.setSoTimeout(0);

				new Transfer(sock1, sock2);

			} catch (Exception e) {
			}
		}
	}

}
