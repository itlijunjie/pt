package com.itlijunjie.pt.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.itlijunjie.pt.services.IUserService;
import com.itlijunjie.pt.util.CaptchaUtil;
import com.itlijunjie.pt.util.PageInfo;
import com.itlijunjie.pt.util.StringUtil;
import com.itlijunjie.pt.vo.User;
import com.itlijunjie.pt.vo.exception.UserException;

@Controller
@RequestMapping("/user")
@SessionAttributes({"loginUser","checkCode"})
public class UserConreoller {
	
	private PageInfo pageInfo = null;
	
	private IUserService userService;
	private CaptchaUtil captchaUtil;
	
	/**
	 * 
	 * @return
	 */
	public CaptchaUtil getCaptchaUtil() {
		return captchaUtil;
	}
	
	/**
	 * 
	 * @param captchaUtil
	 */
	@Resource
	public void setCaptchaUtil(CaptchaUtil captchaUtil) {
		this.captchaUtil = captchaUtil;
	}
	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}
	/**
	 * @param userService the userService to set
	 */
	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public String list(HttpServletRequest request,Model model) {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		pageInfo = userService.pageList(null,pageNo,20);
		model.addAttribute("users", pageInfo);
		return "user/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute(new User());
		//这里是跳转的jsp页面
		return "user/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Valid User user,BindingResult result) {
		if (result.hasErrors()) {
			return "user/add";
		}
		user.setPassword(StringUtil.MD5(user.getPassword()));
		userService.add(user);
		return "redirect:/user/users?pageNo="+pageInfo.getCurPage();
	}
	
	@RequestMapping(value="{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable int id){
		userService.delete(id);
		return "redirect:/user/users?pageNo="+pageInfo.getCurPage();
	}
	
	@RequestMapping(value="{id}/update",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		model.addAttribute(userService.load(id));
		return "user/update";
	}
	
	@RequestMapping(value="{id}/update",method=RequestMethod.POST)
	public String update(@PathVariable int id, @Valid User user ,BindingResult result) {
		user.setId(id);
		user.setPassword(StringUtil.MD5(user.getPassword()));
		userService.update(user);
		return "redirect:/user/users?pageNo="+pageInfo.getCurPage();
	}
	
	@RequestMapping(value="{id}")
	public String show(@PathVariable int id , Model model) {
		model.addAttribute(userService.load(id));
		return "user/show";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String checkCode, String username,String password,Model model) {
		if (!checkCode.equals(model.asMap().get("checkCode"))) {
			model.addAttribute("error","验证码错误");
			return "user/login";
		}
		User u = userService.login(username, password);
		//放到session中
		model.addAttribute("loginUser",u);
		return "redirect:/index.jsp";
	}
	
	@RequestMapping(value="/logout")
	public String logout(ModelMap model,HttpServletRequest request) {
		HttpSession session = request.getSession();
        session.removeAttribute("loginUser");
       // session.removeAttribute(SysConstants.USER_LOGIN_NAME_SESSIONKEY);         
        //model.remove(SysConstants.USER_LOGIN_ID_SESSIONKEY);
        //model.remove(SysConstants.USER_LOGIN_ID_SESSIONKEY);
        model.remove("loginUser");
		return "redirect:/user/login";
	}
	
	@RequestMapping("/drawCheckcode")
	public void drawCheckcode(HttpServletResponse response,Model model) {
		response.setContentType("image/jpeg");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			String str = captchaUtil.generatorStr();
			BufferedImage image = captchaUtil.generatorImageByStr(str);
			model.addAttribute("checkCode",str);
			ImageIO.write(image , "jpg", os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ExceptionHandler(UserException.class)
	public String handlerException(Exception ex,HttpServletRequest req) {
		req.setAttribute("ex", ex);
		return "inc/error";
	}
}