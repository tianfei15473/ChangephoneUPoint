// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.cmd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.util.Log;

public class CommandExec
{

    public CommandExec()
    {
    }

   public synchronized String execute(String as[]) {
        int i = 0;
        ProcessBuilder processbuilder = new ProcessBuilder(new String[]{
                "/system/bin/sh"
        });
        processbuilder.directory(new File("/"));
        Process process;
        BufferedReader bufferedreader;
        PrintWriter printwriter;
        int j;
        String s;
        try {
            process = processbuilder.start();
            bufferedreader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            printwriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(process.getOutputStream())), true);
            printwriter.println("su");
            printwriter.println("export LD_LIBRARY_PATH=/vendor/lib:/system/lib");
            j = as.length;
        } catch (Exception exception) {
            exception.printStackTrace();
            return exception.getMessage();
        }

        while (i < j) {
            printwriter.println(as[i]);
            i++;
        }
        printwriter.println("exit");
        printwriter.close();

        try {
            while ((s = bufferedreader.readLine()) != null) {
               // LLogger.info("CommandExec Info", s, new Object[0]);
                Log.d("feitian-exec", s);
            }


            bufferedreader.close();

            try {
                if (process != null) {
                    // use exitValue() to determine if process is still running.
                    //process.exitValue();
                	process.waitFor();  
                }
            } catch (IllegalThreadStateException e) {
                // process is still running, kill it.
            	/*if(process != null)
            		process.destroy();*/
            	
            	e.printStackTrace();
            }
            return null;

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        
        /*Shell.getInstance().point(as);
        
        return null;*/
    }

    public int getFlag()
    {
        return flag;
    }

    public Object getMessage()
    {
        return message;
    }

    private int flag;
    private Object message;
}
