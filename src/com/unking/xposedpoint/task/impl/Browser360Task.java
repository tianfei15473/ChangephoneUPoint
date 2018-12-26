// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;


import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.task.Application;
import com.unking.xposedpoint.task.Motion;
import com.unking.xposedpoint.task.account.Account;
import com.unking.xposedpoint.task.account.EmailAccountBuilder;
import com.unking.xposedpoint.task.data.Device;
import com.unking.xposedpoint.task.io.RFile;
import com.unking.xposedpoint.task.net.Utils;

import java.util.Random;

public class Browser360Task extends DefaultTask
{

    public Browser360Task()
    {
        ran = new Random();
    }

    private void anquan()
    {
        if(ran.nextInt(100) > 85)
        {
            return;
        } else
        {
            Motion.touch(569, 1750, 2000);
            Motion.touch(441, 1461, 2000);
            Motion.randomTouch(2000);
            Motion.returnLast();
            return;
        }
    }

    private void login(Device device, Apk apk)
    {
        Motion.touch(569, 1750, 2000);
        Motion.touch(160, 1508, 2000);
        Motion.touch(327, 385, 2000);
        Motion.send(device.getAccountName(), 2000);
        Motion.touch(327, 554, 2000);
        Motion.send(device.getAccountPassword(), 5000);
        Motion.touch(742, 783, 8000);
    }

    private void regisiter(Device device, Apk apk)
    {
        Motion.touch(569, 1750, 2000);
        Motion.touch(160, 1508, 2000);
        Motion.touch(290, 783, 2000);
        Motion.touch(823, 276, 2000);
        Motion.touch(381, 499, 2000);
        Motion.send(device.getAccountName(), 2000);
        Motion.touch(289, 656, 2000);
        Motion.send(device.getAccountPassword(), 2000);
        Motion.touch(571, 819, 8000);
    }

    private void soucang()
    {
        if(ran.nextInt(100) > 85)
        {
            return;
        } else
        {
            Motion.touch(569, 1750, 2000);
            Motion.touch(160, 1177, 2000);
            Motion.touch(574, ran.nextInt(1000), 1000);
            Motion.touch(49, 1022, 2000);
            Motion.returnLast();
            return;
        }
    }

    private void sousuo()
    {
        if(ran.nextInt(100) > 85)
        {
            return;
        } else
        {
            Motion.touch(160, 169, 2000);
          //  new String[] { "qq", "长城", "北京", "西湖" };
            return;
        }
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
        Motion.touch(532, 1451, 2000);
        if(Utils.isEmpty(getDevice().getAccountName()))
        {
            Account account = EmailAccountBuilder.getInstance().random();
            getDevice().setAccountName(account.getName());
            getDevice().setAccountPassword(account.getPassword());
            regisiter(getDevice(), apk);
        } else
        {
            login(getDevice(), apk);
        }
        if(ran.nextInt(100) < 85)
            Motion.touch(1035, 359, 2000);
        Motion.returnLast();
        Motion.randomTouch(4000);
        soucang();
        anquan();
    }

    protected void stop(Apk apk)
    {
        super.stop(apk);
    }

    private Random ran;
}
