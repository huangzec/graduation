package com.mvc.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{
	
	/**
	 * 定义一个默认值的String类型的encoding对象
	 * 用于将通过request请求的字符类型传入到此过滤器中
	 */
	protected String encoding = null;
	
	/**
	 * 定义一个词过滤器的联合构造对象
	 * 如果此值为空，说明这个过滤器没有被实例化
	 */
	protected FilterConfig filterConfig = null;
	
	/**
	 * 定义一个boolean型的变量，用于说明客户端的字符编码是否被忽视
	 */
	protected boolean ignore = true;
	
	/**
	 * destroy()方法  销毁过滤器
	 * 
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-7 上午10:06:25
	 */
	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	/**
	 * 实现doFilter()方法
	 * 选择并将设置的字符编码给予使用
	 * 说明请求的请求参数
	 * 
	 * @param request 需要处理的servlet请求
	 * @param result 创建servlet响应
	 * @param chain 需要处理的过滤器chain
	 * 
	 * @exception IOException 抛出输入/输出异常
	 * @exception ServletException 抛出servlet异常
	 * 
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-7 上午10:06:25
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/**
		 * 如果忽视客户端的编码方式，或者通过request 对象得到的编码方式为null
		 * 将编码方式设置成想要转换的方式
		 */
		if (ignore || (request.getCharacterEncoding() == null)) {
			String encoding = selectEncoding(request);
			/**
			 * 如果encoding不为null，将此编码方式设置成request的编码方式
			 */
			if (encoding != null) {
				request.setCharacterEncoding(encoding);
			}
		}
		// 通过控制传递下一个过滤器
		chain.doFilter(request, response);
	}

	/**
	 * 实现init()方法
	 * 初始化过滤器
	 * 
	 * @param filterConfig 过滤器的构造对象
	 * 
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-7 上午10:06:25
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null) {
			this.ignore = true;
		}else if (value.equalsIgnoreCase("true")){
			this.ignore = true;
		}else if (value.equalsIgnoreCase("yes")){
			this.ignore = true;
		}else{
			this.ignore = false;
		}
	}
	
	/**
	 * 选择一个适当的字符编码被使用，基于当前request 特有的过滤器初始化的值
	 * 如果没有设置编码方式返回 null
	 * 此默认并被无条件执行返回的值，被用于设置此过滤器的初始化编码方式
	 * 
	 * @param request 需要被处理的Servlet请求
	 * 
	 * @author Happy_Jqc@163.com
	 * @date 2014-8-7 上午10:06:25
	 * @return String
	 */
	protected String selectEncoding(ServletRequest request) {
		return (this.encoding);
	}


}
