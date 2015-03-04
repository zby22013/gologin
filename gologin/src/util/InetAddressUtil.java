package util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressUtil implements InetAddressUtilInterface{

	@Override
	public String getIPv4Address() {
		String hostAddress = "";
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		if(address!=null){
			hostAddress = address.getHostAddress();
			return hostAddress;
		}else{
			return "获取IP失败，无法登录，请检查网络连接或者重新打开程序！";
		}
		
		
	}
	
	public String getIP()
    {
      String hostAddress = "";
      InetAddress address = null;
      try {
        address = InetAddress.getLocalHost();
      } catch (UnknownHostException e) {
        e.printStackTrace();
      }
      if (address == null) {
    	  return "获取IP失败，请检查网络或者重启程序！";
      }
      hostAddress = address.getHostAddress();
      return hostAddress;
    }
	
}
