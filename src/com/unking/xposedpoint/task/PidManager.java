// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task;

import android.util.Log;

import com.unking.xposedpoint.cmd.CommandExec;
import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.utils.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PidManager
{

    public PidManager()
    {
    }

    private List ps() {
        BufferedReader bufferedreader;
        ArrayList arraylist = new ArrayList();
        Process process;
        PrintWriter printwriter;
        String s;
        try {
            ProcessBuilder processbuilder = new ProcessBuilder(new String[]{
                    "/system/bin/sh"
            });
            processbuilder.directory(new File("/"));
            process = processbuilder.start();
        } catch (Exception exception) {
            Log.e("error", "error", exception);
            return arraylist;
        }
        bufferedreader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        printwriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(process.getOutputStream())), true);
        printwriter.println("su");
        printwriter.println("ps");
        printwriter.println("exit");
        printwriter.close();

        try {
            while ((s = bufferedreader.readLine()) != null) {
                if (s.startsWith("USER")) {
                    s = bufferedreader.readLine();
                    if (s != null) {
                        continue;
                    } else {
                        arraylist.add(new Pid(s));
                    }
                }
            }
        } catch (Exception e) {
            process.destroy();
        }
        return arraylist;
    }

    public void kill(String s)
    {
        List list = ps();
        CommandExec commandexec = new CommandExec();
        do
        {
            Pid pid;
            do
            {
                if(list.isEmpty())
                {
                    Motion.sleep(1000L);
                    return;
                }
                pid = (Pid)list.remove(-1 + list.size());
            } while(StringUtils.isEmpty(pid.getPackageName()) || StringUtils.isEmpty(pid.getPid()) || pid.getPackageName().indexOf(s) < 0);
            try
            {
                String as[] = new String[1];
                as[0] = (new StringBuilder("kill -15 ")).append(pid.getPid()).toString();
                commandexec.execute(as);
            }
            catch(Exception exception)
            {
                Object aobj[] = new Object[2];
                aobj[0] = pid.getPid();
                aobj[1] = pid.getPackageName();
                LLogger.error("kill pid", "{0} : {1} ", aobj);
            }
        } while(true);
    }

    public void kill3(String s)
    {
        kill(s);
        kill(s);
        kill(s);
    }
}
