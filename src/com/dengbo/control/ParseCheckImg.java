package com.dengbo.control;

import java.io.ByteArrayOutputStream;
import com.dengbo.util.StringPoolUtil;

import android.os.Bundle;

public class ParseCheckImg extends ParseBase {

	@Override
	public Bundle parse(ByteArrayOutputStream stream) {
		// TODO Auto-generated method stub
		Bundle mBundle = new Bundle();
		byte[] data = stream.toByteArray();
		if (data == null) {
			return null;
		}
		mBundle.putByteArray(StringPoolUtil.RESP_CHECK, data);
		return mBundle;
	}
}
