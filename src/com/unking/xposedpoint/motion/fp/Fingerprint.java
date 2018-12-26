// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.motion.fp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.motion.action.Command;
import com.unking.xposedpoint.motion.zte.u985.ScreenAction;
import com.unking.xposedpoint.utils.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;

public class Fingerprint
{

    public Fingerprint()
    {
        filePath = root + "/screen.png";
        sum = 0;
    }

    public Fingerprint(String s)
    {
        filePath = root + "/screen.png";
        sum = 0;
        String as[] = s.split(",");
        name = as[0];
        x = Integer.parseInt(as[1]);
        y = Integer.parseInt(as[2]);
        w = Integer.parseInt(as[3]);
        h = Integer.parseInt(as[4]);
        content = as[5];
    }

    private String computeBits(int ai[], int i)
    {
        char ac[] = new char[ai.length];
        int j = 0;
        do
        {
            if(j >= ai.length)
                return new String(ac);
            if(ai[j] < i)
                ac[j] = '0';
            else
                ac[j] = '1';
            j++;
        } while(true);
    }

    private int distance(String s, String s1)
    {
        int i = 0;
        int j = 0;
        LLogger.debug(name, s1, new Object[0]);
        do
        {
            if(j >= s.length())
                if(j == 0)
                    return 100;
                else
                    return Math.round(100F * ((float)i / (float)j));
            if(s.charAt(j) == s1.charAt(j))
                i++;
            j++;
        } while(true);
    }

    private String getFingerprint(String s)
    {
        String s1;
        try
        {
            FileInputStream fileinputstream = new FileInputStream(s);
            Bitmap bitmap = BitmapFactory.decodeStream(fileinputstream);
            StreamUtils.close(fileinputstream);
            Bitmap bitmap1 = Bitmap.createBitmap(bitmap, x, y, w, h);
            saveToFile(new Bitmap[] {
                bitmap1
            });
            Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap1, 32, 32, false);
            sum = 0;
            int ai[] = reduceColor(bitmap2);
            s1 = computeBits(ai, sum / ai.length);
        }
        catch(Exception exception)
        {
            Log.e("getFingerprint", "error", exception);
            return "false";
        }
        return s1;
    }

    private int[] reduceColor(Bitmap paramBitmap)
    {
        this.sum = 0;
        int i = paramBitmap.getWidth();
        int j = paramBitmap.getHeight();
        Log.i("th", "scaled bitmap's width*heith:" + i + "*" + j);
        int[] arrayOfInt1 = new int[i * j];
        int[] arrayOfInt2 = new int[i * j];
        paramBitmap.getPixels(arrayOfInt2, 0, i, 0, 0, i, j);
        int k = 0;
        if (k >= i)
            return arrayOfInt1;
        for (int m = 0; ; m++)
        {
            if (m >= j)
            {
                k++;
                break;
            }
            int n = k + m * i;
            int i1 = 0xFF & arrayOfInt2[n] >> 16;
            int i2 = 0xFF & arrayOfInt2[n] >> 8;
            int i3 = 0xFF & arrayOfInt2[n];
            int i4 = (i1 * 30 + i2 * 59 + i3 * 11) / 100;
            this.sum = (i4 + this.sum);
            arrayOfInt1[n] = i4;
        }
        return arrayOfInt1;
    }

    private  void saveToFile(Bitmap paramArrayOfBitmap[])
    {
        int i = paramArrayOfBitmap.length;
        int j = 0;
        int k = 0;
        while (true)
        {
            if (j >= i)
                return;
            Bitmap localBitmap = paramArrayOfBitmap[j];
            new File(root + "/vnc").mkdirs();
            StringBuilder localStringBuilder = new StringBuilder(String.valueOf(root)).append("/vnc/a");
            int m = k + 1;
            File localFile = new File(k + ".png");
            FileOutputStream localFileOutputStream =null;
            try
            {
                localFileOutputStream = new FileOutputStream(localFile);
                localBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localFileOutputStream);
            }
            catch (Exception localException)
            {
                try
                {
                    localFileOutputStream.flush();
                    localFileOutputStream.close();
                    j++;
                    k = m;
                  //  continue;
                    localException = localException;
                    Log.e("saveToFile", "error", localException);
                    FileOutputStream localFileOutputStream2 = null;
                }
                catch (IOException localIOException)
                {
                    while (true)
                        Log.e("saveToFile", "error", localIOException);
                }
            }
        }
    }

    private void screen()
    {
        (new ScreenAction()).handle(Command.build("screen " + filePath), null);
    }

    public String getContent()
    {
        return content;
    }

    public int getH()
    {
        return h;
    }

    public String getName()
    {
        return name;
    }

    public int getW()
    {
        return w;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int match()
    {
        screen();
        int i = distance(content, getFingerprint(filePath));
        String s = name;
        Object aobj[] = new Object[2];
        aobj[0] = toString();
        aobj[1] = Integer.valueOf(i);
        LLogger.debug(s, "{0}--Match--{1}", aobj);
        return i;
    }

    public int print()
    {
        int i = distance(content, getFingerprint(filePath));
        String s = name;
        Object aobj[] = new Object[2];
        aobj[0] = toString();
        aobj[1] = Integer.valueOf(i);
        LLogger.debug(s, "{0}--Match--{1}", aobj);
        return i;
    }

    public String toString()
    {
        Object aobj[] = new Object[5];
        aobj[0] = name;
        aobj[1] = Integer.valueOf(x);
        aobj[2] = Integer.valueOf(y);
        aobj[3] = Integer.valueOf(w);
        aobj[4] = Integer.valueOf(h);
        return MessageFormat.format("{0}({1},{2},{3},{4})", aobj);
    }

    public static String root = "/sdcard-ext";
    private String content;
    private String filePath;
    private int h;
    private String name;
    private int sum;
    private int w;
    private int x;
    private int y;

}
