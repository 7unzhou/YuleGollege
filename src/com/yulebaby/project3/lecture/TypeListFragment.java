package com.yulebaby.project3.lecture;

import java.util.List;

import com.yulebaby.project3.R;
import com.yulebaby.project3.model.BaseGson;
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

public class TypeListFragment extends Fragment {

	public static final String TAG = "TypeListFragment";
	protected static final int MSG_UPDATA_LIST = 100;
	ListView lv;
	List<Lecture> dataList;
	LectureAdapter mAdapter;

	public TypeListFragment(List<Lecture> dataList2) {
		this.dataList = dataList2;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// getActivity().
	}

	@Override
	public void onStart() {
		super.onStart();

	};

	OnItemClickListener itemListenr = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			((IFragmentControl) getActivity()).showFragment(
					TypeItemListFragment.TAG, dataList.get(position).getId());
			// showTypeListFragment();
			// Utils.showTip("Click item --"+dataList.get(position).getName());
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
}
