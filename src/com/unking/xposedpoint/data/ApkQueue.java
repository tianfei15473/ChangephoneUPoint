// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.data;


import android.util.Log;

import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.utils.StreamUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class ApkQueue
{

    private static final ApkQueue instance = new ApkQueue();
    private LinkedHashMap apkQueen;
    private String encoding;
    private int id;
    private LinkedHashMap runQueen;

    private ApkQueue()
    {
        encoding = "GB2312";
        id = 0;
        apkQueen = new LinkedHashMap();
        runQueen = new LinkedHashMap();
    }

    public static ApkQueue getInstance()
    {
        return instance;
    }

    public Apk getApk(String s) {
        Apk apk = (Apk) apkQueen.get(s);
        if (apk != null)
            return apk;
        else
            return (Apk) runQueen.get(s);
    }

    public synchronized boolean isEmpty() {
        try {
            boolean flag = apkQueen.isEmpty();
            return flag;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public synchronized void load() {
        BufferedReader bufferedreader = null;
        File file = new File((new StringBuilder("/sdcard-ext/point/")).append("point_apk.prop").toString());
        boolean flag = file.exists();
        bufferedreader = null;

        if (!flag) {
            StreamUtils.close(bufferedreader);
        } else {
            try {
                bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
                while (true) {
                    String s1 = bufferedreader.readLine();
                    if (s1 != null) {
                        if (s1.trim().length() == 0) {
                            break;
                        }
                        push(new Apk(s1));
                    } else
                        break;
                }

                StreamUtils.close(bufferedreader);
            } catch (Exception exception) {
                LLogger.error("error", exception);
                StreamUtils.close(bufferedreader);
            }
        }

        BufferedReader bufferedreader2=null;
        try {
           // Log.d("file_fei_example", instance.getClass().getResource("")+ "");
            File file_prop = new File((new StringBuilder("/sdcard-ext/point/")).append("prop/point_apk.prop").toString());
            bufferedreader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file_prop), encoding));

            String s;
            while ((s = bufferedreader2.readLine()) != null) {
                if (s.trim().length() == 0) {
                    s = bufferedreader2.readLine();
                } else {
                    Apk apk;
                    Apk apk1;
                    apk = new Apk(s);
                    apk1 = getApk(apk.getPackageName());
                    if (apk1 == null) {
                        push(apk);
                    } else {
                        apk1.setRemainRule(apk.getRemainRule());
                    }
                }
            }

            StreamUtils.close(bufferedreader2);
        } catch (Exception exception) {
            exception.printStackTrace();
            StreamUtils.close(bufferedreader2);
        }

    }


    public synchronized void push(Apk apk) {
        try {
            update(apk);
        } catch (Exception exception) {
            
        }
    }

    public synchronized List runall()
    {
        try {
            ArrayList arraylist = new ArrayList(runQueen.values());

            return arraylist;
        }catch (Exception exception) {
            return null;
        }
    }

    public synchronized void save() {
        try {
            FileOutputStream fileoutputstream = new FileOutputStream((new StringBuilder("/sdcard-ext/point")).append("/point_apk.prop").toString(), false);
            Iterator iterator = runQueen.values().iterator();
            try {
                while (iterator.hasNext()) {
                    fileoutputstream.write(((Apk) iterator.next()).toString().getBytes(encoding));
                    fileoutputstream.write("\n".getBytes(encoding));
                }

            } catch (Exception exception1) {
                fileoutputstream = null;
                StreamUtils.close(fileoutputstream);
                LLogger.error("Apk Save", exception1);
            }

        } catch (Exception exception) {

        }
    }

    public synchronized List stopall() {
        try {
            ArrayList arraylist = new ArrayList(apkQueen.values());
            return arraylist;
        } catch (Exception exception) {
            return null;
        }
    }

    public synchronized void update(Apk apk)
    {
            Apk  localApk = getApk(apk.getPackageName());
            try
            {

                if (localApk != null)
                {
                    localApk.update(apk);
                    Log.d("feitian-localapk", apk.getPackageName());
                    if (localApk.isEnable())
                    {
                        this.apkQueen.remove(localApk.getPackageName());
                        this.runQueen.put(localApk.getPackageName(), localApk);
                        ApkManager.getInstance().readPackageInfo(localApk);
                    } else {
                        runQueen.remove(localApk.getPackageName());
                        apkQueen.put(localApk.getPackageName(), localApk);
                        ApkManager.getInstance().readPackageInfo(localApk);
                    }
                }
                else
                {
                    localApk = apk;
                    int i = this.id;
                    this.id = (i + 1);
                    localApk.setId(i);

                    if (localApk.isEnable())
                    {
                        this.apkQueen.remove(localApk.getPackageName());
                        this.runQueen.put(localApk.getPackageName(), localApk);
                        ApkManager.getInstance().readPackageInfo(localApk);
                    } else {
                        runQueen.remove(localApk.getPackageName());
                        apkQueen.put(localApk.getPackageName(), localApk);
                        ApkManager.getInstance().readPackageInfo(localApk);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
    }


}
