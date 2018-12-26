// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.set;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.unking.xposedpoint.R;
import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.data.ApkQueue;
import com.unking.xposedpoint.task.data.DeviceManager;
import com.unking.xposedpoint.task.net.Utils;
import com.unking.xposedpoint.utils.ObjectPropertyUtils;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 配置 - APK 详细页面
 */
public class ApkDetailActivity extends Activity {

	public ApkDetailActivity() {
		super();
		map = new HashMap();
		map.put(R.id.apk_name, "name");
		map.put(R.id.apk_package_name, "packageName");
		map.put(R.id.apk_max_total, "maxDownload");
		map.put(R.id.apk_hold_time, "residenceTime");
		map.put(R.id.apk_1day_remain, "day1Remain");
		map.put(R.id.apk_3day_remain, "day3Remain");
		map.put(R.id.apk_7day_remain, "day7Remain");
		map.put(R.id.apk_14day_remain, "day14Remain");
		map.put(R.id.apk_day_remain_ratio, "dayRemainRatio");
		map.put(R.id.apk_uninstall_total, "uninstallTotal");
	}

	private void hide(String s) {
		HashMap hashmap;
		hashmap = new HashMap();
		hashmap.put("1", R.id.apk_day_layout1);
		hashmap.put("3", R.id.apk_day_layout3);
		hashmap.put("7", R.id.apk_day_layout7);
		hashmap.put("14", R.id.apk_day_layout14);
		if (Utils.isEmpty(s)) {
			findViewById(R.id.apk_day_remain_ratio_layout).setVisibility(
					View.GONE);
			Iterator iterator = hashmap.keySet().iterator();
			findViewById(
					((Integer) hashmap.get((String) iterator.next()))
							.intValue()).setVisibility(View.GONE);
		} else {
			String[] as = s.split("[,]");
			int i = as.length;
			int j = 0;

			Iterator iterator = hashmap.keySet().iterator();
			while (j < i) {
				hashmap.remove(as[j]);
				j++;
				if (!iterator.hasNext())
					return;
				break;
			}
			;
		}

	}

	private void put(Apk apk1) {
		Iterator iterator = map.keySet().iterator();
		do {
			String s;
			View view;
			do {
				if (!iterator.hasNext())
					return;
				Integer integer = (Integer) iterator.next();
				s = (String) map.get(integer);
				view = findViewById(integer.intValue());
			} while (!(view instanceof TextView));
			String s1 = ObjectPropertyUtils.getString(apk1, s);
			if ("null".equalsIgnoreCase(s1) || "0".equalsIgnoreCase(s1))
				s1 = "";
			((TextView) view).setText(s1);
		} while (true);
	}

	private void update(Apk apk1) {
		Iterator iterator = map.keySet().iterator();
		String s;
		while (iterator.hasNext()) {
			Integer integer = (Integer) iterator.next();
			s = (String) map.get(integer);
			View view = findViewById(integer.intValue());

			if (view instanceof EditText) {
				ObjectPropertyUtils.setProperty(apk1, s, ((EditText) view)
						.getText().toString());
			}
		}
	}

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.set_apk);
		setTitle((new StringBuilder(String.valueOf(getResources().getString(
				R.string.app_name)))).append("(手机:").append(Build.MODEL)
				.append(")").toString());// Global.PHONE
		apk = (Apk) getIntent().getExtras().getSerializable("apk");
		put(apk);
		hide(apk.getRemainRule());
		Button button = (Button) findViewById(R.id.apk_control_button1);
		Resources resources = getResources();
		int i;
		if (apk.isEnable())
			i = R.string.apk_stop_app;
		else
			i = R.string.apk_start_app;

		final EditText start_remain_et = (EditText) findViewById(R.id.start_remain);
		start_remain_et.setText(DeviceManager.getInstance().getNowXDay(apk)
				+ "");

		final EditText isremain_et = (EditText) findViewById(R.id.isremain_et);

		button.setText(resources.getText(i));
		button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Apk apk1 = apk;
				boolean flag;
				if (apk.isEnable())
					flag = false;
				else
					flag = true;
				apk1.setEnable(flag);
				update(apk);
				ApkQueue.getInstance().update(apk);
				ApkQueue.getInstance().save();
				DeviceManager.getInstance().setNewXDay(apk,
						Integer.parseInt(start_remain_et.getText().toString()));
				if (isremain_et.getText().toString() != null
						&& "1".equals(isremain_et.getText().toString())) 
					DeviceManager.getInstance().updateNowDay();
				

				finish();
			}

		});
		((Button) findViewById(R.id.apk_control_button2))
				.setOnClickListener(new View.OnClickListener() {

					public void onClick(View view) {
						update(apk);
						ApkQueue.getInstance().update(apk);
						ApkQueue.getInstance().save();
						DeviceManager.getInstance().setNewXDay(
								apk,
								Integer.parseInt(start_remain_et.getText()
										.toString()));
						if (isremain_et.getText().toString() != null
								&& "1".equals(isremain_et.getText().toString())) 
							DeviceManager.getInstance().updateNowDay();
						finish();
					}

				}

				);
		((Button) findViewById(R.id.apk_control_button3))
				.setOnClickListener(new View.OnClickListener() {

					public void onClick(View view) {
						try {
							DeviceManager.getInstance().loadRemain(
									getApplicationContext(), apk);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				});
	}

	private Apk apk;
	private HashMap map;

}
