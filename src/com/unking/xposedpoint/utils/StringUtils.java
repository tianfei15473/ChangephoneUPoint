// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.utils;


public class StringUtils
{

    public StringUtils()
    {
    }

    public static final String customDecode(String s)
    {
        return  s;
      /*  if(s == null)
        {
            s = "";
        }
        if(s.trim().length() == 0 || s.indexOf("$") < 0){
            return s;
        } {   char ac[];
        StringBuffer stringbuffer;
        int i;
        ac = s.toCharArray();
        stringbuffer = new StringBuffer();
        i = 0;
    }
        char c;
        StringBuffer stringbuffer1;
        int j;
        if(i >= ac.length)
            return stringbuffer.toString();
        c = ac[i];
        if(c != '$')
            break MISSING_BLOCK_LABEL_124;
        stringbuffer1 = new StringBuffer();
        j = 0;
        //------------------
        if(s != null) goto _L2; else goto _L1
_L1:
        s = "";
_L4:
        return s;
_L2:
        if(s.trim().length() == 0 || s.indexOf("$") < 0) goto _L4; else goto _L3
_L3:
        char ac[];
        StringBuffer stringbuffer;
        int i;
        ac = s.toCharArray();
        stringbuffer = new StringBuffer();
        i = 0;
_L7:
        char c;
        StringBuffer stringbuffer1;
        int j;
        if(i >= ac.length)
            return stringbuffer.toString();
        c = ac[i];
        if(c != '$')
            break MISSING_BLOCK_LABEL_124;
        stringbuffer1 = new StringBuffer();
        j = 0;
_L8:
        if(j < 4) goto _L6; else goto _L5
_L5:
        stringbuffer.append((char)Integer.parseInt(stringbuffer1.toString(), 16));
_L9:
        i++;
          goto _L7
_L6:
        i++;
        stringbuffer1.append(ac[i]);
        j++;
          goto _L8
        stringbuffer.append(c);
          goto _L9*/
    }

    public static final String customEncode(String s)
    {
        return  s;
      /*  char ac[];
        StringBuffer stringbuffer;
        int i;
        if(s == null)
            return "";
        ac = s.toCharArray();
        stringbuffer = new StringBuffer();
        i = 0;
_L2:
        char c;
        if(i >= ac.length)
            return stringbuffer.toString();
        c = ac[i];
        if((c < '0' || c > '9') && (c < 'A' || c > 'Z') && (c < 'a' || c > 'z'))
            break; *//* Loop/switch isn't completed *//*
        stringbuffer.append(c);
_L3:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        String s1 = Integer.toHexString(c);
        stringbuffer.append("$");
        if(s1.length() == 1)
            stringbuffer.append("000");
        if(s1.length() == 2)
            stringbuffer.append("00");
        if(s1.length() == 3)
            stringbuffer.append("0");
        stringbuffer.append(s1);
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:*/
    }

    public static final boolean isEmpty(String s)
    {
        return s == null || s.trim().length() == 0;
    }
}
