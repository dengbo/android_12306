package com.dengbo.model;

public class ReqCookie extends ReqNetDate{

	@Override
	public void setProtocol(String protocol) {
		// TODO Auto-generated method stub
		super.setProtocol(protocol);
	}

	@Override
	public void setUrl(String url) {
		// TODO Auto-generated method stub
		super.setUrl(url);
	}

	@Override
	public void setMethod(String method) {
		// TODO Auto-generated method stub
		super.setMethod(method);
	}

	@Override
	public void initHeadParam() {
		// TODO Auto-generated method stub
		super.initHeadParam();
		//Accept	text/html, application/xhtml+xml, */*
		//Accept-Language	zh-CN
		//User-Agent	Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)
		//Accept-Encoding	gzip, deflate
		//Host	dynamic.12306.cn
		//Connection	Keep-Alive
		//Cache-Control	no-cache
		headParamHashMap.put("Accept", "text/html, application/xhtml+xml, */*");
		headParamHashMap.put("Accept-Language", "zh-CN");
		headParamHashMap.put("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
		headParamHashMap.put("Accept-Encoding", "gzip, deflate");
		headParamHashMap.put("Cache-Control", "no-cache");
	}

}
