package com.dengbo.view;

import java.util.HashMap;

import com.dengbo.util.StringPoolUtil;

import android.os.Bundle;
import android.widget.TextView;

public class BookActivity extends BaseActivity{

	private HashMap<String, Object> resultHashMap;
	private TextView mTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book);
		Bundle mBundle =  getIntent().getExtras();
		if(mBundle != null)
			resultHashMap = (HashMap<String, Object>)mBundle.getSerializable(StringPoolUtil.QUERY_RESULT);

		mTextView.setText((CharSequence) resultHashMap.get(StringPoolUtil.TRAIN));
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
