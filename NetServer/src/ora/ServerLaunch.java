package ora;

public class ServerLaunch {
	
    public static void main(String args[]) {
    	
    	LinkInfo link = new LinkInfo();
    	link.setSerPort(9999);
  
    	
    	new Server(link);
    	
    	
    }


}