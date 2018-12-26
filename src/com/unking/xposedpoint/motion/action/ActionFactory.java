// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.motion.action;

import android.util.Log;

import java.util.Locale;


public class ActionFactory
{

    private ActionFactory()
    {
    }

    public static final Action create(String s)
    {
        Action action = new Action() {
            public void handle(Command command){}
        };

        if(s == null || s.trim().length() < 2)
            return action;
        String s1 = "com.unking.xposedpoint.motion.zte.u985." + s.toUpperCase(Locale.getDefault()).charAt(0) + s.toLowerCase(Locale.getDefault()).substring(1) + "Action";
        Log.d("feitian-action", s1);
        Action action1;
        try
        {
            action1 = (Action)Class.forName(s1).newInstance();
        }
        catch(Exception exception)
        {
            Log.e("ActionFactory.create", "error", exception);
            return action;
        }
        return action1;
    }
}
