package com.unking.xposedpoint.utils;

import java.io.File;

import android.os.Environment;

public class SdCardUtil {

	/**
	 * 获取SD卡目录
	 * @return
	 */
	public static String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);// 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
		}
		System.out.println("sd----->" + sdDir.toString());
		
		return sdDir.toString();
	}

}
