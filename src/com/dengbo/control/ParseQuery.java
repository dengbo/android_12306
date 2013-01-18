package com.dengbo.control;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dengbo.util.CommonUtil;
import com.dengbo.util.StringPoolUtil;

import android.os.Bundle;
import android.util.Log;

public class ParseQuery extends ParseBase {

	@Override
	Bundle parse(ByteArrayOutputStream stream) throws Exception {
		// TODO Auto-generated method stub
		String htmlString = CommonUtil.byteArrayOutStreamToString(stream);
		if (htmlString.equals("-10")) {
			Exception mException = new Exception(
					"您还没有登录或者离开页面的时间过长，请登录系统或者刷新页面");
			throw mException;
		} else if (htmlString.equals("-1")) {
			Exception mException = new Exception("服务器忙，加载查询数据失败！");
			throw mException;
		} else {
			Pattern mPattern_a = Pattern.compile("<a [^>]*>");
			Matcher matcher_a = mPattern_a.matcher(htmlString);
			//HashMap<Integer, String> map = new HashMap<Integer, String>();
			ArrayList<String> mList = new ArrayList<String>();
			int key = 0;
			while (matcher_a.find()) {
				String result = matcher_a.group();
				int index = result.indexOf("('");
				if(index == -1)
				{
					mList.add("");
				}
				else {
					mList.add(result.substring(index+2, result.length()-3));
				}
				key++;
			}


			Pattern mPattern = Pattern.compile("<[^(img)][^>]*>");
			Matcher matcher = mPattern.matcher(htmlString);
			StringBuffer sb = new StringBuffer();
			while (matcher.find()) {
				matcher.appendReplacement(sb, "");
			}
			matcher.appendTail(sb);
			String tmpString = sb.toString().replaceAll("&nbsp;", "");

			Pattern pattern = Pattern.compile(",预订.{0,2}[0-9]+,");
			Matcher matchers = pattern.matcher(tmpString);
			StringBuffer buf = new StringBuffer();
			while (matchers.find()) {
				matchers.appendReplacement(buf, "|");
			}
			matchers.appendTail(buf);
			String tmp = buf.toString();
			Log.v("ParseQuery", tmp);
			Bundle mBundle = new Bundle();
			mBundle.putString(StringPoolUtil.QUERY_RESULT, tmp.substring(2));
			mBundle.putStringArrayList(StringPoolUtil.QUERY_ORDER_LINK, mList);
			return mBundle;
		}
	}

}
