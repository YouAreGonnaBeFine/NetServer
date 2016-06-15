package ora;

import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.Socket;

public class TransferUp extends Thread {
	
	Socket socket1;
	Socket socket2;
	
	static private int TIMEOUT = 0;
	static private int BUFSIZ = 1024;

	public TransferUp(Socket s1, Socket s2) {
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
			exchange(is, socket2.getInputStream(), os, socket1.getOutputStream());
		} catch (Exception e) {
		} finally {
		}
	}

	private void exchange(InputStream is0, InputStream is1, OutputStream os0, OutputStream os1) {
		try {
			int ir;
			byte bytes[] = new byte[BUFSIZ];
			while (true) {
				System.out.println("Up");
				try {
					if ((ir = is0.read(bytes)) > 0) {
						os0.write(bytes, 0, ir);
					} else if (ir < 0) {
						break;
					}
				} catch (InterruptedIOException e) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}