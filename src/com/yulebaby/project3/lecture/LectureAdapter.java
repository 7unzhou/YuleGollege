package com.yulebaby.project3.lecture;

import java.util.ArrayList;
import java.util.List;

import com.yulebaby.project3.R;
import com.yulebaby.project3.model.Lecture;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LectureAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater inflater;
	private List<Lecture> dataList;

	public LectureAdapter(Context context, List<Lecture> list) {
		this.dataList = list;
		this.mContext = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (null == dataList || dataList.size() == 0)
			return null;
		ViewHolder holder;
		if(null ==convertView){
			convertView = inflater.inflate(R.layout.lecture_iten, null);
			holder = new ViewHolder();
			holder.tvLectureName = (TextView) convertView.findViewById(R.id.tv_lecture_itemname);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		holder.tvLectureName.setText(dataList.get(position).getName());
		
		return convertView;
	}

	class ViewHolder {
		TextView tvLectureName;
	}

}