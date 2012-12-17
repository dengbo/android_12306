package com.dengbo.model;

import java.util.HashMap;

import android.os.Bundle;

public class ReqNetDate {
	protected String urlString;
	protected String protocolString;
	protected HashMap<String, String> headParamHashMap;
	protected HashMap<String,String> paramHashMap;
	protected String methodString;
	protected String actionString;
	public ReqNetDate()
	{
		urlString = "";
		methodString = "";
		protocolString = "";
		actionString = "";
		headParamHashMap = new HashMap<String, String>();
		paramHashMap = new HashMap<String, String>();
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

	}
	public HashMap<String, String> getParam() {
		return paramHashMap;
	}
	public void initParam(Bundle mBundle) {

	}
}
