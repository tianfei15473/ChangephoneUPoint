// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.data;

import android.util.Log;

import com.unking.xposedpoint.Global;
import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.task.net.Utils;
import com.unking.xposedpoint.utils.StreamUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DeviceFile {

    public DeviceFile() {
    }

    public static File file(Apk apk, String s) {
        try {

            Log.d("feitian", "创建文件");
            File file1 = new File((new StringBuilder("/sdcard-ext/point/")).append(Global.PHONE).append(apk.getPackageName()).append("/").append(s).append(".log").toString());
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

    public static synchronized void saveRemain(Apk apk, ArrayList arraylist, String s) {
        if (arraylist != null) {
            Log.d("feitian-arraylist", arraylist.size() + "");
            HashMap hashmap;
            Iterator iterator;
            hashmap = new HashMap();
            iterator = arraylist.iterator();
            hashmap.clear();
            while (iterator.hasNext()) {
                try {
                    Device device;
                    File file1;
                    device = (Device) iterator.next();
                    file1 = file(apk, (new StringBuilder("point_remain.")).append(s).append(".").append(device.getRemainDate()).toString());
                    FileOutputStream fileoutputstream = (FileOutputStream) hashmap.get(file1.getAbsolutePath());
                    if (fileoutputstream != null)
                        break;
                    FileOutputStream fileoutputstream1 = new FileOutputStream(file1, false);
                    hashmap.put(file1.getAbsolutePath(), fileoutputstream1);
                    fileoutputstream = fileoutputstream1;
                    fileoutputstream.write(device.toString().getBytes());
                    fileoutputstream.write("\n".getBytes());
                } catch (Exception exception) {
                    LLogger.error("DeviceRemain.init", exception);
                    StreamUtils.close((FileOutputStream) iterator.next());
                }
            }

            Iterator iterator1 = hashmap.values().iterator();
            while(iterator1.hasNext()) {
                StreamUtils.close((FileOutputStream)iterator1.next());
            }

            hashmap.clear();
        }
    }

    public static synchronized void saveRun(Apk apk, ArrayList arraylist, String s) {

        if (arraylist != null) {
            HashMap hashmap;
            Iterator iterator;
            hashmap = new HashMap();
            iterator = arraylist.iterator();
            File file;
            while (iterator.hasNext()) {
                try {
                    Device device = (Device) iterator.next();
                    if (Utils.isEmpty(device.getRemainDate())) {
                        file = file(apk, (new StringBuilder("point_run.")).append(s).toString());
                    } else {
                        File file2 = file(apk, (new StringBuilder("point_remain_run.")).append(s).append(".").append(device.getRemainDate()).toString());
                        file = file2;
                    }

                    FileOutputStream fileoutputstream = (FileOutputStream) hashmap.get(file.getAbsolutePath());
                    if (fileoutputstream != null) {
                        break;
                    }
                    FileOutputStream fileoutputstream1 = new FileOutputStream(file, file.exists());
                    hashmap.put(file.getAbsolutePath(), fileoutputstream1);
                    fileoutputstream = fileoutputstream1;
                    fileoutputstream.write(device.toString().getBytes());
                    fileoutputstream.write("\n".getBytes());
                } catch (Exception exception) {
                    LLogger.error("DeviceRemain.save", exception);
                    StreamUtils.close((FileOutputStream) iterator.next());
                }
            }

            Iterator iterator1 = hashmap.values().iterator();
            while(iterator1.hasNext()) {
                StreamUtils.close((FileOutputStream) iterator1.next());
            }

            hashmap.clear();
        }

    }
}
