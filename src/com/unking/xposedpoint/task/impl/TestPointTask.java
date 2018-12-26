package com.unking.xposedpoint.task.impl;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.task.Motion;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by fei on 2014/12/17.
 */
public class TestPointTask extends DefaultTask {

    private ArrayList select1;

    private class Point
    {

        final TestPointTask this$0;
        private int x;
        private int y;



        public Point(int i, int j)
        { super();
            this$0 = TestPointTask.this;

            x = i;
            y = j;
        }
    }

    public TestPointTask()
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

}
