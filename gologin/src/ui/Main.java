package ui;

import java.util.Scanner;

import service.Account;
import service.AccountInterface;
import util.InetAddressUtil;
import util.InetAddressUtilInterface;
import util.RSAUtils;

public class Main {
	public static void main(String []args){
		Scanner sc = new Scanner(System.in);
		System.out.println("输入账户：");
		String sourceUsername = sc.next();
		System.out.println("输入密码：");
		String sourcePassword = sc.next();
		String username = sourceUsername+ "@zndx.inter";
		String lockedPassword = new RSAUtils().getLockedPassword(sourcePassword);
		InetAddressUtilInterface i = new InetAddressUtil();
		String userIntranetAddress =i.getIPv4Address();
		System.out.print(userIntranetAddress);
		AccountInterface account = new Account(username,lockedPassword,userIntranetAddress);
		//account.login();
		account.logout();
		System.out.print(account.getReturnInfo());

	}	
}
