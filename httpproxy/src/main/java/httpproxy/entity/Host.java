package httpproxy.entity;

//
public class Host {
	
	private String ipAddr;
	
	private Integer port;
	
	public Host(String ipAddr, Integer port) {
		this.ipAddr = ipAddr;
		this.port = port;
	}

	public String getIpAddr() {
		return ipAddr;
	}
	
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	
	public Integer getPort() {
		return port;
	}
	
	public void setPort(Integer port) {
		this.port = port;
	}
	
}
