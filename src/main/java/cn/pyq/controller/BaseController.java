package cn.pyq.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @desc:
 * @author: pyq
 * @date: 2018-12-07 20:29
 */
public class BaseController {
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	ServletContext application;

	@ModelAttribute
	public void setRequest(HttpServletRequest request) {
		this.request = request;
		this.response=response;
		this.session=request.getSession();
		this.application=request.getServletContext();

	}
}

