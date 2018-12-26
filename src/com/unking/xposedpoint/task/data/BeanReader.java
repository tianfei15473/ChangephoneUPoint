// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class BeanReader
{

    public BeanReader(String s)
    {
        out = null;
        file = s;
    }

    public void close()
    {
        try
        {
            out.close();
            return;
        }
        catch(Exception exception)
        {
            return;
        }
    }

    public List read()
        throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        out = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        ArrayList arraylist = new ArrayList();
        do
        {
            String s1;
            do
            {
                String s = out.readLine();
                if(s == null)
                    return arraylist;
                s1 = s.trim();
            } while(s1.length() == 0);
            int i = s1.indexOf(":");
            Bean bean = (Bean)Class.forName(s1.substring(0, i)).newInstance();
            bean.properties(s1.substring(i + 1));
            arraylist.add(bean);
        } while(true);
    }

    private String file;
    private BufferedReader out;
}
