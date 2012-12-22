package com.dengbo.model;


public class ReqLoginToken extends ReqNetDate{

	@Override
	public void initHeadParam() {
		// TODO Auto-generated method stub
		super.initHeadParam();
		headParamHashMap.put("Accept", "application/json, text/javascript, */*");
		headParamHashMap.put("Content-Length", "0");
	}
}
