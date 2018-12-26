// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.util.Log;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.task.Application;
import com.unking.xposedpoint.task.Motion;
import com.unking.xposedpoint.task.PidManager;
import com.unking.xposedpoint.task.SuUtils;
import com.unking.xposedpoint.task.Task;
import com.unking.xposedpoint.task.data.Device;
import com.unking.xposedpoint.task.io.RFile;
import com.unking.xposedpoint.task.net.Utils;

import java.util.Random;

public class DefaultTask implements Task {

	public DefaultTask() {
		running = false;
	}

	private boolean isInstalled(Context context1, Apk apk) {
		android.content.pm.ApplicationInfo applicationinfo;
		try {
			applicationinfo = context1.getPackageManager().getApplicationInfo(
					apk.getPackageName(), 1);
		} catch (Exception exception) {
			return false;
		}
		return applicationinfo != null;
	}

	protected void clear(Context context1) {
	}

	protected void finish(Apk apk) {
	}

	public Device getDevice() {
		return device;
	}

	public final boolean isRunning() {
		return running;
	}

	protected void run(Apk apk) {
		Application.random(apk.residenceTime() / 1000);
	}

	public void setDevice(Device device1) {
		device = device1;
	}

	public final void start(Context context1, Apk apk) {
		context = context1;
		running = true;
		
		start(apk);
		
	}

	protected void start(Apk apk) {
	}
	
	protected boolean next() {
		return false;
	}

	protected void startActivity(Apk apk) {
		if (Utils.isEmpty(apk.getFile()))
			return;
		try {
			Log.d("feitian-startpackage", apk.getPackageName());

			SuUtils.exe((new StringBuilder("am start -n "))
					.append(apk.getPackageName()).append("/")
					.append(apk.getActivity()).toString());
			return;
		} catch (ActivityNotFoundException activitynotfoundexception) {
			Motion.install(apk, 1000L);
		}
		startActivity(apk);
	}

	// tf
	public final void uninstall(Apk apk) {
		Motion.uninstall(apk, 10000L);
	}

	public final void stopNotClear(Apk apk) {
		SuUtils.exit(apk.getPackageName());
	}
	
	public final void stop(Context context1, Apk apk) {
		finish(apk);
		
		Motion.uninstall("com.jlzb.android", 2000);
		
		running = false;
	}

	protected void stop(Apk apk) {
	}

	protected Context context;
	private Device device;
	private boolean running;
}
