package com.lxx.getcontactsdemo;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;

/**
 * 拨打电话功能
 * 
 * @author Administrator
 * 
 */
public class CallTelephone {
	/**
	 * 显示自定义的Dialog
	 */
	public static void showCustomDialog(final Context context,
			final String contant, final String phoneNumber) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle("提示");
		builder.setMessage(contant);
		builder.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				callTelphone(context, phoneNumber);
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {

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
	 * 拨打电话
	 */
	private static void callTelphone(Context context, String phoneNumber) {
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
				+ phoneNumber));
		context.startActivity(intent);
	}

}
