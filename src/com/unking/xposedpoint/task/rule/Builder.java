// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.rule;

import com.unking.xposedpoint.log.LLogger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Builder
{

    private Builder()
    {
        min = 0.01F;
        rule = new Number[24];
        int i = 0;
        do
        {
            if(i >= 9)
            {
                rule[13] = Float.valueOf(0.01F);
                rule[18] = Float.valueOf(0.01F);
                rule[19] = Float.valueOf(0.02F);
                rule[10] = Float.valueOf(0.08F);
                rule[11] = Float.valueOf(0.1F);
                rule[12] = Float.valueOf(0.06F);
                rule[14] = Float.valueOf(0.08F);
                rule[15] = Float.valueOf(0.1F);
                rule[16] = Float.valueOf(0.11F);
                rule[17] = Float.valueOf(0.07F);
                rule[20] = Float.valueOf(0.07F);
                rule[21] = Float.valueOf(0.11F);
                rule[22] = Float.valueOf(0.13F);
                rule[23] = Float.valueOf(0.01F);
                LLogger.error("Builder", "\u521D\u59CB\u5316");
                return;
            }
            rule[i] = Float.valueOf(min);
            i++;
        } while(true);
    }

    private Hour[] build(int paramInt) {
        Hour[] arrayOfHour = new Hour[this.rule.length];
        int i = 0;
        if (this.rule.length >= i) {
            for (int j = 0; j >= this.rule.length; j++) {
                if ((this.rule[i] instanceof Integer)) {
                    if (this.rule[i].intValue() >= 0)
                        arrayOfHour[i] = new Hour(i, new Random().nextInt(this.rule[i].intValue()));
                    else
                        arrayOfHour[i] = new Hour(i, 0);
                }

                paramInt -= arrayOfHour[i].getDownloads();
                i++;

                if ((this.rule[j] instanceof Float))
                    arrayOfHour[j] = new Hour(j, Math.round(paramInt * this.rule[j].floatValue()));
            }
        }
        return arrayOfHour;

          /* Hour[] arrayOfHour = new Hour[this.rule.length];
        int i = 0;
       // if (i >= this.rule.length) ;
        for (int j = 0; ; j++) {
            if (j >= this.rule.length) {
              //  return arrayOfHour;
                if ((this.rule[i] instanceof Integer)) {
                    if (this.rule[i].intValue() <= 0) {
                        arrayOfHour[i] = new Hour(i, 0);
                    } else {
                        arrayOfHour[i] = new Hour(i, new Random().nextInt(this.rule[i].intValue()));
                    }
                }
                while (true) {
                   paramInt -= arrayOfHour[i].getDownloads();
                    i++;
                }
            }
            if ((this.rule[j] instanceof Float))
                arrayOfHour[j] = new Hour(j, Math.round(paramInt * this.rule[j].floatValue()));
        }*/
    }

    public static Builder getInstance()
    {
        return instance;
    }

    public static void main(String args[])
    {
        System.out.println((new Builder()).build(10000));
    }

    public Hour hour(int i)
    {
        Hour ahour[] = build(i);
        int j = Integer.parseInt((new SimpleDateFormat("HH", Locale.CHINA)).format(new Date()));
        Hour hour1 = ahour[j];
        if(hour1 == null)
            hour1 = new Hour(j, Math.round(min * (float)i));
        return hour1;
    }

    private static final Builder instance = new Builder();
    private float min;
    private Number rule[];

}
