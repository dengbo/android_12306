package com.dengbo.model;

//import com.dengbo.control.InitModel;
//import com.dengbo.util.StringPoolUtil;

public class ReqToken extends ReqNetDate{

//	public ReqToken() {
//		// TODO Auto-generated constructor stub
//		super();
//		register();
//	}
	@Override
	public void initHeadParam() {
		// TODO Auto-generated method stub
		//Accept	text/html, application/xhtml+xml, */*
		super.initHeadParam();
		headParamHashMap.put("Accept",	"text/html, application/xhtml+xml, */*");
		headParamHashMap.put("Accept-Encoding",	"gzip, deflate");
		headParamHashMap.put("Referer",	"https://dynamic.12306.cn/otsweb/loginAction.do?method=login");
	}

//	//所有的子类重载该方法，调用initModel的register
//	public static void register() {
//		// TODO Auto-generated method stub
//		InitModel.register("ReqToken", StringPoolUtil.GET_Order_TOKRN, StringPoolUtil.getOrderTokenString, "https", "GET");
//		return ;
//	}

}
