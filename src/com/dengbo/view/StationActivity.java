package com.dengbo.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.dengbo.util.CommonUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class StationActivity extends BaseActivity{

	private GridView mGridView;
	private  ArrayList<HashMap<String, String>> mList = null;
	private int flag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.station_list);
		flag = getIntent().getIntExtra("start", 1);
		mList = CommonUtil.getCode(StationActivity.this);
		mGridView = (GridView) findViewById(R.id.stationList);
		SimpleAdapter mAdapter = new SimpleAdapter(StationActivity.this, mList, android.R.layout.simple_gallery_item, new String[]{"name"}, new int[]{android.R.id.text1});
		mGridView.setAdapter(mAdapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				HashMap<String, String> result = mList.get(arg2);
				String name = result.get("name");
				String code = result.get("code");
				Bundle mBundle = new Bundle();
				mBundle.putString("name", name);
				mBundle.putString("code", code);
				Intent mIntent = new Intent();
				mIntent.putExtras(mBundle);
				if(flag == 1)
				{//出发地
					StationActivity.this.setResult(QueryActivity.START, mIntent);
				}
				else {//目的地
					StationActivity.this.setResult(QueryActivity.END, mIntent);
				}
				StationActivity.this.finish();
			}
		});
	}


}
