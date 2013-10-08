package com.yulebaby.project3.lecture;


import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ListView;

import com.yulebaby.project3.R;
import com.yulebaby.project3.model.BaseGson;
import com.yulebaby.project3.model.Lecture;
import com.yulebaby.project3.net.IParseListener;
import com.yulebaby.project3.net.ZhouRestClientUsage;

public class LectureActivity extends FragmentActivity implements IFragmentControl{

	View contentView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lecture);
		// mAdapter = new
		new ZhouRestClientUsage().getLectureTypeList(listen);
		contentView = findViewById(R.id.lecture_contentview);
		
	}
	
	List<Lecture> dataList;
	IParseListener listen = new IParseListener() {
		@Override
		public void getJsonResult(Object result) {
			BaseGson<Lecture> resultBean = (BaseGson<Lecture>) result;
			List<Lecture> list = resultBean.getData().get("list");

			if (null != list && list.size() > 0) {
				dataList = list;
//				Message msg = mHandler.obtainMessage(MSG_UPDATA_LIST);
//				msg.sendToTarget();
				showFragment(TypeListFragment.TAG,null);

			}

		}
	};
	

//	public void showFragment(String tag) {
//		FragmentManager manager = getSupportFragmentManager();
//		FragmentTransaction fragmentTransaction = manager.beginTransaction();
//		Fragment newFragment = null;
//		if (tag.equals(TypeListFragment.TAG)) {
//			newFragment = new TypeListFragment(dataList);
//		}
//		if(tag.equals(TypeItemListFragment.TAG)){
//			newFragment = new TypeItemListFragment(dataList,1);
//		}
//
//		// Fragment typeListFragment = new TypeItemListFragment();
//		fragmentTransaction.replace(contentView.getId(), newFragment);
//		//fragmentTransaction.addToBackStack(null);
//		// fragmentTransaction.show(newFragment);
//		fragmentTransaction
//				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//		fragmentTransaction.commit();
//
//	}

	@Override
	protected void onStart() {
		super.onStart();

	};

	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {

			default:
				break;
			}
		};
	};

	@Override
	public void showFragment(String indexTAG, Object objectTAG) {
		
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = manager.beginTransaction();
		Fragment newFragment = null;
		if (indexTAG.equals(TypeListFragment.TAG)) {
			newFragment = new TypeListFragment(dataList);
		}
		if(indexTAG.equals(TypeItemListFragment.TAG)){
			//int typeID = (Integer) objectTAG;
			newFragment = new TypeItemListFragment(dataList,((Integer)objectTAG));
		}
		if(indexTAG.equals(PlayFragment.TAG)){
			newFragment = new PlayFragment((String)objectTAG);
		}

		// Fragment typeListFragment = new TypeItemListFragment();
		fragmentTransaction.add(contentView.getId(), newFragment);
		fragmentTransaction.addToBackStack(null);
		// fragmentTransaction.show(newFragment);
		fragmentTransaction
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		fragmentTransaction.commit();
	}




}
