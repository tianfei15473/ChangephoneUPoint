// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.cmd;

import java.io.InputStream;
import java.io.OutputStream;

public class CommandGobbler
    implements Runnable {


    private InputStream in;
    private OutputStream out;

    public CommandGobbler(InputStream inputstream, OutputStream outputstream) {
        out = outputstream;
        in = inputstream;
    }

    public void run() {
        byte[] arrayOfByte = new byte[1024];
        try {
            while (true) {
                int i;
                i = this.in.read(arrayOfByte);
                if (i > 0) ;
                this.in.close();
                if (this.out == null) {
                    return;
                }
                this.out.write(arrayOfByte, 0, i);
                continue;
            }
        } catch (Exception localException) {
            localException.printStackTrace();
            this.in = null;
            //   this.out.close();
        }


    }
}


