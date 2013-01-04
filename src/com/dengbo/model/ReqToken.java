package com.dengbo.model;

public class ReqToken extends ReqNetDate{

	@Override
	public void initHeadParam() {
		// TODO Auto-generated method stub
		super.initHeadParam();
		headParamHashMap.put("Accept",	"text/html, application/xhtml+xml, */*");
	}

}
