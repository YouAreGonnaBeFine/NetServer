package mis;

import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;

public class Transfer {
	
	public  void pipe(InputStream is0, InputStream is1, OutputStream os0,OutputStream os1) {
		try {
			int ir;
			byte bytes[] = new byte[1024];
			while (true) {
				System.out.println("true");
				try {
					if ((ir = is1.read(bytes)) > 0) {
						System.out.println(ir);
						os1.write(bytes, 0, ir);
					} else if (ir < 0) {
						break;
					}
				} catch (InterruptedIOException e) {
					e.printStackTrace();
				}				
				try {
					if ((ir = is0.read(bytes)) > 0) {
						System.out.println(ir);
						os0.write(bytes, 0, ir);
					} else if (ir < 0) {
						break;
					}
				} catch (InterruptedIOException e) {
					e.printStackTrace();
				}
				
				
			}
		} catch (Exception e0) {
			e0.printStackTrace();
		}
	}	

}
