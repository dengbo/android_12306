package com.dengbo.control;

import com.dengbo.model.ReqCookie;
import com.dengbo.model.ReqNetDate;
import com.dengbo.network.Network;
import com.dengbo.util.CommonUtil;
import com.dengbo.util.NotifyExceptionUtil;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

public class InitCookieService extends Service{

	String actionString;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		actionString = intent.getAction();
		ReqNetDate cookieDate = new ReqCookie();
		cookieDate.setMethod("GET");
		cookieDate.setProtocol("https");
		cookieDate.setUrl("https://dynamic.12306.cn/otsweb/");
		AsyncCookie cookie = new AsyncCookie();
		cookie.execute(cookieDate);
		return super.onStartCommand(intent, flags, startId);
	}
	 private class AsyncCookie extends AsyncTask<ReqNetDate, Integer, String>
	 {

		@Override
		protected String doInBackground(ReqNetDate... params) {
			// TODO Auto-generated method stub
			try {
				CommonUtil.COOKIE = new Network().getCookieString(params[0]);
				Intent mIntent = new Intent();
				mIntent.setAction(actionString);
				sendBroadcast(mIntent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				NotifyExceptionUtil.notify(InitCookieService.this, e);
				return "";
			}
			return "";
		}

	 }

}
