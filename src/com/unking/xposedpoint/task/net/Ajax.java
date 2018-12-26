// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.net;

import com.unking.xposedpoint.log.LLogger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;

public final class Ajax
{

    private Ajax()
    {
    }

    private String _get(Request request)
        throws Exception {
        StringBuffer stringbuffer = new StringBuffer();
        DefaultHttpClient defaulthttpclient = null;
        try {
            defaulthttpclient = new DefaultHttpClient(createParams(request));
            String s = request.getRequestIp();
            if (s == null) {
                return "";//why? (add by yaosansi 20141213
            }
            String s1 = request.getUrl();
            if (s1.indexOf("?") <= 0) {
                s1 = s1 + "?" + request.getContent();
            } else {
                s1 = s1 + "&" + request.getContent();
            }
            if (Utils.isEmpty(request.getContent())) {
                HttpGet httpget = new HttpGet(s1);
                httpget.setHeader("Content-Type", request.getContentType());
                httpget.setHeader("x-forwarded-for", s);
                httpget.setHeader("Proxy-Client-IP", s);
                httpget.setHeader("WL-Proxy-Client-IP", s);
                httpget.setHeader("HTTP_CLIENT_IP", s);
                Iterator iterator = request.getHeadersName().iterator();
                while (iterator.hasNext()) {
                    String s2 = (String) iterator.next();
                    httpget.setHeader(s2, request.getHeader(s2));
                }
                if (request.getCookie() != null)
                    defaulthttpclient.setCookieStore(request.getCookie());
                HttpResponse httpresponse = defaulthttpclient.execute(httpget);
                if (request.getHandler() != null)
                    stringbuffer.append(request.getHandler().handle(httpresponse.getEntity(), httpresponse.getStatusLine(), request.getResponseEncoding()));
                request.setCookie(defaulthttpclient.getCookieStore());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            defaulthttpclient.getConnectionManager().shutdown();
        }
        return stringbuffer.toString();
    }

    private String _post(Request request) {
        StringBuffer stringbuffer = new StringBuffer();
        DefaultHttpClient defaulthttpclient = null;
        try {
            defaulthttpclient = new DefaultHttpClient(createParams(request));
            String s = request.getRequestIp();
            if (s == null)
                return "";// why ? (add by yaosansi 20141213
            HttpPost httppost = new HttpPost(request.getUrl());
            httppost.setHeader("Content-Type", request.getContentType());
            httppost.setHeader("x-forwarded-for", s);
            httppost.setHeader("Proxy-Client-IP", s);
            httppost.setHeader("WL-Proxy-Client-IP", s);
            httppost.setHeader("HTTP_CLIENT_IP", s);
            httppost.setHeader("user-agent", request.getUserAgent());

            Iterator iterator = request.getHeadersName().iterator();
            while (iterator.hasNext()) {
                String s1 = (String) iterator.next();
                httppost.setHeader(s1, request.getHeader(s1));
            }
            if (request.getContent() != null) {
                httppost.setEntity(new StringEntity(request.getContent(), "UTF-8"));
            }
            if (request.getCookie() != null) {
                defaulthttpclient.setCookieStore(request.getCookie());
            }
            HttpEntity httpentity = defaulthttpclient.execute(httppost).getEntity();
            if (httpentity == null) {
                if (request.isStoreCookie(stringbuffer.toString())) {
                    request.setCookie(defaulthttpclient.getCookieStore());
                }
            } else {
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(httpentity.getContent(), request.getResponseEncoding()));
                String s2 = "";
                while ((s2 = bufferedreader.readLine()) != null) {
                    if (stringbuffer.length() > 0) {
                        stringbuffer.append("\n\r");
                        stringbuffer.append(s2);
                    }
                }
            }
        } catch (Exception e) {
            LLogger.error("Ajax", e);
        } finally {
            defaulthttpclient.getConnectionManager().shutdown();
        }
        return stringbuffer.toString();
    }

    private HttpParams createParams(Request request)
    {
        BasicHttpParams basichttpparams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basichttpparams, request.getConnectTimeOut());
        HttpConnectionParams.setSoTimeout(basichttpparams, request.getReadTimeOut());
        HttpProtocolParams.setContentCharset(basichttpparams, request.getResponseEncoding());
        if(request.getProxy() != null)
            basichttpparams.setParameter("http.route.default-proxy", request.getProxy());
        return basichttpparams;
    }

    public static String get(Request request)
        throws Exception
    {
        return AJAX._get(request);
    }

    public static void main(String args[])
        throws Exception
    {
        GetRequest getrequest = new GetRequest("http://reg.jiayuan.com/signup/fillbasic.php?bd=55");
        getrequest.setRequestIp("100.45.34.200");
        getrequest.setResponseEncoding("utf-8");
        System.out.println(get(getrequest));
    }

    public static String post(Request request)
    {
        return AJAX._post(request);
    }

    private static final Ajax AJAX = new Ajax();

}
