// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;
import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.task.Application;
import com.unking.xposedpoint.task.Motion;
import com.unking.xposedpoint.task.io.RFile;

public class MlTask extends DefaultTask
{

    public MlTask()
    {
    }

    protected void finish(Apk apk)
    {
        super.finish(apk);
        RFile arfile[] = new RFile[6];
        arfile[0] = RFile.createData(apk.getPackageName(), "app_appcache", "*");
        arfile[1] = RFile.createData(apk.getPackageName(), "app_bookmark", "*");
        arfile[2] = RFile.createData(apk.getPackageName(), "app_database", "*");
        arfile[3] = RFile.createData(apk.getPackageName(), "app_databases", "*");
        arfile[4] = RFile.createData(apk.getPackageName(), "app_geolocation", "*");
        arfile[5] = RFile.createData(apk.getPackageName(), "app_icons", "*");
        Application.delete(arfile);
    }

    protected void start(Apk apk)
    {
        Motion.sleep(10000L);
        Motion.right2left(2000);
        Motion.right2left(2000);
        Motion.right2left(2000);
        Motion.right2left(2000);
        Motion.right2left(2000);
        Motion.touch(532, 1451, 2000);
        Application.random(30);
    }

    protected void stop(Apk apk)
    {
        super.stop(apk);
        Motion.touch(274, 1967, 100);
        Motion.touch(274, 1967, 100);
        Motion.touch(274, 1967, 100);
        Motion.touch(274, 1967, 100);
        Motion.touch(783, 1270, 2000);
    }
}
