package com.dengbo.control;

import java.io.ByteArrayOutputStream;

import org.json.JSONObject;

import com.dengbo.util.CommonUtil;
import com.dengbo.util.StringPoolUtil;

import android.os.Bundle;

public class ParseBook extends ParseBase{

	@Override
	Bundle parse(ByteArrayOutputStream stream) throws Exception {
		// TODO Auto-generated method stub
		Bundle mBundle = new Bundle();
		String tmpResult = CommonUtil.byteArrayOutStreamToString(stream);
		JSONObject tmpObject = new JSONObject(tmpResult);
		String errmsg = tmpObject.getString("errMsg");
		mBundle.putString(StringPoolUtil.BOOK_RET, errmsg);
		return mBundle;
	}

}
