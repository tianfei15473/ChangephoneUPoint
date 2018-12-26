// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.sync;

import com.unking.xposedpoint.Global;
import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.data.ApkQueue;
import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.task.net.Utils;
import com.unking.xposedpoint.utils.Base64;
import com.unking.xposedpoint.utils.StreamUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

public class SyncRunner extends Thread
{
    public SyncRunner() {
        super();
        running = false;
        setDaemon(true);
    }

    private void download(SyncChannel syncchannel)
        throws Exception {
        String s;
        Iterator iterator;
        s = (new SimpleDateFormat("yyyyMMdd", Locale.getDefault())).format(new Date());
        iterator = ApkQueue.getInstance().runall().iterator();
        while (iterator.hasNext()) {
            String s1;
            File file;
            int i;
            Apk apk = (Apk) iterator.next();
            s1 = (new StringBuilder("/sdcard-ext/point/")).append(Global.PHONE).append(apk.getPackageName()).append("/point_remain.").append(s).append(".log").toString();
            file = new File(s1);
            syncchannel.writeln((new StringBuilder("cmd:file ")).append(s1).toString());
            i = Utils.parseInt(syncchannel.readLine(), -1);
            if (i < 0) {
                break;
            }
            Object obj;
            obj = null;
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            FileOutputStream fileoutputstream = new FileOutputStream(file);
            try {
                syncchannel.writeln((new StringBuilder("cmd:get ")).append(s1).toString());
                if ((!file.exists() || file.length() == (long) i) && file.exists()) {
                    StreamUtils.close(fileoutputstream);
                } else {
                    String s2 = null;
                    while ((s2 = syncchannel.readLine()) != null) {
                        if (s2.trim().length() <= 0) {
                            continue;
                        }
                        fileoutputstream.write(s2.getBytes());
                        fileoutputstream.write("\n".getBytes());
                    }

                    StreamUtils.close(fileoutputstream);


                }
            } catch (Exception e) {
                throw e;
            }
        }



    }

    private void update(SyncChannel syncchannel)
        throws Exception
    {
        String s;
        File file;
        int i;
        Object obj;
        s = (new StringBuilder(String.valueOf(Global.PHONE))).append("/point_apk.xml").toString();
        file = new File((new StringBuilder("/sdcard-ext/point/")).append(s).toString());
        syncchannel.writeln((new StringBuilder("cmd:file ")).append(s).toString());
        String s1 = syncchannel.readLine();
        i = Utils.parseInt(s1, -1);
        if(i < 0)
            throw new Exception(s1);
        obj = null;
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        FileOutputStream fileoutputstream = new FileOutputStream(file);
        try {
            syncchannel.writeln((new StringBuilder("cmd:apk ")).append(s).toString());
            if (file.exists() && file.length() != (long) i || !file.exists())
                fileoutputstream.write(Base64.decode(syncchannel.readLine()));
            StreamUtils.close(fileoutputstream);
            ApkQueue.getInstance().load();
        }
        catch (Exception exception)
        {
            throw exception;
        }
        finally {
            StreamUtils.close(((OutputStream) (obj)));
        }

    }

    private void upload(SyncChannel syncchannel)
        throws Exception
    {
        String s = (new SimpleDateFormat("yyyyMMdd", Locale.getDefault())).format(new Date());
        final String fn = (new StringBuilder("point_run.")).append(s).append(".log").toString();
        Iterator iterator = ApkQueue.getInstance().runall().iterator();
        do
        {
            if(!iterator.hasNext())
                return;
            Apk apk = (Apk)iterator.next();
            File afile[] = (new File((new StringBuilder("/sdcard-ext/point/")).append(Global.PHONE).append("/").append(apk.getPackageName()).toString())).listFiles(new FilenameFilter() {

                public boolean accept(File file, String s1)
                {
                    LLogger.error("file", s1);
                    return s1.startsWith("point_run.") && s1.endsWith(".log") && s1.compareToIgnoreCase(fn) < 0;
                }
            }
);
            int i = afile.length;
            int j = 0;
            while(j < i) 
            {
                upload(syncchannel, apk, afile[j]);
                j++;
            }
        } while(true);
    }

    private void upload(SyncChannel syncchannel, Apk apk, File file)
        throws Exception
    {
        String s = (new StringBuilder(String.valueOf(Global.PHONE))).append("/").append(apk.getPackageName()).append("/").append(file.getName()).toString();
        syncchannel.writeln((new StringBuilder("cmd:put ")).append(s).toString());
        syncchannel.writeln(file);
        syncchannel.over();
    }

    public void interrupt()
    {
        running = false;
        LLogger.error("SyncRunner", "interrupt");
        super.interrupt();
    }

    public synchronized void run()
    {
        running = true;
        SyncChannel syncchannel = new SyncChannel();
        try {
           while (running) {
                syncchannel.connect();
                upload(syncchannel);
                update(syncchannel);
                download(syncchannel);
                syncchannel.writeln("exit");
                wait(0x36ee80L);
                syncchannel.disconnect();
                wait(60000L);
            }
        }catch (Exception e)
        {
            LLogger.error("Sync Error", e);
            syncchannel.disconnect();
        }

    }

    private boolean running;
}
