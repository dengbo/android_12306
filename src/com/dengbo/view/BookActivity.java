package com.dengbo.view;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import com.dengbo.adapter.QueryListAdapter;
import com.dengbo.app.App;
import com.dengbo.control.RouterService;
import com.dengbo.util.StringPoolUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow.OnDismissListener;

public class BookActivity extends BaseActivity {

	private String[] result;
	private TextView trainTextView, dateTextView, timeTextView, classTextView;
	private EditText checkEditText;
	private ProgressBar bar;
	private ImageView checkImageView;
	private Button backButton, subButton, addButton;
	private ListView passagerListView;
	private PopupWindow mWindow;
	private ArrayList<String> mList;
	private Recieve recieve;
	private Intent mIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book);
		Bundle mBundle = getIntent().getExtras();
		if (mBundle != null)
			result = mBundle.getString(StringPoolUtil.BOOK).split(",");
		mIntent = App.mIntent;
		initView();
		initReciever();
	}

	private void initView() {
		trainTextView = (TextView) findViewById(R.id.book_train);
		dateTextView = (TextView) findViewById(R.id.book_date);
		timeTextView = (TextView) findViewById(R.id.book_time);
		classTextView = (TextView) findViewById(R.id.book_class);
		checkEditText = (EditText) findViewById(R.id.book_check);
		bar = (ProgressBar) findViewById(R.id.book_bar);
		checkImageView = (ImageView) findViewById(R.id.book_check_img);
		backButton = (Button) findViewById(R.id.book_back);
		subButton = (Button) findViewById(R.id.book_sub);
		addButton = (Button) findViewById(R.id.book_add_passager);
		passagerListView = (ListView) findViewById(R.id.book_passager);

		bar.setIndeterminate(true);
		dateTextView.setText(result[0]);
		String tmp_1 = result[2].replaceAll(" ", "");
		String tmp_2 = result[3].replaceAll(" ", "");
		int p1 = tmp_1.indexOf('(');
		int p2 = tmp_2.indexOf('(');
		String time_tmp_1 = tmp_1.substring(p1 + 1, tmp_1.indexOf(')') - 1);
		String time_tmp_2 = tmp_2.substring(p2 + 1, tmp_2.indexOf(')') - 1);
		String start = tmp_1.substring(0, p1);
		String end = tmp_2.substring(0, p2);
		StringBuilder trainBuilder = new StringBuilder();
		trainBuilder.append(result[1]).append("  ").append(start).append("-")
				.append(end);
		trainTextView.setText(trainBuilder.toString());
		StringBuilder timeBuilder = new StringBuilder();
		timeBuilder.append(time_tmp_1).append("-").append(time_tmp_2);
		timeTextView.setText(timeBuilder.toString());
		classTextView.setText(result[5]);
		mList = new ArrayList<String>();
		for (int i = 5; i < result.length; i++) {
			mList.add(result[i]);
		}
		classTextView.setOnClickListener(classClickListener);
		checkImageView.setOnClickListener(refreshClickListener);
		// 获取验证码
		bar.setVisibility(View.VISIBLE);
		checkImageView.setVisibility(View.GONE);
		mIntent.setAction(StringPoolUtil.BOOK_IMG);
		startService(mIntent);

		mIntent.setAction(StringPoolUtil.READ_PASSAGER);
		startService(mIntent);
	}

	private OnClickListener classClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			popAwindow();
		}
	};

	private OnClickListener refreshClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			bar.setVisibility(View.VISIBLE);
			checkImageView.setVisibility(View.GONE);
			mIntent.setAction(StringPoolUtil.F5_BOOK_IMG);
			startService(mIntent);
		}
	};

	private void popAwindow() {
		LayoutInflater lay = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = lay.inflate(R.layout.query_pop, null);
		final ListView list = (ListView) v.findViewById(R.id.query_pop);
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(
				BookActivity.this, android.R.layout.simple_list_item_1, mList);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String data = (String) list.getItemAtPosition(position);
				classTextView.setText(data);
				mWindow.dismiss();
			}
		});
		list.setAdapter(mAdapter);
		mWindow = new PopupWindow(v, 500, 260);
		list.setItemsCanFocus(true);
		mWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.popup));
		mWindow.setOutsideTouchable(true);
		mWindow.setFocusable(true);// 如果不设置setFocusable为true，popupwindow里面是获取不到焦点的，那么如果popupwindow里面有输入框等的话就无法输入。
		mWindow.update();
		View parent = lay.inflate(R.layout.book, null);
		mWindow.showAtLocation(parent, Gravity.CENTER_VERTICAL, 0, 0);
	}

	private void initReciever()
	{
		recieve = new Recieve();
		IntentFilter filter = new IntentFilter();
		filter.addAction(StringPoolUtil.BOOK_IMG);
		filter.addAction(StringPoolUtil.F5_BOOK_IMG);
		filter.addAction(StringPoolUtil.SUB_BOOK);
		filter.addAction(StringPoolUtil.READ_PASSAGER);
		filter.addAction(StringPoolUtil.ADD_PASSAGER);
		registerReceiver(recieve, filter);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(recieve);
	}

	private class Recieve extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String actionString = intent.getAction();
			Bundle mBundle = intent.getExtras();
			if(actionString.equals(StringPoolUtil.BOOK_IMG))
			{
				byte[] data = mBundle
						.getByteArray(StringPoolUtil.RESP_CHECK);
				Bitmap mBitmap = BitmapFactory.decodeByteArray(data, 0,
						data.length);
				checkImageView.setImageBitmap(mBitmap);
				bar.setVisibility(View.GONE);
				checkImageView.setVisibility(View.VISIBLE);
			}
			else if(actionString.equals(StringPoolUtil.F5_BOOK_IMG))
			{
				byte[] data = mBundle
						.getByteArray(StringPoolUtil.RESP_CHECK);
				Bitmap mBitmap = BitmapFactory.decodeByteArray(data, 0,
						data.length);
				checkImageView.setImageBitmap(mBitmap);
				bar.setVisibility(View.GONE);
				checkImageView.setVisibility(View.VISIBLE);
			}
			else if(actionString.equals(StringPoolUtil.SUB_BOOK))
			{

			}
			else if(actionString.equals(StringPoolUtil.ADD_PASSAGER))
			{

			}
			else if(actionString.equals(StringPoolUtil.READ_PASSAGER))
			{

			}
		}

	}
}
