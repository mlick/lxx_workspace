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
	 * 当ListView数据发生变化时,调用此方法来更新ListView
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
		
		//根据position获取分类的首字母的Char ascii值
		int section = getSectionForPosition(position);
		
		//如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
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
	 * 根据ListView的当前位置获取分类的首字母的Char ascii值
	 */
	public int getSectionForPosition(int position) {
		return list.get(position).getLetter().charAt(0);
	}

	/**
	 * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
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