package com.lxx.indicatorbottomdemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.lxx.demo.R;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * guid fragmentActivity
 * 
 * @author mlick
 * @create_time
 */
public class GuideCirclesActivity extends BaseSampleActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// The look of this sample is set via a style in the manifest
		setContentView(R.layout.indicatorbottom_simple_circles);

		mAdapter = new GuideFragmentAdapter(getSupportFragmentManager());

		// ArrayList<MyCustomFragment> arrayList = new
		// ArrayList<MyCustomFragment>();
		//
		// for (int i = 0; i < 4; i++) {
		// arrayList.add(new MyCustomFragment());
		// }
		//
		// mAdapter.setFragmentsList(arrayList);
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);
		mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);
	}
}