package com.dengbo.control;

import java.io.ByteArrayOutputStream;

import com.dengbo.util.StringPoolUtil;

import android.os.Bundle;

public class Parse {
	public Bundle parseStream(String actionString , ByteArrayOutputStream stream) throws Exception
	{
		if (actionString.equals(StringPoolUtil.GET_CHECK_IMG) || actionString.equals(StringPoolUtil.BOOK_IMG)) {
			return new ParseCheckImg().parse(stream);
		}
		else if (actionString.equals(StringPoolUtil.F5_CHECK_IMG) || actionString.equals(StringPoolUtil.F5_BOOK_IMG)) {
			return new ParseCheckImg().parse(stream);
		}
		else if (actionString.equals(StringPoolUtil.SEND_LOGIN_AUTH)) {
			return new ParseLoginAuth().parse(stream);
		}
		else if (actionString.equals(StringPoolUtil.SEND_LOGIN)) {
			return new ParseLogin(stream).parse(stream);
		}
		else if(actionString.equals(StringPoolUtil.CHECK_ORDER_UNPAID)){
			return new ParseOrderUnpaid(stream).parse(stream);
		}
		else if(actionString.equals(StringPoolUtil.GET_Order_TOKRN)){
			return new ParseToken(stream).parse(stream);
		}
		else if(actionString.equals(StringPoolUtil.QUERY_TICKET))
		{
			return new ParseQuery().parse(stream);
		}else if(actionString.equalsIgnoreCase(StringPoolUtil.REG_CHECK_IMG)){
			return new ParseCheckImg().parse(stream);
		}else if(actionString.equalsIgnoreCase(StringPoolUtil.REG_F5_CHECK_IMG)){
			return new ParseCheckImg().parse(stream);
		}
		else if(actionString.equals(StringPoolUtil.QUERY_BOOK))
		{
			return new ParseBookPre(stream).parse(stream);
		}
		else if(actionString.equals(StringPoolUtil.READ_PASSAGER))
		{
			return new ParsePassenger().parse(stream);
		}
		else if(actionString.equals(StringPoolUtil.SUB_BOOK) || actionString.equals(StringPoolUtil.SUB_BOOK_2))
		{
			return new ParseBook().parse(stream);
		}
		else if(actionString.equals(StringPoolUtil.SUB_BOOK_1))
		{
			return new ParseBook1().parse(stream);
		}

		return null;
	}
}
