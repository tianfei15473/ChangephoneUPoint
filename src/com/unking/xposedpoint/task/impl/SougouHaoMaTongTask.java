// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.task.Motion;

public class SougouHaoMaTongTask extends DefaultTask
{

    public SougouHaoMaTongTask()
    {
    }

    protected void start(Apk apk)
    {
        Motion.sleep(3000L);
        Motion.randomTouch(2000);
        Motion.randomTouch(2000);
        Motion.randomTouch(2000);
    }
}
