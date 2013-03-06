package com.dengbo.view;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dengbo.adapter.QueryListAdapter;
import com.dengbo.app.App;
import com.dengbo.control.RouterService;
import com.dengbo.util.NotifyExceptionUtil;
import com.dengbo.util.StringPoolUtil;

import android.R.anim;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
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
	private String TAG ="BookActivity";
	private String[] result;
	private String[] tmpStrings;
	private TextView trainTextView, dateTextView, timeTextView, classTextView;
	private EditText checkEditText;
	private ProgressBar bar;
	private ImageView checkImageView;
	private Button backButton, subButton, addButton;
	private ListView passengerListView;
	private PopupWindow mWindow;
	private ArrayList<String> mList;
	private Recieve recieve;
	private Intent mIntent;
	private int position;			//the passenger position you choose
	private int seat; 				//the seat you choose
	private JSONArray p_array;		//passenger array
	private HashMap<String, String> seatMap ;	//seat kind and num
	private String t_paramString;			//store network param for the last apply
	private String tmpSeatString; 			//store which kind seat you choose to find the num in seatMap
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book);
		Bundle mBundle = getIntent().getExtras();
		if (mBundle != null)
		{
			tmpStrings = mBundle.getString(StringPoolUtil.BOOK).split("[|]");
			result = tmpStrings[0].split(",");
			mIntent = App.mIntent;
			position = 0;
			initView();
			initReciever();
		}
		else {
			NotifyExceptionUtil.notify(BookActivity.this, "parse error!!!");
			back();
		}
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
		passengerListView = (ListView) findViewById(R.id.book_passager);

		bar.setIndeterminate(true);
		dateTextView.setText(result[0]);
		String tmp_1 = result[2].replaceAll(" ", "");
		String tmp_2 = result[3].replaceAll(" ", "");
		int p1 = tmp_1.indexOf('（');
		int p2 = tmp_2.indexOf('（');
		String time_tmp_1 = tmp_1.substring(p1 + 1, tmp_1.indexOf('）') - 1);
		String time_tmp_2 = tmp_2.substring(p2 + 1, tmp_2.indexOf('）') - 1);
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

		subButton.setOnClickListener(subClickListener);
		backButton.setOnClickListener(backListener);

		seatMap = new HashMap<String, String>();
		seatMap.put("硬座", "1");
		seatMap.put("无座", "1");
		seatMap.put("软座", "2");
		seatMap.put("硬卧", "3");
		seatMap.put("软卧", "4");
		seatMap.put("高级软卧", "6");
		seatMap.put("一等座", "M");
		seatMap.put("二等座", "0");
		seatMap.put("商务座", "9");
		seatMap.put("特等座","P" );
	}

	//back
	private OnClickListener backListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			back();
		}
	};
	private void back()
	{
		Intent mIntent = new Intent(BookActivity.this , ShowQueryResultActivity.class);
		startActivity(mIntent);
		BookActivity.this.finish();
	}

	//submit
	private OnClickListener subClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String  tmpTypeString , tmpNameString , tmpIdString , tmpIdTypeString , tmpMobileString;
			Bundle mBundle = new Bundle();
			StringBuilder mBuilder = new StringBuilder(tmpStrings[1]);
			StringBuilder tmpBuilder = new StringBuilder();
			StringBuilder tmpBuilder2 = new StringBuilder();
			JSONObject tmpJsonObject = null;
			try {
				tmpJsonObject = p_array.getJSONObject(position);
				try {
					tmpIdString = (String) tmpJsonObject.get("passenger_id_no");
					tmpIdTypeString = (String) tmpJsonObject.get("passenger_id_type_code");
					tmpNameString = URLEncoder.encode((String) tmpJsonObject.get("passenger_name"), "UTF-8");
					String classTextString = classTextView.getText().toString();
					tmpSeatString = seatMap.get(classTextString.substring(0, classTextString.indexOf('(')));
					tmpTypeString = (String) tmpJsonObject.get("passenger_type");
					tmpMobileString = (String) tmpJsonObject.get("mobile_no");
					tmpBuilder.append(tmpSeatString)
					.append("%2c").append(tmpJsonObject.get("passenger_flag"))
					.append("%2c").append(tmpTypeString)
					.append("%2c").append(tmpNameString)
					.append("%2c").append(tmpIdTypeString)
					.append("%2c").append(tmpIdString)
					.append("%2c").append(tmpMobileString)
					.append("%2cCY");                                      //student CN other CY
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					NotifyExceptionUtil.notify(BookActivity.this, e);
					return;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					NotifyExceptionUtil.notify(BookActivity.this, e);
					return;
				}
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				NotifyExceptionUtil.notify(BookActivity.this, e1);
				return;
			}
			String checkString = checkEditText.getText().toString();
			mBuilder.append("&passengerTickets=").append(tmpBuilder.toString());
			tmpBuilder2.append(tmpNameString).append(tmpIdTypeString).append(tmpIdString);
			mBuilder.append("&oldPassengers=").append(tmpBuilder2.toString())
			.append("&passenger_1_seat=").append(tmpSeatString)
			.append("&passenger_1_ticket=").append(tmpTypeString)
			.append("&passenger_1_name=").append(tmpNameString)
			.append("&passenger_1_cardtype=").append(tmpIdTypeString)
			.append("&passenger_1_cardno=").append(tmpNameString)
			.append("&passenger_1_mobileno=").append(tmpMobileString)
			.append("&checkbox9=Y&oldPassengers=&checkbox9=Y&oldPassengers=&checkbox9=Y" +
					"&oldPassengers=&checkbox9=Y&oldPassengers=&checkbox9=Y&randCode=" + checkString +
					"&orderRequest.reserve_flag=A&tFlag=dc");
			t_paramString = mBuilder.substring(0, mBuilder.length()-9);
			mBundle.putString(StringPoolUtil.SUB_BOOKS, mBuilder.toString());
			mBundle.putString(StringPoolUtil.GET_CHECK_IMG, checkString);
			mIntent.setAction(StringPoolUtil.SUB_BOOK);
			mIntent.putExtras(mBundle);
			startService(mIntent);
		}
	};

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

	private OnItemClickListener pListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			arg0.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.white));
			position = arg2;
			arg1.setBackgroundColor(getResources().getColor(R.color.light_blue));
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
		filter.addAction(StringPoolUtil.SUB_BOOK_1);
		filter.addAction(StringPoolUtil.SUB_BOOK_2);
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
			if(mBundle == null)
			{
				NotifyExceptionUtil.notify(BookActivity.this, "bundle is null");
				return;
			}
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
			else if(actionString.equals(StringPoolUtil.SUB_BOOK_1))
			{//不需要做什么也可以成功预订
				boolean[] op = mBundle.getBooleanArray(StringPoolUtil.BOOK_RET);
//				if(op[0] && !op[1])
//				{
					Bundle mBundle2 = new Bundle();
					mBundle2.putString(StringPoolUtil.SUB_BOOKS, t_paramString);
					mIntent.setAction(StringPoolUtil.SUB_BOOK_2);
					mIntent.putExtras(mBundle2);
					startService(mIntent);
//				}
//				else {
//					NotifyExceptionUtil.notify(BookActivity.this, "server is busy");
//					return;
//				}
			}
			else if(actionString.equals(StringPoolUtil.SUB_BOOK))
			{
				String errmsg = mBundle.getString(StringPoolUtil.BOOK_RET);
				if(errmsg.equals("Y"))
				{
					Bundle mBundle2 = new Bundle();
					StringBuilder mBuilder = new StringBuilder();
					//method=getQueueCount&train_date=2013-03-04&train_no=240000G50701&
					//station=G507&seat=M&from=BXP&to=WHN&ticket=O052050642M0832500619164250019
					String[] s_paramStrings = tmpStrings[2].split(",");
					mBuilder.append("method=getQueueCount&train_date=").append(s_paramStrings[1])
					.append("&train_no=").append(s_paramStrings[2])
					.append("&station=").append(s_paramStrings[3])
					.append("&seat=").append(tmpSeatString)
					.append("&from=").append(s_paramStrings[4])
					.append("&to=").append(s_paramStrings[5])
					.append("&ticket=").append(s_paramStrings[0]);
					mBundle.putString(StringPoolUtil.SUB_BOOKS, mBuilder.toString());
					mIntent.setAction(StringPoolUtil.SUB_BOOK_1);
					mIntent.putExtras(mBundle);
					startService(mIntent);
				}
				else {
					NotifyExceptionUtil.notify(BookActivity.this, errmsg);
					return;
				}
			}
			else if(actionString.equals(StringPoolUtil.SUB_BOOK_2))
			{
				String errmsg = mBundle.getString(StringPoolUtil.BOOK_RET);
				if(errmsg.equals("Y"))
				{
					NotifyExceptionUtil.notify(BookActivity.this, "book success");
					Log.v(TAG, "book success");
					Intent intent2 = new Intent(BookActivity.this,HomeActivity.class);
					startActivity(intent2);
					BookActivity.this.finish();
				}
				else {
					NotifyExceptionUtil.notify(BookActivity.this, errmsg);
					return;
				}
			}
			else if(actionString.equals(StringPoolUtil.ADD_PASSAGER))
			{

			}
			else if(actionString.equals(StringPoolUtil.READ_PASSAGER))
			{
				p_array = App.array;
				ArrayList<String> mList = new ArrayList<String>();
				for(int i= 0; i < p_array.length();i++)
				{
					JSONObject object;
					try {
						object = p_array.getJSONObject(i);
						mList.add(object.getString("passenger_name"));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						NotifyExceptionUtil.notify(BookActivity.this, e);
						e.printStackTrace();
					}
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(BookActivity.this, android.R.layout.simple_list_item_1,mList);
				passengerListView.setAdapter(adapter);
				passengerListView.setItemsCanFocus(true);
				passengerListView.setOnItemClickListener(pListener);
			}
		}

	}
}
