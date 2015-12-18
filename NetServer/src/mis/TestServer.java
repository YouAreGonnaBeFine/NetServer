package mis;

import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
	
	public static void main(String[] args) throws Exception {
		

		
		
		ServerSocket clientServer = new ServerSocket(8082);
		Socket forClient = clientServer.accept();
		InputStream toServerInStream = forClient.getInputStream();
		OutputStream toServerOutStream = forClient.getOutputStream();	
		System.out.println("服务器");
		Socket toDb = new Socket("10.11.112.48", 1521);
		InputStream toDbInStream = toDb.getInputStream();
		OutputStream toDbOutStream = toDb.getOutputStream();		
		System.out.println("客户端");
		new Transfer().pipe(toDbInStream,toServerInStream,toServerOutStream,toDbOutStream);
		
		
	}

	

}
