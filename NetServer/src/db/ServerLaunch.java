package db;

public class ServerLaunch {
	
    public static void main(String args[]) {
    	
    	LinkInfo link = new LinkInfo();
    	link.setSerPort(1521);
  
    	
    	new Server(link);
    	
    	
    }


}