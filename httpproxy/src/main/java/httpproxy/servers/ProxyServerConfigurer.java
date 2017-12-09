package httpproxy.servers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import httpproxy.entity.Host;

public class ProxyServerConfigurer {
	
	public static ArrayList<Host> SERVERS = new ArrayList<Host>();
	
	private static IPAddrValidator ipValidator = null;
	
	public static void config(String proxyConfigFile) throws IOException{

		File configFile = new File(proxyConfigFile);
		BufferedReader br = new BufferedReader(new FileReader(configFile));
		String line = null;
		Host server = null;
		while ((line = br.readLine()) != null){
			if (line.trim().startsWith("#")){
				continue;
			}
			server = parseIp(line);
			if (null != server){
				SERVERS.add(server);
			}
		}
		br.close();
	}
	
	
	private static Host parseIp(String addrPortString) {
		
		char[] addrPortCharArray = addrPortString.toCharArray();
		int beginIdx = 0;
		int endIdx = addrPortCharArray.length - 1;
		while(beginIdx < endIdx ){
			if (isWhiteSpace(addrPortCharArray[beginIdx])){
				beginIdx ++;
			}else{
				break;
			}
		}
		
		while (beginIdx < endIdx) {
			if (isWhiteSpace(addrPortCharArray[endIdx])){
				endIdx --;
			}else {
				break;
			}
		}
		
		char[] addrArr = new char[15];
		char[] portArr = new char[5];
		for (int i=0; i<15 && !isWhiteSpace(addrPortCharArray[beginIdx]); i++){
			addrArr[i] = addrPortCharArray[beginIdx];
			beginIdx ++;
		}
		
		int i=4;
		for (; i>-1 && !isWhiteSpace(addrPortCharArray[endIdx]); i--){
			portArr[i] = addrPortCharArray[endIdx];
			endIdx--;
		}
		
		if(i!=-1){
			for (int k=0; k<4-i; k++){
				if(portArr[k]=='\0' && portArr[k+i+1]!='\0'){
					portArr[k] = portArr[k+1+i];
					portArr[k+1+i] = '\0';
				}
			}
		}
		
		String ipAddr =  
		
		return new Host(addrPort[0], addrPort[1]);
	}
	
	private static class IPAddrValidator{
		private Pattern ipPattern;
	    private Matcher matcher;
	    private static final String IPADDRESS_PATTERN =
	    		"^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."+
  "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."+
  "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."+
  "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
	    

	    private IPAddrValidator(){
		  ipPattern = Pattern.compile(IPADDRESS_PATTERN);
	    }

	    private boolean validateIp(final String ip){
		  matcher = ipPattern.matcher(ip);
		  return matcher.matches();
	    }
	    
	}
	
	
	private static boolean isWhiteSpace(char ch){
		return ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n';
	}
	
}
