// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class RandomStringList extends ArrayList
{
    public static interface RandomStringListReader
    {

        public abstract String read(String s);
    }


    public RandomStringList(InputStream inputstream)
    {
        this(inputstream, null);
    }

    public RandomStringList(InputStream inputstream,
                            RandomStringListReader randomstringlistreader) {
        ran = new Random();
        BufferedReader bufferedreader = new BufferedReader(
                new InputStreamReader(inputstream));
        try {
            String s = "";
            while ((s = bufferedreader.readLine()) != null) {
                int i = s.trim().length();
                if (i == 0) {
                    continue;
                }
                try {
                    if (!s.equals("") && !contains(s)) {
                        this.add(s);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedreader != null) {
                try {
                    bufferedreader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    public String random()
    {
        if(isEmpty())
            return null;
        else
            return (String)get(ran.nextInt(size()));
    }

    private static final long serialVersionUID = 0x48053fa03a17dc0dL;
    private Random ran;
}
