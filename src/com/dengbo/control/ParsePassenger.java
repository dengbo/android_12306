package com.dengbo.control;

import java.io.ByteArrayOutputStream;
import org.json.JSONObject;

import com.dengbo.app.App;
import com.dengbo.util.CommonUtil;

import android.os.Bundle;

public class ParsePassenger extends ParseBase{

	@Override
	Bundle parse(ByteArrayOutputStream stream) throws Exception {
		// TODO Auto-generated method stub
		String result = CommonUtil.byteArrayOutStreamToString(stream);
		JSONObject object = new JSONObject(result);
		App.array = object.getJSONArray("passengerJson");
		Bundle mBundle = new Bundle();
		return mBundle;
	}

}
