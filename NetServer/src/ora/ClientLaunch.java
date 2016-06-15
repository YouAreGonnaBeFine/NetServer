package ora;

public class ClientLaunch {

	public static void main(String[] args) {
		
    	LinkInfo link = new LinkInfo();
    	link.setSerIP("115.159.152.136");
    	link.setSerPort(8888);
    	link.setDbIP("192.168.56.101");
    	link.setDbPort(1521);
    	
    	new Client(link);

	}

}
