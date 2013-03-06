package com.dengbo.control;

import java.io.ByteArrayOutputStream;

import org.json.JSONObject;

import com.dengbo.util.CommonUtil;
import com.dengbo.util.StringPoolUtil;

import android.os.Bundle;

public class ParseBook1 extends ParseBase{

	@Override
	Bundle parse(ByteArrayOutputStream stream) throws Exception {
		// TODO Auto-generated method stub
		Bundle mBundle = new Bundle();
		String tmpResult = CommonUtil.byteArrayOutStreamToString(stream);
		JSONObject tmpObject = new JSONObject(tmpResult);
		boolean[] op = new boolean[2];
		op[0] = tmpObject.getBoolean("op_1");
		op[1] = tmpObject.getBoolean("op_2");
		mBundle.putBooleanArray(StringPoolUtil.BOOK_RET, op);
		return mBundle;
	}

}
