package service;

/**
 * @author 周博宇
 *
 */
public interface AccountInterface {
	final String BRASADDRESS = "59df7586";	//暂时不清楚，从数字中南登录页面找到的必传参数
	final String LOGINURL = "http://61.137.86.87:8080/portalNat444/AccessServices/login";
	final String LOGOUTURL = "http://61.137.86.87:8080/portalNat444/AccessServices/logout?";
	final String BEFORELOGINURL = "http://61.137.86.87:8080/portalNat444/AccessServices/bas.59df7586?wlanuserip=";
	
	/**
	 * @time:2014-10-12 20:06
	 *
	 */
	String login();
	String logout();
	/**
	 * @Description:获取response信息
	 * @time:2014-10-12 
	 *
	 */
	String getReturnInfo();
}
