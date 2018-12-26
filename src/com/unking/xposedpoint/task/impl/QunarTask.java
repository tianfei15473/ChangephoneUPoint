// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import android.content.Context;
import android.provider.Settings;

import com.unking.xposedpoint.cmd.CommandExec;
import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.task.Motion;

import java.util.Random;

public class QunarTask extends DefaultTask
{

    public QunarTask()
    {
    }

    protected void clear(Context context)
    {
        int i = 0;
        String[] arrayOfString = { "bd_setting_i", "com.baidu.deviceid" };
        int j;
        do
            try
            {
                j = arrayOfString.length;
                String str = arrayOfString[i];
                Settings.System.putString(context.getContentResolver(), str, "");
                i++;
            }
            catch (Exception localException)
            {
                break;
            }
        while (i < j);
    }

    protected void finish(Apk apk)
    {
        String s = (new StringBuilder("/data/data/")).append(apk.getPackageName()).append("/").toString();
        try
        {
            CommandExec commandexec = new CommandExec();
            String as[] = new String[8];
            as[0] = (new StringBuilder("cd ")).append(s).append("shared_prefs").toString();
            as[1] = "rm -r *";
            as[2] = (new StringBuilder("cd ")).append(s).append("databases").toString();
            as[3] = "rm -r *";
            as[4] = (new StringBuilder("cd ")).append(s).append("files").toString();
            as[5] = "rm -r *";
            as[6] = (new StringBuilder("cd ")).append(s).append("cache").toString();
            as[7] = "rm -r *";
            commandexec.execute(as);
            return;
        }
        catch(Exception exception)
        {
            LLogger.error("UCWeb", exception);
        }
    }

    protected void start(Apk apk)
    {
        Motion.sleep(5000L);
        Random random = new Random();
        Motion.touch(random.nextInt(1000), random.nextInt(1000), 2000);
    }
}
