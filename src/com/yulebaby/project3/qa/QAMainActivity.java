package com.yulebaby.project3.qa;

import java.util.ArrayList;
import java.util.List;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yulebaby.project3.R;
import com.yulebaby.project3.comment.Utils;
import com.yulebaby.project3.lecture.LectureAdapter;
import com.yulebaby.project3.model.BaseGson;
import com.yulebaby.project3.model.BaseModelGson;
import com.yulebaby.project3.model.ExamDetail;
import com.yulebaby.project3.model.Lecture;
import com.yulebaby.project3.net.IParseListener;
import com.yulebaby.project3.net.ZhouRestClientUsage;

public class QAMainActivity extends FragmentActivity {

	protected static final int MSG_UPDATE_EXPERTLIST = 300;
	private ViewPager viewPager;// 页卡内容
	private ImageView ivCursor;// 动画图片
	private TextView tvTitleExpert, tvTitleMyQA;
	private ArrayList<Fragment> fragmentsList;// Tab页面列表
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	// private View view1, view2;// 各个页卡

	Handler mHander = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			// case MSG_UPDATE_EXPERTLIST:
			// mAdapter.notifyDataSetChanged();
			// lv.invalidate();
			// break;

			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qamain);
		// dataList = new ArrayList<Lecture>();
		InitImageView();
		InitTextView();
		InitViewPager();

	}

	private void InitViewPager() {
		viewPager = (ViewPager) findViewById(R.id.vPager);
		fragmentsList = new ArrayList<Fragment>();
		// LayoutInflater inflater = getLayoutInflater();
		// view1 = inflater.inflate(R.layout.fragment_typelist, null);
		// view2 = inflater.inflate(R.layout.layout_myquestion, null);
		// initExpertView(view1);
		// views.add(view1);
		// views.add(view2);
		Fragment expertFragment = new FragmentExpertList();
		Fragment qaFragment = new MyQAFragment();
		fragmentsList.add(expertFragment);
		fragmentsList.add(qaFragment);
		viewPager.setAdapter(new QAFragmentPagerAdapter(
				getSupportFragmentManager(), fragmentsList));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	/**
	 * 初始化头标
	 */

	private void InitTextView() {
		tvTitleExpert = (TextView) findViewById(R.id.tv_qa_expert);
		tvTitleMyQA = (TextView) findViewById(R.id.tv_qa_my);

		tvTitleExpert.setOnClickListener(new MyOnClickListener(0));
		tvTitleMyQA.setOnClickListener(new MyOnClickListener(1));
	}

	/**
	 * 2 * 初始化动画 3
	 */

	private void InitImageView() {
		ivCursor = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a)
				.getWidth();// 获取图片宽度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		offset = (screenW / 2 - bmpW) / 2;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		ivCursor.setImageMatrix(matrix);// 设置动画初始位置
	}

	/**
	 * 
	 * 头标点击监听 3
	 */
	private class MyOnClickListener implements OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		public void onClick(View v) {
			viewPager.setCurrentItem(index);
		}

	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		int two = one * 2;// 页卡1 -> 页卡3 偏移量

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageSelected(int arg0) {
			Animation animation = new TranslateAnimation(one * currIndex, one
					* arg0, 0, 0);
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			ivCursor.startAnimation(animation);
			Utils.showTip("您选择了" + viewPager.getCurrentItem() + "页卡");
		}

	}

	// List<Lecture> dataList;

}
