// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.impl;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.task.Motion;
import com.unking.xposedpoint.task.net.Ajax;
import com.unking.xposedpoint.task.net.GetRequest;
import com.unking.xposedpoint.task.net.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class Search360Task extends DefaultTask
{

    public Search360Task()
    {
        ran = new Random();
        words = new ArrayList();
    }

    private void loadSearchWord()
    {
        GetRequest localGetRequest = new GetRequest("http://api.yi18.net/top/list");
        int i;
        int j;
        do
            try
            {
                JSONArray localJSONArray = new JSONObject(Ajax.get(localGetRequest)).getJSONArray("yi18");
                i = localJSONArray.length();
                j = 0;
                String str = localJSONArray.getJSONObject(j).getString("keywords");
                if (!this.words.contains(str))
                    this.words.add(str);
                j++;
            }
            catch (Exception localException)
            {
                LLogger.error("error", localException);
                break;
            }
        while (j < i);
    }

    private String randomString()
    {
        if(words.size() > 0)
            return (String)words.get(ran.nextInt(words.size()));
        int i = ran.nextInt(10);
        StringBuffer stringbuffer = new StringBuffer();
        int j = 0;
        do
        {
            if(j >= i)
                return stringbuffer.toString();
            int k = ran.nextInt(42191);
            if(k < 11904)
                k += 11904;
            stringbuffer.append((char)k);
            j++;
        } while(true);
    }

    protected void run(Apk apk)
    {
    }

    protected void search()
    {
        Motion.touch(804, 163, 2000);
        Motion.send(randomString(), 2000);
        Motion.touch(1043, 191, 2000);
    }

    protected void start(Apk apk)
    {
        Motion.sleep(5000L);
        Motion.touch(353, 516, 3000);
        int i = Utils.parseInt(apk.getResidenceTime(), 0) / 1000;
        loadSearchWord();
        do
        {
            if(i <= 0)
                return;
            int j = Math.max(ran.nextInt(12), 6);
            search();
            Motion.random(j);
            i -= j;
        } while(true);
    }

    private Random ran;
    private ArrayList words;
}
