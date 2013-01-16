package com.dengbo.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.dengbo.view.R;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.util.Log;

public class CommonUtil {
	static String LOG = "CommonUtil";
	/*
	 * 保存cookie
	 */
	public static String COOKIE = "";

	/*
	 * 保存token
	 */
	public static ArrayList<String> tokenList = new ArrayList<String>();

	/*
	 * 保存station telecode
	 */
	public static ArrayList<HashMap<String, String>> codeArrayList = new ArrayList<HashMap<String, String>>();

	public static ArrayList<HashMap<String, String>> getCode(Context mContext)
	{
		if(codeArrayList.isEmpty())
		{
			XmlResourceParser parser = null;
			parser = mContext.getResources().getXml(R.xml.station_telcode);
			if(parser != null)
			{
				try {
					int cout = 1;
					String nameString="";
					HashMap<String, String> map = new HashMap<String, String>();
					while (parser.getEventType() != XmlResourceParser.END_DOCUMENT) {
					    if (parser.getEventType() == XmlPullParser.TEXT) {
					    	if(cout%2 == 0)
					    	{
					    		map.put(nameString, parser.getText());
					    		cout++;
					    	}
					    	else {
								nameString = parser.getText();
								cout++;
							}
					    	if(cout == 9)
					    	{
					    		codeArrayList.add(map);
					    		map = new HashMap<String, String>();
					    		cout = 1;
					    	}
					    }
					    parser.next();
					}
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
		}
		return codeArrayList;
	}

	public static String getCookie() {
		return COOKIE;
	}

	public static byte[] inputStreamToByteArray(InputStream inputStream) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			byte[] b = new byte[1024];
			int n;
			while ((n = inputStream.read(b)) != -1) {
				out.write(b, 0, n);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(LOG, "parse error");
			return null;
		}
		return out.toByteArray();
	}

	public static ByteArrayOutputStream inputStreamToByteArrayOutputStream(
			InputStream inputStream) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			byte[] b = new byte[1024];
			int n;
			while ((n = inputStream.read(b)) != -1) {
				out.write(b, 0, n);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(LOG, "parse error");
			return null;
		}
		return out;
	}

	public static InputStream byteArrayOutputStreamToInputStream(
			ByteArrayOutputStream outputStream) {
		return null;
	}

	public static String byteArrayOutStreamToString(
			ByteArrayOutputStream outputStream) {
		byte[] buf = outputStream.toByteArray();
		try {
			return new String(buf, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public static String inputStreamToString(InputStream inputStream) {
		InputStreamReader in = new InputStreamReader(inputStream);
		BufferedReader buffer = new BufferedReader(in);
		String inputLine = "";
		StringBuilder resultData = new StringBuilder();
		try {
			while (((inputLine = buffer.readLine()) != null)) {
				resultData.append(inputLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.v(LOG, e.getMessage());
			return null;

		}
		return resultData.toString();
	}

}
