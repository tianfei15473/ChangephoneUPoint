// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.data;

import java.io.FileOutputStream;
import java.io.IOException;


public class BeanWriter
{

    public BeanWriter(String s)
    {
        this(s, false);
    }

    public BeanWriter(String s, boolean flag)
    {
        out = null;
        file = s;
        append = flag;
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

    public  void write(Bean abean[])
        throws IOException
    {
        if(out == null)
            out = new FileOutputStream(file, append);
        int i = abean.length;
        int j = 0;
        do
        {
            if(j >= i)
                return;
            Bean bean = abean[j];
            out.write(bean.getClass().getName().getBytes());
            out.write(":".getBytes());
            out.write(bean.toString().getBytes());
            out.write("\n".getBytes());
            j++;
        } while(true);
    }

    private boolean append;
    private String file;
    private FileOutputStream out;
}
