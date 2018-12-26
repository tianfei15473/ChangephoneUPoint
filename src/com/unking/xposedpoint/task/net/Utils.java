// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.net;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utils
{

    private Utils()
    {
    }

    public static final String formatDate(Date date, String s)
    {
        return (new SimpleDateFormat(s, Locale.CHINA)).format(date);
    }

    public static  String formatString(String s, Object aobj[])
    {
        if(isEmpty(s))
            s = "";
        else
        if(!isEmpty(((Object) (aobj))))
        {
            Matcher matcher = Pattern.compile("[{][0-9]+[}]").matcher(s);
            StringBuffer stringbuffer = new StringBuffer();
            do
            {
                if(!matcher.find())
                {
                    matcher.appendTail(stringbuffer);
                    return stringbuffer.toString();
                }
                matcher.appendReplacement(stringbuffer, Matcher.quoteReplacement(aobj[Integer.parseInt(matcher.group().replaceAll("[{]|[}]", ""))].toString()));
            } while(true);
        }
        return s;
    }

    public static final boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            return "".equals(((String) obj).trim());
        }
        if ((obj instanceof String[])) {
            if (((String[]) obj).length != 0) {
                return false;
            }
        }
        if (obj instanceof List) {
            if (((List) obj).size() != 0) {
                return false;
            }
        } else if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        } else {
            return false;
        }
        return true;
    }

    public static final boolean isEmpty(String s)
    {
        return s == null || s.length() == 0;
    }

    public static final boolean isFalse(String s)
    {
        return "false".equalsIgnoreCase(s);
    }

    public static final boolean isTrue(Object obj)
    {
        if(obj instanceof String)
            return "true".equalsIgnoreCase((String)obj);
        if(obj instanceof Boolean)
            return ((Boolean)obj).booleanValue();
        else
            return false;
    }

    public static final boolean isTrue(String s)
    {
        return "true".equalsIgnoreCase(s);
    }

    public static final Date parseDate(String s, String s1)
        throws ParseException
    {
        return (new SimpleDateFormat(s1, Locale.CHINA)).parse(s);
    }

    public static final int parseInt(String s, int i)
    {
        int j;
        try
        {
            j = Integer.parseInt(s);
        }
        catch(Exception exception)
        {
            return i;
        }
        return j;
    }
    
    //24小时 + 10分钟
    public static final boolean isAfter24Hour(String starttime) {
    	Date nowtime = new Date();
    	if(nowtime.getTime() - Long.parseLong(starttime) > 87000000)
    	//if(nowtime.getTime() - Long.parseLong(starttime) > 10000)
    		return true;
    	else 
    		return false;
    }
}
