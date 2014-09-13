package com.lxx.fragmentbottomdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 该构造类适合在FragmentTabHost中使用
 * 
 * @author mlick 2014年8月5日17:14:57
 * @category BaseFragmentV2 表示第二种形态
 */
public abstract class BaseFragmentV2 extends Fragment {

	private View rootView = null; // 设置缓存的rootView
	private boolean isFirstInstantiation = true;

	@Override
	public void onCreate(Bundle savedInstanceState) { // 这个在不需要重新加载
		super.onCreate(savedInstanceState);
		System.out.println("onCreate");
		getMyArguments(); // 在开始的构造函数汇总就开始调用
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if (isRepeat()) {
			return rootView;
		}

		rootView = inflater.inflate(getLayoutId(), null);
		System.out.println("第一次实例化!!!");
		return rootView;
	}

	/**
	 * 判断是否是重复加载OnCreateView
	 * 
	 * @return 如果是重复加载返回true 否则返回false
	 */
	private boolean isRepeat() {
		if (rootView != null) {
			// 缓存的rootView需要判断是否已经被加过parent，
			// 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (parent != null) {
				parent.removeView(rootView);
			}

			System.out.println("重复实例化!!!");
			return true;
		}
		return false;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (!isFirstInstantiation) {
			System.out.println("onActivityCreated 重复实例化");
			return;
		}
		isFirstInstantiation = false;
		System.out.println("onActivityCreated 第一次实例化");
	}

	/**
	 * 获取从上一次传过来的数据即返回该Fragment对象被实例化时所提供的参数。
	 */
	public abstract void getMyArguments();

	/**
	 * 获取到R.layout.xxx布局的id，并将其返回
	 * 
	 * @return Rid
	 */
	public abstract int getLayoutId();

}
