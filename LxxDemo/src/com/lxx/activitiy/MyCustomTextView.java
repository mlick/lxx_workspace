package com.lxx.activitiy;

import java.util.Arrays;

import com.lxx.demo.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyCustomTextView extends TextView {

	public MyCustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyCustomTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	private Paint mPaint;

	protected void onDraw(Canvas canvas) {
		FontMetrics fm = mPaint.getFontMetrics();

		float baseline = fm.descent - fm.ascent;
		float x = 0;
		float y = baseline; // ����ϵͳ��������ĵײ��������ı���������Ҫ��������ĸ߶ȡ�

		String txt = getResources().getString(R.string.hello_world);

		// �ı��Զ�����
		String[] texts = autoSplit(txt, mPaint, getWidth() - 5);

		System.out.printf("line indexs: %s\n", Arrays.toString(texts));

		for (String text : texts) {
			canvas.drawText(text, x, y, mPaint); // �����Կؼ����Ͻ�Ϊԭ��
			y += baseline + fm.leading; // ��������м��
		}
	}

	/**
	 * �Զ��ָ��ı�
	 * 
	 * @param content
	 *            ��Ҫ�ָ���ı�
	 * @param p
	 *            ���ʣ�����������������ı��Ŀ��
	 * @param width
	 *            ���Ŀ���ʾ���أ�һ��Ϊ�ؼ��Ŀ�ȣ�
	 * @return һ���ַ������飬����ÿ�е��ı�
	 */
	private String[] autoSplit(String content, Paint p, float width) {
		int length = content.length();
		float textWidth = p.measureText(content);
		if (textWidth <= width) {
			return new String[] { content };
		}

		int start = 0, end = 1, i = 0;
		int lines = (int) Math.ceil(textWidth / width); // ��������
		String[] lineTexts = new String[lines];
		while (start < length) {
			if (p.measureText(content, start, end) > width) { // �ı���ȳ����ؼ����ʱ
				lineTexts[i++] = (String) content.subSequence(start, end);
				start = end;
			}
			if (end == length) { // ����һ�е��ı�
				lineTexts[i] = (String) content.subSequence(start, end);
				break;
			}
			end += 1;
		}
		return lineTexts;
	}
}
