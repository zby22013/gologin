package util;

/**
 * @author 周博宇
 *
 */
public interface InetAddressUtilInterface {
	/**
	 * @Description:获取本机内网地址，在windows下为cmd输入ipconfig的IPv4地址
	 * @time:2014-10-12
	 */
	String getIPv4Address();
	String getIP();
}
