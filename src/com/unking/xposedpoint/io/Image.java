// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.io;

import com.unking.xposedpoint.utils.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class Image
{

    public Image()
    {
    }

    public String encode(File file)
        throws Exception {
        FileInputStream fileinputstream;
        byte abyte0[];
        ByteArrayOutputStream bytearrayoutputstream;
        fileinputstream = null;
        abyte0 = new byte[1024];
        bytearrayoutputstream = new ByteArrayOutputStream();
        FileInputStream fileinputstream1 = new FileInputStream(file);
        String s = null;
        while (true) {
            try {
                int i = fileinputstream1.read(abyte0);
                if (i > 0) break;
                bytearrayoutputstream.write(abyte0, 0, i);
            } catch (Exception exception) {

                if (fileinputstream1 != null)
                    try {
                        fileinputstream1.close();
                    } catch (Exception exception3) {
                        return s;
                    }

            }

        }
        s = Base64.encode(bytearrayoutputstream.toByteArray());
        return s;
    }
}
