package com.cpjl.managecenter.configuration;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
 
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cpjl.managecenter.interceptor.PrivilegeInterceptor;
 @Configuration 
public class MyWebMvcConfigurationSupport implements WebMvcConfigurer {
 	@Resource
 	private PrivilegeInterceptor privilegeInterceptor;  
	 
      @Override
	public void addInterceptors(InterceptorRegistry registry) { 
    	  registry.addInterceptor(privilegeInterceptor)//
         .addPathPatterns("/**")//
         .excludePathPatterns("/login/index"//
        		 ,"/login/imageCode"//
        		 ,"/login/verify"//
        		 ,"/error"//
        		 ,"/static/**" );   
	}
 
}
