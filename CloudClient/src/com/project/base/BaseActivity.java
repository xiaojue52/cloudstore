package com.project.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
/**
 * ����Ŀ��Ϊ����������̣�֮���Ե�����һ������Ϊ�˼򻯴��룬������ֲ
 * @author Administrator
 *
 */
public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
	}
	/** * ����touch�¼����ر������ */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) { // ��õ�ǰ�õ������View��һ������¾���EditText������������ǹ켣�����ʵ�尸�����ƶ����㣩
			View v = getCurrentFocus();
			if (isShouldHideInput(v, ev)) {
				hideSoftInput(v.getWindowToken());
			}
		}
		return super.dispatchTouchEvent(ev);
	}

	/**
	 * * ����EditText����������û������������Աȣ����ж��Ƿ����ؼ��̣���Ϊ���û����EditTextʱû��Ҫ���� * * @param v
	 * * @param event * @return
	 */
	private boolean isShouldHideInput(View v, MotionEvent event) {
		if (v != null && (v instanceof EditText)) {
			int[] l = { 0, 0 };
			v.getLocationInWindow(l);
			int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
					+ v.getWidth();
			if (event.getX() > left && event.getX() < right
					&& event.getY() > top && event.getY() < bottom) { // ���EditText���¼�����������
				return false;
			} else {
				return true;
			}
		} // ������㲻��EditText����ԣ������������ͼ�ջ����꣬��һ�����㲻��EditView�ϣ����û��ù켣��ѡ�������Ľ���
		return false;
	}

	/** * ������������̷���������һ�� * * @param token */
	private void hideSoftInput(IBinder token) {
		if (token != null) {
			InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			im.hideSoftInputFromWindow(token,
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
}
