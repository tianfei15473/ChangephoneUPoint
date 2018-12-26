// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import com.unking.xposedpoint.cmd.CommandExec;
import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.log.LLogger;

public class UCWebTask extends DefaultTask
{

    public UCWebTask()
    {
    }

    protected void finish(Apk apk)
    {
        try
        {
            CommandExec commandexec = new CommandExec();
            String as[] = new String[2];
            as[0] = (new StringBuilder("cd ")).append("/data/data/com.UCMobile/UCMobile").toString();
            as[1] = "rm -r *";
            commandexec.execute(as);
            return;
        }
        catch(Exception exception)
        {
            LLogger.error("UCWeb", exception);
        }
    }
}
