// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import com.unking.xposedpoint.cmd.CommandExec;
import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.task.Motion;

public class SinaLattoryTask extends DefaultTask
{

    public SinaLattoryTask()
    {
    }

    protected void finish(Apk apk)
    {
        String s = (new StringBuilder("/data/data/")).append(apk.getPackageName()).append("/").toString();
        try
        {
            CommandExec commandexec = new CommandExec();
            String as[] = new String[6];
            as[0] = (new StringBuilder("cd ")).append(s).append("shared_prefs").toString();
            as[1] = "rm -r *";
            as[2] = (new StringBuilder("cd ")).append(s).append("databases").toString();
            as[3] = "rm -r *";
            as[4] = (new StringBuilder("cd ")).append(s).append("files").toString();
            as[5] = "rm -r *";
            commandexec.execute(as);
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
        Motion.right2left(2500);
        Motion.touch(564, 1746, 2000);
    }

    protected void stop(Apk apk)
    {
        Motion.touch(274, 1967, 2000);
        Motion.touch(274, 1967, 100);
        Motion.touch(274, 1967, 1000);
    }
}
