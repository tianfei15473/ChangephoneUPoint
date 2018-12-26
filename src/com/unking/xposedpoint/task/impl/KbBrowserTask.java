// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import android.content.Context;
import android.provider.Settings;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.task.Motion;

public class KbBrowserTask extends DefaultTask
{
    public KbBrowserTask()
    {
    }

    protected void clear(Context context)
    {
        int i = 0;
        String[] arrayOfString = { "King_Msg_User_Id", "bd_setting_i", "com.baidu.deviceid" };
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

    protected void start(Apk apk)
    {
        Motion.sleep(5000L);
        Motion.randomTouch(2000);
        Motion.randomTouch(2000);
        Motion.randomTouch(2000);
    }
}
