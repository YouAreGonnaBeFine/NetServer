package mis.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	

	public static void main(String[] args) throws Exception {
		
		
		ServerSocket dbServer = new ServerSocket(8081);
		ServerSocket clientServer = new ServerSocket(8082);
		
		
		Socket forDb = dbServer.accept();
		Socket forClient = clientServer.accept();
		
		InputStream toDbInStream = forDb.getInputStream();
		OutputStream toDbOutStream = forDb.getOutputStream();		
		
		InputStream toServerInStream = forClient.getInputStream();
		OutputStream toServerOutStream = forClient.getOutputStream();			
		
		System.out.println("服务端初始化完成");
		
		pipe(toDbInStream,toServerInStream,toServerOutStream,toDbOutStream);
		

	}
	
	private static void pipe(InputStream is0, InputStream is1, OutputStream os0,OutputStream os1) {
		try {
			int ir;
			byte bytes[] = new byte[1024];
			while (true) {

				try {
					if ((ir = is1.read(bytes)) > 0) {
						os1.write(bytes, 0, ir);
					} else if (ir < 0) {
						break;
					}
				} catch (InterruptedIOException e) {
				}
				
				try {
					if ((ir = is0.read(bytes)) > 0) {
						os0.write(bytes, 0, ir);
					} else if (ir < 0) {
						break;
					}
				} catch (InterruptedIOException e) {
				}				
			}
		} catch (Exception e0) {
		}
		
	}		

}
