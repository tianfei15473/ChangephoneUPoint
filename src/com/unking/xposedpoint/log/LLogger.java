// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.log;

import android.util.Log;

import java.io.FileOutputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class LLogger
{

    private LLogger()
    {
    }

    public static final  void debug(String s, String s1, Object aobj[])
    {
        if("debug".equalsIgnoreCase(LEVEL))
        {
            if(aobj != null && aobj.length != 0)
                s1 = MessageFormat.format(s1, aobj);
            Log.e(s, s1);
        }
    }

    public static final void debug(String s, Throwable throwable)
    {
        if("debug".equalsIgnoreCase(LEVEL))
            Log.e(s, "debug", throwable);
    }

    public static final void error(String s, Object obj)
    {
        error(s, "{0}", new Object[] {
            obj
        });
    }

    public static final  void error(String s, String s1, Object aobj[])
    {
        if(LEVEL.matches("error|debug"))
        {
            if(aobj != null && aobj.length != 0)
                s1 = MessageFormat.format(s1, aobj);
            Log.e(s, s1);
        }
    }

    public static final void error(String s, Throwable throwable)
    {
        if(LEVEL.matches("error|debug"))
            Log.e(s, "error", throwable);
    }

    public static final  void info(String s, String s1, Object aobj[])
    {
        if("info".equalsIgnoreCase(LEVEL))
        {
            if(aobj != null && aobj.length != 0)
                s1 = MessageFormat.format(s1, aobj);
            Log.e(s, s1);
        }
    }

    public static final void info(String s, Throwable throwable)
    {
        if("info".equalsIgnoreCase(LEVEL))
            Log.e(s, "info", throwable);
    }

    /**
     * 记录日志到/mnt/sdcard-ext/bbn_run_日期.log
     * @param s 记录的日志内容
     */
    public static final void log(String s)
    {
        FileOutputStream fileoutputstream = null;
        FileOutputStream fileoutputstream1;
        try {
            String s1 = (new SimpleDateFormat("yyyyMMdd", Locale.CHINESE)).format(new Date());
            fileoutputstream = new FileOutputStream((new StringBuilder("/mnt/sdcard-ext/point_run_")).append(s1).append(".log").toString(), true);
            fileoutputstream.write((new StringBuilder(String.valueOf(s))).append("\n").toString().getBytes());
            fileoutputstream.close();
        }catch (Exception exception) {

        }

    }

    public static final String DEBUG = "debug";
    public static final String ERROR = "error";
    public static String LEVEL = "error";

}
