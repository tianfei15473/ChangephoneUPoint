// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.task.Application;
import com.unking.xposedpoint.task.Motion;
import com.unking.xposedpoint.task.io.RFile;

public class TTPodTask extends DefaultTask
{

    public TTPodTask()
    {
    }

    protected void finish(Apk apk)
    {
        super.finish(apk);
        RFile arfile[] = new RFile[1];
        arfile[0] = RFile.create("/sdcard-ext/ttpod", "*");
        Application.delete(arfile);
    }

    protected void start(Apk apk)
    {
        Motion.sleep(10000L);
        Motion.right2left(2000);
        Motion.right2left(2000);
        Motion.right2left(2000);
        Motion.right2left(2000);
        Motion.touch(502, 1291, 2000);
        Motion.right2left(2000);
        Application.random(120);
    }

    protected void stop(Apk apk)
    {
        super.stop(apk);
    }
}
