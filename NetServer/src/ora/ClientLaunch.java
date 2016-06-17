package ora;

public class ClientLaunch {

	public static void main(String[] args) {
		
    	LinkInfo link = new LinkInfo();
    	link.setSerIP("115.159.152.136");
    	link.setSerPort(8888);
    	link.setDbIP("127.0.0.1");
    	link.setDbPort(1521);
    	
    	new Client(link);
    	

	}

}