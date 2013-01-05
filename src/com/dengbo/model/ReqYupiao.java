package com.dengbo.model;

//import com.dengbo.control.InitModel;
//import com.dengbo.util.StringPoolUtil;

import android.os.Bundle;

public class ReqYupiao extends ReqNetDate{

//	public ReqYupiao() {
//		// TODO Auto-generated constructor stub
//		super();
//		register();
//	}
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

//	//所有的子类重载该方法，调用initModel的register
//	public static void register() {
//		// TODO Auto-generated method stub
//		InitModel.register("ReqYupiao", StringPoolUtil.SEND_YUPIAOREQ, StringPoolUtil.yupiaoReqString, "https", "GET");
//		return ;
//	}

}
