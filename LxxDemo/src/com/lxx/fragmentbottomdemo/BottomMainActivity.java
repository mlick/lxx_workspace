package com.lxx.fragmentbottomdemo;

import com.lxx.demo.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

public class BottomMainActivity extends FragmentActivity {

	private FragmentTabHost mTabHost;

	// �������������Fragment����
	private Class fragmentArray[] = { FragmentPage1.class, FragmentPage2.class,
			FragmentPage3.class, FragmentPage4.class };

	// ������������Ű�ťͼƬ
	private int mImageViewArray[] = { R.drawable.image1, R.drawable.image1,
			R.drawable.image1, R.drawable.image1 };

	// Tabѡ�������
	private String mTextviewArray[] = { "Simple", "Contacts", "Custom", "Mlick" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bottom_tabs);
		// mTabHost = new FragmentTabHost(this);
		// mTabHost.setup(this, getSupportFragmentManager(),
		// R.id.menu_settings);
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		TabWidget mTabWidget = (TabWidget) findViewById(android.R.id.tabs);
		mTabWidget.setBackgroundColor(Color.BLACK);
		mTabWidget.setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
		mTabWidget.setGravity(Gravity.CENTER_VERTICAL);

		// �õ�fragment�ĸ���
		int count = fragmentArray.length;

		for (int i = 0; i < count; i++) {
			Bundle b = new Bundle();
			TabSpec tabSpec = setIndicator(
					mTabHost.newTabSpec(mTextviewArray[i]), mImageViewArray[i]);
			b.putString("key", mTextviewArray[i]);
			mTabHost.addTab(tabSpec, fragmentArray[i], b);
		}

//		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
//
//			@Override
//			public void onTabChanged(String tabId) {
//				for (int i = 0; i < mTextviewArray.length; i++) {
//					if (mTextviewArray[i].equals(tabId)) {
////						Toast.makeText(getApplicationContext(),
////								"�������" + mTextviewArray[i], Toast.LENGTH_SHORT)
////								.show();
//					}
//				}
//			}
//		});

	}

	public TabSpec setIndicator(TabSpec spec, int resid) {
		// TODO Auto-generated method stub
		View v = LayoutInflater.from(this).inflate(R.layout.bottom_tabs_text, null);
		v.setBackgroundResource(resid);
		TextView text = (TextView) v.findViewById(R.id.tab_title);
		text.setText("");
		return spec.setIndicator(v);
	}

}
