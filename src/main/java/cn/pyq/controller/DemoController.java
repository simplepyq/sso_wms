package cn.pyq.controller;

import cn.pyq.util.SendToUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @desc:
 * @author: pyq
 * @date: 2018-12-08 21:31
 */
@Controller
public class DemoController extends BaseController {
	@RequestMapping("demo")
	public String demo(){
		session.setAttribute("logOutUrl", SendToUrl.getServerLogOutUrl());
		return "redirect:/index.jsp";
	}
	@RequestMapping("logOut")
	public String logOut(){
		session.invalidate();
		return "redirect:/demo";
	}
}

