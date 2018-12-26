// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import com.unking.xposedpoint.cmd.CommandExec;
import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.task.Motion;

import java.util.Random;

public class Video360Task extends DefaultTask
{

    public Video360Task()
    {
    }

    protected void finish(Apk apk)
    {
        super.finish(apk);
        try
        {
            (new CommandExec()).execute(new String[] {
                "cd /sdcard-ext", "rm -r 360Video"
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
        Motion.right2left(2500);
        Motion.right2left(2500);
        Motion.right2left(2500);
        Motion.right2left(2500);
        Motion.touch(533, 1612, 1000);
        Random random = new Random();
        int i = random.nextInt(240);
        do
        {
            if(i <= 0)
                return;
            int j = random.nextInt(15);
            i -= j;
            Motion.touch(random.nextInt(1000), random.nextInt(1000), j * 1000);
        } while(true);
    }

    protected void stop(Apk apk)
    {
        super.stop(apk);
    }
}
