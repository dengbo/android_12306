package com.dengbo.view;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dengbo.app.App;
import com.dengbo.control.InitCookieService;
import com.dengbo.control.RouterService;
import com.dengbo.util.CommonUtil;
import com.dengbo.util.NotifyExceptionUtil;
import com.dengbo.util.StringPoolUtil;

public class LoginActivity extends BaseActivity {
	public static final String LOG = "LoginActivity";

	private EditText userEditText, passwdEditText, checkEditText;
	private Button loginButton;
	private ImageView checkImageView;
	private TextView loginSignTextView;
	private ProgressBar bar;


	// 启动获取cookie的service
	private Intent cookieIntent;
	private Recieve_login_data mLogin_data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		initReciever();
		init();
	}

	// 初始化receiver
	private void initReciever() {
		// TODO Auto-generated method stub
		IntentFilter filter = new IntentFilter();
		filter.addAction(StringPoolUtil.GET_CHECK_IMG);
		filter.addAction(StringPoolUtil.F5_CHECK_IMG);
		filter.addAction(StringPoolUtil.SEND_LOGIN_AUTH);
		filter.addAction(StringPoolUtil.SEND_LOGIN);
		filter.addAction(StringPoolUtil.GET_COOKIE);
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		mLogin_data = new Recieve_login_data();
		registerReceiver(mLogin_data, filter);
	}

	// 初始化view
	private void init() {
		userEditText = (EditText) findViewById(R.id.login_user_edit);
		passwdEditText = (EditText) findViewById(R.id.login_passwd_edit);
		checkEditText = (EditText) findViewById(R.id.login_check_edit);
		checkImageView = (ImageView) findViewById(R.id.login_check_img);
		loginButton = (Button) findViewById(R.id.login_btn);
		loginSignTextView = (TextView) findViewById(R.id.login_sign);
		bar =(ProgressBar)findViewById(R.id.bar);

		userEditText.setFocusable(true);
		userEditText.requestFocus();

		loginButton.setOnClickListener(loginClickListener);
		checkImageView.setOnClickListener(refreshClickListener);
		loginSignTextView.setOnClickListener(signClickListener);
		bar.setIndeterminate(true);

		if (isOpenNetwork() == true) {
			cookieIntent = new Intent(LoginActivity.this,
					InitCookieService.class);
			cookieIntent.setAction(StringPoolUtil.GET_COOKIE);
			startService(cookieIntent);
		} else {
			Resources resources = getResources();
			AlertDialog.Builder builder = new AlertDialog.Builder(
					LoginActivity.this);
			builder.setTitle(resources.getString(R.string.login_network_title))
					.setMessage(resources.getString(R.string.login_network_msg));

			builder.setPositiveButton(resources.getString(R.string.ok),
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = null;

							try {
								String sdkVersion = android.os.Build.VERSION.SDK;
								if (Integer.valueOf(sdkVersion) > 10) {
									intent = new Intent(
											android.provider.Settings.ACTION_SETTINGS);
								} else {
									intent = new Intent();
									ComponentName comp = new ComponentName(
											"com.android.settings",
											"com.android.settings.Settings");
									intent.setComponent(comp);
									intent.setAction("android.intent.action.VIEW");
								}
								LoginActivity.this.startActivity(intent);
							} catch (Exception e) {
								Log.v(LOG,
										"open network settings failed, please check...");
								e.printStackTrace();
							}
						}
					})
					.setNegativeButton(resources.getString(R.string.no),
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
									finish();
								}
							}).show();
		}
	}

	/**
	 * 对网络连接状态进行判断
	 *
	 * @return true, 可用； false， 不可用
	 */
	private boolean isOpenNetwork() {
		ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connManager.getActiveNetworkInfo() != null) {
			return connManager.getActiveNetworkInfo().isAvailable();
		}

		return false;
	}

	//点击注册
	private OnClickListener signClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent(LoginActivity.this , LoginSignActivity.class);
			startActivity(mIntent);
		}
	};
	// 点击刷新验证码
	private OnClickListener refreshClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			bar.setVisibility(View.VISIBLE);
			checkImageView.setVisibility(View.GONE);
			App.mIntent.setAction(StringPoolUtil.F5_CHECK_IMG);
			startService(App.mIntent);
		}
	};
	// 点击登录
	private OnClickListener loginClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (CommonUtil.COOKIE != "") {
				Log.v(LOG, "login");
				App.mIntent.setAction(StringPoolUtil.SEND_LOGIN_AUTH);
				startService(App.mIntent);
			}
			else {
				NotifyExceptionUtil.notify(LoginActivity.this, getResources().getString(R.string.login_wait));
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	// 处理接收到的广播
	private class Recieve_login_data extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			Bundle mBundle = intent.getExtras();
			ConnectivityManager connectivityManager;
			NetworkInfo info;
			Log.v(LOG, action);
			if (mBundle != null) {
				if (action.equalsIgnoreCase(StringPoolUtil.GET_CHECK_IMG)) {// 获取验证码图片
					byte[] data = mBundle
							.getByteArray(StringPoolUtil.RESP_CHECK);
					Bitmap mBitmap = BitmapFactory.decodeByteArray(data, 0,
							data.length);
					checkImageView.setImageBitmap(mBitmap);
					bar.setVisibility(View.GONE);
					checkImageView.setVisibility(View.VISIBLE);
				} else if (action.equalsIgnoreCase(StringPoolUtil.F5_CHECK_IMG)) {// 刷新验证码
					byte[] data = mBundle
							.getByteArray(StringPoolUtil.RESP_CHECK);
					Bitmap mBitmap = BitmapFactory.decodeByteArray(data, 0,
							data.length);
					checkImageView.setImageBitmap(mBitmap);
					bar.setVisibility(View.GONE);
					checkImageView.setVisibility(View.VISIBLE);
				} else if (action
						.equalsIgnoreCase(StringPoolUtil.SEND_LOGIN_AUTH)) {// 处理获取到autho后，发送请求登录信息
					App.mIntent.setAction(StringPoolUtil.SEND_LOGIN);
					String[] dataStrings = new String[4];
					dataStrings[0] = "dengbodb@sina.com";//userEditText.getText().toString();
					dataStrings[1] = "03170822l";//passwdEditText.getText().toString();
					dataStrings[2] = checkEditText.getText().toString();
					dataStrings[3] = mBundle
							.getString(StringPoolUtil.LOGIN_RAND);
					Bundle mBundles = new Bundle();
					mBundles.putStringArray(StringPoolUtil.LOGIN, dataStrings);
					App.mIntent.putExtras(mBundles);
					startService(App.mIntent);
					NotifyExceptionUtil.notify(LoginActivity.this, getResources().getString(R.string.login_ing));
				} else if (action.equalsIgnoreCase(StringPoolUtil.SEND_LOGIN)) {// 处理登录后
					boolean isError = mBundle.getBoolean(StringPoolUtil.ERROR);
					if (isError) {
						String errorString = mBundle
								.getString(StringPoolUtil.ERROR_REASON);
						Resources res = LoginActivity.this.getResources();
						if (errorString
								.equalsIgnoreCase(StringPoolUtil.CHECK_IMG_ERROR)) {
							checkEditText.setText("");
							Log.v(LOG, StringPoolUtil.CHECK_IMG_ERROR);
							NotifyExceptionUtil.notify(LoginActivity.this,
									res.getString(R.string.login_check_error));
							// 获取验证码
							bar.setVisibility(View.VISIBLE);
							checkImageView.setVisibility(View.GONE);
							App.mIntent = new Intent(LoginActivity.this, RouterService.class);
							App.mIntent.setAction(StringPoolUtil.GET_CHECK_IMG);
							startService(App.mIntent);
						} else {
							userEditText.setText("");
							passwdEditText.setText("");
							Log.v(LOG, StringPoolUtil.NAME_OR_PW_ERROR);
							NotifyExceptionUtil.notify(LoginActivity.this,
									res.getString(R.string.login_name_error));
							// 获取验证码
							bar.setVisibility(View.VISIBLE);
							checkImageView.setVisibility(View.GONE);
							App.mIntent = new Intent(LoginActivity.this, RouterService.class);
							App.mIntent.setAction(StringPoolUtil.GET_CHECK_IMG);
							startService(App.mIntent);
						}
					} else {
						Log.v(LOG, "login ok");
						Intent startIntent = new Intent(LoginActivity.this,
								HomeActivity.class);
						LoginActivity.this.startActivity(startIntent);
						LoginActivity.this.finish();
					}
				} else if (action.equals(StringPoolUtil.GET_COOKIE)) {
					Log.v(LOG, "get the cookie");
					stopService(cookieIntent);
					// 获取验证码
					bar.setVisibility(View.VISIBLE);
					checkImageView.setVisibility(View.GONE);
					App.mIntent = new Intent(LoginActivity.this, RouterService.class);
					App.mIntent.setAction(StringPoolUtil.GET_CHECK_IMG);
					startService(App.mIntent);
				} else if (action
						.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
					Log.d("mark", "网络状态已经改变");
					connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
					info = connectivityManager.getActiveNetworkInfo();
					if (info != null && info.isAvailable()) {
						cookieIntent = new Intent(LoginActivity.this,
								InitCookieService.class);
						cookieIntent.setAction(StringPoolUtil.GET_COOKIE);
						startService(cookieIntent);
						// 获取验证码
						App.mIntent = new Intent(LoginActivity.this,
								RouterService.class);
						App.mIntent.setAction(StringPoolUtil.GET_CHECK_IMG);
						startService(App.mIntent);
						String name = info.getTypeName();
						Log.d("mark", "当前网络名称：" + name);
					}
				}
			}

		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(mLogin_data);
		// stopService(mIntent);
	}
}
