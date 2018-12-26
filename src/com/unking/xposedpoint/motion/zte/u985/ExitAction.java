// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.motion.zte.u985;


import com.unking.xposedpoint.motion.action.Action;
import com.unking.xposedpoint.motion.action.Command;
import com.unking.xposedpoint.task.Motion;

public class ExitAction
    implements Action
{

    public ExitAction()
    {
    }

    public void handle(Command command)
    {
        Motion.touch(274, 1967, 2000);
        Motion.touch(274, 1967, 100);
        Motion.touch(274, 1967, 2000);
    }
}
