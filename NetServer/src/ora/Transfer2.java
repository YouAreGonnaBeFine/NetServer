package ora;

import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.Socket;

public class Transfer2 extends Thread {

	public Transfer2(Socket s1, Socket s2) {
		this.socket1 = s1;
		this.socket2 = s2;
		this.start();
	}

	public void run() {
		try {
			socket1.setSoTimeout(TIMEOUT);
			InputStream is = socket1.getInputStream();
			socket2.setSoTimeout(TIMEOUT);
			OutputStream os = socket2.getOutputStream();
			pipe(is, socket2.getInputStream(), os, socket1.getOutputStream());
		} catch (Exception e) {
		} finally {
			closeSocket(socket2);
			closeSocket(socket1);
		}
	}

	/**
	 * 传输的实现方法
	 */
	private void pipe(InputStream is0, InputStream is1, OutputStream os0, OutputStream os1) {
		try {
			int ir;
			byte bytes[] = new byte[BUFSIZ];
			while (true) {
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

	void closeSocket(Socket s) {
		try {
			s.close();
		} catch (Exception ef) {
		}
	}

	Socket socket1;
	Socket socket2;
	
	static private int TIMEOUT = 0;
	static private int BUFSIZ = 1024;
}