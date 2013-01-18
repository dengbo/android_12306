package com.dengbo.view;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;

public class LoginSignActivity extends BaseActivity implements OnClickListener{
	private Button btn_date; // 出身日期
	private int mYear, mMonth, mDay;
	static final int DATE_DIALOG_ID = 1;
	private Button btn_card_type;  //证件类型
	private String[] cardTypes = {"二代身份证","一代身份证","港澳身份证","台湾身份证"};
	private int cardTempId = 0;
	private int cardId = 0;
	

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
		this.btn_card_type = (Button)findViewById(R.id.btn_reg_card_type);
		this.btn_card_type.setOnClickListener(this);
	}

	//证件类型选择对话框
	private void showCardTypeDailog(){
		new AlertDialog.Builder(this).setTitle("证件类型选择")
			.setSingleChoiceItems(cardTypes, 0, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					cardTempId = which;
				};
			})
			.setPositiveButton("确认", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					cardId = cardTempId;
					btn_card_type.setText(cardTypes[cardId]);
					
				}
			})
			.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			})
			.create().show();
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
			String date = mYear + "-" + (mMonth+1) + "-" + mDay;
			btn_date.setText(date);
		}
	};


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = ((Button)v).getId();
		switch (id) {
		case R.id.btn_reg_date:
			showDialog(DATE_DIALOG_ID);
			break;
		case R.id.btn_reg_card_type:
			showCardTypeDailog();
			break;
		}
	}

}
