package com.dengbo.view;

import java.util.Calendar;

import com.dengbo.app.App;
import com.dengbo.util.StringPoolUtil;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;

public class LoginSignActivity extends BaseActivity implements OnClickListener {
	private Button btn_date; // 出身日期
	private int mYear, mMonth, mDay;
	static final int DATE_DIALOG_ID = 1;
	private Spinner sp_card_type; // 证件类型
	private Spinner sp_passenger_type;
	private RecieveData recieveData;
	private ImageView img_checkImage; // 验证码

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		// 日期
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		this.btn_date = (Button) findViewById(R.id.btn_reg_date);
		this.btn_date.setOnClickListener(this);
		this.sp_card_type = (Spinner) findViewById(R.id.spinner_reg_card_type);
		this.sp_passenger_type = (Spinner) findViewById(R.id.spinner_reg_passenger_type);
		this.img_checkImage = (ImageView) findViewById(R.id.img_reg_checkCode);
		this.img_checkImage.setOnClickListener(this);

		// 获取验证码
		App.mIntent.setAction(StringPoolUtil.REG_CHECK_IMG);
		startService(App.mIntent);

		// 广播信息初始化
		this.recieveData = new RecieveData();
		IntentFilter filter = new IntentFilter();
		filter.addAction(StringPoolUtil.REG_CHECK_IMG);
		filter.addAction(StringPoolUtil.REG_F5_CHECK_IMG);
		registerReceiver(recieveData, filter);

		initPassengetType();
		initCardType();

	}

	/*
	 * 证件类型选择
	 */
	private void initCardType() {
		// TODO Auto-generated method stub
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.card_type, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_card_type.setAdapter(adapter);
		sp_card_type.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
	}

	/*
	 * 旅客类型选择
	 */
	private void initPassengetType() {
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.str_passenger_type,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_passenger_type.setAdapter(adapter);
		sp_passenger_type
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
					}
					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub
					}
				});

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == DATE_DIALOG_ID) {
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case DATE_DIALOG_ID:
			((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
			break;
		}
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			String date = mYear + "-" + (mMonth + 1) + "-" + mDay;
			btn_date.setText(date);
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id =  v.getId();
		switch (id) {
		case R.id.btn_reg_date:
			showDialog(DATE_DIALOG_ID);
			break;
		case R.id.img_reg_checkCode:
			App.mIntent.setAction(StringPoolUtil.REG_F5_CHECK_IMG);
			startService(App.mIntent);
			break;
		}
	}

	// 结果处理
	class RecieveData extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			Bundle mBundle = intent.getExtras();
			if (action != null) {
				if (action.equalsIgnoreCase(StringPoolUtil.REG_CHECK_IMG)
						|| action.equalsIgnoreCase(StringPoolUtil.REG_F5_CHECK_IMG)) { // 显示验证码
					byte[] data = mBundle
							.getByteArray(StringPoolUtil.RESP_CHECK);
					Bitmap mBitmap = BitmapFactory.decodeByteArray(data, 0,
							data.length);
					img_checkImage.setImageBitmap(mBitmap);
				}
			}
		}

	}

}
