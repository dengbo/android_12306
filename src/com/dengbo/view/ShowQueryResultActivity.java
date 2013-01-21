package com.dengbo.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.dengbo.app.App;
import com.dengbo.util.NotifyExceptionUtil;
import com.dengbo.util.StringPoolUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ShowQueryResultActivity extends BaseActivity{

	private String result;
	private String start , end ,date,time,kind;
	private ArrayList<String> orderLinkList ;
	private ListView mListView;
	private Button backButton;
	private TextView dateTextView,startTextView,endTextView;
	private ArrayList<HashMap<String, Object>> mList;
	private Recieve recieve;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.query_result);
		mListView = (ListView)findViewById(R.id.query_result_list);
		dateTextView = (TextView)findViewById(R.id.query_result_date);
		startTextView = (TextView)findViewById(R.id.query_result_start);
		endTextView = (TextView)findViewById(R.id.query_result_end);
		backButton = (Button)findViewById(R.id.query_back);

		Bundle mBundle = getIntent().getExtras();
		orderLinkList = mBundle.getStringArrayList(StringPoolUtil.QUERY_ORDER_LINK);
		result = mBundle.getString(StringPoolUtil.QUERY_RESULT);
		start = mBundle.getString(StringPoolUtil.QUERY_START);
		end = mBundle.getString(StringPoolUtil.QUERY_END);
		date = mBundle.getString(StringPoolUtil.QUERY_DATE);
		time = mBundle.getString(StringPoolUtil.QUERY_TIME);
		kind = mBundle.getString(StringPoolUtil.QUERY_KIND);

		dateTextView.setText(date);
		startTextView.setText(start);
		endTextView.setText(end);
		backButton.setOnClickListener(backClickListener);
		initListView();

		recieve = new Recieve();
		IntentFilter filter = new IntentFilter();
		filter.addAction(StringPoolUtil.QUERY_BOOK);
		registerReceiver(recieve, filter);
	}

	private OnClickListener backClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent(ShowQueryResultActivity.this , QueryActivity.class);
			startActivity(mIntent);
			ShowQueryResultActivity.this.finish();
		}
	};

	private void initListView()
	{
		mList = new ArrayList<HashMap<String,Object>>();
		String[] tmp = result.split("[|]");
		for(int i=0;i<tmp.length;i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			String[] item = tmp[i].split(",");
			map.put(StringPoolUtil.TRAIN, item[0]);
			String[] startStrings = breakString(item[1] , true);
			map.put(StringPoolUtil.START, startStrings[0]+" - ");
			String[] endStrings = breakString(item[2] , false);
			map.put(StringPoolUtil.END, endStrings[0]);
			map.put(StringPoolUtil.TIME, startStrings[1]+" - "+endStrings[1]+"    共"+item[3]);
			map.put(StringPoolUtil.IMG_S, Integer.parseInt(startStrings[2]));
			map.put(StringPoolUtil.IMG_E, Integer.parseInt(endStrings[2]));
			StringBuilder mBuilder = new StringBuilder();
			mBuilder.append("商务座：").append(item[4]).append(",");
			mBuilder.append("特等座：").append(item[5]).append(",");
			mBuilder.append("一等座：").append(item[6]).append(",");
			mBuilder.append("二等座：").append(item[7]).append(",");
			mBuilder.append("高级软卧：").append(item[8]).append(",");
			mBuilder.append("软卧：").append(item[9]).append(",");
			mBuilder.append("硬卧：").append(item[10]).append(",");
			mBuilder.append("软座：").append(item[11]).append(",");
			mBuilder.append("硬座：").append(item[12]).append(",");
			mBuilder.append("无座：").append(item[13]).append(",");
			mBuilder.append("其他：").append(item[14]);
			map.put(StringPoolUtil.NUM, mBuilder.toString());
			mList.add(map);
		}
		SimpleAdapter mAdapter = new SimpleAdapter(ShowQueryResultActivity.this, mList, R.layout.query_result_item,
				new String[]{StringPoolUtil.TRAIN,StringPoolUtil.IMG_S,StringPoolUtil.START,StringPoolUtil.IMG_E,
				StringPoolUtil.END,StringPoolUtil.TIME,StringPoolUtil.NUM},
				new int[]{R.id.query_result_item_train,R.id.query_result_item_1,R.id.query_result_item_start,
				R.id.query_result_item_2,R.id.query_result_item_end,R.id.query_result_item_time,R.id.query_result_item_kind});
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(mItemClickListener);
	}

	private OnItemClickListener mItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			String linkString = orderLinkList.get(arg2);
			if(linkString.equals(""))
				NotifyExceptionUtil.notify(ShowQueryResultActivity.this , getResources().getString(R.string.no_ticket_hint));
			else {
				Intent mIntent = App.mIntent;
				StringBuilder mBuilder = new StringBuilder();
				mBuilder.append(linkString).append("#").append(kind).append("#").append(time)
				.append("#").append(date);
				mIntent.putExtra(StringPoolUtil.QUERY_ORDER_LINK, mBuilder.toString());
				mIntent.setAction(StringPoolUtil.QUERY_BOOK);
				startService(mIntent);
			}
		}
	};

	//从数字处截断
	private String[] breakString(String data , boolean isStart)
	{
		boolean flag=false;
		if(data.startsWith("<img"))
		{
			data = data.substring(data.indexOf('>')+1);
			flag=true;
		}
		for(int i= 0;i<data.length();i++)
		{
			char c = data.charAt(i);
			if(c <58 && c>47)
			{
				int id;
				if(flag)
				{
					if(isStart)
						id = R.drawable.start_station;
					else {
						id=R.drawable.end_station;
					}
				}
				else {
					id = R.drawable.place;
				}
				String[] result ={data.substring(0, i) , data.substring(i) ,""+id};
				return result;
			}
		}
		return null;
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
			String acton = intent.getAction();
			if (acton.equals(StringPoolUtil.QUERY_BOOK)) {
				Intent mIntent = new Intent(ShowQueryResultActivity.this , BookActivity.class);
				Bundle mBundle = new Bundle();
				mBundle.putString(StringPoolUtil.BOOK, intent.getExtras().getString(StringPoolUtil.BOOK));
				mIntent.putExtras(mBundle);
				startActivity(mIntent);
				ShowQueryResultActivity.this.finish();
			}
		}

	}
}
