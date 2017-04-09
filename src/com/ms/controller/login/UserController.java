package com.ms.controller.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.ms.service.login.IUserService;
import com.ms.util.MD5Utils;
import com.ms.util.TokenGenerateUtil;
import com.ms.util.TokenManager;

@Controller
public class UserController {
	@Resource
	private IUserService userService;
	/**
	 * 登录
	 * @author lcz
	 *
	 */
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("loginName")  String loginName,
			@RequestParam("password")  String password,
			HttpSession session,ModelAndView mv){
		//密码进行MD5加密
		password=MD5Utils.hashed(password);
		Map<String,String> map = new HashMap<String,String>();
		map.put("loginName", loginName);
		map.put("password", password);
		List resList = this.userService.login(map);
		//账号密码验证成功
		if(resList.size()>0){
			Map datamap = (Map)resList.get(0);
			String user =(String)datamap.get("LOGINNAME")==null?"":(String)datamap.get("LOGINNAME");
			String token =ssoToken(user);
			session.setAttribute("user", user);
			session.setAttribute("token", token);
			mv.setViewName("main");
		}else{
			mv.addObject("message", "登录名或密码错误！请重新输入");
			mv.setViewName("index");
		}
		return mv;
	}
	/**
	 * 退出
	 * @author lcz
	 *
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(@RequestParam("user")  String user,ModelAndView mv,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession(false);  
		if(session==null){  
			mv.setViewName("index"); 
		}  
		session.removeAttribute("user");  
		mv.setViewName("index"); 
		return mv;
	}
	/**
	 * SSO-TOKEN服务端实现
	 * @author lcz
	 *
	 */
	public String ssoToken(String user){
		//生成token
		String token = TokenGenerateUtil.getToken();
		//服务器端，将生成的Token和用户信息放到一个全局唯一的存储结构中
		TokenManager.addToken(token, user);
		//设置Token的过期时间和最后一次操作时间
		TokenManager.updateLastOperate(token);
		return token;
	}
}

