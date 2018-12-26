// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.data;

import java.lang.reflect.Method;

public class Bean
{

    public Bean()
    {
    }

    public Bean(String s)
    {
        properties(s);
    }

    public void properties(String paramString)
    {
        int i = 0;
        String[] arrayOfString1 = paramString.split("[,]");
        int j = arrayOfString1.length;
        if (i >= j)
            return;
        String[] arrayOfString2 = arrayOfString1[i].split("[=]");
        if (arrayOfString2.length != 2);
        while (true)
        {
            i++;

            try
            {
                Method localMethod = getClass().getMethod("set" + arrayOfString2[0], new Class[] { String.class });
                Object[] arrayOfObject = new Object[1];
                arrayOfObject[0] = arrayOfString2[1];
                localMethod.invoke(this, arrayOfObject);
            }
            catch (Exception localException)
            {
                break;
            }
        }
    }

    public String toString()
    {
        int i = 0;
        StringBuffer localStringBuffer = new StringBuffer();
        Method[] arrayOfMethod = getClass().getDeclaredMethods();
        int j = arrayOfMethod.length;
        while (true)
        {
            if (i >= j)
                return localStringBuffer.toString();
            Method localMethod = arrayOfMethod[i];
            try
            {
                String str1 = localMethod.getName();
                if ((str1.startsWith("get")) && ((localMethod.getParameterTypes() == null) || (localMethod.getParameterTypes().length <= 0)) && (localMethod.getReturnType().equals(String.class)))
                {
                    String str2 = str1.substring(3);
                    String str3 = (String)localMethod.invoke(this, new Object[0]);
                    if ((str3 != null) && (str3.trim().length() != 0))
                        localStringBuffer.append(str2).append("=").append(str3).append(",");
                }
            }
            catch (Exception localException)
            {
            }
            i++;
        }
    }


}
