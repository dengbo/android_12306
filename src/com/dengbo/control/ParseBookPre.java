package com.dengbo.control;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.HashSet;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dengbo.util.StringPoolUtil;

import android.os.Bundle;

public class ParseBookPre extends ParseHtmlBase{

	public ParseBookPre(ByteArrayOutputStream outputStream) {
		super(outputStream);
		// TODO Auto-generated constructor stub
	}

	@Override
	Bundle parse(ByteArrayOutputStream stream) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder paramBuilder = new StringBuilder();
		HashSet<String> set = new HashSet<String>();
		set.add("leftTicketStr");
		set.add("orderRequest.train_date");
		set.add("orderRequest.train_no");
		set.add("orderRequest.station_train_code");
		set.add("orderRequest.from_station_telecode");
		set.add("orderRequest.to_station_telecode");
		String[] params = {"org.apache.struts.taglib.html.TOKEN","leftTicketStr","textfield","orderRequest.train_date","orderRequest.train_no"
				,"orderRequest.station_train_code","orderRequest.from_station_telecode","orderRequest.to_station_telecode","orderRequest.seat_type_code"
				,"orderRequest.ticket_type_order_num","orderRequest.bed_level_order_num","orderRequest.start_time"
				,"orderRequest.end_time","orderRequest.from_station_name","orderRequest.to_station_name","orderRequest.cancel_flag"
				,"orderRequest.id_mode"};
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
		mBuilder.append("|");
		Elements inputElements = getElementBySelector("input");
		for(String paramString : params)
		{
			Elements input = inputElements.select("input[name="+paramString+"]");
			Element element = input.first();
			mBuilder.append(paramString).append("=");
			String tmp = URLEncoder.encode(element.attr("value"), "UTF-8");
			mBuilder.append(tmp);
			mBuilder.append("&");
			if(set.contains(paramString))
			{
				paramBuilder.append(tmp).append(",");
			}
		}
		mBuilder.append("checkbox0=0&");
		mBuilder.append("|");
		mBuilder.append(paramBuilder);
		Bundle mBundle = new Bundle();
		mBundle.putString(StringPoolUtil.BOOK, mBuilder.toString());
		return mBundle;
	}

}
