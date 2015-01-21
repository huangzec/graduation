package com.mvc.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class RequestException implements HandlerExceptionResolver {

	/**
	 * 请求异常分发
	 */
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();  
        model.put("ex", ex);  
          
        // 根据不同错误转向不同页面  
        if(ex instanceof BusinessException) {  
            return new ModelAndView("public/alert", "exp", model);  
        }else if(ex instanceof ParameterException) {  
            return new ModelAndView("public/alert", "exp", model);  
        } else if(ex instanceof VerifyException) {  
            return new ModelAndView("public/alert", "exp", model);  
        } else {  
            return new ModelAndView("public/alert", "exp", model);  
        }
	}
		
}
