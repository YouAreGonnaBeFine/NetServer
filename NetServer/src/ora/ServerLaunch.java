package ora;

public class ServerLaunch {
	
    public static void main(String args[]) {
    	
    	Route route = new Route();
    	route.setLocalPort(8888);
  
    	
    	new Server(route);
    	
    	
    	
    }


}
