package com.dengbo.model;

import java.util.HashMap;

import com.dengbo.util.CommonUtil;

import android.os.Bundle;
import android.text.StaticLayout;

public abstract class ReqNetDate {
	protected String urlString;
	protected String protocolString;
	protected HashMap<String, String> headParamHashMap;
	protected StringBuilder param;
	protected String methodString;
	protected String actionString;
	public ReqNetDate()
	{
		urlString = "";
		methodString = "";
		protocolString = "";
		actionString = "";
		param = new StringBuilder("");
		headParamHashMap = new HashMap<String, String>();
	}
	public void setAction(String action) {
		actionString = action;
	}
	public String getAction() {
		return actionString;
	}
	public void setProtocol(String protocol) {
		protocolString = protocol;
	}
	public String getProtocol() {
		return protocolString;
	}
	public void setUrl(String url) {
		urlString = url;
	}
	public String getUrl() {
		return urlString;
	}
	public void setMethod(String method) {
		methodString = method;
	}
	public String getMethod() {
		return methodString;
	}
	public HashMap<String, String> getHeadParam() {
		return headParamHashMap;
	}
	public void initHeadParam() {
		headParamHashMap.put("Accept-Language", "zh-CN");
		headParamHashMap.put("User-Agent", " Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
		headParamHashMap.put("Host","dynamic.12306.cn");
		headParamHashMap.put("Connection", " Keep-Alive");
		//headParamHashMap.put("Cache-Control", " no-cache");
		headParamHashMap.put("Cookie", CommonUtil.getCookie());
	}
	public String getParam()
	{
		return param.toString();
	}
	public void initParam(Bundle mBundle) {

	}
//	//所有的子类重载该方法，调用initModel的register,并在相应的activity里调用model的register，初始化
//	public static  void register()
//	{
//		return ;
//	}
}
