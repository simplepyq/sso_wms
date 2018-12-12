package cn.pyq.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * @desc:
 * @author: pyq
 * @date: 2018-12-08 16:58
 */
public class SendToUrl {
	private static Properties properties = new Properties();
	public static String SERVER_URL_PREFIX; //统一认证中心url
	public static String CLIENT_HOST_URL; //当前客户端url

	static {
		try {
			properties.load(SendToUrl.class.getClassLoader().getResourceAsStream("sendUrl.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		SERVER_URL_PREFIX = properties.getProperty("server-url-prefix");
		CLIENT_HOST_URL = properties.getProperty("client-host-url");
	}

	/**
	 * @desc:获取拦截的完整url
	 * @Author: pyq
	 * @Date: 2018-12-08 17:09
	 */
	private static String getRedirectUrl(HttpServletRequest request) {
		return CLIENT_HOST_URL + request.getServletPath();
	}

	/**
	 * @desc:发送到统一认证中心
	 * @Author: pyq
	 * @Date: 2018-12-08 17:11
	 */
	public static void sendSSOClientUrl(HttpServletRequest request, HttpServletResponse response) {
		String redirectUrl = getRedirectUrl(request);
//		String url = SERVER_URL_PREFIX.concat("/checkLogin?redirectUrl=").concat(redirectUrl);
		StringBuffer url = new StringBuffer().append(SERVER_URL_PREFIX).append("/user/checkLogin?redirectUrl=").append(redirectUrl);
		try {
			response.sendRedirect(url.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getLogOutUrl(){
		return CLIENT_HOST_URL+"/logOut";
	}
	public static String getServerLogOutUrl(){
		return SERVER_URL_PREFIX+"/user/logOut";
	}
}

