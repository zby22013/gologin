package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.Header;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Account implements AccountInterface{
	private String username;
	private String lockedPassword;
	private String userIntranetAddress;
	private String returnInfo;
	public Account(){
		
	}
	
	public Account (String username,String lockedPassword,String userIntranetAddress){
		this.username = username;	
		this.lockedPassword = lockedPassword;
		this.userIntranetAddress = userIntranetAddress;
	}
	
	public String login(){
		HttpPost httpPost = null;
		//两种方式均可
		//1.直接放在url中传参
/*
		String parameters = "accountID="+username+"&password="+lockedPassword+"&brasAddress=59df7586&userIntranetAddress="+userIntranetAddress;
		String url = LOGINURL + "?" + parameters;
		httpPost = new HttpPost(url);
*/
		//2.使用Entity,模拟form、json等形式
		httpPost = new HttpPost(LOGINURL);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("accountID", username)); 
		params.add(new BasicNameValuePair("password", lockedPassword));
		params.add(new BasicNameValuePair("brasAddress", BRASADDRESS)); 
		params.add(new BasicNameValuePair("userIntranetAddress", userIntranetAddress));
		UrlEncodedFormEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(params);
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if(entity == null){
			return "failure";
		}
		entity.setContentType("application/x-www-form-urlencoded");
		httpPost.setEntity(entity);
	    httpPost.addHeader("Referer" , "http://61.137.86.87:8080/portalNat444/index.jsp");
	    if(doPost(httpPost).equals("success")){
	    	return "success";
	    }
	    else
	    	return "failure";
	}
	
	public String logout(){
		HttpPost httpPost = null;
		httpPost = new HttpPost(LOGOUTURL);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("brasAddress", BRASADDRESS)); 
		params.add(new BasicNameValuePair("userIntranetAddress", userIntranetAddress));
		UrlEncodedFormEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(params);
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if(entity == null){
			return "failure";
		}
		entity.setContentType("application/x-www-form-urlencoded");
		httpPost.setEntity(entity);
	    httpPost.addHeader("Referer" , "http://61.137.86.87:8080/portalNat444/main.jsp");
	    if(doPost(httpPost).equals("success")){
	    	return "success";
	    }
	    else
	    	return "failure";
	}
	
	public String doPost(HttpPost httpPost){
		HttpClient httpclient = new DefaultHttpClient(); 	
//	httppost.addHeader("Accept" , "application/json, text/javascript, */*; q=0.01");
//	httppost.addHeader("Accept-Language" , "zh-cn");
//	httppost.addHeader("ContentType" , "application/x-www-form-urlencoded;charset=utf-8");
        HttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if(response == null){
			return "failure";
		}
        HttpEntity entity = response.getEntity();  	 
        if (entity == null) {  
            return "failure";
        }
  
        InputStream inSm = null;
		try {
			inSm = entity.getContent();
		} catch (IllegalStateException | IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if(inSm == null){
			return "failure";
		}
		Scanner inScn = new Scanner(inSm);
        while (inScn.hasNextLine()) {
            returnInfo = inScn.nextLine(); 
        }  
        httpPost.abort();  
        httpclient.getConnectionManager().shutdown();
        return "success";
    }

	@Override
	public String getReturnInfo() {
		return returnInfo;
	}
}
	
