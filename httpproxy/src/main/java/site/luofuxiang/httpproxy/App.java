package site.luofuxiang.httpproxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import httpproxy.entity.Host;
import httpproxy.servers.ProxyServerConfigurer;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger logger = LogManager.getLogger("logger");
	
    public static void main( String[] args ) throws IOException 
    {
    	
    	System.out.println(logger.hashCode());
    	
    	System.out.println(LogManager.getLogger("logger").hashCode());
    	
    	for (long i=0; i<1000000; i++){
    		logger.error("Hello, this an error log.");
    	}
    	
    	logger.warn("this is a warning.");
    	
    	/*String serverFile = "F:/projects/httpproxy/httpproxy/target/classes/servers.txt";
    	System.out.println(serverFile);
    	ProxyServerConfigurer.config(serverFile);
    	for (final Server server : ProxyServerConfigurer.SERVERS){
    		(new Runnable() {
				public void run() {
					Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(server.getIpAddr(), Integer.parseInt(server.getPort())));
					try {
						new URL("http://139.199.156.122:6060/visit").openConnection(proxy).getContent();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).run();
    	}*/
    }
}
