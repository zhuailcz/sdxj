package com.ms.util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 *  判断用户权限的Spring mvc拦截器
 *  @author lcz
 *
 */
public class Authorizedlnterceptor implements HandlerInterceptor{
	private static Log log = LogFactory.getLog(Authorizedlnterceptor.class);
	//定义不需要拦截的请求
	private static final String[] IGNORE_URI = {"/loginForm","/login","/404.html"};
	/**
	 * 该方法在preHandle方法的返回值为true时才会执行
	 * 该方法将在整个请求完成之后执行，主要作用是用于清理资源
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception)
			throws Exception {
		if(log.isDebugEnabled())
			log.debug("AuthorizationInterceptor afterCompletion -->");
		
	}
	/** 
    * 该方法将在Controller的方法调用之后执行， 方法中可以对ModelAndView进行操作 ，
    * 该方法也只能在当前Interceptor的preHandle方法的返回值为true时才会执行。 
    */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView mv) throws Exception {
		if(log.isDebugEnabled())
			log.debug("AuthorizationInterceptor postHandle -->");
		
	}
	
	/** 
     * preHandle方法是进行处理器拦截用的，该方法将在Controller处理之前进行调用，
     * 该方法的返回值为true拦截器才会继续往下执行，该方法的返回值为false的时候整个请求就结束了。 
     */  
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		if(log.isDebugEnabled())
			log.debug("AuthorizationInterceptor preHandle --> ");
		// flag变量用于判断用户是否登录，默认为false 
		boolean flag = false; 
		//获取请求的路径进行判断
		String servletPath = request.getServletPath();
		// 判断请求是否需要拦截
        for (String s : IGNORE_URI) {
            if (servletPath.contains(s)) {
                flag = true;
                break;
            }
        }
        // 拦截请求
        if (!flag){
        	//1.获取session中的token
        	String token = (String) request.getSession().getAttribute("token");
        	//2.验证token有效性 
        	boolean isValid = TokenManager.validate(token);
        	if(!isValid){
        		if(log.isDebugEnabled())
        			log.debug("AuthorizationInterceptor拦截请求： ");
        		 request.setAttribute("message", "请先登录再访问系统");
        		 request.getRequestDispatcher(OrgConstants.LOGIN).forward(request, response);
        	}else{
        		//验证通过
        		//设置Token的过期时间和最后一次操作时间
        		TokenManager.updateLastOperate(token);
        		flag = true;
        	}
        }
        return flag;
		
	}
}
