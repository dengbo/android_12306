package com.dengbo.model;

import com.dengbo.util.StringPoolUtil;

import android.os.Bundle;

public class ReqLogin extends ReqNetDate{

	@Override
	public void initHeadParam() {
		// TODO Auto-generated method stub
		super.initHeadParam();
		headParamHashMap.put("Accept","text/html, application/xhtml+xml, */*");
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
		String[] dataStrings = mBundle.getStringArray(StringPoolUtil.LOGIN);
		paramHashMap.put(StringPoolUtil.LOGIN_RAND, dataStrings[3]);
		paramHashMap.put(StringPoolUtil.LOGIN_REFUND, "N");
		paramHashMap.put(StringPoolUtil.LOGIN_FLAG, "Y");
		paramHashMap.put(StringPoolUtil.LOGIN_USER, dataStrings[0]);
		paramHashMap.put(StringPoolUtil.LOGIN_NAME_F, "");
		paramHashMap.put(StringPoolUtil.LOGIN_PW, dataStrings[1]);
		paramHashMap.put(StringPoolUtil.LOGIN_PW_F, "");
		paramHashMap.put(StringPoolUtil.LOGIN_CHECK, dataStrings[2]);
		paramHashMap.put(StringPoolUtil.LOGIN_CHECK_F, "");
	}

}
