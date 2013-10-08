package com.yulebaby.project3.lecture;

import com.yulebaby.project3.R;
import ru.denivip.android.video.MediaController;
import ru.denivip.android.video.VideoView;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlayFragment extends Fragment {
	public static final String TAG = "PlayFragment";
	private VideoView mVideo;
	private String playVideoUrl;

	public PlayFragment(String parseUrl) {
		this.playVideoUrl = parseUrl;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_videoplay, null);
		mVideo = (VideoView) view.findViewById(R.id.videoView);
		mVideo.requestFocus();

		MediaController controller = new MediaController(getActivity());
		mVideo.setMediaController(controller);
		mVideo.setVideoURI(Uri.parse(playVideoUrl));
		mVideo.start();

		return view;
	}
}
