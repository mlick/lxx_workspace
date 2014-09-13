package com.lxx.leftandrightslidingmenuandtabsdemo;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lxx.demo.R;
import com.viewpagerindicator.TabPageIndicator;

public class LeftAndRightMenuAndTabsMainActivity extends BaseActivity {

	public LeftAndRightMenuAndTabsMainActivity() {
		super(R.string.hello_world);
	}

	private final int[] COLORS = new int[] { R.color.red, R.color.green,
			R.color.blue, R.color.white, R.color.black, R.color.red,
			R.color.green, R.color.blue, R.color.white, R.color.black };

	private static final String[] CONTENT = new String[] { "Recent", "Artists",
			"Albums", "Songs", "Playlists", "Recent", "Artists", "Albums",
			"Songs", "Playlists" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.leftandrightmenu_activity_main);

		ViewPager vp = (ViewPager) findViewById(R.id.pager);
		vp.setId("VP".hashCode());
		vp.setAdapter(new ColorPagerAdapter(getSupportFragmentManager()));

		getSlidingMenu().setSecondaryMenu(R.layout.leftandrightmenu_menu_frame_two);
		getSlidingMenu().setSecondaryMenuOffset(100);

		getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame_two, new SampleListFragment())
				.commit();

		vp.setCurrentItem(0);

		getSlidingMenu().setMode(SlidingMenu.LEFT);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(vp);
		indicator.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			int s = COLORS.length - 1;

			@Override
			public void onPageSelected(int position) {
				if (position == 0) {
					getSlidingMenu().setMode(SlidingMenu.LEFT);
					getSlidingMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_FULLSCREEN);
				} else if (position == s) {
					getSlidingMenu().setMode(SlidingMenu.RIGHT);
					// getSlidingMenu().showSecondaryMenu();
					getSlidingMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_FULLSCREEN);
				} else {
					getSlidingMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_NONE);
				}
			}
		});
	}

	public class ColorPagerAdapter extends FragmentPagerAdapter {

		private ArrayList<Fragment> mFragments;

		public ColorPagerAdapter(FragmentManager fm) {
			super(fm);
			mFragments = new ArrayList<Fragment>();
			for (int color : COLORS)
				mFragments.add(new ColorFragment(color));
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return CONTENT[position % CONTENT.length].toUpperCase();
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}

	}

}
