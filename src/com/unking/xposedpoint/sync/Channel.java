// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.sync;

import com.unking.xposedpoint.utils.Base64;
import com.unking.xposedpoint.utils.StreamUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;


public class Channel
{

    public Channel()
    {
    }

    private void writeln(byte abyte0[])
        throws IOException
    {
        if(abyte0 == null)
        {
            return;
        } else
        {
            out.write(Base64.encode((new Des()).encrypt(abyte0)));
            out.write("\n");
            out.flush();
            return;
        }
    }

    public void over()
        throws IOException
    {
        writeln("over");
    }

    public String readLine()
        throws Exception
    {
        String s = in.readLine();
        String s1;
        if(s == null)
        {
            s1 = null;
        } else
        {
            s1 = new String((new Des()).decrypt(Base64.decode(s)));
            if("over".equalsIgnoreCase(s1) || "heartpackage".equalsIgnoreCase(s1))
                return null;
        }
        return s1;
    }

    public void writeln(File file)
        throws Exception
    {
        Object obj = null;
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        while (true) {
            try {
                String s = bufferedreader.readLine();
                if (s == null) {
                    StreamUtils.close(bufferedreader);
                    return;
                }
                if (s.trim().length() == 0) {
                    break;
                } else {
                    writeln(s.getBytes());
                }
            }
            catch (Exception exception)
            {
                obj = bufferedreader;
                StreamUtils.close(((Reader) (obj)));
                obj = null;
            }

        }


    }

    public void writeln(String s)
        throws IOException
    {
        writeln(s.getBytes());
    }

    protected BufferedReader in;
    protected BufferedWriter out;
}
