// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.motion.zte.u985;
import com.unking.xposedpoint.motion.action.Action;
import com.unking.xposedpoint.motion.action.Command;
import com.unking.xposedpoint.motion.action.Root;

public class MenuAction
    implements Action
{

    public MenuAction()
    {
    }

    public void handle(Command command)
    {
        try
        {
            String as[] = new String[4];
            as[0] = (new StringBuilder(String.valueOf("sendevent /dev/input/event10"))).append(" 1 139 1").toString();
            as[1] = (new StringBuilder(String.valueOf("sendevent /dev/input/event10"))).append(" 0 0 0").toString();
            as[2] = (new StringBuilder(String.valueOf("sendevent /dev/input/event10"))).append(" 1 139 0").toString();
            as[3] = (new StringBuilder(String.valueOf("sendevent /dev/input/event10"))).append(" 0 0 0").toString();
            Root.exec(as);
            return;
        }
        catch(Exception exception)
        {
            return;
        }
    }
}
