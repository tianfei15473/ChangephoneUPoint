// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import android.content.Context;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.task.Motion;

public class BaiduNewsTask extends DefaultTask
{

    public BaiduNewsTask()
    {
    }

    protected void clear(Context context)
    {
        try
        {
            android.provider.Settings.System.putString(context.getContentResolver(), "com.baidu.deviceid", "");
            android.provider.Settings.System.putString(context.getContentResolver(), "com.baidu.pushservice.channel_token", "");
            android.provider.Settings.System.putString(context.getContentResolver(), "bd_setting_i", "");
            return;
        }
        catch(Exception exception)
        {
            return;
        }
    }

    protected void start(Apk apk)
    {
        Motion.sleep(10000L);
        Motion.randomTouch(2000);
        Motion.touch(544, 1680, 5000);
        Motion.randomTouch(2000);
        Motion.randomTouch(2000);
    }

    protected void stop(Apk apk)
    {
        Motion.touch(274, 1967, 2000);
        Motion.touch(334, 1180, 1000);
    }
}
