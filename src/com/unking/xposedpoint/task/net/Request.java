// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.net;

import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;

import java.util.Collection;
import java.util.HashMap;

public abstract class Request
{

    public Request()
    {
        connectTimeOut = 3000;
        contentType = "text/html";
        handler = new DefaultResponseHandler();
        headers = new HashMap();
        readTimeOut = 3000;
        responseEncoding = "utf-8";
        userAgent = "Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)";
    }

    public final void addAllHeader(HashMap hashmap)
    {
        headers.putAll(hashmap);
    }

    public final void addHeader(String s, String s1)
    {
        headers.put(s, s1);
    }

    public int getConnectTimeOut()
    {
        return connectTimeOut;
    }

    public String getContent()
    {
        return content;
    }

    public String getContentType()
    {
        return contentType;
    }

    public CookieStore getCookie()
    {
        return cookie;
    }

    public ResponseHandler getHandler()
    {
        return handler;
    }

    public final String getHeader(String s)
    {
        return (String)headers.get(s);
    }

    public final Collection getHeadersName()
    {
        return headers.keySet();
    }

    public final HttpHost getProxy()
    {
        return proxy;
    }

    public int getReadTimeOut()
    {
        return readTimeOut;
    }

    public String getRequestIp()
    {
        if(Utils.isEmpty(requestIp))
            return "127.0.0.1";
        else
            return requestIp;
    }

    public String getResponseEncoding()
    {
        return responseEncoding;
    }

    public String getUrl()
    {
        return url;
    }

    public String getUserAgent()
    {
        return userAgent;
    }

    protected boolean isStoreCookie(String s)
    {
        return true;
    }

    public void setConnectTimeOut(int i)
    {
        connectTimeOut = i;
    }

    public void setContent(String s)
    {
        content = s;
    }

    public void setContentType(String s)
    {
        contentType = s;
    }

    public void setCookie(CookieStore cookiestore)
    {
        cookie = cookiestore;
    }

    public void setHandler(ResponseHandler responsehandler)
    {
        handler = responsehandler;
    }

    public final void setProxy(HttpHost httphost)
    {
        proxy = httphost;
    }

    public void setReadTimeOut(int i)
    {
        readTimeOut = i;
    }

    public void setRequestIp(String s)
    {
        requestIp = s;
    }

    public void setResponseEncoding(String s)
    {
        responseEncoding = s;
    }

    public void setUrl(String s)
    {
        url = s;
    }

    public void setUserAgent(String s)
    {
        userAgent = s;
    }

    private int connectTimeOut;
    private String content;
    private String contentType;
    private CookieStore cookie;
    private ResponseHandler handler;
    private HashMap headers;
    private HttpHost proxy;
    private int readTimeOut;
    private String requestIp;
    private String responseEncoding;
    private String url;
    private String userAgent;
}
