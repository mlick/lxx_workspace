package com.lxx.indicatorbottomdemo;

import com.lxx.demo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 首次引导的页面
 * 
 * @author lxx
 * 
 */
public final class GuideFragment extends Fragment {
	private static final String KEY_CONTENT = "TestFragment:Content";
	private static final String KEY_SHOWVIEW = "TestFragment:View";

	public static GuideFragment newInstance(int resId, boolean b) {
		GuideFragment fragment = new GuideFragment();

		// StringBuilder builder = new StringBuilder();
		// for (int i = 0; i < 20; i++) {
		// builder.append(content).append(" ");
		// }
		// builder.deleteCharAt(builder.length() - 1);
		// fragment.mContent = builder.toString();
		// fragment.imageView.setImageResource(resId);
		fragment.picId = resId;
		fragment.isShowPic = b;
		return fragment;
	}

	// private String mContent = "???";
	private int picId;
	private boolean isShowPic;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if ((savedInstanceState != null)
				&& savedInstanceState.containsKey(KEY_CONTENT)) {
			// mContent = savedInstanceState.getString(KEY_CONTENT);
			picId = savedInstanceState.getInt(KEY_CONTENT);
			isShowPic = savedInstanceState.getBoolean(KEY_SHOWVIEW);
			// System.out.println("Here！");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TextView text = new TextView(getActivity());
		View view = inflater.inflate(R.layout.indicatorbottom_custom_fragment, null);

		ImageView imageView = (ImageView) view
				.findViewById(R.id.custom_image_view);

		// = new ImageView(getActivity());

		// text.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
		// LayoutParams.MATCH_PARENT));

		imageView.setImageResource(picId);

		if (isShowPic) {
			Button button = (Button) view.findViewById(R.id.custom_go_on);
			button.setVisibility(View.VISIBLE);
			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					getActivity().finish();
				}
			});
		}
		// text.setText(mContent);
		// text.setTextSize(20 * getResources().getDisplayMetrics().density);
		// text.setPadding(20, 20, 20, 20);

		// LinearLayout layout = new LinearLayout(getActivity());
		// layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
		// LayoutParams.MATCH_PARENT));
		// layout.setGravity(Gravity.CENTER);
		// layout.addView(text);

		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// outState.putString(KEY_CONTENT, mContent);
		outState.putInt(KEY_CONTENT, picId);
		outState.putBoolean(KEY_SHOWVIEW, isShowPic);
	}
}
