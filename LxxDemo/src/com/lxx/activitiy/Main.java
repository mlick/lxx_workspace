package com.lxx.activitiy;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lxx.demo.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Main extends ListActivity {

	private String[] explain_list = { "fragmentbottomdemo",
			"leftandrightslidingmenuandtabsdemo", "pagerslidingtabstripdemo",
			"pagerslidingtabstripdemo", "getcontactsdemo", "getcontacts2demo",
			"indicatorbottomdemo", "circleimageviewdemo" };

	private String[] sample_list = { "�ײ�buttom����", "���Ҳ໬�˵���Tab����", "Tab������Ч ",
			"Tab������Ч�Լ���ɫ�任", "�Զ����ȡ��ϵ��", "�Զ����ȡ��ϵ��2", "ԲȦ��ʽ������ҳ��", "Բ��ͷ��Demo" };

	private String[] demo_actions = { "fragmenttabhostdemo",
			"leftandrightslidingmenuandtabsdemo", "pagerslidingtabstripdemo2",
			"pagerslidingtabstripdemo", "getcontactsdemo", "getcontactsdemo2",
			"indicatorbottom", "circleimageview" };

	// @ViewInject(R.id.)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getcontacts2_activity_main_layout);
		ViewUtils.inject(this);
		List<String> items = fillList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);

		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		Toast toast = Toast.makeText(getApplicationContext(),
				explain_list[position] + "��", Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
		// Toast.makeText(getApplicationContext(), ,
		// Toast.LENGTH_LONG).show();
		Intent intent = new Intent(demo_actions[position]);
		startActivity(intent);
	}

	private List<String> fillList() {
		List<String> items = new ArrayList<String>();
		for (int i = 0; i < sample_list.length; i++) {
			items.add(sample_list[i]);
		}
		return items;
	}

}
