package com.dengbo.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import android.util.Log;

import com.dengbo.model.ReqNetDate;
import com.dengbo.util.CommonUtil;
import com.dengbo.util.HandleCAUtil;

public class Network {

	public static final String TAG ="Network";

	public ByteArrayOutputStream getInputStream(ReqNetDate reqNetDate) throws Exception {
		if (reqNetDate.getProtocol().equalsIgnoreCase("http")) {
			if (reqNetDate.getMethod().equalsIgnoreCase("get")) {
				return createHttpConnectGet(new StringBuilder(reqNetDate.getUrl()), reqNetDate.getHeadParam(), reqNetDate.getParam());
			}
			else {
				return createHttpConnectPost(new StringBuilder(reqNetDate.getUrl()), reqNetDate.getHeadParam(), reqNetDate.getParam());
			}
		} else {
			if (reqNetDate.getMethod().equalsIgnoreCase("get")) {
				return createHttpsConnectGet(new StringBuilder(reqNetDate.getUrl()), reqNetDate.getHeadParam(), reqNetDate.getParam());
			}
			else {
				return createHttpsConnectPost(new StringBuilder(reqNetDate.getUrl()), reqNetDate.getHeadParam(), reqNetDate.getParam());
			}
		}
	}

	private ByteArrayOutputStream createHttpConnectGet(StringBuilder url,
			HashMap<String, String> headParam, HashMap<String, String> param)
			throws Exception {
		HttpURLConnection connection = null;
		ByteArrayOutputStream outputStream = null;
		try {
			Set<String> paramSet = param.keySet();
			if (paramSet.size() != 0) {
				url.append("?");
				for (String key : paramSet) {
					url.append(key).append("=").append(param.get(key)).append("&");
				}
			}
			connection = (HttpURLConnection) new URL(url.toString())
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			Set<String> keySets = headParam.keySet();
			for (String key : keySets) {
				connection.setRequestProperty(key, headParam.get(key));
			}
			int resp = connection.getResponseCode();
			switch (resp) {
			case 200:
				InputStream inputStream = connection.getInputStream();
				outputStream = CommonUtil.inputStreamToByteArrayOutputStream(inputStream);
				inputStream.close();
				break;
			default:
				Exception exception = new Exception(resp + "");
				throw exception;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			connection.disconnect();
		}
		return outputStream;
	}

	private ByteArrayOutputStream createHttpConnectPost(StringBuilder url,HashMap<String, String> headParam, HashMap<String, String> param) throws Exception {
		HttpURLConnection connection = null;
		ByteArrayOutputStream outputStream = null;
		try {
			connection = (HttpURLConnection) new URL(url.toString()).openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setConnectTimeout(5000);
			Set<String> headParamSet = headParam.keySet();
			for (String key : headParamSet) {
				connection.setRequestProperty(key, headParam.get(key));
			}
			DataOutputStream dos = new DataOutputStream(
					connection.getOutputStream());
			Set<String> paramSet = param.keySet();
			StringBuilder tmpBuilder = new StringBuilder();
			for(String key : paramSet)
			{
				tmpBuilder.append(key).append("=").append(param.get(key)).append("&");
			}
			String postContent = tmpBuilder.toString();
			Log.v(TAG, postContent);
			dos.write(postContent.getBytes());
			dos.flush();
			// ִ����dos.close()��POST�������
			dos.close();
			int resp = connection.getResponseCode();
			switch (resp) {
			case 200:
				InputStream inputStream = connection.getInputStream();
				outputStream = CommonUtil.inputStreamToByteArrayOutputStream(inputStream);
				inputStream.close();
				break;
			default:
				Exception exception = new Exception(resp + "");
				throw exception;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			connection.disconnect();
		}
		return outputStream;
	}

	private ByteArrayOutputStream createHttpsConnectGet(StringBuilder url,
			HashMap<String, String> headParam, HashMap<String, String> param)
			throws Exception {
		HttpsURLConnection connection = null;
		ByteArrayOutputStream outputStream=null;
		HandleCAUtil.initTrustAllSSL();
		try {
			Set<String> paramSet = param.keySet();
			if (paramSet.size() != 0) {
				url.append("?");
				for (String key : paramSet) {
					url.append(key).append("=").append(param.get(key)).append("&");
				}
			}
			connection = (HttpsURLConnection) new URL(url.toString())
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			Set<String> keySets = headParam.keySet();
			for (String key : keySets) {
				connection.setRequestProperty(key, headParam.get(key));
			}
			int resp = connection.getResponseCode();
			switch (resp) {
			case 200:
				InputStream inputStream = connection.getInputStream();
				outputStream = CommonUtil.inputStreamToByteArrayOutputStream(inputStream);
				inputStream.close();
				break;
			default:
				Exception exception = new Exception(resp + "");
				throw exception;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			connection.disconnect();
		}
		return outputStream;
	}

	private ByteArrayOutputStream createHttpsConnectPost(StringBuilder url,HashMap<String, String> headParam, HashMap<String, String> param) throws Exception {
		HttpsURLConnection connection = null;
		ByteArrayOutputStream outputStream = null;
		HandleCAUtil.initTrustAllSSL();
		try {
			connection = (HttpsURLConnection) new URL(url.toString()).openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setConnectTimeout(5000);
			Set<String> headParamSet = headParam.keySet();
			for (String key : headParamSet) {
				connection.setRequestProperty(key, headParam.get(key));
			}
			DataOutputStream dos = new DataOutputStream(
					connection.getOutputStream());
			Set<String> paramSet = param.keySet();
			StringBuilder tmpBuilder = new StringBuilder();
			for(String key : paramSet)
			{
				tmpBuilder.append(key).append("=").append(param.get(key)).append("&");
			}
			String postContent = tmpBuilder.toString();
			Log.v(TAG, postContent);
			dos.write(postContent.getBytes());
			dos.flush();
			// ִ����dos.close()��POST�������
			dos.close();
			int resp = connection.getResponseCode();
			switch (resp) {
			case 200:
				InputStream inputStream = connection.getInputStream();
				outputStream = CommonUtil.inputStreamToByteArrayOutputStream(inputStream);
				inputStream.close();
				break;
			default:
				Exception exception = new Exception(resp + "");
				throw exception;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			connection.disconnect();
		}
		return outputStream;
	}


}
