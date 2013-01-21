package com.dengbo.control;

import java.io.ByteArrayOutputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dengbo.util.CommonUtil;

import android.os.Bundle;

public class ParsePassager extends ParseBase{

	@Override
	Bundle parse(ByteArrayOutputStream stream) throws Exception {
		// TODO Auto-generated method stub
		String result = CommonUtil.byteArrayOutStreamToString(stream);
		JSONObject object = new JSONObject(result);
		JSONArray array = object.getJSONArray("passengerJson");
		
		return null;
	}

}
