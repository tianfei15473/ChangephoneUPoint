// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.task.Motion;

public class RecipeTask extends DefaultTask
{

    public RecipeTask()
    {
    }

    protected void start(Apk apk)
    {
        Motion.sleep(15000L);
        Motion.right2left(3000);
        Motion.right2left(3000);
        Motion.right2left(3000);
        Motion.touch(520, 1460, 2000);
    }
}
