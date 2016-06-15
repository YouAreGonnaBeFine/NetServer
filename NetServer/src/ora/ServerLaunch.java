package ora;

public class ServerLaunch {
	
    public static void main(String args[]) {
    	
    	LinkInfo link = new LinkInfo();
    	link.setSerPort(8888);
  
    	
    	new Server(link);
    	
    	
    	
    }


}
