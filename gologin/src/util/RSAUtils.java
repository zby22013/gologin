package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLDecoder;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class RSAUtils {
	/**
	 * @Description:加载JS中的加密函数，对密码加密
	 * @time:2014-10-12
	 */
	public String getLockedPassword(String sourcePassword){
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
//		URL fileURL=this.getClass().getResource("/js/rsa.js");   //实际路径为bin/js/rsa.js 利用classLoader，在对象允许时也可以动态获取到资源文件
//		String fileName = fileURL.getFile();
		
		File file = new File("js/rsa.js");
		  //FileReader reader = new FileReader(file.getAbsolutePath().replaceAll("\\\\", "/"));
		  FileReader reader = null;
			try {
				reader = new FileReader(file);
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			if(reader == null){
				return "failure";
			}
			else{
				try {
					engine.eval(reader);
				} catch (ScriptException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				  if(engine instanceof Invocable){
					  Invocable invoke = (Invocable) engine;
					  String publicKey = null;
					try {
						publicKey = (String) invoke.invokeFunction("get",sourcePassword);
					} catch (NoSuchMethodException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					} catch (ScriptException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					  try {
						reader.close();
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					  return publicKey;
				  }
				  else{
					  try {
						reader.close();
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					  return "failure";
				  }
			}
	}

}
