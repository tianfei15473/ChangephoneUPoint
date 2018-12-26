// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.net;

import java.util.HashMap;
import java.util.Iterator;

public class FormRequest extends Request
{

    public FormRequest()
    {
        parameters = new HashMap();
    }

    protected String encodeString(String s)
    {
        return s;
    }

    public String getContent()
    {
        StringBuffer stringbuffer = new StringBuffer();
        Iterator iterator = parameters.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                return stringbuffer.toString();
            String s = (String)iterator.next();
            if(stringbuffer.length() > 0)
                stringbuffer.append("&");
            stringbuffer.append(s).append("=").append(encodeString((String)parameters.get(s)));
        } while(true);
    }

    public void pusAll(HashMap hashmap)
    {
        if(hashmap != null && !hashmap.isEmpty())
            parameters.putAll(hashmap);
    }

    public void put(String s, int i)
    {
        put(s, String.valueOf(i));
    }

    public void put(String s, long l)
    {
        put(s, String.valueOf(l));
    }

    public void put(String s, String s1)
    {
        parameters.put(s, s1);
    }

    public void put(String s, boolean flag)
    {
        put(s, String.valueOf(flag));
    }

    public  void remove(String as[])
    {
        int i = as.length;
        int j = 0;
        do
        {
            if(j >= i)
                return;
            String s = as[j];
            try
            {
                parameters.remove(s);
            }
            catch(Exception exception) { }
            j++;
        } while(true);
    }

    private HashMap parameters;
}
