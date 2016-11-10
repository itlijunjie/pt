package com.itlijunjie.pt.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itlijunjie.pt.vo.User;

public class loginFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = -8527942053687062625L;

	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpSession session = request.getSession(true);
		User usercode = (User) session.getAttribute("loginUser");
		String url = request.getRequestURI();
		
		//静态资源不做处理 和 手机调用不审核
		if (url.indexOf("resources")>0||url.indexOf("call")>0||url.indexOf("upload")>0) {
			arg2.doFilter(arg0, arg1);
			return;
		}
		
		//查看session看看是否登录了
		if (usercode == null) {
			/**
			 * 判断获取的路径不为空且不是访问登录页面或执行登录操作时跳转
			 * 当请求登录和验证码是时不进行过滤其余情况均进行过滤
			 */
			if (url != null && !url.equals("") && ((url.indexOf("login") >= 0 && url.indexOf("user") >= 0)||url.indexOf("drawCheckcode")>=0)) {
				//没有登录成功，但是准备登录
			}else {
				//非法操作
				response.sendRedirect("/pt/user/login");
				return;
			}
		}
		// 已通过验证，用户访问继续
		arg2.doFilter(arg0, arg1);
		return;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
