package com.dengbo.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import android.util.Log;

public class CommonUtil {
	static String LOG = "CommonUtil";

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

	public static ByteArrayOutputStream inputStreamToByteArrayOutputStream(InputStream inputStream) {
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

	public static InputStream byteArrayOutputStreamToInputStream(ByteArrayOutputStream outputStream) {
		return null;
	}

	public static String byteArrayOutStreamToString(ByteArrayOutputStream outputStream) {
		byte[] buf = outputStream.toByteArray();
		return new String(buf);
	}

	public static String inputStreamToString(InputStream inputStream)
	{
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
