// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.net;

import com.unking.xposedpoint.log.LLogger;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DefaultResponseHandler
    implements ResponseHandler
{

    public DefaultResponseHandler()
    {
    }

    protected String handle(InputStream inputstream, String s)
        throws IOException {
        StringBuffer stringbuffer = new StringBuffer();
        BufferedReader bufferedreader = null;
        try {
            bufferedreader = new BufferedReader(new InputStreamReader(inputstream, s));
            String s1 = null;
            while ((s1 = bufferedreader.readLine()) != null) {
                if (s1 != null) {
                    if (stringbuffer.length() > 0)
                        stringbuffer.append("\n\r");
                    stringbuffer.append(s1);
                }
            }
        } catch (Exception exception) {
            LLogger.error("DefaultResponseHandler", exception);
        }
        return stringbuffer.toString();
    }

    public String handle(HttpEntity httpentity, StatusLine statusline, String s)
        throws Exception
    {
        return handle(httpentity.getContent(), s);
    }
}
