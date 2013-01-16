package com.dengbo.control;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.dengbo.app.App;
import com.dengbo.model.ReqNetDate;
import com.dengbo.network.Network;
import com.dengbo.util.NotifyExceptionUtil;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class RouterService extends Service {

	private static final String TAG = "RouterService";

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.v(TAG, "service is destroy");
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		String actionString = intent.getAction();
		Bundle mBundle = intent.getExtras();
		ReqNetDate reqNetDate = null;
		try {
			reqNetDate = InitModel.initModel(actionString, mBundle);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.getMessage());
			NotifyExceptionUtil.notify(RouterService.this, e);
			return super.onStartCommand(intent, flags, startId);
		}
		catch (InstantiationException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.getMessage());
			NotifyExceptionUtil.notify(RouterService.this, e);
			return super.onStartCommand(intent, flags, startId);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.getMessage());
			NotifyExceptionUtil.notify(RouterService.this, e);
			return super.onStartCommand(intent, flags, startId);
		}
		AsyncNet taskAsyncNet = new AsyncNet();
		taskAsyncNet.execute(reqNetDate);
		return super.onStartCommand(intent, flags, startId);
	}

	private class AsyncNet extends
			AsyncTask<ReqNetDate, Integer, ByteArrayOutputStream> {
		private Exception mException = null;
		private String actionString = "";

		@Override
		protected ByteArrayOutputStream doInBackground(ReqNetDate... reqNetDate) {
			// TODO Auto-generated method stub
			actionString = reqNetDate[0].getAction();
			ByteArrayOutputStream outputStream = null;
			try {
				outputStream = new Network().getInputStream(reqNetDate[0]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.e("rounterService", e.toString());
				mException = e;
				return null;
			}
			return outputStream;
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

		@Override
		protected void onPostExecute(ByteArrayOutputStream result) {
			// TODO Auto-generated method stub
			if (mException != null) {
				NotifyExceptionUtil.notify(RouterService.this, mException);
				return;
			}
			if(result == null)
			{
				NotifyExceptionUtil.notify(RouterService.this, "network error");
				return;
			}
			Parse mParse = new Parse();
			Bundle respBundle = null;
			try {
				respBundle = mParse.parseStream(actionString, result);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				NotifyExceptionUtil.notify(RouterService.this, e1);
				return;
			}
			try {
				result.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (respBundle == null) {
				NotifyExceptionUtil.notify(RouterService.this);
				return;
			}
			Intent mIntent = new Intent();
			mIntent.setAction(actionString);
			mIntent.putExtras(respBundle);
			sendBroadcast(mIntent);
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

	}
}
