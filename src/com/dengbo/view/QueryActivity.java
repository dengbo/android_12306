package com.dengbo.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.dengbo.adapter.QueryListAdapter;

import android.R.integer;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class QueryActivity extends BaseActivity{

	private Button backButton,queryButton;
	private TextView query_dateTextView,query_timeTextView,query_kindTextView,query_classTextView;
	private TextView query_startTextView , query_endTextView;

	private String[] query_timeStrings;
	private String[] query_kindStrings;
	private String[] query_classStrings;
	private PopupWindow mTimeWindow;				//弹出时间选项
	private LayoutInflater lay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.query);

		lay = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		backButton = (Button) findViewById(R.id.back);
		queryButton = (Button) findViewById(R.id.query);
		query_startTextView = (EditText) findViewById(R.id.query_start);
		query_endTextView = (EditText) findViewById(R.id.query_end);
		query_dateTextView = (TextView) findViewById(R.id.query_date);
		query_timeTextView = (TextView) findViewById(R.id.query_time);
		query_classTextView = (TextView) findViewById(R.id.query_class);
		query_kindTextView = (TextView) findViewById(R.id.query_kind);

		init();
	}

	private void init()
	{
		backButton.setOnClickListener(backClickListener);
		queryButton.setOnClickListener(queryClickListener);
		Resources resources = getResources();
		query_timeStrings = resources.getStringArray(R.array.query_time_array);
		query_classStrings = resources.getStringArray(R.array.query_class_array);
		query_kindStrings = resources.getStringArray(R.array.query_kind_array);
		SimpleDateFormat mDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String date=mDateFormat.format(new java.util.Date());
		query_dateTextView.setText(date);
		query_dateTextView.setOnClickListener(mod_dateClickListener);
		query_timeTextView.setText(query_timeStrings[0]);
		query_timeTextView.setOnClickListener(mod_timeClickListener);

	}

	//修改日期
	private OnClickListener mod_dateClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			new DatePickerDialog(QueryActivity.this, mListener, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH).show();
		}
	};
	private OnDateSetListener mListener = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			Calendar cal = Calendar.getInstance();
			cal .set(Calendar. YEAR , year);
			cal .set(Calendar. MONTH , monthOfYear);
			cal .set(Calendar. DAY_OF_MONTH , dayOfMonth);
			SimpleDateFormat mDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			String date=mDateFormat.format(cal.getTime());
			query_dateTextView.setText(date);
		}
	};

	//修改时间
	private OnClickListener mod_timeClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			View parent = lay.inflate(R.layout.query, null);
			popAwindow(parent, v , query_timeStrings , true);
		}
	};

	private void popAwindow(View parent , final View thisViews ,String[] dataStrings , boolean isSingle) {
        if (mTimeWindow == null) {
            View v = lay.inflate(R.layout.query_pop, null);
            Button mButton = null;
            //初始化listview，加载数据。
            final ListView list=(ListView)v.findViewById(R.id.query_pop);
            final ArrayList<String> queryArrayList = new ArrayList<String>();
			for(String item : dataStrings)
			{
				queryArrayList.add(item);
			}
            if(isSingle)
            {
            	 ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(QueryActivity.this, android.R.layout.simple_expandable_list_item_1);
            	 list.setOnItemClickListener(new OnItemClickListener() {
                     @Override
                     public void onItemClick(AdapterView<?> parent, View view, int position,
                             long id) {
                         String data =  (String) list.getItemAtPosition(position);
                         ((TextView) thisViews).setText(data);
                         mTimeWindow.dismiss();
                     }
                 } );
            	 list.setAdapter(mAdapter);
            }
            else {
            	final QueryListAdapter mAdapter = new QueryListAdapter(QueryActivity.this, queryArrayList);
            	mButton = (Button)v.findViewById(R.id.query_pop_button);
            	mButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						StringBuilder dataBuilder = new StringBuilder();
						List<Boolean> mList = mAdapter.mChecked;
						for(int i = 0;i<mList.size();i++)
						{
							if(mList.get(i))
							{
								dataBuilder.append(queryArrayList.get(i));
								dataBuilder.append(",");
							}
						}
						((TextView) thisViews).setText(dataBuilder.toString());
						mTimeWindow.dismiss();
					}
				});
            	list.setAdapter(mAdapter);
			}

            list.setItemsCanFocus(true);
            mTimeWindow = new PopupWindow(v, 500,260);
        }

        //设置整个popupwindow的样式。
        mTimeWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup));
        //使窗口里面的空间显示其相应的效果，比较点击button时背景颜色改变。
        //如果为false点击相关的空间表面上没有反应，但事件是可以监听到的。
        //listview的话就没有了作用。
        mTimeWindow.setFocusable(true);//如果不设置setFocusable为true，popupwindow里面是获取不到焦点的，那么如果popupwindow里面有输入框等的话就无法输入。
        mTimeWindow.update();
        mTimeWindow.showAtLocation(parent, Gravity.CENTER_VERTICAL, 0, 0);
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
			Bundle mBundle = new Bundle();

		}
	};
}
