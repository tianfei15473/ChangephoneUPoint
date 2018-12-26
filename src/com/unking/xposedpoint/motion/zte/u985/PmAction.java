// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.motion.zte.u985;
import com.unking.xposedpoint.motion.action.Action;
import com.unking.xposedpoint.motion.action.Command;
import com.unking.xposedpoint.motion.action.Root;

public class PmAction
    implements Action
{

    public PmAction()
    {
    }

    public void handle(Command command)
    {
        String as[] = new String[2];
        as[0] = "export LD_LIBRARY_PATH=/vendor/lib:/system/lib";
        as[1] = (new StringBuilder("pm ")).append(command.getMessage()).toString();
        Root.exec(as);
    }
}
