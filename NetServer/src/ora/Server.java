package ora;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {

	private LinkInfo link;

	private List<Socket> localServerPool = new ArrayList<Socket>();

	private List<Socket> remoteServerPool = new ArrayList<Socket>();

	public Server(LinkInfo link) {
		this.link = link;
		start();
	}

	public void run() {
		ServerSocket localServer = null;
		ServerSocket remoteServer = null;
		try {
			localServer = new ServerSocket(link.getSerPort());
			remoteServer = new ServerSocket(8888);
			System.out.println("System Online!");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		for (int i = 0; i < LinkInfo.poolSize; i++) {
			try {
				Socket remoteSocket = remoteServer.accept();
//				System.out.println("remoteSocket accpet!");
				remoteSocket.setSoTimeout(0);
				remoteServerPool.add(remoteSocket);
				System.out.println("服务端初始化远程连接："+i);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < LinkInfo.poolSize; i++){
			try {
				Socket localSocket = localServer.accept();
//				System.out.println("localSocket accpet!");
				localSocket.setSoTimeout(0);
				localServerPool.add(localSocket);
				System.out.println("服务端开启本地连接："+i);
				
				new TransferDown(remoteServerPool.get(i), localSocket,"ToCZ "+i);
				new TransferUp(remoteServerPool.get(i), localSocket,"ToYonYou "+i);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}