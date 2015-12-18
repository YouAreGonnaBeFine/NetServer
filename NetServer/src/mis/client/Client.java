package mis.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.Socket;

import lcc.SysLog;

public class Client {

	public static void main(String[] args) throws Exception {

		Socket toDb = new Socket("10.11.112.48", 1521);
		Socket toServer = new Socket("127.0.0.1", 8081);
		
		InputStream toDbInStream = toDb.getInputStream();
		OutputStream toDbOutStream = toDb.getOutputStream();		
		
		
		InputStream toServerInStream = toServer.getInputStream();
		OutputStream toServerOutStream = toServer.getOutputStream();	
		
		System.out.println("客户端初始化完成");
		
		pipe(toDbInStream,toServerInStream,toServerOutStream,toDbOutStream);
		
		
		
		
		
		
		
	}
	
	private static void pipe(InputStream is0, InputStream is1, OutputStream os0,OutputStream os1) {
		try {
			int ir;
			byte bytes[] = new byte[1024];
			while (true) {
				try {
					if ((ir = is0.read(bytes)) > 0) {
						os0.write(bytes, 0, ir);
					} else if (ir < 0) {
						break;
					}
				} catch (InterruptedIOException e) {
				}
				try {
					if ((ir = is1.read(bytes)) > 0) {
						os1.write(bytes, 0, ir);
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
