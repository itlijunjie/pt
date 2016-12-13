package com.itlijunjie.pt.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class ConstUtil {
    public static final String IP = ConstUtil.getIP();

    public static final String HOME_PATH = ConstUtil.getHomePath();

    public static final String UPLOAD_PATH = HOME_PATH + "/Sites/upload/";

    public static final String DOWNLOAD_PATH = "http://" + IP + "/upload";

    public static final String PORT = "8080";

    public static final String SERVER_NAME = "/pt";

    public static final String SERVER_PATH = "http://" + IP + ":" + PORT
            + SERVER_NAME;

    public static final String SERVER_RESOURCES_NAME =  "/resources";

    public static final String SERVER_RESOURCES = SERVER_PATH + SERVER_RESOURCES_NAME;

    public static final String JSON_TEST_PATH_NAME = "/call/json";

    public static final String JSON_TEST_PATH = SERVER_PATH + JSON_TEST_PATH_NAME;

    private static String getIP() {
        String res = "";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            res = addr.getHostAddress();
            System.out.print(res);
        } catch (UnknownHostException e) {
            System.out.println("严重错误，没有获取到本地IP");
            e.printStackTrace();
        }
        return res;
    }

    private static String getHomePath() {
        String usrHome = System.getProperty("user.home");
        if (usrHome == null) {
            System.out.println("严重错误，获取不到用户Home目录");
        } else {
            System.out.print(usrHome);
        }
        return usrHome;
    }
}
