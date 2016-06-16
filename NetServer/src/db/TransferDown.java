package db;

import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.Socket;

public class TransferDown extends Thread {
	

	String name;
	Socket socket1;
	Socket socket2;
	
	static private int TIMEOUT = 0;
	static private int BUFSIZ = 8;

	public TransferDown(Socket s1, Socket s2, String name) {
		this.socket1 = s1;
		this.socket2 = s2;
		this.name = name;
		this.start();
	}

	public void run() {
		try {
			Thread.currentThread().setName(name);
			socket1.setSoTimeout(TIMEOUT);
			InputStream is = socket1.getInputStream();
			socket2.setSoTimeout(TIMEOUT);
			OutputStream os = socket2.getOutputStream();
			exchange(is, socket2.getInputStream(), os, socket1.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	private void exchange(InputStream is0, InputStream is1, OutputStream os0, OutputStream os1) {
		try {
			int ir;
			byte bytes[] = new byte[BUFSIZ];
			while (true) {
				System.out.println("Down - "+Thread.currentThread().getName());
				try {
					if ((ir = is1.read(bytes)) > 0) {
						os1.write(bytes, 0, ir);
						os1.flush();
					}else if (ir < 0) {
						os1.flush();
}
				} catch (InterruptedIOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}