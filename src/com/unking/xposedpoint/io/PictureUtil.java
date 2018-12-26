// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.io;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.unking.xposedpoint.utils.Base64;

import java.io.ByteArrayOutputStream;

public class PictureUtil
{

    public PictureUtil()
    {
    }

    public static String bitmapToString(String s)
    {
        Bitmap bitmap = getSmallBitmap(s);
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bytearrayoutputstream);
        return Base64.encode(bytearrayoutputstream.toByteArray());
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int i, int j)
    {
        int k1;
label0:
        {
            int k = options.outHeight;
            int l = options.outWidth;
            int i1 = 1;
            if(k > j || l > i)
            {
                int j1 = Math.round((float)k / (float)j);
                k1 = Math.round((float)l / (float)i);
                if(j1 >= k1)
                    break label0;
                i1 = j1;
            }
            return i1;
        }
        return k1;
    }

    private static Bitmap getSmallBitmap(String s)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(s, options);
        options.inSampleSize = calculateInSampleSize(options, 555, 987);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(s, options);
    }
}
