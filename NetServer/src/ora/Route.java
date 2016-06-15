package ora;

public class Route {
	
	private String LocalIP;
	
	private int LocalPort;
	
	private String DestHost;
	
	private int DestPort;

	public String getLocalIP() {
		return LocalIP;
	}

	public void setLocalIP(String localIP) {
		LocalIP = localIP;
	}

	public int getLocalPort() {
		return LocalPort;
	}

	public void setLocalPort(int localPort) {
		LocalPort = localPort;
	}

	public String getDestHost() {
		return DestHost;
	}

	public void setDestHost(String destHost) {
		DestHost = destHost;
	}

	public int getDestPort() {
		return DestPort;
	}

	public void setDestPort(int destPort) {
		DestPort = destPort;
	}

}
