package ora;

public class ServerLaunch {
	
    public static void main(String args[]) {
    	
    	Route route = new Route();
    	route.setLocalIP("127.0.0.1");
    	route.setLocalPort(8888);
  
    	
    	new Server(route);
    	
    	
    	
    }


}
