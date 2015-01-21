package com.mvc.exception;

/**
 * 验证异常类
 * 
 * @author 		huangzec<huangzec@foxmail.com>
 * @date 		2015-1-3 上午10:42:37 
 * @version 	1.0
 */
public class VerifyException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4241090289695521104L;

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public VerifyException() {
		super();
	}

	public VerifyException(String message, Throwable cause) {
		super(message, cause);
	}

	public VerifyException(String message) {
		super(message);
	}

	public VerifyException(Throwable cause) {
		super(cause);
	}
	
}
