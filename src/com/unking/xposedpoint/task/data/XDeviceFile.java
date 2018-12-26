// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import android.util.Log;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.task.net.Utils;

public class XDeviceFile {

	public XDeviceFile() {
	}

	public static File file(Apk apk, String s) {
		try {

			Log.d("feitian", "创建文件");
			File file1 = new File((new StringBuilder("/sdcard-ext/point/"))
					.append(apk.getPackageName()).append("/").append(s)
					.append(".log").toString());
			if (!file1.getParentFile().exists())
				file1.getParentFile().mkdirs();

			if (!file1.exists())
				file1.createNewFile();
			return file1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static synchronized ArrayList<String> getLastRemainList(Apk apk) {
		BufferedReader reader = null;
		try {
			ArrayList<String> list = new ArrayList<String>();

			File r_file = new File((new StringBuilder("/sdcard-ext/point/"))
					.append(apk.getPackageName()).append("/")
					.append("point_remain_last").append(".log").toString());

			if (!r_file.exists())
				return null;

			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(r_file)));

			String r_str = "";
			while ((r_str = reader.readLine()) != null) {
				list.add(r_str);
			}

			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return null;
	}
	

	public static synchronized ArrayList<String> getRemainList(Apk apk, int day) {
		BufferedReader reader = null;
		try {
			ArrayList<String> list = new ArrayList<String>();

			File r_file = new File((new StringBuilder("/sdcard-ext/point/"))
					.append(apk.getPackageName()).append("/")
					.append("point_remain_" + day).append(".log").toString());

			if (!r_file.exists())
				return null;

			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(r_file)));

			String r_str = "";
			while ((r_str = reader.readLine()) != null) {
				list.add(r_str);
			}

			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return null;
	}

	public static synchronized void saveRemain(Apk apk, int day,
			String remainStr) {
		FileOutputStream fileoutputstream = null;
		try {
			File file1;
			file1 = file(apk, "point_remain_" + day);
			fileoutputstream = new FileOutputStream(file1, true);
			fileoutputstream.write(remainStr.getBytes());
			fileoutputstream.write("\n".getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (fileoutputstream != null)
				try {
					fileoutputstream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public static synchronized void saveRun(Apk apk, int day, String runStr) {
		FileOutputStream fileoutputstream = null;
		try {
			File file1;
			file1 = file(apk, "point_run_" + day);
			fileoutputstream = new FileOutputStream(file1, true);
			fileoutputstream.write(runStr.getBytes());
			fileoutputstream.write("\n".getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (fileoutputstream != null)
				try {
					fileoutputstream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}
	
	public static synchronized void saveLastRemainRun(Apk apk, String runStr) {
		FileOutputStream fileoutputstream = null;
		try {
			File file1;
			file1 = file(apk, "point_lastRemainRun_" + Utils.formatDate(new Date(), "yyyyMMdd"));
			fileoutputstream = new FileOutputStream(file1, true);
			fileoutputstream.write(runStr.getBytes());
			fileoutputstream.write("\n".getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (fileoutputstream != null)
				try {
					fileoutputstream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}
}
