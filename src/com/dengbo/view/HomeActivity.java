package com.dengbo.view;

import com.dengbo.control.InitModel;
import com.dengbo.control.RouterService;
import com.dengbo.util.StringPoolUtil;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class HomeActivity extends BaseActivity {

	// 从第一条到最后一条顺序命名，每一条是一个layout
	private LinearLayout l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;

	private Datareciever mDatareciever;
	private Intent startServiceIntent ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		l1 = (LinearLayout) findViewById(R.id.home_1);
		l2 = (LinearLayout) findViewById(R.id.home_2);
		l3 = (LinearLayout) findViewById(R.id.home_3);
		l4 = (LinearLayout) findViewById(R.id.home_4);
		l5 = (LinearLayout) findViewById(R.id.home_5);
		l6 = (LinearLayout) findViewById(R.id.home_6);
		l7 = (LinearLayout) findViewById(R.id.home_7);
		l8 = (LinearLayout) findViewById(R.id.home_8);
		l9 = (LinearLayout) findViewById(R.id.home_9);
		l10 = (LinearLayout) findViewById(R.id.home_10);

		l1.setOnClickListener(home_1_listener);
		l2.setOnClickListener(home_2_listener);
		l3.setOnClickListener(home_3_listener);
		l4.setOnClickListener(home_4_listener);
		l5.setOnClickListener(home_5_listener);
		l6.setOnClickListener(home_6_listener);
		l7.setOnClickListener(home_7_listener);
		l8.setOnClickListener(home_8_listener);
		l9.setOnClickListener(home_9_listener);
		l10.setOnClickListener(home_10_listener);

		startServiceIntent = new Intent(HomeActivity.this , RouterService.class);

		IntentFilter filter = new IntentFilter();
		mDatareciever = new Datareciever();
		registerReceiver(mDatareciever, filter);
	}

	// 我的资料
	private OnClickListener home_1_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent(HomeActivity.this, DataActivity.class);
			startActivity(mIntent);
		}
	};

	// 同行者管理
	private OnClickListener home_2_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent(HomeActivity.this, FellowActivity.class);
			startActivity(mIntent);
		}
	};

	// 车票查询
	private OnClickListener home_3_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent(HomeActivity.this, QueryActivity.class);
			startActivity(mIntent);
		}
	};

	// 订单管理
	private OnClickListener home_4_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//先取token，再跳转
			startServiceIntent.setAction(StringPoolUtil.GET_Order_TOKRN);
			Bundle mBundle = new Bundle();
			mBundle.putString(StringPoolUtil.ORDER_TOKEN, InitModel.getOrderTokenString);
			startServiceIntent.putExtras(mBundle);
			startService(startServiceIntent);
		}
	};

	// 余票通知
	private OnClickListener home_5_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent(HomeActivity.this,
					NotifyYupiaoActivity.class);
			startActivity(mIntent);
		}
	};

	// 订单通知
	private OnClickListener home_6_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent(HomeActivity.this,
					NotifyOrderActivity.class);
			startActivity(mIntent);
		}
	};

	// 反馈建议
	private OnClickListener home_7_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent(HomeActivity.this,
					FeedbackActivity.class);
			startActivity(mIntent);
		}
	};

	// 软件评分
	private OnClickListener home_8_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent(HomeActivity.this, RateActivity.class);
			startActivity(mIntent);
		}
	};

	// 软件升级
	private OnClickListener home_9_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent(HomeActivity.this,
					SoftUpgradeActivity.class);
			startActivity(mIntent);
		}
	};

	// 关于我们
	private OnClickListener home_10_listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent(HomeActivity.this, AboutActivity.class);
			startActivity(mIntent);
		}
	};

	private class Datareciever extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if (action.equalsIgnoreCase(StringPoolUtil.GET_Order_TOKRN)) {
				Intent mIntent = new Intent(HomeActivity.this, OrderActivity.class);
				startActivity(mIntent);
			}
		}

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(mDatareciever);
	}
}
