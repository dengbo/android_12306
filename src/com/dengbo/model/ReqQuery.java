package com.dengbo.model;

import android.os.Bundle;

public class ReqQuery extends ReqNetDate{

	@Override
	public void initHeadParam() {
		// TODO Auto-generated method stub
		super.initHeadParam();

//		 x-requested-with	XMLHttpRequest
//		 Accept-Language	zh-cn
//		 Referer	https://dynamic.12306.cn/otsweb/order/querySingleAction.do?method=init
//		 Accept	text/plain, */*
//		 Content-Type	application/x-www-form-urlencoded
//		 Accept-Encoding	gzip, deflate
//		 User-Agent	Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)
//		 Host	dynamic.12306.cn
//		 Connection	Keep-Alive
//		 Cookie	JSESSIONID=2CF1031EA694BE09A879B3819CA1BCD7; BIGipServerotsweb=2463367434.48160.0000
		 headParamHashMap.put("x-requested-with", "XMLHttpRequest");
		 headParamHashMap.put("Accept", "text/plain, */*");
		 headParamHashMap.put("Accept-Encoding", "gzip, deflate");
		 headParamHashMap.put("Content-Type", "application/x-www-form-urlencoded");
		 headParamHashMap.put("Referer", "https://dynamic.12306.cn/otsweb/order/querySingleAction.do?method=init");
	}

	@Override
	public void initParam(Bundle mBundle) {
		// TODO Auto-generated method stub
		///otsweb/order/querySingleAction.do?method=queryLeftTicket&orderRequest.train_date=2013-01-14&
		//orderRequest.from_station_telecode=BJP&orderRequest.to_station_telecode=SHH&orderRequest.train_no=&
		//trainPassType=QB&trainClass=QB%23D%23Z%23T%23K%23QT%23&includeStudent=00&seatTypeAndNum=&
		//orderRequest.start_time_str=00%3A00--24%3A00
		super.initParam(mBundle);
		
	}

}
