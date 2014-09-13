package com.lxx.indicatorbottomdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lxx.demo.R;
import com.viewpagerindicator.IconPagerAdapter;

public class GuideFragmentAdapter extends FragmentPagerAdapter implements
		IconPagerAdapter {

	protected static final int[] ICONS = new int[] {
			R.drawable.perm_group_calendar_selected,
			R.drawable.perm_group_camera_selected,
			R.drawable.perm_group_device_alarms_selected,
			R.drawable.perm_group_location_selected };

	private int mCount = ICONS.length;

	public GuideFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		System.out.println("position is " + position);
		if (position == (ICONS.length - 1)) {
			return GuideFragment.newInstance(ICONS[position % ICONS.length],
					true);
		}
		return GuideFragment.newInstance(ICONS[position % ICONS.length], false);
	}

	@Override
	public int getCount() {
		return mCount;
	}

	// @Override
	// public CharSequence getPageTitle(int position) {
	// return TestFragmentAdapter.CONTENT[position % CONTENT.length];
	// }

	@Override
	public int getIconResId(int index) {
		return ICONS[index % ICONS.length];
	}

	// public void setCount(int count) {
	// if (count > 0 && count <= 10) {
	// mCount = count;
	// notifyDataSetChanged();
	// }
	// }
}