// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.motion.zte.u985;

import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.motion.action.Action;
import com.unking.xposedpoint.motion.action.Command;

public class ClearAction
    implements Action
{

    public ClearAction()
    {
    }

    public void handle(Command command)
    {
        String as[];
        as = command.getMessage().split("[ ]");
        if(as.length != 2)
            return;
        try
        {
            if("system".equalsIgnoreCase(as[0]))
                android.provider.Settings.System.putString(command.getResolver(), as[1], "");
            if("Secure".equalsIgnoreCase(as[0]))
            {
                android.provider.Settings.Secure.putString(command.getResolver(), as[1], "");
                return;
            }
        }
        catch(Exception exception)
        {
            LLogger.error("RmAction", exception);
        }
        return;
    }
}
