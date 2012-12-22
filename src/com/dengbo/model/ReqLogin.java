package com.dengbo.model;

import com.dengbo.util.StringPoolUtil;

import android.os.Bundle;

public class ReqLogin extends ReqNetDate{

	@Override
	public void initHeadParam() {
		// TODO Auto-generated method stub
		super.initHeadParam();
		headParamHashMap.put("Accept","text/html, application/xhtml+xml, */*");
		headParamHashMap.put("Referer","https://dynamic.12306.cn/otsweb/loginAction.do?method=init");
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

}
