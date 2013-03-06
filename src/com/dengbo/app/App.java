package com.dengbo.app;

import java.util.HashMap;

import org.json.JSONArray;

import com.dengbo.control.InitModel;
import com.dengbo.model.ReqCheckImg;
import com.dengbo.model.ReqCookie;
import com.dengbo.model.ReqLogin;
import com.dengbo.model.ReqLoginToken;
import com.dengbo.model.ReqOrderUnpaid;
import com.dengbo.model.ReqToken;
import com.dengbo.model.ReqYupiao;
import com.dengbo.util.DbHelper;

import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;

public class App extends Application{

	//共享程序数据
//	public static HashMap<String, HashMap<String, String>> modelHashMap , parseHashMap;
	// 启动主service的intent
	public static Intent mIntent;
	public static JSONArray array;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
//		modelHashMap = new HashMap<String, HashMap<String,String>>();
//		parseHashMap = new HashMap<String, HashMap<String,String>>();
//		initModel();
//		initParse();
	}
	private void initParse() {
		// TODO Auto-generated method stub

	}
	private void initModel() {
		// TODO Auto-generated method stub
//		ReqCheckImg.register();
//		ReqCookie.register();
//		ReqLogin.register();
//		ReqLoginToken.register();
//		ReqOrderUnpaid.register();
//		ReqYupiao.register();
//		ReqToken.register();
	}
}
