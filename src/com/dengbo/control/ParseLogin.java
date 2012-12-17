package com.dengbo.control;

import java.io.ByteArrayOutputStream;
import org.jsoup.select.Elements;

import com.dengbo.util.CommonUtil;
import com.dengbo.util.StringPoolUtil;
import com.dengbo.view.LoginActivity;

import android.os.Bundle;
import android.util.Log;

public class ParseLogin extends ParseHtmlBase{

	public ParseLogin(ByteArrayOutputStream outputStream) {
		super(outputStream);
		// TODO Auto-generated constructor stub
	}

	@Override
	Bundle parse(ByteArrayOutputStream stream) {
		// TODO Auto-generated method stub
		Bundle mBundle = new Bundle();
		Log.v(LoginActivity.LOG, document.html());
		Elements elements = getElementBySelector("#randErr");
		if (elements !=null&&!elements.isEmpty())
		{
			mBundle.putBoolean(StringPoolUtil.ERROR, true);
			mBundle.putString(StringPoolUtil.ERROR_REASON, StringPoolUtil.CHECK_IMG_ERROR);
			return mBundle;
		}
		Elements element = getElementBySelector("title");
		if(element!=null && element.first().html().equals("系统消息"))
		{
			mBundle.putBoolean(StringPoolUtil.ERROR, false);
			return mBundle;
		}
		else {
			mBundle.putBoolean(StringPoolUtil.ERROR, true);
			mBundle.putString(StringPoolUtil.ERROR_REASON, StringPoolUtil.NAME_OR_PW_ERROR);
			return mBundle;
		}
	}

}
