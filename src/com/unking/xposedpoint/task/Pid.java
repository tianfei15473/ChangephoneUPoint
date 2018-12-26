// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task;


public class Pid
{

    public Pid(String s)
    {
        String as[] = s.replaceAll("[ ]+", " ").split("[ ]");
        if(as.length == 9)
        {
            int i = 0 + 1;
            user = as[0];
            int j = i + 1;
            pid = as[i];
            int k = j + 1;
            ppid = as[j];
            int l = k + 1;
            vsize = as[k];
            int i1 = l + 1;
            rss = as[l];
            int j1 = i1 + 1;
            wchan = as[i1];
            int k1 = j1 + 1;
            pc = as[j1];
            int l1 = k1 + 1;
            type = as[k1];
            int _tmp = l1 + 1;
            packageName = as[l1];
        }
    }

    public String getPackageName()
    {
        return packageName;
    }

    public String getPc()
    {
        return pc;
    }

    public String getPid()
    {
        return pid;
    }

    public String getPpid()
    {
        return ppid;
    }

    public String getRss()
    {
        return rss;
    }

    public String getType()
    {
        return type;
    }

    public String getUser()
    {
        return user;
    }

    public String getVsize()
    {
        return vsize;
    }

    public String getWchan()
    {
        return wchan;
    }

    private String packageName;
    private String pc;
    private String pid;
    private String ppid;
    private String rss;
    private String type;
    private String user;
    private String vsize;
    private String wchan;
}
