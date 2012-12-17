package com.dengbo.control;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.dengbo.model.ReqNetDate;
import com.dengbo.network.Network;
import com.dengbo.util.NotifyExceptionUtil;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;

public class RouterService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		String actionString = intent.getAction();
		Bundle mBundle = intent.getExtras();
		ReqNetDate reqNetDate = InitModel.initModel(actionString, mBundle);
		AsyncNet taskAsyncNet = new AsyncNet();
		taskAsyncNet.execute(reqNetDate);
		return super.onStartCommand(intent, flags, startId);
	}

	private class AsyncNet extends AsyncTask<ReqNetDate, Integer, ByteArrayOutputStream>
	{
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
				e.printStackTrace();
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
			Parse mParse = new Parse();
			Bundle respBundle =mParse.parseStream(actionString,result);
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
