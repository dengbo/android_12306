package com.dengbo.model;

import com.dengbo.control.InitModel;
import com.dengbo.util.StringPoolUtil;

import android.os.Bundle;

public class ReqOrderUnpaid extends ReqNetDate {
//	public ReqOrderUnpaid() {
//		// TODO Auto-generated constructor stub
//		super();
//		register();
//	}
	@Override
	public void initHeadParam() {
		super.initHeadParam();
		headParamHashMap.put("accept", "text/html, application/xhtml+xml, */*");
		headParamHashMap.put("Content-Type", "application/x-www-form-urlencoded");
	}

	@Override
	public void initParam(Bundle mBundle) {
		super.initParam(mBundle);
		// paramHashMap.put("method", "queryMyOrderNotComplete");
		param.append("method=queryMyOrderNotComplete&leftmenu=Y");
	}

//	//所有的子类重载该方法，调用initModel的register
//	public static void register() {
//		// TODO Auto-generated method stub
//		InitModel.register("ReqOrderUnpaid", StringPoolUtil.CHECK_ORDER_UNPAID, StringPoolUtil.orderUnpaidUrl, "https", "GET");
//		return ;
//	}
}
