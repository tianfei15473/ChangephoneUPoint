// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.account;
public class Account
{

    public Account()
    {
    }

    public Account(String s, String s1)
    {
        name = s;
        password = s1;
    }

    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password;
    }

    private String name;
    private String password;
}
