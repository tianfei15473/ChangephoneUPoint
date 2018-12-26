// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.motion.fp;

import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.utils.StreamUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public final class FingerprintFactory
{

    private FingerprintFactory()
    {
        map = new LinkedHashMap();
    }

    public static final FingerprintFactory getFactory()
    {
        if(FACTORY.map.isEmpty())
            FACTORY.init();
        return FACTORY;
    }

    private void init()
    {
        load("zte.u985.properties");
        load("zte.u985.download.properties");
    }

    private void load(String s)
    {
        try
        {
            File file_prop = new File((new StringBuilder("/sdcard-ext/point/")).append("/prop/").append(s).toString());
            BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file_prop)));
            while (true)
            {
                try
                {
                    String str = localBufferedReader.readLine();
                    if (str == null)
                    {
                        StreamUtils.close(localBufferedReader);
                        return;
                    }
                    if (str.trim().length() == 0)
                        continue;
                    Fingerprint localFingerprint = new Fingerprint(str);
                    this.map.put(localFingerprint.getName(), localFingerprint);
                    continue;
                }
                catch (Exception localException)
                {
                    localException.printStackTrace();
                    LLogger.error("factory.init", localException);
                    break;
                }

            }
        }
        catch (Exception localException2)
        {
            localException2.printStackTrace();;
        }
    }

    public static final int match(String s)
    {
        return getFactory().getFingerprint(s).match();
    }

    public static final int print(String s)
    {
        return getFactory().getFingerprint(s).print();
    }

    public Fingerprint getFingerprint(String s)
    {
        return (Fingerprint)map.get(s);
    }

    private static final FingerprintFactory FACTORY = new FingerprintFactory();
    private LinkedHashMap map;

}
