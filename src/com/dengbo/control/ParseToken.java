package com.dengbo.control;

import java.io.ByteArrayOutputStream;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dengbo.util.CommonUtil;

import android.os.Bundle;

public class ParseToken extends ParseHtmlBase{

	public ParseToken(ByteArrayOutputStream outputStream) {
		super(outputStream);
		// TODO Auto-generated constructor stub
	}

	@Override
	Bundle parse(ByteArrayOutputStream stream) {
		// TODO Auto-generated method stub
		Elements result = getElementBySelector("input[name='org.apache.struts.taglib.html.TOKEN']");
		CommonUtil.tokenList.clear();
		for (Element element : result) {
			CommonUtil.tokenList.add(element.attr("value"));
		}
		Bundle mBundle = new Bundle();
		mBundle.putBoolean("hidden", true); 		//只是为了统一格式，没有用
		return mBundle;
	}

}
