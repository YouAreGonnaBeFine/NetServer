package ora;

public class ClientLaunch {

	public static void main(String[] args) {
		
    	Route route = new Route();
    	route.setLocalIP("127.0.0.1");
    	route.setLocalPort(8888);
    	route.setDestHost("192.168.56.101");
    	route.setDestPort(1521);
    	
    	new Client(route);

	}

}
