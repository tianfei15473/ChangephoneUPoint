// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.io;


public class RFile
{

    private RFile(int i, String s, String s1, String s2)
    {
        dir = s1;
        name = s2;
        if(i == 1)
        {
            dir = (new StringBuilder("/data/data/")).append(s).append("/").append(s1).toString();
            name = s2;
        } else
        if(i == 2)
        {
            dir = "/sdcard-ext/android/data";
            name = s;
            return;
        }
    }

    public static final RFile create(String s, String s1)
    {
        return new RFile(-9, null, s, s1);
    }

    public static final RFile createData(String s, String s1, String s2)
    {
        return new RFile(1, s, s1, s2);
    }

    public static final RFile createSdcardData(String s)
    {
        return new RFile(2, s, null, null);
    }

    public String getDir()
    {
        return dir;
    }

    public String getName()
    {
        return name;
    }

    private static final int DATA = 1;
    private static final int SDCARD_DATA = 2;
    private String dir;
    private String name;
}
