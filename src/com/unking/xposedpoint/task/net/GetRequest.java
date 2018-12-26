// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.net;

import java.net.URLEncoder;

public class GetRequest extends FormRequest
{

    public GetRequest(String s)
    {
        setUrl(s);
    }

    protected String encodeString(String s)
    {
        String s1;
        try
        {
            s1 = URLEncoder.encode(s, "gbk");
        }
        catch(Exception exception)
        {
            return s;
        }
        return s1;
    }

    public String getContent()
    {
        return null;
    }

    public String getUrl()
    {
        StringBuffer stringbuffer = new StringBuffer(super.getUrl());
        String s = super.getContent();
        if(!Utils.isEmpty(s) && !"null".equals(s))
        {
            if(stringbuffer.indexOf("?") > 0)
                stringbuffer.append("&");
            else
                stringbuffer.append("?");
            stringbuffer.append(s);
        }
        return stringbuffer.toString();
    }

    protected boolean isStoreCookie(String s)
    {
        return true;
    }
}
