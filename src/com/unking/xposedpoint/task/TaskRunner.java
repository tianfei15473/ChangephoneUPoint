// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task;

import android.content.Context;
import android.util.Log;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.data.ApkQueue;
import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.task.data.DeviceManager;
import com.unking.xposedpoint.task.rule.Builder;

import java.util.Iterator;
import java.util.List;

public class TaskRunner extends Thread {

	public TaskRunner(Context context1) {
		running = false;
		context = context1;
	}

	private void runAll() {
		try {
			ApkQueue.getInstance().load();
			List localList = ApkQueue.getInstance().runall();
			if (localList.isEmpty())
				Motion.sleep(60000L);
			Apk localApk = null;
			Task task = null;
			Iterator localIterator = localList.iterator();
			while (localIterator.hasNext()) {
				localApk = (Apk) localIterator.next();
				DeviceManager.getInstance().loadRemain(context, localApk);
				long l1;
				long l2;
				long l3;
				if (this.running) {
					/*if (DeviceManager.getInstance().getTotalRunCount(
							localApk.getPackageName()) < Integer
							.parseInt(localApk.getMaxDownload())) {*/
						while (true) {
							try {
								task = (Task) Class.forName(
										localApk.taskClass()).newInstance();
								
								l1 = Builder.getInstance()
										.hour(localApk.maxDownload())
										.getInterval();
								if (localApk.getStopTime() <= 0L) {
									l2 = l1;
								} else {
									l2 = System.currentTimeMillis()
											- localApk.getStopTime();
								}
								l3 = l1 - l2;

								if (l3 <= 0L)
									break;

								long l4 = Math.min(l3, 5000l);
								l4 += DeviceManager.getInstance().getRandomSleep();
								Log.d("feitian", "应该休眠" + l3 + "毫秒，实际休眠" + l4
										+ "毫秒，" + localApk.getActivity());
								Motion.sleep(l4);
								
								break;
							} catch (Exception e) {
								e.printStackTrace();
								Motion.sleep(5000L);
							}
						}
						DeviceManager.getInstance().random(localApk);
						task.setDevice(DeviceManager.getInstance().getCurrent());
						task.start(this.context, localApk);
						/*DeviceManager.getInstance().uninstallRandom(localApk);*/
						Motion.sleep(5000L);
						localApk.setStopTime(System.currentTimeMillis());
						task.stop(this.context, localApk);
						DeviceManager.getInstance().updateTotalRunCount(
								localApk.getPackageName());
						Motion.sleep(3000L);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void interrupt() {
		running = false;
		Motion.stop = true;
		super.interrupt();
		LLogger.error("SyncRunner", "interrupt");
	}

	public void run() {
		running = true;
		Motion.stop = false;
		while (true) {
			if (!running) {
				LLogger.error("BootServices", "finish task");
				return;
			}
			Log.d("fei_example", "startService");
			runAll();
		}
	}

	private Context context;
	private boolean running;
}
