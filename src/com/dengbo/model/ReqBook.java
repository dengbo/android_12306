package com.dengbo.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.dengbo.util.StringPoolUtil;

import android.os.Bundle;

public class ReqBook extends ReqNetDate{

	@Override
	public void initHeadParam() {
		// TODO Auto-generated method stub
		super.initHeadParam();
		headParamHashMap.put("Accept","text/html, application/xhtml+xml, */*");
		headParamHashMap.put("Referer", "https://dynamic.12306.cn/otsweb/order/querySingleAction.do?method=init");
	}

	@Override
	public void initParam(Bundle mBundle) {
		// TODO Auto-generated method stub
		super.initParam(mBundle);
		String[] linkString = mBundle.getString(StringPoolUtil.QUERY_ORDER_LINK).split("#");
		int length = linkString.length;
		try {
			String tmp = linkString[length-2].replaceAll(":", "%3A");
			String tmp1 = URLEncoder.encode(linkString[7],"UTF-8");
			String tmp2 = URLEncoder.encode(linkString[8], "UTF-8");
			param.append("station_train_code=").append(linkString[0]).append("&train_date=").append(linkString[length-1])
			.append("&seattype_num=&from_station_telecode=").append(linkString[4]).append("&to_station_telecode=").append(linkString[5])
			.append("&include_student=00&from_station_telecode_name=").append(tmp1)
			.append("&to_station_telecode_name=").append(tmp2)
			.append("&round_train_date=").append(linkString[length-1]).append("&round_start_time_str=")
			.append(tmp).append("&single_round_type=1&train_pass_type=QB&train_class_arr=")
			.append(linkString[length-3].replaceAll("#", "%23")).append("&start_time_str=").append(tmp)
			.append(linkString[1].replaceAll(":", "%3A")).append("&train_start_time=")
			.append(linkString[2].replaceAll(":", "%3A")).append("&trainno4=")
			.append(linkString[3]).append("&arrive_time=").append(linkString[6].replaceAll(":", "%3A"))
			.append("&from_station_name=").append(tmp1).append("&to_station_name=").append(tmp2)
			.append("&from_station_no=").append(linkString[9]).append("&to_station_no=").append(linkString[10])
			.append("&ypInfoDetail=").append(linkString[11]).append("&mmStr=").append(linkString[12])
			.append("&locationCode=").append(linkString[13]);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
