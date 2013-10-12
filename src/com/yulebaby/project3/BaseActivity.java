/**
 * 
 */
package com.yulebaby.project3;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;

import com.yulebaby.project3.component.SlidingMenu;
import com.yulebaby.project3.component.app.SlidingFragmentActivity;


/**
 * @author : zhoujunzhou
 * @date : 2013-10-12
 * @Description : 
 */
public class BaseActivity extends SlidingFragmentActivity{

	private int mTitleRes;
	protected ListFragment mFrag;

	public BaseActivity(int titleRes) {
		mTitleRes = titleRes;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle(mTitleRes);

		// set the Behind View
		//setBehindContentView(R.layout.menu_frame);
		setBehindContentView(R.layout.menu_frame);
		if (savedInstanceState == null) {
			FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
			mFrag = new SampleListFragment();
			t.replace(R.id.menu_frame, mFrag);
			t.commit();
		} else {
			mFrag = (ListFragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
		}

		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);


		//getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
//	@Override
//	public boolean onOptionsItemSelected(android.view.MenuItem item) {
//		switch (item.getItemId()) {
//		case android.R.id.home:
//			toggle();
//			return true;
//		case R.id.github:
//			Util.goToGitHub(this);
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case android.R.id.home:
//			toggle();
//			return true;
//		case R.id.github:
//			Util.goToGitHub(this);
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

}
