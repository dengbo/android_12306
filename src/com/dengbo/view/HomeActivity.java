package com.dengbo.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.dengbo.control.RouterService;
import com.dengbo.util.StringPoolUtil;

public class HomeActivity extends BaseActivity {

	private ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		mImageView = (ImageView) findViewById(R.id.home_3);
		mImageView.setOnClickListener(home_3_listener);
		
		/**
		 * edited by minGong
		 */
		initReceiver();
	}

	private OnClickListener home_3_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
//			Intent mIntent = new Intent(HomeActivity.this, QueryActivity.class);
//			startActivity(mIntent);
			checkOrder();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		/**
		 * edited by minGong
		 */
		unregisterReceiver(mReceiver);
	}

	/**
	 * temporary method by maNong
	 */
	private void checkOrder() {
		Intent underUnpaid = new Intent(HomeActivity.this, RouterService.class);
		underUnpaid.setAction(StringPoolUtil.CHECK_ORDER_UNPAID);
		startService(underUnpaid);
	}

	private class OrderUnpaidReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context arg0, Intent arg1) {

		}
	}

	private void initReceiver() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(StringPoolUtil.CHECK_ORDER_UNPAID);
		registerReceiver(mReceiver, filter);
	}

	private OrderUnpaidReceiver mReceiver = new OrderUnpaidReceiver();
}
