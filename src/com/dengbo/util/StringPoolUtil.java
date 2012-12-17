package com.dengbo.util;

public interface StringPoolUtil {
	/*
	 * 所有的action 字符串
	 */
	String GET_CHECK_IMG = "com.dengbo.view.getCheckImg";
	String F5_CHECK_IMG = "com.dengbo.view.refreshCheckImg";
	String SEND_LOGIN = "com.dengbo.view.sendLogin";
	String SEND_LOGIN_AUTH = "com.dengbo.view.sendLoginAuth";
	String SEND_YUPIAOREQ = "com.dengbo.view.sendYupiaoReq";

	/*
	 * 所有的请求命令，相当于命令表，统一管理
	 */


	/*
	 * 错误原因
	 */
	String CHECK_IMG_ERROR = "checkImgError";
	String NAME_OR_PW_ERROR = "nameOrPwError";

	/*
	 * 所有的信息交互key,主要用于信息存在bundle中流动时，get和put使用同一份key，避免造成混乱
	 */
	String RESP_CHECK = "resp_check"; 			//请求返回的验证码
	String ERROR = "error";						//错误信息
	String ERROR_REASON = "error_reson";		//错误原因

	//login 界面
	String LOGIN_ERROR = "loginerror";			//登录请求auth中返回的error字符串

	String LOGIN = "login";						//登录时发送数据
	String LOGIN_RAND = "loginrand";			//登录请求中的loginrand参数已经auth中返回的loginrand字符串
	String LOGIN_USER = "loginUser.user_name";			//登录请求中的用户名
	String LOGIN_PW = "user.password";			    //登录请求中的密码
	String LOGIN_CHECK = "randCode";			//登录请求中的验证码
	String LOGIN_REFUND = "refundLogin";
	String LOGIN_FLAG = "refundFlag";
	String LOGIN_NAME_F = "nameErrorFocus";
	String LOGIN_PW_F = "passwordErrorFocus";
	String LOGIN_CHECK_F = "randErrorFocus";
}
