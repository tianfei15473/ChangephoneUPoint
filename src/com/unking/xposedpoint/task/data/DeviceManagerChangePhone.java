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
import java.util.Locale;
import java.util.Random;

import android.os.Environment;

import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.task.net.Utils;
import com.unking.xposedpoint.utils.SdCardUtil;

/**
 * 切换手机
 * @author Administrator
 *
 */
public class DeviceManagerChangePhone {

	private DeviceManagerChangePhone() {
		super();
		init();
	}
	
	public void init() {
		infos_baidu = new ArrayList<DeviceInfo>();
		infos_huawei = new ArrayList<DeviceInfo>();
		infos_oppo = new ArrayList<DeviceInfo>();
		infos_vivo = new ArrayList<DeviceInfo>();
		m = new Device();
		ran = new Random();
		longitudeAndLatitudes = null;
		wifiSsid = null;
		
		readPhoneinfo(infos_baidu, "devicesinfo_baidu.properties");
		readPhoneinfo(infos_huawei, "devicesinfo_huawei.properties");
		readPhoneinfo(infos_oppo, "devicesinfo_oppo.properties");
		readPhoneinfo(infos_vivo, "devicesinfo_vivo.properties");
		
		try {
			File ll_prop = new File((new StringBuilder()).append(SdCardUtil.getSDPath())
					.append("/point/prop/").append("ll.properties").toString());
			longitudeAndLatitudes = new RandomStringList(new FileInputStream(
					ll_prop));
		} catch (Exception exception) {
			exception.printStackTrace();
			LLogger.error("Devicesinfo", exception);
		}

		try {
			File wifi_prop = new File((new StringBuilder()).append(SdCardUtil.getSDPath())
					.append("/point/prop/").append("wifi.properties").toString());
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
			File ip_prop = new File((new StringBuilder()).append(SdCardUtil.getSDPath())
					.append("/point/prop/").append("point_ip.properties").toString());
			ip_list = new RandomStringList(new FileInputStream(ip_prop));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	private void readPhoneinfo(ArrayList list, String filename) {
		BufferedReader bufferedreader = null;

		String s = null;
		try {
			File file_prop = new File((new StringBuilder()).append(SdCardUtil.getSDPath())
					.append("/point/prop/").append(filename)
					.toString());
			bufferedreader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file_prop)));
			while ((s = bufferedreader.readLine()) != null) {
				if (s.trim().length() == 0) {
					continue;
				}
				DeviceInfo deviceinfo = new DeviceInfo(s);// 读取硬件信息
				if (!list.contains(deviceinfo)) {
					list.add(deviceinfo);
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
	}



	public static DeviceManagerChangePhone getInstance() {
		if(instance == null)
			instance = new DeviceManagerChangePhone();
		return instance;
	}


	public String random(String type) {
		DeviceInfo d_info = null;
		if(type.equals("baidu"))
			d_info = infos_baidu.get(ran.nextInt(infos_baidu.size()));
		else if(type.equals("huawei"))
			d_info = infos_huawei.get(ran.nextInt(infos_huawei.size()));
		else if(type.equals("oppo"))
			d_info = infos_oppo.get(ran.nextInt(infos_oppo.size()));
		else if(type.equals("vivo"))
			d_info = infos_vivo.get(ran.nextInt(infos_vivo.size()));
		
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
		
		return this.m.getProduct();
	}

	private static DeviceManagerChangePhone instance = null;
	private ArrayList<DeviceInfo> infos_baidu;
	private ArrayList<DeviceInfo> infos_huawei;
	private ArrayList<DeviceInfo> infos_oppo;
	private ArrayList<DeviceInfo> infos_vivo;
	private RandomStringList longitudeAndLatitudes;
	private Device m;
	private Random ran;
	private RandomStringList wifiSsid;
	private RandomStringList ip_list;

}
