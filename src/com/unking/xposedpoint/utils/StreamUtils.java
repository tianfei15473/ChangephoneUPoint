// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public final class StreamUtils
{

    private StreamUtils()
    {
    }

    public static final void close(InputStream inputstream) {
        try {
            if (inputstream != null) {
                inputstream.close();
            }
        } catch (Exception e) {
        }
    }

    public static final void close(OutputStream outputstream) {
        try {
            if (outputstream != null) {
                outputstream.close();
            }
        } catch (Exception e) {
        }
    }

    public static final void close(Reader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (Exception e) {
        }
    }

    public static final void close(Writer writer) {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (Exception e) {
        }
    }

    public static final void closeAfterWrite(OutputStream outputstream, InputStream inputstream)
        throws Exception {
        try {
            byte abyte0[] = new byte[1024];
            int i = 0;
            while ((i = inputstream.read(abyte0)) > 0) {
                outputstream.write(abyte0, 0, i);
            }
            close(inputstream);
            close(outputstream);
        } catch (Exception e) {
            throw e;
        }

    }

    public static final void write(OutputStream outputstream, InputStream inputstream)
        throws Exception {
        if (inputstream != null) {
            byte abyte0[] = new byte[1024];
            int i = 0;
            while ((i = inputstream.read(abyte0)) > 0) {
                try {
                    outputstream.write(abyte0, 0, i);
                } catch (Exception exception) {
                    throw exception;
                }
            }
        }
    }
}
