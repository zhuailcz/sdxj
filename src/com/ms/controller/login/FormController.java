package com.ms.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 动态页面跳转控制器
 * @author lcz
 *
 */
@Controller
public class FormController {
	@RequestMapping("/{formName}")
	public String loginForm(@PathVariable String formName){
		System.out.println(formName);
		//动态页面跳转
		return formName;
	}
}
