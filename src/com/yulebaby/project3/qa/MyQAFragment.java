package com.yulebaby.project3.qa;

import com.yulebaby.project3.R;
import ru.denivip.android.video.MediaController;
import ru.denivip.android.video.VideoView;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyQAFragment extends Fragment {
	public static final String TAG = "MyQAFragment";

	public MyQAFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_myqa, null);

		return view;
	}
}
