package com.slideviewsoft.pt.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class ConstUtil {
	public static final String IP = ConstUtil.getIP();

	public static final String PORT = "8080";

	public static final String URL_SEPARATING_CHARACTER = "http://" + IP + ":"
			+ PORT + "/pt";

	public static final String SERVER_NAME = "http://" + IP + ":" + PORT
			+ "/pt";

	public static final String SERVER_RESOURCES = "http://" + IP + ":" + PORT
			+ "/pt/resources";

	public static final String JSON_TEST_PATH = "http://" + IP + ":" + PORT
			+ "/pt/call/json";

	static String getIP() {
		String res = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			res = addr.getHostAddress();
		} catch (UnknownHostException e) {
			System.out.println("严重错误，没有获取到本地IP");
			e.printStackTrace();
		}
		return res;
	}
}
