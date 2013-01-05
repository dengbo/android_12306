package com.dengbo.model;

import com.dengbo.util.StringPoolUtil;

import android.os.Bundle;

public class ReqLogin extends ReqNetDate{

//	public ReqLogin() {
//		// TODO Auto-generated constructor stub
//		super();
//		register();
//	}
	@Override
	public void initHeadParam() {
		// TODO Auto-generated method stub
		super.initHeadParam();
		headParamHashMap.put("Accept","text/html, application/xhtml+xml, */*");
		headParamHashMap.put("Referer","https://dynamic.12306.cn/otsweb/loginAction.do?method=init");
		headParamHashMap.put("Content-Type", "application/x-www-form-urlencoded");
	}

	@Override
	public void initParam(Bundle mBundle) {
		// TODO Auto-generated method stub
		/*
		 * loginRand=465&refundLogin=N&refundFlag=Y&loginUser.user_name=username
		 * &nameErrorFocus=&user.password=userpasseord&passwordErrorFocus=
		 * &randCode=5EH8&randErrorFocus=
		 */
		super.initParam(mBundle);
		String[] dataStrings = mBundle.getStringArray(StringPoolUtil.LOGIN);
		param.append("loginRand=").append(dataStrings[3])
		.append("&refundLogin=N&refundFlag=Y&loginUser.user_name=")
		.append(dataStrings[0])
		.append("&nameErrorFocus=&user.password=")
		.append(dataStrings[1])
		.append("&passwordErrorFocus=&randCode=")
		.append(dataStrings[2])
		.append("&randErrorFocus=focus");
	}

//	//所有的子类重载该方法，调用initModel的register
//	public static void register() {
//		// TODO Auto-generated method stub
//		InitModel.register("ReqLogin", StringPoolUtil.SEND_LOGIN, StringPoolUtil.loginString, "https", "POST");
//		return ;
//	}

}
