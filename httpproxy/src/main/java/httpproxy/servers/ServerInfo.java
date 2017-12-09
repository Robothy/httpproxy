package httpproxy.servers;

import httpproxy.entity.Host;

/**
 * ProxyServerQuality is the flag of http proxy server's quality,
 * shorter the network delay, higher the proxy server quality.
 * 
 * @author robothy
 *
 */
enum ProxyServerQuality {
	HIGH,
	MIDDLE,
	ROW,
	UNUSABLE
}

public class ServerInfo {
	
	private Host server;
	
	/**
	 * @see #ProxyServerQuality
	 */
	private ProxyServerQuality quality;
	
	public void initServerInfo(){
		;
	}
	
	public ProxyServerQuality quality(){
		return quality();
	}
	
}
