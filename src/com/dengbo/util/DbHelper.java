package com.dengbo.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper {

	private  DatabaseHelper dmHelper;
	private  SQLiteDatabase dm;

	private static final String DATABASE_NAME="android_12306";
	private static final int DATABASE_VERSION=1;

	private final Context dmCtx;

	private class DatabaseHelper extends SQLiteOpenHelper{

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME,null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE IF NOT EXISTS "+StringPoolUtil.MODEL_TABLE +
					"(id integer primary key autoincrement, "+StringPoolUtil.KEY_NAME+
					" varchar(20), "+StringPoolUtil.KEY_ACTION+
					" varchar(30), "+StringPoolUtil.KEY_URL+
					" varchar(60), "+StringPoolUtil.KEY_PRO+
					" varchar(60), "+StringPoolUtil.KEY_METHOD+
					" varchar(60));");
			db.execSQL("CREATE TABLE IF NOT EXISTS "+StringPoolUtil.MODEL_TABLE +
					"(id integer primary key autoincrement, "+StringPoolUtil.KEY_NAME+
					" varchar(20), "+StringPoolUtil.KEY_ACTION+
					" varchar(30));");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			String drop_modelString = "DROP TABLE IF EXISTS "+StringPoolUtil.MODEL_TABLE;
			String drop_parseString = "DROP TABLE IF EXISTS "+StringPoolUtil.PARSE_TABLE;
			db.execSQL(drop_modelString);
			db.execSQL(drop_parseString);
			onCreate(db);
		}
	}

	public DbHelper (Context ctx){
		this.dmCtx=ctx;
	}


	public DbHelper open() throws SQLException{
		dmHelper=new DatabaseHelper(dmCtx);
		dm=dmHelper.getWritableDatabase();
		return this;
	}

	public boolean isOpen() {
		return dm.isOpen();
	}
	public void close(){
		if(dm.isOpen())
		    dmHelper.close();
	}


	public void deletetable(String name){
		String deleteString = "DROP TABLE IF EXISTS "+name;
		dm.execSQL(deleteString);
	}


	public long addItems(String name , String action , String url , String protocol , String method){

		ContentValues initialValues=new ContentValues();
		initialValues.put(StringPoolUtil.KEY_NAME,name);
		initialValues.put(StringPoolUtil.KEY_ACTION,action);
		initialValues.put(StringPoolUtil.KEY_URL, url);
		initialValues.put(StringPoolUtil.KEY_PRO, protocol);
		initialValues.put(StringPoolUtil.KEY_METHOD, method);

		return dm.insert(StringPoolUtil.MODEL_TABLE,null,initialValues);
	}

	public long addItems(String name , String action){

		ContentValues initialValues=new ContentValues();
		initialValues.put(StringPoolUtil.KEY_NAME,name);
		initialValues.put(StringPoolUtil.KEY_ACTION,action);

		return dm.insert(StringPoolUtil.PARSE_TABLE,null,initialValues);
	}


	public boolean deleteItem(String action , String table){
		return dm.delete(table, StringPoolUtil.KEY_ACTION+"='"+action+"'", null)>0;
	}


	public Cursor getAllItems(String table){
		Cursor mCursor;
		if (table.equals(StringPoolUtil.MODEL_TABLE)) {
			mCursor=dm.query(table ,new String [] {StringPoolUtil.KEY_NAME,StringPoolUtil.KEY_ACTION,StringPoolUtil.KEY_URL,StringPoolUtil.KEY_PRO,StringPoolUtil.KEY_METHOD},null, null, null, null,null);
		}
		else {
			mCursor=dm.query(table ,new String [] {StringPoolUtil.KEY_NAME,StringPoolUtil.KEY_ACTION},null, null, null, null,null);
		}

		if(mCursor!=null){
			mCursor.moveToFirst();
		}

		return mCursor;
	}


	public Cursor getItem(String action , String table)throws SQLException{
		Cursor mCursor;
		if (table.equals(StringPoolUtil.MODEL_TABLE)) {
			mCursor=dm.query(table ,new String [] {StringPoolUtil.KEY_NAME,StringPoolUtil.KEY_ACTION,StringPoolUtil.KEY_URL,StringPoolUtil.KEY_PRO,StringPoolUtil.KEY_METHOD},StringPoolUtil.KEY_ACTION+"='"+action+"'",null, null, null, null,null);
		}
		else {
			mCursor=dm.query(table ,new String [] {StringPoolUtil.KEY_NAME,StringPoolUtil.KEY_ACTION},StringPoolUtil.KEY_ACTION+"='"+action+"'",null, null, null, null,null);
		}

		if(mCursor!=null){
			mCursor.moveToFirst();
		}

		return mCursor;
	}

	public boolean updateItem(String name , String action , String url , String protocol , String method){
		ContentValues initialValues=new ContentValues();
		initialValues.put(StringPoolUtil.KEY_NAME,name);
		initialValues.put(StringPoolUtil.KEY_URL, url);
		initialValues.put(StringPoolUtil.KEY_PRO, protocol);
		initialValues.put(StringPoolUtil.KEY_METHOD, method);

		return dm.update(StringPoolUtil.MODEL_TABLE, initialValues, StringPoolUtil.KEY_ACTION+"='"+action+"'", null)>0;
	}

	public boolean updateItem(String name , String action){
		ContentValues initialValues=new ContentValues();
		initialValues.put(StringPoolUtil.KEY_NAME,name);

		return dm.update(StringPoolUtil.PARSE_TABLE, initialValues, StringPoolUtil.KEY_ACTION+"='"+action+"'", null)>0;
	}
}
