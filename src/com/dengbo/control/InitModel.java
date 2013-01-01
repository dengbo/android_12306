package com.dengbo.control;

import android.os.Bundle;

import com.dengbo.model.ReqCheckImg;
import com.dengbo.model.ReqCookie;
import com.dengbo.model.ReqLogin;
import com.dengbo.model.ReqLoginToken;
import com.dengbo.model.ReqNetDate;
import com.dengbo.model.ReqOrderUnpaid;
import com.dengbo.util.StringPoolUtil;

public class InitModel {
	// 所有用到的url
	private static String initCookieString = "https://dynamic.12306.cn/otsweb/";
	private static String getCheckImgString = "https://dynamic.12306.cn/otsweb/passCodeAction.do?rand=sjrand";
	private static String f5CheckImgString = "https://dynamic.12306.cn/otsweb/passCodeAction.do?rand=sjrand&"
			+ Math.random();
	private static String loginString = "https://dynamic.12306.cn/otsweb/loginAction.do?method=login";
	private static String loginAuthString = "https://dynamic.12306.cn/otsweb/loginAction.do?method=loginAysnSuggest";
	private static String yupiaoReqString = "http://dynamic.12306.cn/otsquery/query/queryRemanentTicketAction.do";
	private static final String orderUnpaidUrl = "http://dynamic.12306.cn/otsweb/myOrderAction.do?method=queryMyOrderNotComplete&leftmenu=Y";

	public static ReqNetDate initModel(String action, Bundle mBundle) {
		ReqNetDate reqNetDate = null;
		if (action.equalsIgnoreCase(StringPoolUtil.GET_CHECK_IMG)) {
			reqNetDate = new ReqCheckImg();
			reqNetDate.setMethod("GET");
			reqNetDate.setUrl(f5CheckImgString);
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
		reqNetDate.initHeadParam();
		reqNetDate.initParam(mBundle);
		reqNetDate.setAction(action);
		return reqNetDate;
	}
}
