// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.utils;

import android.util.Log;

import java.util.Locale;

public class ObjectPropertyUtils
{

    public ObjectPropertyUtils()
    {
    }

    public static String getString(Object obj, String s)
    {
        StringBuffer stringbuffer = (new StringBuffer("get")).append(s.toUpperCase(Locale.getDefault()).charAt(0)).append(s.substring(1));
        String s1;
        try
        {
            s1 = (String)obj.getClass().getMethod(stringbuffer.toString(), new Class[0]).invoke(obj, new Object[0]);
        }
        catch(Exception exception)
        {
            Log.e("ObjectPropertyUtils.setProperty", "错误", exception);
            return "";
        }
        return s1;
    }

    public static void setProperty(Object obj, String s, String s1)
    {
        StringBuffer stringbuffer = (new StringBuffer("set")).append(s.toUpperCase(Locale.getDefault()).charAt(0)).append(s.substring(1));
        try
        {
            obj.getClass().getMethod(stringbuffer.toString(), new Class[] {
              String.class
            }).invoke(obj, new Object[] {
                s1
            });
            return;
        }
        catch(Exception exception)
        {
            Log.e("ObjectPropertyUtils.setProperty", "错误", exception);
        }
    }
}
