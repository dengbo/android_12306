package com.dengbo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QueryActivity extends BaseActivity{

	private Button backButton,queryButton;
	private EditText query_startEditText,query_endEditText;
	private TextView query_dateTextView,query_timeTextView,query_kindTextView,query_classTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		backButton = (Button) findViewById(R.id.back);
		queryButton = (Button) findViewById(R.id.query);
		query_startEditText = (EditText) findViewById(R.id.query_start);
		query_endEditText = (EditText) findViewById(R.id.query_end);
		query_dateTextView = (TextView) findViewById(R.id.query_date);
		query_timeTextView = (TextView) findViewById(R.id.query_time);
		query_classTextView = (TextView) findViewById(R.id.query_class);
		query_kindTextView = (TextView) findViewById(R.id.query_kind);


	}
	//返回键响应
	private OnClickListener backClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent mIntent = new Intent(QueryActivity.this , HomeActivity.class);
			startActivity(mIntent);
			QueryActivity.this.finish();
		}
	};
	//查询键响应
	private OnClickListener queryClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

		}
	};
}
