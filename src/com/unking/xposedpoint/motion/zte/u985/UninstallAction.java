// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.motion.zte.u985;

import android.util.Log;

import com.unking.xposedpoint.motion.action.Action;
import com.unking.xposedpoint.motion.action.Command;
import com.unking.xposedpoint.motion.action.Root;

public class UninstallAction
    implements Action
{

    public UninstallAction()
    {
    }

    public void handle(Command command)
    {
        try
        {
            String as[] = new String[1];
            as[0] = (new StringBuilder("uninstall ")).append(command.getMessage()).toString();
            Root.exec(as);
            return;
        }
        catch(Exception exception)
        {
            Log.e("KeyAction", "错误", exception);
        }
    }
}
