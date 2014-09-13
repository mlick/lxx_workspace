package com.lxx.getcontactsdemo;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;

/**
 * ����绰����
 * 
 * @author Administrator
 * 
 */
public class CallTelephone {
	/**
	 * ��ʾ�Զ����Dialog
	 */
	public static void showCustomDialog(final Context context,
			final String contant, final String phoneNumber) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle("��ʾ");
		builder.setMessage(contant);
		builder.setPositiveButton("ȷ��", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				callTelphone(context, phoneNumber);
			}
		});
		builder.setNegativeButton("ȡ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}

		});
		AlertDialog alertDialog = builder.create();
		alertDialog.setCanceledOnTouchOutside(false);
		alertDialog.show();
	}

	/**
	 * ����绰
	 */
	private static void callTelphone(Context context, String phoneNumber) {
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
				+ phoneNumber));
		context.startActivity(intent);
	}

}
