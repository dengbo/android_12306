package com.dengbo.util;

import android.content.Context;
import android.widget.Toast;

public class NotifyExceptionUtil {
	public static void notify(Context context, Exception exception) {
		String msg = exception.getMessage();
		if (msg.equals("400")) {
			Toast.makeText(context, "Bad Request", Toast.LENGTH_LONG).show();
		} else if (msg.equals("401")) {
			Toast.makeText(context, "Unauthorized", Toast.LENGTH_LONG).show();
		} else if (msg.equals("403")) {
			Toast.makeText(context, "Forbidden", Toast.LENGTH_LONG).show();
		} else if (msg.equals("404")) {
			Toast.makeText(context, "Not Found", Toast.LENGTH_LONG).show();
		} else if (msg.equals("500")) {
			Toast.makeText(context, "Server internal error", Toast.LENGTH_LONG).show();
		}
		else {
			Toast.makeText(context, "error: "+msg, Toast.LENGTH_LONG).show();
		}
	}
	public static void notify(Context context) {
		Toast.makeText(context, "parse the data error", Toast.LENGTH_LONG).show();
	}
	public static void notify(Context context , String errorString) {
		Toast.makeText(context, errorString, Toast.LENGTH_LONG).show();
	}
}
