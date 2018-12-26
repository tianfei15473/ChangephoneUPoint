// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint;

import com.unking.xposedpoint.log.LLogger;

public class Global
{

    private Global()
    {
    }

    private static final String getString(String s)
    {
        String s1;
        try
        {
            Class class1 = Class.forName("android.os.SystemProperties");
            s1 = (String)class1.getMethod("get", new Class[] {
              String.class
            }).invoke(class1, new Object[] {
                s
            });
        }
        catch(Exception exception)
        {
            LLogger.error("Global.getString", exception);
            return null;
        }
        return s1;
    }

    public static final String DATADIR = "/sdcard-ext/point";
    public static final String PHONE = getString("ro.build.bbnid");

}
