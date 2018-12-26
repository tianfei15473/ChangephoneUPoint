// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.net;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;

public interface ResponseHandler
{

    public abstract String handle(HttpEntity httpentity, StatusLine statusline, String s)
        throws Exception;
}
