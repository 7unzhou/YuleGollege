package com.yulebaby.project3.qa;

import java.util.ArrayList;
import java.util.List;

import com.yulebaby.project3.R;
import com.yulebaby.project3.lecture.LectureAdapter;
import com.yulebaby.project3.model.BaseGson;
import com.yulebaby.project3.model.BaseModelGson;
import com.yulebaby.project3.model.ExamDetail;
import com.yulebaby.project3.model.Lecture;
import com.yulebaby.project3.net.IParseListener;
import com.yulebaby.project3.net.ZhouRestClientUsage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FragmentExpertList extends Fragment {

	public static final String TAG = "FragmentExpertList";
	protected static final int MSG_UPDATA_LIST = 100;
	ListView lv;
	List<Lecture> dataList;
	LectureAdapter mAdapter;

	public FragmentExpertList() {
		new ZhouRestClientUsage().getExamTypeList(listen);
	}

	IParseListener listen = new IParseListener() {

		@Override
		public void getJsonResult(Object result) {
			BaseGson<Lecture> resultBean = (BaseGson<Lecture>) result;
			List<Lecture> list = resultBean.getData().get("list");

			// if (null != list && list.size() > 0) {
			dataList = list;
			System.out.println("Examlist size:" + dataList.size());
			mAdapter = new LectureAdapter(getActivity(), dataList);
			lv.setAdapter(mAdapter);
			// mHander.sendEmptyMessage(MSG_UPDATE_EXPERTLIST);
			// }

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// getActivity().
		dataList = new ArrayList<Lecture>();
	}

	@Override
	public void onStart() {
		super.onStart();

	};

	OnItemClickListener itemListenr = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			int detailID = dataList.get(position).getId();
			new ZhouRestClientUsage().getExamDetail(detailListen, detailID);

		}
	};

	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_UPDATA_LIST:

				break;

			default:
				break;
			}
		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_typelist, null);

		lv = (ListView) view.findViewById(R.id.lv_lecture);
		lv.setOnItemClickListener(itemListenr);
		mAdapter = new LectureAdapter(getActivity(), dataList);
		lv.setAdapter(mAdapter);
		return view;
	}

	IParseListener detailListen = new IParseListener() {

		@Override
		public void getJsonResult(Object result) {
			BaseModelGson<ExamDetail> resultBean = (BaseModelGson<ExamDetail>) result;
			ExamDetail data = resultBean.getData();
			System.out.println("ExamDetail:" + data.getTitle());

			// BaseGson<Lecture> resultBean = (BaseGson<Lecture>) result;
			// List<Lecture> list = resultBean.getData().get("list");

			// if (null != list && list.size() > 0) {

			// mHander.sendEmptyMessage(MSG_UPDATE_EXPERTLIST);
			// }

		}
	};

}
