// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.task.Application;
import com.unking.xposedpoint.task.Motion;
import com.unking.xposedpoint.task.io.RFile;

import java.util.ArrayList;
import java.util.Random;

public class Jike360Task extends DefaultTask
{
    private class Point
    {

        final Jike360Task this$0;
        int x;
        int y;

        private Point(int i, int j)
        {   super();
            this$0 = Jike360Task.this;

            x = i;
            y = j;
        }

        Point(int i, int j, Point point)
        {
            this(i, j);
        }
    }


    public Jike360Task()
    {
        first = new ArrayList();
        first.add(new Point(537, 707, null));
        first.add(new Point(226, 1350, null));
        first.add(new Point(567, 1350, null));
        first.add(new Point(914, 1350, null));
        first.add(new Point(226, 1702, null));
        first.add(new Point(567, 1702, null));
        first.add(new Point(914, 1702, null));
        ran = new Random();
    }

    private void download()
    {
        Point point = (Point)first.get(ran.nextInt(first.size()));
        Motion.touch(point.x, point.y, 3000);
        Motion.touch(347, 1277, 2000);
        Motion.returnLast();
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
        Motion.sleep(10000L);
        Motion.right2left(2000);
        Motion.touch(514, 1593, 3000);
        int i = Math.max(1, ran.nextInt(first.size()));
        int j = 0;
        do
        {
            if(j >= i)
                return;
            download();
            j++;
        } while(true);
    }

    private ArrayList first;
    private Random ran;
}
