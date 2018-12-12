package cn.pyq.filter;



import cn.pyq.util.HttpUtil;
import cn.pyq.util.SendToUrl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class SSOFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req= (HttpServletRequest) request;
		HttpServletResponse resp= (HttpServletResponse) response;
		HttpSession session=req.getSession();
		Boolean isLogin= (Boolean) session.getAttribute("isLogin");
		if (isLogin != null&&isLogin) {
			chain.doFilter(req,resp);
			return;
		}
		String token=req.getParameter("token");
		if(token!=null){
			String httpUrl= SendToUrl.SERVER_URL_PREFIX+"/user/verify";
			Map<String,String> params=new HashMap<>();
			params.put("token",token);
			params.put("logOutUrl", SendToUrl.getLogOutUrl());
			params.put("jSessionId",session.getId());
			try {
				String isVerify = HttpUtil.sendHttpRequest(httpUrl, params);
				if("true".equals(isVerify)){
					session.setAttribute("isLogin",true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			chain.doFilter(req,resp);
			return;
		}
		SendToUrl.sendSSOClientUrl(req,resp);

	}

	@Override
	public void destroy() {

	}
}

