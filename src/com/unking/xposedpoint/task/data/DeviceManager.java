// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.json.JSONObject;

import android.content.Context;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.task.net.Utils;

public class DeviceManager {

	private ArrayList<String> remain_device_list;

	private DeviceManager() {
		super();
		nowDay = new Date().getDay();
		infos = new ArrayList<DeviceInfo>();
		m = new Device();
		ran = new Random();
		longitudeAndLatitudes = null;
		wifiSsid = null;
		BufferedReader bufferedreader = null;

		String s = null;
		try {
			File file_prop = new File((new StringBuilder("/sdcard-ext/point/"))
					.append("prop/").append("devicesinfo.properties")
					.toString());
			bufferedreader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file_prop)));
			while ((s = bufferedreader.readLine()) != null) {
				if (s.trim().length() == 0) {
					continue;
				}
				DeviceInfo deviceinfo = new DeviceInfo(s);// 读取硬件信息
				if (!infos.contains(deviceinfo)) {
					infos.add(deviceinfo);
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (bufferedreader != null)
				try {
					bufferedreader.close();
				} catch (Exception exception7) {
					exception7.printStackTrace();
				}
		}
		try {
			File ll_prop = new File((new StringBuilder("/sdcard-ext/point/"))
					.append("prop/").append("ll.properties").toString());
			longitudeAndLatitudes = new RandomStringList(new FileInputStream(
					ll_prop));
		} catch (Exception exception) {
			exception.printStackTrace();
			LLogger.error("Devicesinfo", exception);
		}

		try {
			File wifi_prop = new File((new StringBuilder("/sdcard-ext/point/"))
					.append("prop/").append("wifi.properties").toString());
			wifiSsid = new RandomStringList(new FileInputStream(wifi_prop),
					new RandomStringList.RandomStringListReader() {

						public String read(String s1) {
							String as[] = s1.split("[ ]");
							if (as.length > 0)
								return as[0];
							else
								return null;
						}
					});
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			File ip_prop = new File((new StringBuilder("/sdcard-ext/point/"))
					.append("prop/").append("point_ip.properties").toString());
			ip_list = new RandomStringList(new FileInputStream(ip_prop));
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	public String read(String s1) {
		String as[] = s1.split("[ ]");
		if (as.length > 0)
			return as[0];
		else
			return null;
	}

	public static DeviceManager getInstance() {
		return instance;
	}

	public Device getCurrent() {
		return m;
	}

	public void randomLastRemain(Apk localApk) {
		if (remain_device_list != null && remain_device_list.size() > 0) {
			String remain_str = remain_device_list.remove(this.ran
					.nextInt(remain_device_list.size()));
			this.m.change(remain_str);
			saveLastRemianRunDevice(localApk, this.m.toString());
		}
	}

	public void uninstallRandom(Apk localApk) {
		DeviceInfo d_info = this.infos.get(ran.nextInt(infos.size()));
		m = d_info.random();

		if (Utils.isEmpty(this.m.getLongitude()))
			this.m.setLocation(this.longitudeAndLatitudes.random(),
					this.ran.nextInt(20), this.ran.nextInt(20));
		if (Utils.isEmpty(this.m.getSsid())) {
			String str = wifiSsid.random();
			int i = this.ran.nextInt();
			if (i < 43690)
				i -= 21846;
			this.m.setSsid(str + "_"
					+ Integer.toHexString(i).toUpperCase(Locale.CHINA));
		}

		AndroidSdkRandom.getInstance().random();
		this.m.setId(AndroidSdkRandom.getInstance().sdk.displayId);
		this.m.setSdk(String.valueOf(AndroidSdkRandom.getInstance().sdk.api));
		this.m.setRelease(AndroidSdkRandom.getInstance().sdk.release);
		this.m.setIp(String.valueOf(ip_list.get(ran.nextInt(ip_list.size()))));
		this.m.setStarttime(new Date().getTime() + "");
		this.m.setStarttime_str(Utils.formatDate(new Date(),
				"yyyy-MM-dd HH:mm:ss"));

		this.m.change(this.m.toString());
	}

	public void random(Apk localApk) {
		DeviceInfo d_info = this.infos.get(ran.nextInt(infos.size()));
		m = d_info.random();

		if (Utils.isEmpty(this.m.getLongitude()))
			this.m.setLocation(this.longitudeAndLatitudes.random(),
					this.ran.nextInt(20), this.ran.nextInt(20));
		if (Utils.isEmpty(this.m.getSsid())) {
			String str = wifiSsid.random();
			int i = this.ran.nextInt();
			if (i < 43690)
				i -= 21846;
			this.m.setSsid(str + "_"
					+ Integer.toHexString(i).toUpperCase(Locale.CHINA));
		}

		AndroidSdkRandom.getInstance().random();
		this.m.setId(AndroidSdkRandom.getInstance().sdk.displayId);
		this.m.setSdk(String.valueOf(AndroidSdkRandom.getInstance().sdk.api));
		this.m.setRelease(AndroidSdkRandom.getInstance().sdk.release);
		this.m.setIp(String.valueOf(ip_list.get(ran.nextInt(ip_list.size()))));
		this.m.setStarttime(new Date().getTime() + "");
		this.m.setStarttime_str(Utils.formatDate(new Date(),
				"yyyy-MM-dd HH:mm:ss"));

		this.m.change(this.m.toString());

		saveAllDevice(localApk, this.m.toString());
		updateXDay(localApk.getPackageName());
	}

	// 加载上次运行的设备信息
	public void loadRemain(Context context, Apk localApk) {
		
	}

	public void setNewXDay(Apk localApk, int value) {
		xDay.put(localApk.getPackageName(), value);
	}

	public int getNowXDay(Apk localApk) {
		Integer value = xDay.get(localApk.getPackageName());
		if (value == null)
			return 0;
		else
			return value;
	}

	public void loadLastRemain(Context context, Apk localApk) {
		remain_device_list = XDeviceFile.getLastRemainList(localApk);
	}

	// 保存当天刷入的所有设备信息
	public void saveAllDevice(Apk localApk, String runStr) {
		Integer day = xDay.get(localApk.getPackageName());
		if (day != null && day > 0)
			XDeviceFile.saveRun(localApk, day, runStr);
		else
			XDeviceFile.saveRun(localApk, 0, runStr);
	}

	public void saveLastRemianRunDevice(Apk localApk, String runStr) {
		XDeviceFile.saveLastRemainRun(localApk, runStr);
	}

	// 保存需要留存的设备信息
	public void saveRemainDevice(Apk localApk, String remainStr) {
		Integer day = xDay.get(localApk.getPackageName());
		if (day != null && day > 0)
			XDeviceFile.saveRemain(localApk, day, remainStr);
		else
			XDeviceFile.saveRemain(localApk, 0, remainStr);
	}

	private static final DeviceManager instance = new DeviceManager();
	private ArrayList<DeviceInfo> infos;
	private RandomStringList longitudeAndLatitudes;
	private Device m;
	private Random ran;
	private RandomStringList wifiSsid;
	private RandomStringList ip_list;
	private HashMap<String, Integer> xDeviceTotal = new HashMap<String, Integer>();
	private HashMap<String, Integer> xDeviceRemainTotal = new HashMap<String, Integer>();
	private HashMap<String, Integer> xDay = new HashMap<String, Integer>();
	private HashMap<String, Integer> totalRunCount = new HashMap<String, Integer>();
	private Integer total;
	private Integer remainTotal;
	private int nowDay = 0;

	public void updateTotalRunCount(String packagename) {
		Integer t = totalRunCount.get(packagename);
		if (t == null)
			totalRunCount.put(packagename, 1);
		else
			totalRunCount.put(packagename, t + 1);
	}

	public int getTotalRunCount(String packagename) {
		Integer t = totalRunCount.get(packagename);
		if (t == null)
			return 0;
		else
			return t;
	}

	public void updateNowDay() {
		nowDay = new Date().getDay() + 1;
	}

	private void updateXDeviceTotal(String packagename) {
		Integer u_total = xDeviceTotal.get(packagename);
		if (u_total == null)
			u_total = 1;
		else
			u_total += 1;
		xDeviceTotal.put(packagename, u_total);
	}

	private void updateXDeviceRemainTotal(String packagename) {
		Integer u_remaintotal = xDeviceRemainTotal.get(packagename);
		if (u_remaintotal == null)
			u_remaintotal = 1;
		else
			u_remaintotal += 1;
		xDeviceRemainTotal.put(packagename, u_remaintotal);
	}

	private void updateXDay(String packagename) {

		Integer remainDay = xDay.get(packagename);
		if (remainDay != null) {
			int t_nowDay = new Date().getDay();
			if (t_nowDay != nowDay) {
				xDay.put(packagename, remainDay + 1);
				nowDay = t_nowDay;

				xDeviceRemainTotal.put(packagename, 0);
				xDeviceTotal.put(packagename, 0);
			}

		} else {
			xDay.put(packagename, 0);
		}
	}

	public long getRandomSleep() {
		Date nowtime = new Date();
		if (nowtime.getHours() > 1 && nowtime.getHours() < 6)
			return Long.parseLong(ran.nextInt(150000) + "");
		else
			return 0;
	}

}
