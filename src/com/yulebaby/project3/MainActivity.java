package com.yulebaby.project3;

import com.yulebaby.project3.component.SlidingMenu;
import com.yulebaby.project3.component.app.SlidingFragmentActivity;
import com.yulebaby.project3.evalution.EvalutionActivity;
import com.yulebaby.project3.lecture.LectureActivity;
import com.yulebaby.project3.qa.QAMainActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends BaseActivity implements OnClickListener {

	/**
	 * @param titleRes
	 */
	public MainActivity() {
		super(R.string.app_name);
		// TODO Auto-generated constructor stub
	}

	View viewLecture;
	View viewQA;
	View viewEvaluation;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		//getSlidingMenu(
		getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
//		getSupportFragmentManager()
//		.beginTransaction()
//		.replace(R.id.content_frame, new SampleListFragment())
//		.commit();
		
		getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);
		getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame_two, new SampleListFragment())
		.commit();
		
		
//		setContentView(R.layout.activity_main);
		viewEvaluation = findViewById(R.id.ic_main_3);
		viewLecture = findViewById(R.id.ic_main_1);
		viewQA = findViewById(R.id.ic_main_2);
		viewEvaluation.setOnClickListener(this);
		viewLecture.setOnClickListener(this);
		viewQA.setOnClickListener(this);

	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.ic_main_1:
			intent = new Intent(this, LectureActivity.class);
			startActivity(intent);
			break;
		case R.id.ic_main_2:
			intent = new Intent(this, QAMainActivity.class);
			startActivity(intent);
			break;
		case R.id.ic_main_3:
			intent = new Intent(this, EvalutionActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}

}
