// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.data;

import java.util.Random;

public class AndroidSdkRandom
{
    public class Sdk
    {

        public int api;
        public String displayId;
        public String release;
        final AndroidSdkRandom this$0;

        private Sdk(int i, String s, String s1)
        { super();
            this$0 = AndroidSdkRandom.this;

            api = i;
            release = s;
            displayId = s1;
        }

        Sdk(int i, String s, String s1, Sdk sdk1)
        {
            this(i, s, s1);
        }
    }


    private AndroidSdkRandom()
    {
        Sdk asdk[] = new Sdk[17];
        
        asdk[0] = new Sdk(15, "4.0.4", "MocorDroid2.3.5", null);
        asdk[1] = new Sdk(15, "4.0.3", "IMM76L", null);
        asdk[2] = new Sdk(15, "4.0.3", "IMM76D", null);
        asdk[3] = new Sdk(15, "4.0.3", "IMM76I", null);
        asdk[4] = new Sdk(14, "4.0.2", "GRK39F", null);
        asdk[5] = new Sdk(14, "4.0.2", "MocorDroid2.3.5", null);
        asdk[6] = new Sdk(16, "4.1.1", "MocorDroid2.3.5", null);
        asdk[7] = new Sdk(16, "4.1.2", "JZO54K", null);
        asdk[8] = new Sdk(17, "4.2.0", "IML74K", null);
        asdk[9] = new Sdk(17, "4.2.1", "MocorDroid2.3.5", null);
        asdk[10] = new Sdk(17, "4.2.2", "JDQ39", null);
        asdk[11] = new Sdk(17, "4.2.3", "IML74K", null);
        asdk[12] = new Sdk(18, "4.3.0", "MocorDroid2.3.5", null);
        asdk[13] = new Sdk(18, "4.3.1", "JDQ39", null);
        asdk[14] = new Sdk(18, "4.3.2", "MocorDroid2.3.5", null);
        asdk[15] = new Sdk(19, "4.4.3", "JDQ39", null);
        asdk[16] = new Sdk(19, "4.4.4", "AD519_M41_OLD_Q7", null);
        sdks = asdk;
    }

    public static final AndroidSdkRandom getInstance()
    {
        return RANDOM;
    }

    public void random()
    {
        sdk = sdks[(new Random()).nextInt(sdks.length)];
    }

    private static final AndroidSdkRandom RANDOM = new AndroidSdkRandom();
    public Sdk sdk;
    private Sdk sdks[];

}
