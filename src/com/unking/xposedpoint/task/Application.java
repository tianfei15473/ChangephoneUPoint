// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task;

import android.content.Context;

import com.unking.xposedpoint.cmd.CommandExec;
import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.task.io.RFile;

import java.util.Random;


public class Application
{

    private Application()
    {
    }

    public static final  void delete(RFile paramArrayOfRFile[])
    {
        int i=0;
        int j=0;
        do
            try
            {
                CommandExec localCommandExec = new CommandExec();
                i = paramArrayOfRFile.length;

                RFile localRFile = paramArrayOfRFile[j];
                localCommandExec.execute(new String[] { "cd " + localRFile.getDir(), "rm -r " + localRFile.getName() });
                j++;
            }
            catch (Exception localException)
            {
                LLogger.error("File.rm", localException);
                continue;
            }
        while (j < i);
    }

    public static final  void exe(String as[])
    {
        try
        {
            (new CommandExec()).execute(as);
            return;
        }
        catch(Exception exception)
        {
            LLogger.error("File.rm", exception);
        }
    }

    public static final boolean isInstalled(Context context, Apk apk)
    {
        android.content.pm.ApplicationInfo applicationinfo;
        try
        {
            applicationinfo = context.getPackageManager().getApplicationInfo(apk.getPackageName(), 1);
        }
        catch(Exception exception)
        {
            return false;
        }
        return applicationinfo != null;
    }

    public static final void random(int i)
    {
        if(i >= 1)
        {
            Random random1 = new Random();
            int j = random1.nextInt(i);
            if(j == 0)
                j = i;
            while(j > 0) 
            {
                int k = Math.min(j, random1.nextInt(15));
                j -= k;
                Motion.randomTouch(k * 1000);
            }
        }
    }
}
