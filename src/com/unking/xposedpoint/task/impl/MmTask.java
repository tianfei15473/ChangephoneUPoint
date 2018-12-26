// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.task.Motion;


public class MmTask extends DefaultTask
{

    public MmTask()
    {
    }

    protected void start(Apk apk)
    {
        Motion.sleep(10000L);
        Motion.touch(778, 1410, 5000);
        Motion.right2left(2000);
        Motion.right2left(2000);
        Motion.right2left(2000);
        Motion.right2left(2000);
        Motion.touch(982, 1777, 5000);
    }
}
