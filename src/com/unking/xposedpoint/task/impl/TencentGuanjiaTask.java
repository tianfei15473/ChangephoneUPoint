// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import android.content.ComponentName;
import android.content.Intent;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.task.Application;
import com.unking.xposedpoint.task.Motion;
import com.unking.xposedpoint.task.PidManager;
import com.unking.xposedpoint.task.io.RFile;

public class TencentGuanjiaTask extends DefaultTask
{

    public TencentGuanjiaTask()
    {
    }

    protected void finish(Apk apk)
    {
        super.finish(apk);
        RFile arfile[] = new RFile[1];
        arfile[0] = RFile.createData(apk.getPackageName(), "", ".qm_guid");
        Application.delete(arfile);
    }

    protected void start(Apk apk)
    {
        Motion.sleep(5000L);
        Motion.up2down(1000);
        Motion.up2down(1000);
        Motion.up2down(1000);
        Motion.up2down(1000);
        Motion.up2down(5000);
        Motion.touch(485, 1695, 5000);
        (new PidManager()).kill(apk.getPackageName());
        Intent intent = new Intent();
        intent.addFlags(0x10000000);
        intent.setComponent(new ComponentName(apk.getPackageName(), apk.getActivity()));
        context.startActivity(intent);
        Motion.sleep(5000L);
        Motion.randomTouch(1000);
        Motion.randomTouch(1000);
        Motion.randomTouch(1000);
    }
}
