// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.task.Application;
import com.unking.xposedpoint.task.Motion;
import com.unking.xposedpoint.task.io.RFile;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Random;

public class Store360Task extends DefaultTask
{
    private class Point
    {

        final Store360Task this$0;
        int x;
        int y;

        private Point(int i, int j)
        {  super();
            this$0 = Store360Task.this;

            x = i;
            y = j;
        }

        Point(int i, int j, Point point)
        {
            this(i, j);
        }
    }


    public Store360Task()
    { super();
        first = new ArrayList();
        first.add(new Point(120, 1743, null));
        first.add(new Point(357, 1743, null));
        first.add(new Point(549, 1743, null));
        ran = new Random();
    }

    private void download()
    {
        Point point = (Point)first.get(ran.nextInt(first.size()));
        Motion.touch(point.x, point.y, 3000);
        randomTouch(3000);
        Motion.touch(530, 1746, 3000);
        do
        {
            if(isFinishDownload())
            {
                Motion.returnLast();
                return;
            }
            Motion.sleep(1000L);
        } while(true);
    }

    private boolean isFinishDownload()
    {
        return (new File("/sdcard-ext/360Download/360SecDownload/")).listFiles(new FilenameFilter() {

            public boolean accept(File file, String s)
            {
                LLogger.error("error", s);
                return s.endsWith(".temp!");
            }


        }
).length == 0;
    }

    public static final void randomTouch(int i)
    {
        Random random = new Random();
        Motion.touch(100 + random.nextInt(1000), Math.max(100 + random.nextInt(1000), 500), i);
    }

    protected void finish(Apk apk)
    {
        super.finish(apk);
        RFile arfile[] = new RFile[1];
        arfile[0] = RFile.create("/sdcard-ext/360Download/360SecDownload", "*");
        Application.delete(arfile);
    }

    protected void run(Apk apk)
    {
    }

    protected void start(Apk apk)
    {
        Motion.sleep(5000L);
        int i = 0;
        do
        {
            if(i >= 2)
                return;
            download();
            i++;
        } while(true);
    }

    private ArrayList first;
    private Random ran;
}
