// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.net;

import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.utils.StreamUtils;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.conn.ConnectTimeoutException;

import java.io.ByteArrayOutputStream;

public class DownloadResponseHandler
    implements ResponseHandler
{

    public DownloadResponseHandler()
    {
        contentLength = 0;
    }

    public DownloadResponseHandler(int i)
    {
        contentLength = 0;
        contentLength = i;
    }

    public String handle(HttpEntity httpentity, StatusLine statusline, String s)
        throws Exception {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        int i = 0;
        int j = 0;
        try {
            StreamUtils.write(bytearrayoutputstream, httpentity.getContent());
            i = bytearrayoutputstream.toByteArray().length;
            j = contentLength;
        } catch (Exception exception) {
        }

        if (i > j)
            return "true";
        if (statusline.getStatusCode() >= 304) {
            byte abyte0[];
            if (bytearrayoutputstream == null)
                abyte0 = "error".getBytes();
            else
                abyte0 = bytearrayoutputstream.toByteArray();
            LLogger.error("warn", new String(abyte0));
            throw new ConnectTimeoutException();
        } else {
            return (new StringBuilder(String.valueOf(statusline.getStatusCode()))).append(":").append(httpentity.getContentLength()).toString();
        }
    }

    private int contentLength;
}
