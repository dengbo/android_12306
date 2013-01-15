package com.dengbo.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dengbo.view.R;

public class QueryListAdapter extends BaseAdapter{
	public List<Boolean> mChecked;
	public List<String> mList;
	private Context mContext;

	public QueryListAdapter(Context context , List<String> list){
		mList = list;
		mContext = context;
		mChecked = new ArrayList<Boolean>();
		for(int i=0;i<list.size();i++){
			mChecked.add(false);
		}
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.query_pop_item, null);
			holder = new ViewHolder();
			holder.selected = (CheckBox)convertView.findViewById(R.id.query_item_check);
			holder.name = (TextView)convertView.findViewById(R.id.query_item_text);
			final int p = position;
			holder.selected.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					CheckBox cb = (CheckBox)v;
					mChecked.set(p, cb.isChecked());
				}
			});
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}

		holder.selected.setChecked(mChecked.get(position));
		holder.name.setText(mList.get(position));
		return convertView;
	}



static class ViewHolder{
	CheckBox selected;
	TextView name;
}

}