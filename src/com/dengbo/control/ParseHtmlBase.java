package com.dengbo.control;

import java.io.ByteArrayOutputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.dengbo.util.CommonUtil;

public abstract class ParseHtmlBase extends ParseBase{
	protected Document document;
	protected String htmlString;
	public ParseHtmlBase(ByteArrayOutputStream outputStream)
	{
		htmlString = CommonUtil.byteArrayOutStreamToString(outputStream);
		document = Jsoup.parse(htmlString);
	}
	protected Elements getElementBySelector(String cssQuery) {
		return document.select(cssQuery);
	}
}
