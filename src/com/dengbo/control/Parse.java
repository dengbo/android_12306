package com.dengbo.control;

import java.io.ByteArrayOutputStream;

import com.dengbo.util.StringPoolUtil;

import android.os.Bundle;

public class Parse {
	public Bundle parseStream(String actionString , ByteArrayOutputStream stream)
	{
		if (actionString.equalsIgnoreCase(StringPoolUtil.GET_CHECK_IMG)) {
			return new ParseCheckImg().parse(stream);
		}
		else if (actionString.equalsIgnoreCase(StringPoolUtil.F5_CHECK_IMG)) {
			return new ParseCheckImg().parse(stream);
		}
		else if (actionString.equalsIgnoreCase(StringPoolUtil.SEND_LOGIN_AUTH)) {
			return new ParseLoginAuth().parse(stream);
		}
		else if (actionString.equalsIgnoreCase(StringPoolUtil.SEND_LOGIN)) {
			return new ParseLogin(stream).parse(stream);
		}
		else if(actionString.equals(StringPoolUtil.CHECK_ORDER_UNPAID)){
			return new ParseOrderUnpaid(stream).parse(stream);
		}
		return null;
	}
}
