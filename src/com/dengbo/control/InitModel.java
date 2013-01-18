package com.dengbo.control;

import android.os.Bundle;

import com.dengbo.model.ReqBook;
import com.dengbo.model.ReqCheckImg;
import com.dengbo.model.ReqLogin;
import com.dengbo.model.ReqLoginToken;
import com.dengbo.model.ReqNetDate;
import com.dengbo.model.ReqOrderUnpaid;
import com.dengbo.model.ReqQuery;
import com.dengbo.model.ReqToken;
import com.dengbo.util.StringPoolUtil;

public class InitModel {
	// 所有的url
	static String initCookieString = "https://dynamic.12306.cn/otsweb/";
	static String getCheckImgString = "https://dynamic.12306.cn/otsweb/passCodeAction.do?rand=sjrand";
	static String f5CheckImgString = "https://dynamic.12306.cn/otsweb/passCodeAction.do?rand=sjrand&"
			+ Math.random();
	static String loginString = "https://dynamic.12306.cn/otsweb/loginAction.do?method=login";
	static String loginAuthString = "https://dynamic.12306.cn/otsweb/loginAction.do?method=loginAysnSuggest";
	static String yupiaoReqString = "http://dynamic.12306.cn/otsquery/query/queryRemanentTicketAction.do";
	public static String getOrderTokenString = "https://dynamic.12306.cn/otsweb/order/myOrderAction.do?method=init&showMessage=Y";
	static String orderUnpaidUrl = "http://dynamic.12306.cn/otsweb/myOrderAction.do";
	static String queryString ="https://dynamic.12306.cn/otsweb/order/querySingleAction.do";
	static String bookString = "";

	public static ReqNetDate initModel(String action, Bundle mBundle) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		ReqNetDate reqNetDate = null;
//		HashMap<String, String> model = App.modelHashMap.get(action);
//		String nameString =model.get(StringPoolUtil.KEY_NAME);
//		String urlString = model.get(StringPoolUtil.KEY_ACTION);
//		String protocolString = model.get(StringPoolUtil.KEY_PRO);
//		String methodString =model.get(StringPoolUtil.KEY_METHOD);
//		Class reqClass = Class.forName(nameString);
//		reqNetDate = (ReqNetDate) reqClass.newInstance();
//		reqNetDate.setMethod(protocolString);
//		reqNetDate.setUrl(urlString);
//		reqNetDate.setProtocol(methodString);
		if (action.equalsIgnoreCase(StringPoolUtil.GET_CHECK_IMG)) {
			reqNetDate = new ReqCheckImg();
			reqNetDate.setMethod("GET");
			reqNetDate.setUrl(getCheckImgString);
			reqNetDate.setProtocol("https");
		} else if (action.equalsIgnoreCase(StringPoolUtil.F5_CHECK_IMG)) {
			reqNetDate = new ReqCheckImg();
			reqNetDate.setMethod("GET");
			reqNetDate.setUrl(f5CheckImgString);
			reqNetDate.setProtocol("https");
		} else if (action.equalsIgnoreCase(StringPoolUtil.SEND_LOGIN_AUTH)) {
			reqNetDate = new ReqLoginToken();
			reqNetDate.setMethod("POST");
			reqNetDate.setUrl(loginAuthString);
			reqNetDate.setProtocol("https");
		} else if (action.equalsIgnoreCase(StringPoolUtil.SEND_LOGIN)) {
			reqNetDate = new ReqLogin();
			reqNetDate.setMethod("POST");
			reqNetDate.setUrl(loginString);
			reqNetDate.setProtocol("https");
		} else if (action.equalsIgnoreCase(StringPoolUtil.CHECK_ORDER_UNPAID)) {
			reqNetDate = new ReqOrderUnpaid();
			reqNetDate.setMethod("GET");
			reqNetDate.setUrl(orderUnpaidUrl);
			reqNetDate.setProtocol("https");
		}
		else if (action.equalsIgnoreCase(StringPoolUtil.GET_Order_TOKRN)) {
			reqNetDate = new ReqToken();
			reqNetDate.setMethod("GET");
			reqNetDate.setProtocol("https");
			reqNetDate.setUrl(getOrderTokenString);
		}
		else if(action.equalsIgnoreCase(StringPoolUtil.QUERY_TICKET))
		{
			reqNetDate = new ReqQuery();
			reqNetDate.setMethod("GET");
			reqNetDate.setProtocol("https");
			reqNetDate.setUrl(queryString);
		}
		else if(action.equalsIgnoreCase(StringPoolUtil.QUERY_BOOK))
		{
			reqNetDate = new ReqBook();
			reqNetDate.setMethod("PUT");
			reqNetDate.setProtocol("https");
			reqNetDate.setUrl(bookString);
		}
		reqNetDate.initHeadParam();
		reqNetDate.initParam(mBundle);
		reqNetDate.setAction(action);
		return reqNetDate;
	}
//	public static void register(String name , String action , String url , String protocol , String method)
//	{
//		HashMap<String, String> model = new HashMap<String, String>();
//		model.put(StringPoolUtil.KEY_NAME, name);
//		model.put(StringPoolUtil.KEY_ACTION, action);
//		model.put(StringPoolUtil.KEY_URL, url);
//		model.put(StringPoolUtil.KEY_PRO, protocol);
//		model.put(StringPoolUtil.KEY_METHOD, method);
//		App.modelHashMap.put(action, model);
//	}
}
