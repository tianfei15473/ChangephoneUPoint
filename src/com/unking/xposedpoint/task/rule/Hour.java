// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.rule;


public class Hour
{

    public Hour()
    {
    }

    public Hour(int i, int j)
    {
        time = i;
        downloads = j;
        if(j < 0)
            j = 0 - j;
        long l = 0x36ee80L;
        if(j != 0)
            l = Math.round((float)l / (float)j);
        interval = l;
    }

    public int getDownloads()
    {
        return downloads;
    }

    public long getInterval()
    {
        return interval;
    }

    public int getTime()
    {
        return time;
    }

    private int downloads;
    private long interval;
    private int time;
}
