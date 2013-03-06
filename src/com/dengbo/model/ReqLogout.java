package com.dengbo.model;

public class ReqLogout extends ReqNetDate{

	@Override
	public void initHeadParam() {
		// TODO Auto-generated method stub
		super.initHeadParam();
		headParamHashMap.put("Accept","text/html, application/xhtml+xml, */*");
		headParamHashMap.put("Referer","https://dynamic.12306.cn/otsweb/");
	}

}
