package com.lxx.indicatorbottomdemo;

import com.viewpagerindicator.PageIndicator;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public abstract class BaseSampleActivity extends FragmentActivity {
    protected GuideFragmentAdapter mAdapter;
    protected ViewPager mPager;
    protected PageIndicator mIndicator;
}
