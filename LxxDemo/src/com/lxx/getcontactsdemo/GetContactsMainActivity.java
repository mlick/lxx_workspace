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

	/** ��ȡ��Phon���ֶ� **/
	private static final String[] PHONES_PROJECTION = new String[] {
			Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_ID, Phone.CONTACT_ID };

	/** ��ϵ����ʾ���� **/
	private static final int PHONES_DISPLAY_NAME_INDEX = 0;

	/** �绰���� **/
	private static final int PHONES_NUMBER_INDEX = 1;

	/** ͷ��ID **/
	private static final int PHONES_PHOTO_ID_INDEX = 2;

	/** ��ϵ�˵�ID **/
	private static final int PHONES_CONTACT_ID_INDEX = 3;

	private List<Contact> contacts = new ArrayList<Contact>();

	private SideBar mSideBar;
	private ListView mListView;
	private TextView tvLetter;
	private SortAdapter adapter;

	private ProgressDialog dialog;
	/**
	 * �����첽����
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
							"�����ڲ���" + contact.getName() + "�Ƿ�ǰ����",
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
		dialog = ProgressDialog.show(this, "���Ե�", "���ڶ�ȡ��...", false);
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
	 * ��SideBar������
	 */
	private void setListeners() {
		mSideBar.setOnTouchingLetterChangedListener(new LetterListViewListener());
	}

	private void getContacts() {
		ContentResolver resolver = getContentResolver();

		// ��ȡ�ֻ���ϵ��
		Cursor phoneCursor = resolver.query(Phone.CONTENT_URI,
				PHONES_PROJECTION, null, null, null);
		final CharacterParser characterParser = CharacterParser.getInstance();
		if (phoneCursor != null) {
			while (phoneCursor.moveToNext()) {

				// �õ��ֻ�����
				String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
				// ���ֻ�����Ϊ�յĻ���Ϊ���ֶ� ������ǰѭ��
				if (TextUtils.isEmpty(phoneNumber))
					continue;

				// �õ���ϵ������
				String contactName = phoneCursor
						.getString(PHONES_DISPLAY_NAME_INDEX);

				// �õ���ϵ��ID
				Long contactid = phoneCursor.getLong(PHONES_CONTACT_ID_INDEX);

				// �õ���ϵ��ͷ��ID
				Long photoid = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX);

				// �õ���ϵ��ͷ��Bitamp
				Bitmap contactPhoto = null;

				// photoid ����0 ��ʾ��ϵ����ͷ�� ���û�и���������ͷ�������һ��Ĭ�ϵ�
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
			// ����ĸ�״γ��ֵ�λ��
			int position = adapter.getPositionForSection(s.charAt(0));
			if (position != -1) {
				mListView.setSelection(position);
			}
		}
	}

}
