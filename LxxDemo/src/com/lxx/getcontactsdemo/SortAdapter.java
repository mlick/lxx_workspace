package com.lxx.getcontactsdemo;

import java.util.List;

import com.lxx.demo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class SortAdapter extends BaseAdapter implements SectionIndexer{
	private List<Contact> list = null;
	private Context mContext;
	
	public SortAdapter(Context mContext, List<Contact> list) {
		this.mContext = mContext;
		this.list = list;
	}
	
	/**
	 * ��ListView���ݷ����仯ʱ,���ô˷���������ListView
	 * @param list
	 */
	public void updateListView(List<Contact> list){
		this.list = list;
		notifyDataSetChanged();
	}

	public int getCount() {
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.getcontacts_item_fragment_contact, null);
			view.setTag(viewHolder);
			
			viewHolder.tvName = (TextView) view.findViewById(R.id.item_fragment_contact_name);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.item_fragment_contact_letter);
			viewHolder.letterContainer = view.findViewById(R.id.item_fragment_contact_letter_container);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		
		Contact mContact = list.get(position);
		
		//����position��ȡ���������ĸ��Char asciiֵ
		int section = getSectionForPosition(position);
		
		//�����ǰλ�õ��ڸ÷�������ĸ��Char��λ�� ������Ϊ�ǵ�һ�γ���
		if(position == getPositionForSection(section)){
			viewHolder.letterContainer.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(mContact.getLetter());
		}else{
			viewHolder.letterContainer.setVisibility(View.GONE);
		}
	
		viewHolder.tvName.setText(this.list.get(position).getName());
		
		return view;
	}
	


	final static class ViewHolder {
		TextView tvLetter, tvName;
		View letterContainer;
	}


	/**
	 * ����ListView�ĵ�ǰλ�û�ȡ���������ĸ��Char asciiֵ
	 */
	public int getSectionForPosition(int position) {
		return list.get(position).getLetter().charAt(0);
	}

	/**
	 * ���ݷ��������ĸ��Char asciiֵ��ȡ���һ�γ��ָ�����ĸ��λ��
	 */
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getLetter();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		
		return -1;
	}

	@Override
	public Object[] getSections() {
		return null;
	}
}