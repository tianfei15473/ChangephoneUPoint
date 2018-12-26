// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task;
import com.unking.xposedpoint.log.LLogger;

import java.io.OutputStream;

public class SuUtils
{

    public SuUtils()
    {
    }

    private static void clearUserData(String s)
    {
        OutputStream outputstream = process.getOutputStream();
        String s1 = (new StringBuilder("pm clear ")).append(s).append(" \n").toString();
        try
        {
            outputstream.write("export LD_LIBRARY_PATH=/vendor/lib:/system/lib\n".getBytes());
            outputstream.write(s1.getBytes());
            outputstream.flush();
            return;
        }
        catch(Exception exception)
        {
            LLogger.error("SuUtil.clearUserData", exception);
        }
    }

    private static void close()
    {
        if (process != null);
        try
        {
            process.getOutputStream().close();
            process = null;
            return;
        }
        catch (Exception localException)
        {
            LLogger.error("SuUtil.close", localException);
        }
    }

    public static void exe(String s)
    {
        initProcess();
        exeProcess(s);
        close();
    }

    private static void exeProcess(String s)
    {
        OutputStream outputstream = process.getOutputStream();
        try
        {
            outputstream.write("export LD_LIBRARY_PATH=/vendor/lib:/system/lib\n".getBytes());
            outputstream.write(s.getBytes());
            outputstream.flush();
            return;
        }
        catch(Exception exception)
        {
            LLogger.error("SuUtil.killProcess", exception);
        }
    }

    public static void exit(String s)
    {
        initProcess();
        killProcess(s);
        close();
    }

    private static void initProcess()
    {
        if (process == null);
        try
        {
            process = Runtime.getRuntime().exec("su");
            return;
        }
        catch (Exception localException)
        {
            LLogger.error("SuUtil.initProcess", localException);
        }
    }

    public static void kill(String s)
    {
        initProcess();
        killProcess(s);
        clearUserData(s);
        close();
    }

    private static void killProcess(String s)
    {
        OutputStream outputstream = process.getOutputStream();
        String s1 = (new StringBuilder("am force-stop ")).append(s).append(" \n").toString();
        try
        {
            outputstream.write("export LD_LIBRARY_PATH=/vendor/lib:/system/lib\n".getBytes());
            outputstream.write(s1.getBytes());
            outputstream.flush();
            return;
        }
        catch(Exception exception)
        {
            LLogger.error("SuUtil.killProcess", exception);
        }
    }

    private static Process process;
}
