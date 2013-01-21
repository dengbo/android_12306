package com.dengbo.control;

import java.io.ByteArrayOutputStream;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dengbo.util.StringPoolUtil;

import android.os.Bundle;

public class ParseBook extends ParseHtmlBase{

	public ParseBook(ByteArrayOutputStream outputStream) {
		super(outputStream);
		// TODO Auto-generated constructor stub
	}

	@Override
	Bundle parse(ByteArrayOutputStream stream) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder mBuilder = new StringBuilder();
		Elements tableElements = getElementBySelector("table");
		Element table = tableElements.first();
		Elements trElements = table.getElementsByTag("tr");
		for (int i= 0 ; i<2 ;i++) {
			Elements tdElements = trElements.get(i).getElementsByTag("td");
			for (Element element2 : tdElements) {
				mBuilder.append(element2.html()).append(",");
			}
		}
		Bundle mBundle = new Bundle();
		mBundle.putString(StringPoolUtil.BOOK, mBuilder.toString());
		return mBundle;
	}

}
