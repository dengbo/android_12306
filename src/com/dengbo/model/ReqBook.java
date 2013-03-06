package com.dengbo.model;

import com.dengbo.util.StringPoolUtil;

import android.os.Bundle;

public class ReqBook extends ReqNetDate{

	@Override
	public void initHeadParam() {
		// TODO Auto-generated method stub
		super.initHeadParam();
		headParamHashMap.put("Accept",	"application/json, text/javascript, */*");
		headParamHashMap.put("Content-Type", "application/x-www-form-urlencoded");
		headParamHashMap.put("Referer","https://dynamic.12306.cn/otsweb/order/confirmPassengerAction.do?method=init");
	}

	@Override
	public void initParam(Bundle mBundle) {
		// TODO Auto-generated method stub
		super.initParam(mBundle);
		param.append(mBundle.get(StringPoolUtil.SUB_BOOKS));
		setUrl("https://dynamic.12306.cn/otsweb/order/confirmPassengerAction.do?" +
				"method=checkOrderInfo&rand="+mBundle.getString(StringPoolUtil.GET_CHECK_IMG));
	}
}
