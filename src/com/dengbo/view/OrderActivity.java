package com.dengbo.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.dengbo.control.RouterService;
import com.dengbo.util.StringPoolUtil;

public class OrderActivity extends BaseActivity{
	private BroadcastReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initReceiver();

		test();
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(receiver);

		super.onDestroy();
	}

	private void initReceiver(){
		receiver = new OrderDataReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(StringPoolUtil.CHECK_ORDER_UNPAID);

		registerReceiver(receiver, filter);
	}


	private class OrderDataReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.e("OrderDataReceiver","receiver");
		}

	}

	private void test(){
		Intent intent = new Intent(this, RouterService.class);
		intent.setAction(StringPoolUtil.CHECK_ORDER_UNPAID);
		startService(intent);
	}
}
