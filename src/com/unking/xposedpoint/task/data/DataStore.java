// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.data;

import java.util.HashMap;

/**
 * 网络类型
 */
public class DataStore
{

    private DataStore()
    {
        networkMap.put("0", "UNKNOWN");
        networkMap.put("1", "GPRS");
        networkMap.put("2", "EDGE");
        networkMap.put("3", "UMTS");
        networkMap.put("4", "CDMA");
        networkMap.put("5", "EVDO_0");
        networkMap.put("6", "EVDO_A");
        networkMap.put("7", "1xRTT");
        networkMap.put("8", "HSDPA");
        networkMap.put("9", "HSUPA");
        networkMap.put("10", "HSPA");
        networkMap.put("11", "IDEN");
        networkMap.put("12", "EVDO_B");
        networkMap.put("13", "LTE");
        networkMap.put("14", "EHRPD");
        networkMap.put("15", "HSPAP");
        networkMap.put("16", "WIFI");
    }

    public static final DataStore getInstance()
    {
        return STORE;
    }

    private static final DataStore STORE = new DataStore();
    public final HashMap networkMap = new HashMap();

}
