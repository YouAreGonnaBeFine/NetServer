package ora;

import java.net.Socket;

public class Client extends Thread {

	private Route route;

	public Client(Route route) {
		this.route = route;
		start();
	}

	public void run() {
		Socket client1 = null;
		Socket client2 = null;
		try {
			client1 = new Socket(route.getLocalIP(), route.getLocalPort());
			System.out.println("client1 set up!");
			client2 = new Socket(route.getDestHost(), route.getDestPort());
			System.out.println("client2 set up!");
		} catch (Exception e) {
			return;
		}

			try {
				new Transfer(client1, client2);
				new Transfer2(client1, client2);

			} catch (Exception e) {
			}
		
	}

}
