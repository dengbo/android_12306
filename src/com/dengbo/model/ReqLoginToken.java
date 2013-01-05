package com.dengbo.model;



public class ReqLoginToken extends ReqNetDate{

//	public ReqLoginToken() {
//		// TODO Auto-generated constructor stub
//		super();
//		register();
//	}
	@Override
	public void initHeadParam() {
		// TODO Auto-generated method stub
		super.initHeadParam();
		headParamHashMap.put("Accept", "application/json, text/javascript, */*");
		headParamHashMap.put("Content-Length", "0");
		headParamHashMap.put("Content-Type", "application/x-www-form-urlencoded");
	}

//	//所有的子类重载该方法，调用initModel的register
//	public static void register() {
//		// TODO Auto-generated method stub
//		InitModel.register("ReqLoginToken", StringPoolUtil.SEND_LOGIN_AUTH, StringPoolUtil.loginAuthString, "https", "POST");
//		return ;
//	}
}
