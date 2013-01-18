package com.dengbo.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import com.dengbo.adapter.QueryListAdapter;
import com.dengbo.app.App;
import com.dengbo.util.StringPoolUtil;

import android.R.mipmap;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.StaticLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

public class QueryActivity extends BaseActivity {

	public static final int START = 1;
	public static final int END = 2;

	private Button backButton, queryButton;
	private TextView query_dateTextView, query_timeTextView,
			query_kindTextView, query_classTextView;
	private TextView query_startTextView, query_endTextView;

	private String[] query_timeStrings;
	private String[] query_kindStrings;
	private String[] query_kindStrings_l;
	private String[] query_classStrings;

	private PopupWindow mTimeWindow = null;
	private LayoutInflater lay;
	private View parent;

	private String startString , endString;
	private StringBuilder kindBuilder;

	private Recieve dataRecieve ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.query);

		lay = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		backButton = (Button) findViewById(R.id.back);
		queryButton = (Button) findViewById(R.id.query);
		query_startTextView = (TextView) findViewById(R.id.query_start);
		query_endTextView = (TextView) findViewById(R.id.query_end);
		query_dateTextView = (TextView) findViewById(R.id.query_date);
		query_timeTextView = (TextView) findViewById(R.id.query_time);
		query_classTextView = (TextView) findViewById(R.id.query_class);
		query_kindTextView = (TextView) findViewById(R.id.query_kind);

		init();
	}

	private void init() {
		parent = lay.inflate(R.layout.query, null);
		backButton.setOnClickListener(backClickListener);
		queryButton.setOnClickListener(queryClickListener);
		Resources resources = getResources();
		query_timeStrings = resources.getStringArray(R.array.query_time_array);
		query_classStrings = resources
				.getStringArray(R.array.query_class_array);
		query_kindStrings = resources.getStringArray(R.array.query_kind_array);
		query_kindStrings_l = resources.getStringArray(R.array.query_kind_array_l);
		SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = mDateFormat.format(Calendar.getInstance().getTime());
		query_dateTextView.setText(date);
		query_dateTextView.setOnClickListener(mod_dateClickListener);
		query_timeTextView.setText(query_timeStrings[0]);
		query_timeTextView.setOnClickListener(mod_timeClickListener);
		query_classTextView.setOnClickListener(mod_classClickListener);
		query_kindTextView.setOnClickListener(mod_kindClickListener);
		query_startTextView.setOnClickListener(mod_startClickListener);
		query_endTextView.setOnClickListener(mod_endClickListener);

		startString = "BJP";
		endString = "WHN";
		kindBuilder = new StringBuilder("QB");

		IntentFilter filter = new IntentFilter();
		filter.addAction(StringPoolUtil.QUERY_TICKET);
		dataRecieve = new Recieve();
		registerReceiver(dataRecieve, filter);
	}

	//修改出发地
	private OnClickListener mod_startClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent(QueryActivity.this , StationActivity.class);
			mIntent.putExtra("start", START);
			startActivityForResult(mIntent, START);
		}
	};

	//修改目的地
		private OnClickListener mod_endClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mIntent = new Intent(QueryActivity.this , StationActivity.class);
				mIntent.putExtra("start", END);
				startActivityForResult(mIntent, END);
			}
		};

	// 修改日期
	private OnClickListener mod_dateClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Calendar cal = Calendar.getInstance();
			new DatePickerDialog(QueryActivity.this, mListener, cal.get(Calendar.YEAR),
					cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
		}
	};
	private OnDateSetListener mListener = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, monthOfYear);
			cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = mDateFormat.format(cal.getTime());
			query_dateTextView.setText(date);
		}
	};

	// 修改时间
	private OnClickListener mod_timeClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			popAwindow(v, query_timeStrings, true);
		}
	};

	// 修改列车类别
	private OnClickListener mod_kindClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			popAwindow(v, query_kindStrings, false);
		}
	};

	// 修改席别
	private OnClickListener mod_classClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			popAwindow(v, query_classStrings, true);
		}
	};

	private void popAwindow(final View thisViews, String[] dataStrings,
			boolean isSingle) {
		View v = lay.inflate(R.layout.query_pop, null);
		final ListView list = (ListView) v.findViewById(R.id.query_pop);
		final ArrayList<String> queryArrayList = new ArrayList<String>();
		for (String item : dataStrings) {
			queryArrayList.add(item);
		}
		if (isSingle) {
			ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(
					QueryActivity.this,
					android.R.layout.simple_list_item_1,
					queryArrayList);
			list.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					String data = (String) list.getItemAtPosition(position);
					((TextView) thisViews).setText(data);
					mTimeWindow.dismiss();
				}
			});
			list.setAdapter(mAdapter);
			mTimeWindow = new PopupWindow(v, 500, 260);
		} else {
			final QueryListAdapter mAdapter = new QueryListAdapter(
					QueryActivity.this, queryArrayList);
			mTimeWindow = new PopupWindow(v, 500, 260);
			mTimeWindow.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss() {
					// TODO Auto-generated method stub
					StringBuilder dataBuilder = new StringBuilder();
					kindBuilder = new StringBuilder();
					List<Boolean> mList = mAdapter.mChecked;
					for (int i = 0; i < mList.size(); i++) {
						if (mList.get(i)) {
							kindBuilder.append(query_kindStrings_l[i]);
							kindBuilder.append("#");
							dataBuilder.append(queryArrayList.get(i)).append(",");
						}
					}
					((TextView) thisViews).setText(dataBuilder.toString());
					mTimeWindow.dismiss();
				}
			});
			list.setAdapter(mAdapter);
		}

		list.setItemsCanFocus(true);
		mTimeWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.popup));
		mTimeWindow.setOutsideTouchable(true);
		mTimeWindow.setFocusable(true);// 如果不设置setFocusable为true，popupwindow里面是获取不到焦点的，那么如果popupwindow里面有输入框等的话就无法输入。
		mTimeWindow.update();
		mTimeWindow.showAtLocation(parent, Gravity.CENTER_VERTICAL, 0, 0);
	}

	// 返回键响应
	private OnClickListener backClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent(QueryActivity.this, HomeActivity.class);
			startActivity(mIntent);
			QueryActivity.this.finish();
		}
	};
	// 查询键响应
	private OnClickListener queryClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Bundle mBundle = new Bundle();
			mBundle.putString(StringPoolUtil.QUERY_START, startString);
			mBundle.putString(StringPoolUtil.QUERY_END, endString);
			mBundle.putString(StringPoolUtil.QUERY_DATE, query_dateTextView.getText().toString());
			mBundle.putString(StringPoolUtil.QUERY_TIME, query_timeTextView.getText().toString());
			mBundle.putString(StringPoolUtil.QUERY_KIND, kindBuilder.toString());
			mBundle.putString(StringPoolUtil.QUERY_CLASS, query_classTextView.getText().toString());
			App.mIntent.putExtras(mBundle);
			App.mIntent.setAction(StringPoolUtil.QUERY_TICKET);
			startService(App.mIntent);
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Bundle mBundle = data.getExtras();
		switch (resultCode) {
		case START:
			if(!mBundle.isEmpty())
			{
				startString = mBundle.getString("code");
				query_startTextView.setText(mBundle.getString("name"));
			}
			break;
		case END:
			if(!mBundle.isEmpty())
			{
				endString = mBundle.getString("code");
				query_endTextView.setText(mBundle.getString("name"));
			}
			break;
		default:
			break;
		}
	}

	private class Recieve extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if(action.equalsIgnoreCase(StringPoolUtil.QUERY_TICKET))
			{
				Bundle mBundle = intent.getExtras();
				String start = query_startTextView.getText().toString();
				String end = query_endTextView.getText().toString();
				String date = query_dateTextView.getText().toString();
				Intent mIntent = new Intent(QueryActivity.this , ShowQueryResultActivity.class);
				mBundle.putString(StringPoolUtil.QUERY_START, start);
				mBundle.putString(StringPoolUtil.QUERY_END, end);
				mBundle.putString(StringPoolUtil.QUERY_DATE, date);
				mBundle.putString(StringPoolUtil.QUERY_TIME, query_timeTextView.getText().toString());
				mBundle.putString(StringPoolUtil.QUERY_KIND, kindBuilder.toString());
				mIntent.putExtras(mBundle);
				startActivity(mIntent);
				QueryActivity.this.finish();
			}
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(dataRecieve);
	}


}
