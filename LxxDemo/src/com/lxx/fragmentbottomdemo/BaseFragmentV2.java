package com.lxx.fragmentbottomdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * �ù������ʺ���FragmentTabHost��ʹ��
 * 
 * @author mlick 2014��8��5��17:14:57
 * @category BaseFragmentV2 ��ʾ�ڶ�����̬
 */
public abstract class BaseFragmentV2 extends Fragment {

	private View rootView = null; // ���û����rootView
	private boolean isFirstInstantiation = true;

	@Override
	public void onCreate(Bundle savedInstanceState) { // ����ڲ���Ҫ���¼���
		super.onCreate(savedInstanceState);
		System.out.println("onCreate");
		getMyArguments(); // �ڿ�ʼ�Ĺ��캯�����ܾͿ�ʼ����
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if (isRepeat()) {
			return rootView;
		}

		rootView = inflater.inflate(getLayoutId(), null);
		System.out.println("��һ��ʵ����!!!");
		return rootView;
	}

	/**
	 * �ж��Ƿ����ظ�����OnCreateView
	 * 
	 * @return ������ظ����ط���true ���򷵻�false
	 */
	private boolean isRepeat() {
		if (rootView != null) {
			// �����rootView��Ҫ�ж��Ƿ��Ѿ����ӹ�parent��
			// �����parent��Ҫ��parentɾ����Ҫ��Ȼ�ᷢ�����rootview�Ѿ���parent�Ĵ���
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (parent != null) {
				parent.removeView(rootView);
			}

			System.out.println("�ظ�ʵ����!!!");
			return true;
		}
		return false;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (!isFirstInstantiation) {
			System.out.println("onActivityCreated �ظ�ʵ����");
			return;
		}
		isFirstInstantiation = false;
		System.out.println("onActivityCreated ��һ��ʵ����");
	}

	/**
	 * ��ȡ����һ�δ����������ݼ����ظ�Fragment����ʵ����ʱ���ṩ�Ĳ�����
	 */
	public abstract void getMyArguments();

	/**
	 * ��ȡ��R.layout.xxx���ֵ�id�������䷵��
	 * 
	 * @return Rid
	 */
	public abstract int getLayoutId();

}
