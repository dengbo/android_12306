package com.dengbo.model;

import android.os.Bundle;

public class ReqBookCheckImg extends ReqNetDate{

	@Override
	public void initHeadParam() {
		// TODO Auto-generated method stub
		super.initHeadParam();
		headParamHashMap.put("Accept", "image/png, image/svg+xml, image/*;q=0.8, */*;q=0.5");
		headParamHashMap.put("Referer","https://dynamic.12306.cn/otsweb/order/confirmPassengerAction.do?method=init");
		headParamHashMap.put("Accept-Encoding","gzip, deflate");
	}

	@Override
	public void initParam(Bundle mBundle) {
		// TODO Auto-generated method stub
		super.initParam(mBundle);
	}

}
