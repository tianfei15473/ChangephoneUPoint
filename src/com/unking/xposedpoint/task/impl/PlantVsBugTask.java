// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import com.unking.xposedpoint.cmd.CommandExec;
import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.log.LLogger;

public class PlantVsBugTask extends DefaultTask
{

    public PlantVsBugTask()
    {
    }

    protected void finish(Apk apk)
    {
        try
        {
            CommandExec commandexec = new CommandExec();
            String as[] = new String[5];
            as[0] = (new StringBuilder("cd ")).append("/data/data/org.anddev.amatidev.pvb").toString();
            as[1] = "rm -r cache";
            as[2] = "rm -r databases";
            as[3] = "rm -r files";
            as[4] = "rm -r shared_prefs";
            commandexec.execute(as);
            return;
        }
        catch(Exception exception)
        {
            LLogger.error("Baidu Browser delete", exception);
        }
    }

    protected void stop(Apk apk)
    {
    }
}
