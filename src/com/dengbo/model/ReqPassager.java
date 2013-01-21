package com.dengbo.model;

import android.os.Bundle;

public class ReqPassager extends ReqNetDate{

	@Override
	public void initHeadParam() {
		// TODO Auto-generated method stub
		//Referer
		//Accept	application/json, text/javascript, */*
		//Content-Type	application/x-www-form-urlencoded
		//Content-Length	0
		super.initHeadParam();
		headParamHashMap.put("Referer", "https://dynamic.12306.cn/otsweb/order/confirmPassengerAction.do?method=init");
		headParamHashMap.put("Accept", "application/json, text/javascript, */*");
		headParamHashMap.put("Content-Type", "application/x-www-form-urlencoded");
		headParamHashMap.put("Content-Length", "0");
	}

	@Override
	public void initParam(Bundle mBundle) {
		// TODO Auto-generated method stub
		super.initParam(mBundle);
	}

}
