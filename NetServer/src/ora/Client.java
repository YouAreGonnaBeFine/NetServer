package ora;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client extends Thread {

	private LinkInfo link;

	private List<Socket> serverClientPool = new ArrayList<>();

	private List<Socket> dbClientPool = new ArrayList<>();

	public Client(LinkInfo link) {
		this.link = link;
		start();
	}

	public void run() {
		Socket clientSocketToServer = null;
		Socket clientSocketToDb = null;
		try {

			for (int i = 0; i < LinkInfo.poolSize; i++) {

				clientSocketToServer = new Socket(link.getSerIP(),
						link.getSerPort());
				clientSocketToServer.setSoTimeout(0);
				clientSocketToServer.setTcpNoDelay(true);
//				System.out.println("SerLink init complete!");
				serverClientPool.add(clientSocketToServer);
				System.out.println("客户端初始化远程连接："+i);

			}

			for (int i = 0; i < LinkInfo.poolSize; i++) {

				clientSocketToDb = new Socket(link.getDbIP(), link.getDbPort());
				clientSocketToDb.setSoTimeout(0);
				clientSocketToDb.setTcpNoDelay(true);
//				System.out.println("DbLink  init complete!");
				dbClientPool.add(clientSocketToDb);
				System.out.println("客户端初始化数据库连接："+i);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		try {
			for (int i = 0; i < LinkInfo.poolSize; i++) {
				new TransferUp(serverClientPool.get(i), dbClientPool.get(i), "ToDb "+i);
				new TransferDown(serverClientPool.get(i), dbClientPool.get(i), "ToSer "+i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}