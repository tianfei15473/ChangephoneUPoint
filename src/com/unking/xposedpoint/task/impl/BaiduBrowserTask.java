// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import java.util.Random;

import android.content.Context;
import android.util.MonthDisplayHelper;

import com.unking.xposedpoint.cmd.CommandExec;
import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.task.Motion;

public class BaiduBrowserTask extends DefaultTask {
	private Random ran;

	public BaiduBrowserTask() {
	}

	protected void clear(Context context) {
		try {
			android.provider.Settings.System.putString(
					context.getContentResolver(), "com.baidu.deviceid", "");
			return;
		} catch (Exception exception) {
			return;
		}
	}

	protected void finish(Apk apk) {
		try {
			CommandExec commandexec = new CommandExec();
			String as[] = new String[2];
			as[0] = (new StringBuilder("cd ")).append(
					"/data/data/com.baidu.browser.apps/shared_prefs")
					.toString();
			as[1] = "rm -r *";
			commandexec.execute(as);
			return;
		} catch (Exception exception) {
			LLogger.error("Baidu Browser delete", exception);
		}
	}

	private void more() {
		Motion.touch(791, 1776, 5000);

		Motion.touch(791, 1776, 5000);
	}

	protected void start(Apk apk) {
		ran = new Random();
		
		//点击图标
		//Motion.touch(967, 708, 1000);
		
		Motion.sleep(10000L);

		Motion.downRight2Left(8000);
		Motion.downRight2Left(8000);

		Motion.touch(558, 1353, 8000);

		if (ran.nextInt(10) < 4)
			taobao();

		if (ran.nextInt(10) > 8)
			rensheng();

		if (ran.nextInt(10) < 3)
			more();

		int leftorright = ran.nextInt(3);
		if (leftorright == 0) {
			Motion.touch(561, 1780, 5000);
			Motion.touch(561, 1780, 5000);
		} else if (leftorright == 2) {
			Motion.touch(561, 1780, 2000);
		}

		if (leftorright == 1)
			zhoujielun1();
		else if (leftorright == 2) {
			dingyue2();

			Motion.down2up(5000);

			Motion.touch(868, 922, 5000);

			Motion.touch(87, 1770, 5000);

		} else if (leftorright == 0) {
			Motion.down2up(5000);

			Motion.down2up(5000);
		}

		Motion.touch(561, 1780, 2000);
		
		Motion.touch(274, 1967, 2000);
		Motion.touch(334, 1180, 10000);

		Motion.randomSleep(15000);
		
		/*next();
		
		if(ran.nextInt(2) == 1) 
			next();*/
	}
	
	protected boolean next() {
		ran = new Random();
		
		//点击图标
		//Motion.touch(967, 708, 1000);
		
		Motion.sleep(10000L);

		//Motion.downRight2Left(8000);
		//Motion.downRight2Left(8000);

		//Motion.touch(558, 1353, 8000);

		if (ran.nextInt(10) < 4)
			taobao();

		if (ran.nextInt(10) > 8)
			rensheng();

		if (ran.nextInt(10) < 3)
			more();

		int leftorright = ran.nextInt(3);
		if (leftorright == 0) {
			Motion.touch(561, 1780, 5000);
			Motion.touch(561, 1780, 5000);
		} else if (leftorright == 2) {
			Motion.touch(561, 1780, 2000);
		}

		if (leftorright == 1)
			zhoujielun1();
		else if (leftorright == 2) {
			dingyue2();

			Motion.down2up(5000);

			Motion.touch(868, 922, 5000);

			Motion.touch(87, 1770, 5000);

		} else if (leftorright == 0) {
			Motion.down2up(5000);

			Motion.down2up(5000);
		}

		Motion.touch(561, 1780, 2000);
		
		Motion.touch(274, 1967, 2000);
		Motion.touch(334, 1180, 10000);

		Motion.randomSleep(15000);
		
		if(ran.nextInt(2) == 1) 
			return true;
		
		return false;
	}

	private void zhoujielun1() {
		Motion.touch(414, 391, 5000);

		Motion.touch(283, 763, 5000);
		Motion.touch(239, 1579, 5000);
		Motion.touch(792, 1367, 5000);
		Motion.touch(1008, 1388, 5000);
		Motion.touch(844, 1006, 5000);
		Motion.touch(955, 168, 5000);
		Motion.touch(128, 1792, 8000);
		Motion.touch(128, 1792, 8000);

	}

	private void dingyue2() {
		Motion.touch(951, 305, 5000);
		Motion.touch(91, 1771, 5000);
	}

	protected void stop(Apk apk) {
		Motion.touch(274, 1967, 2000);
		Motion.touch(334, 1180, 1000);
	}

	private void taobao() {
		Motion.touch(305, 185, 5000);
		Motion.touch(490, 1214, 3000);
		Motion.touch(680, 1595, 3000);
		Motion.touch(569, 1014, 5000);
		Motion.touch(292, 511, 5000);

		Motion.down2up(5000);

		Motion.touch(107, 1785, 5000);

	}

	private void rensheng() {
		Motion.touch(305, 185, 5000);
		Motion.touch(403, 1207, 3000);
		Motion.touch(220, 1378, 3000);
		Motion.touch(554, 1042, 5000);
		Motion.touch(322, 502, 5000);

		Motion.down2up(5000);

		Motion.touch(107, 1785, 5000);
	}
}
