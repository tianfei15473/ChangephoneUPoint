// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.cmd;


public class CommandMessageParser
{

    public CommandMessageParser()
    {
    }

    public Object parse(byte abyte0[])
    {
        if(abyte0.length == 0)
            return null;
        String s;
        try
        {
            s = new String(abyte0, "GBK");
        }
        catch(Exception exception)
        {
            return new String(abyte0);
        }
        return s;
    }
}
