// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.motion.action;

import android.content.ContentResolver;

public class Command
{

    public Command()
    {
    }

    public static final Command build(String s)
    {
        String s1 = s.trim();
        int i = s1.indexOf(" ");
        Command command = new Command();
        if(i > 0)
        {
            command.action = s1.substring(0, i).trim();
            command.message = s1.substring(i).trim();
            return command;
        } else
        {
            command.action = s1;
            return command;
        }
    }

    public String getAction()
    {
        return action;
    }

    public String getMessage()
    {
        while(message == null || message.trim().length() == 0) 
            return null;
        return message;
    }

    public String getModel()
    {
        return model;
    }

    public ContentResolver getResolver()
    {
        return resolver;
    }

    public long getSleep()
    {
        return sleep;
    }

    public void setAction(String s)
    {
        action = s;
    }

    public void setMessage(String s)
    {
        message = s;
    }

    public void setResolver(ContentResolver contentresolver)
    {
        resolver = contentresolver;
    }

    public void setSleep(String s)
    {
        try
        {
            sleep = Long.parseLong(s);
            return;
        }
        catch(Exception exception)
        {
            return;
        }
    }

    private String action;
    private String message;
    private String model;
    private ContentResolver resolver;
    private long sleep;
}
