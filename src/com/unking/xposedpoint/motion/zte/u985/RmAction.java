// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.motion.zte.u985;

import com.unking.xposedpoint.cmd.CommandExec;
import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.motion.action.Action;
import com.unking.xposedpoint.motion.action.Command;

public class RmAction
    implements Action
{

    public RmAction()
    {
    }

    public void handle(Command command)
    {
        try
        {
            CommandExec commandexec = new CommandExec();
            String as[] = command.getMessage().split("[ ]");
            if(as.length == 2)
            {
                String as1[] = new String[2];
                as1[0] = (new StringBuilder("cd ")).append(as[0]).toString();
                as1[1] = (new StringBuilder("rm -r ")).append(as[1]).toString();
                commandexec.execute(as1);
            }
            return;
        }
        catch(Exception exception)
        {
            LLogger.error("RmAction", exception);
        }
    }
}
