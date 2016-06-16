package db;

public class ClientLaunch {

	public static void main(String[] args) {
		
    	LinkInfo link = new LinkInfo();
    	link.setSerIP("127.0.0.1");
    	link.setSerPort(1521);
    	link.setDbIP("10.11.112.13");
    	link.setDbPort(1521);
    	
    	new Client(link);
    	

	}

}