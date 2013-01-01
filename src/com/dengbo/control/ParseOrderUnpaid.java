package com.dengbo.control;

import java.io.ByteArrayOutputStream;

import android.os.Bundle;
import android.util.Log;

public class ParseOrderUnpaid extends ParseHtmlBase {
	public ParseOrderUnpaid(ByteArrayOutputStream outputStream) {
		super(outputStream);
		// TODO Auto-generated constructor stub
	}

	@Override
	Bundle parse(ByteArrayOutputStream stream) {
		Log.e("ParseOrderUnpaid", "parse");
		return new Bundle();
	}

}
