package com.dengbo.model;

import android.os.Bundle;

public class ReqYupiao extends ReqNetDate{

	@Override
	public void initHeadParam() {
		// TODO Auto-generated method stub
		super.initHeadParam();
		headParamHashMap.put("Accept","application/json, text/javascript, */*");
		headParamHashMap.put("Content-Type", "application/x-www-form-urlencoded");
	}

	@Override
	public void initParam(Bundle mBundle) {
		// TODO Auto-generated method stub
		super.initParam(mBundle);

	}
	//不同车次之间的#号用%23替代连接，时间之间的冒号用%3A替代

}
