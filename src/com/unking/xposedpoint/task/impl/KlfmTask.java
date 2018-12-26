// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.task.Motion;

import java.util.ArrayList;
import java.util.Random;

public class KlfmTask extends DefaultTask
{
    private class Point
    {

        final KlfmTask this$0;
        private int x;
        private int y;



        public Point(int i, int j)
        { super();
            this$0 = KlfmTask.this;

            x = i;
            y = j;
        }
    }


    public KlfmTask()
    {
        select1 = new ArrayList();
        select1.add(new Point(242, 334));
        select1.add(new Point(577, 334));
        select1.add(new Point(919, 334));
        select1.add(new Point(242, 713));
        select1.add(new Point(577, 713));
        select1.add(new Point(919, 713));
        select1.add(new Point(242, 1043));
        select1.add(new Point(577, 1043));
        select1.add(new Point(919, 1043));
    }

    private void dianzhan()
    {
        Motion.touch(346, 1430, 2000);
    }

    private void fenxiang()
    {
        Motion.touch(97, 1430, 2000);
        Motion.touch(934, 1643, 1000);
    }

    /*private void next()
    {
        Motion.touch(776, 1430, 2000);
    }*/

    private void select()
    {
        Motion.touch(108, 173, 2000);
        Motion.randomTouch(2000);
        Motion.randomTouch(2000);
        Motion.touch(1040, 173, 2000);
    }

    private void userinfo()
    {
        Motion.touch(1040, 173, 2000);
        Motion.randomTouch(2000);
        Motion.randomTouch(2000);
        Motion.touch(108, 173, 2000);
    }

    private void xiazai()
    {
        Motion.touch(999, 1430, 2000);
    }

    private void xinyuan()
    {
        Motion.touch(562, 1401, 2000);
    }

    protected void start(Apk apk)
    {
        Motion.randomSleep(20000);
        Motion.touch(1053, 1773, 7000);
        Random random = new Random();
        Point point = (Point)select1.get(random.nextInt(select1.size()));
        Motion.touch(point.x, point.y, 3000);
        Motion.touch(930, 1782, 5000);
        if(random.nextInt(100) > 80)
        {
            return;
        } else
        {
            Motion.random(120, 60);
            return;
        }
    }

    private ArrayList select1;
}
