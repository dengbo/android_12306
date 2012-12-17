package com.dengbo.control;


import java.io.ByteArrayOutputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;

import com.dengbo.util.CommonUtil;
import com.dengbo.util.StringPoolUtil;

public class ParseLoginAuth extends ParseBase{

	private static String TAG = "ParseLoginAuth";
	@Override
	public Bundle parse(ByteArrayOutputStream stream) {
		// TODO Auto-generated method stub
		Bundle mBundle = new Bundle();
		String inputString = CommonUtil.byteArrayOutStreamToString(stream);
		if (inputString != null) {
			try {
				JSONObject mJsonObject = new JSONObject(inputString);
				mBundle.putString(StringPoolUtil.LOGIN_RAND, mJsonObject.getString("loginRand"));
				mBundle.putString(StringPoolUtil.LOGIN_ERROR, mJsonObject.getString("randError"));
				return mBundle;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.v(TAG, e.getMessage());
				return null;
			}
		}
		else
		{
			return null;
		}

	}

}
