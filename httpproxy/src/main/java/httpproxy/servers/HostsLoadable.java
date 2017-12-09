package httpproxy.servers;

import java.util.List;

/**
 * Load proxy server information.
 * @author robothy
 *
 */
public abstract class HostsLoadable {
	
	/**
	 * 
	 */
	private static List<?> proxyServers = null;
	
	/**
	 * Read host information and load to memory.
	 * @param hostsDataSource the data source of hosts information.
	 */
	abstract void load(Object hostsDataSource);
	
	
	
}
