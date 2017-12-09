package httpproxy.servers;

/**
 * Load a text file include proxy servers' IP address and port. 
 * @author robothy
 *
 */
final class HostsLoader {
	
	private static HostsLoader hostsLoader = null;
	
	private HostsLoader(){}
	public static HostsLoader getInstance(){
		if(hostsLoader == null){
			synchronized(hostsLoader){
				if(hostsLoader == null){
					hostsLoader = new HostsLoader();
				}
			}
		}
		return hostsLoader;
	}
	
	protected static void load(final String hostsFileName){
		
	}
	
}
