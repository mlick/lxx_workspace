package com.lxx.getcontactsdemo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.lxx.demo.R;
import com.lxx.getcontactsdemo.SideBar.OnTouchingLetterChangedListener;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts.Photo;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class GetContactsMainActivity extends Activity {

	/** 获取库Phon表字段 **/
	private static final String[] PHONES_PROJECTION = new String[] {
			Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_ID, Phone.CONTACT_ID };

	/** 联系人显示名称 **/
	private static final int PHONES_DISPLAY_NAME_INDEX = 0;

	/** 电话号码 **/
	private static final int PHONES_NUMBER_INDEX = 1;

	/** 头像ID **/
	private static final int PHONES_PHOTO_ID_INDEX = 2;

	/** 联系人的ID **/
	private static final int PHONES_CONTACT_ID_INDEX = 3;

	private List<Contact> contacts = new ArrayList<Contact>();

	private SideBar mSideBar;
	private ListView mListView;
	private TextView tvLetter;
	private SortAdapter adapter;

	private ProgressDialog dialog;
	/**
	 * 进行异步处理
	 */
	private Handler handler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			Collections.sort(contacts, new Comparator<Contact>() {
				@Override
				public int compare(Contact lhs, Contact rhs) {
					return lhs.getLetter().compareTo(rhs.getLetter());
				}
			});
			dialog.dismiss();
			adapter = new SortAdapter(GetContactsMainActivity.this, contacts);
			mListView.setAdapter(adapter);
			mSideBar.setTextView(tvLetter);
			mListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Contact contact = contacts.get(arg2);
					CallTelephone.showCustomDialog(
							GetContactsMainActivity.this,
							"您正在拨打" + contact.getName() + "是否前往？",
							contact.getNumber());
				}
			});
			return false;
		}
	});

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getcontacts_fragment_contacts);
		dialog = ProgressDialog.show(this, "请稍等", "正在读取中...", false);
		findViews();
		setListeners();
		new Thread(new Runnable() {

			@Override
			public void run() {
				getContacts();
				handler.sendEmptyMessage(0);
			}
		}).start();

	}

	private void findViews() {
		mSideBar = (SideBar) findViewById(R.id.fragment_contacts_sideBar);
		mListView = (ListView) findViewById(R.id.fragment_contacts_list);
		tvLetter = (TextView) findViewById(R.id.fragment_contacts_letter);
	}

	/**
	 * 对SideBar做监听
	 */
	private void setListeners() {
		mSideBar.setOnTouchingLetterChangedListener(new LetterListViewListener());
	}

	private void getContacts() {
		ContentResolver resolver = getContentResolver();

		// 获取手机联系人
		Cursor phoneCursor = resolver.query(Phone.CONTENT_URI,
				PHONES_PROJECTION, null, null, null);
		final CharacterParser characterParser = CharacterParser.getInstance();
		if (phoneCursor != null) {
			while (phoneCursor.moveToNext()) {

				// 得到手机号码
				String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
				// 当手机号码为空的或者为空字段 跳过当前循环
				if (TextUtils.isEmpty(phoneNumber))
					continue;

				// 得到联系人名称
				String contactName = phoneCursor
						.getString(PHONES_DISPLAY_NAME_INDEX);

				// 得到联系人ID
				Long contactid = phoneCursor.getLong(PHONES_CONTACT_ID_INDEX);

				// 得到联系人头像ID
				Long photoid = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX);

				// 得到联系人头像Bitamp
				Bitmap contactPhoto = null;

				// photoid 大于0 表示联系人有头像 如果没有给此人设置头像则给他一个默认的
				if (photoid > 0) {
					Uri uri = ContentUris.withAppendedId(
							ContactsContract.Contacts.CONTENT_URI, contactid);
					InputStream input = ContactsContract.Contacts
							.openContactPhotoInputStream(resolver, uri);
					contactPhoto = BitmapFactory.decodeStream(input);
				} else {
					contactPhoto = BitmapFactory.decodeResource(getResources(),
							R.drawable.brand_default_head);
				}

				Contact contact = new Contact();
				contact.setName(contactName);
				contact.setNumber(phoneNumber);
				contact.setHead(contactPhoto);
				contact.setLetter(characterParser.getFirstLetter(contactName));

				contacts.add(contact);
			}

			phoneCursor.close();
		}
	}

	private class LetterListViewListener implements
			OnTouchingLetterChangedListener {

		@Override
		public void onTouchingLetterChanged(final String s) {
			// 该字母首次出现的位置
			int position = adapter.getPositionForSection(s.charAt(0));
			if (position != -1) {
				mListView.setSelection(position);
			}
		}
	}

}
