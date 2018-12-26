// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import com.unking.xposedpoint.cmd.CommandExec;
import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.task.Motion;

public class Mobile360Task extends DefaultTask
{

    public Mobile360Task()
    {
    }

    protected void finish(Apk apk)
    {
        super.finish(apk);
        try
        {
            (new CommandExec()).execute(new String[] {
                "cd /sdcard-ext", "rm -r 360Download"
            });
            return;
        }
        catch(Exception exception)
        {
            LLogger.error("UCWeb", exception);
        }
    }

    protected void start(Apk apk)
    {
        Motion.sleep(10000L);
        Motion.returnLast();
        Motion.randomTouch(2000);
        Motion.randomTouch(1000);
        Motion.randomTouch(1000);
    }

    protected void stop(Apk apk)
    {
        super.stop(apk);
    }
}
