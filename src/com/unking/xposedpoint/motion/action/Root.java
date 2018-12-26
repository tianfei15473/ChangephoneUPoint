// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.motion.action;

import com.unking.xposedpoint.cmd.CommandExec;

public class Root
{

    private Root()
    {
    }

    public static final  void exec(String as[])
    {
        (new CommandExec()).execute(as);
    }
}
