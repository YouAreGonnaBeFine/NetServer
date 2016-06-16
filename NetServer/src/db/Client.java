package db;

import java.net.Socket;

public class Client extends Thread {

	private LinkInfo link;

	public Client(LinkInfo link) {
		this.link = link;
		start();
	}

	public void run() {
		Socket clientSocketToServer = null;
		Socket clientSocketToDb = null;
		try {
			clientSocketToServer = new Socket(link.getSerIP(), link.getSerPort());
			System.out.println("SerLink init complete!");
			clientSocketToDb = new Socket(link.getDbIP(), link.getDbPort());
			System.out.println("DbLink  init complete!");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

			try {
				clientSocketToServer.setSoTimeout(0);
				clientSocketToDb.setSoTimeout(0);
				clientSocketToServer.setTcpNoDelay(true);
				clientSocketToDb.setTcpNoDelay(true);
				new TransferUp(clientSocketToServer, clientSocketToDb,"ToDb");
				new TransferDown(clientSocketToServer, clientSocketToDb,"ToSer");

			} catch (Exception e) {
				e.printStackTrace();	
			}
		
	}

}