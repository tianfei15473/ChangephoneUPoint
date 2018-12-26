// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.motion.action.Command;
import com.unking.xposedpoint.task.Application;
import com.unking.xposedpoint.task.Motion;
import com.unking.xposedpoint.task.PidManager;
import com.unking.xposedpoint.task.Task;
import com.unking.xposedpoint.task.data.Device;
import com.unking.xposedpoint.task.io.RFile;

import java.util.Iterator;

public class TaskImpl
    implements Task
{

    public TaskImpl()
    {
        running = false;
    }

    public Device getDevice()
    {
        return device;
    }

    public boolean isRunning()
    {
        return running;
    }

    public void setDevice(Device device1)
    {
        device = device1;
    }

    public void start(Context context, Apk apk)
    {
        running = true;
        Intent intent = new Intent();
        intent.addFlags(0x10000000);
        intent.setComponent(new ComponentName(apk.getPackageName(), apk.getActivity()));
        context.startActivity(intent);
        Iterator iterator = apk.getStartCmds().iterator();
        do
        {
            if(!iterator.hasNext())
                return;
            Motion.execute(context, apk, (Command)iterator.next());
        } while(true);
    }

    public void stop(Context context, Apk apk)
    {
        Iterator iterator = apk.getStopCmds().iterator();
        do
        {
            if(!iterator.hasNext())
            {
                RFile arfile[] = new RFile[4];
                arfile[0] = RFile.createData(apk.getPackageName(), "shared_prefs", "*");
                arfile[1] = RFile.createData(apk.getPackageName(), "cache", "*");
                arfile[2] = RFile.createData(apk.getPackageName(), "databases", "*");
                arfile[3] = RFile.createData(apk.getPackageName(), "files", "*");
                Application.delete(arfile);
                (new PidManager()).kill(apk.getPackageName());
                running = false;
                return;
            }
            Motion.execute(context, apk, (Command)iterator.next());
        } while(true);
    }

    private Device device;
    private boolean running;
}
