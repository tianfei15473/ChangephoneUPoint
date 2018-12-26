// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.net;

import java.util.Random;

public final class IpAddress
{

    private IpAddress()
    {
        random = new Random();
        threadlocalip = new ThreadLocal();
    }

    public static final IpAddress getInstance()
    {
        return IP_ADDRESS;
    }

    private String toIP4String(long l)
    {
        StringBuffer stringbuffer = new StringBuffer("");
        stringbuffer.append(String.valueOf(l >>> 24));
        stringbuffer.append(".");
        stringbuffer.append(String.valueOf((0xffffffL & l) >>> 16));
        stringbuffer.append(".");
        stringbuffer.append(String.valueOf((65535L & l) >>> 8));
        stringbuffer.append(".");
        stringbuffer.append(String.valueOf(255L & l));
        return stringbuffer.toString();
    }

    public String getIp()
    {
        return (String)threadlocalip.get();
    }

    public synchronized String nextIp() {
        long l = random.nextLong();
        String s;
        if (l < 0L) {
            l = 0L - l;
        }
        if (l > 0xffffffffL) {
            l %= 0xffffffffL;
        }
        s = toIP4String(l);
        threadlocalip.set(s);
        return s;
    }

    private static final IpAddress IP_ADDRESS = new IpAddress();
    private Random random;
    private ThreadLocal threadlocalip;

}
