// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.data;

import com.unking.xposedpoint.task.net.Utils;
import com.unking.xposedpoint.utils.SdCardUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public final class Device extends M
{

    public Device()
    {
    }

    public Device(String s)
    {
        super(s);
    }

    public void change(String s) {
        BufferedWriter writer = null;
        try {
            File temp_prop = new File((new StringBuilder()).append(SdCardUtil.getSDPath())
                    .append("/point/prop/").append("temp_device.properties")
                    .toString());
            if (!temp_prop.exists())
                temp_prop.createNewFile();
            else {
                temp_prop.delete();
                temp_prop.createNewFile();
            }

            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(temp_prop)));
            writer.write(s);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }

    public boolean equals(Object obj)
    {
        while(obj == null || !(obj instanceof Device)) 
            return false;
        return getImei().equals(((Device)obj).getImei());
    }

    public String getRemainDate()
    {
        return remainDate;
    }

    public void setLocation(String s, int i, int j)
    {
        String as[];
        if(!Utils.isEmpty(s) && Utils.isEmpty(getLongitude()))
            if((as = s.split("[,]")).length == 2)
            {
                setLongitude(String.valueOf(Double.parseDouble(as[0]) + (double)i / 100D));
                setLatitude(String.valueOf(Double.parseDouble(as[1]) + (double)j / 100D));
                return;
            }
    }

    public void setRemainDate(String s)
    {
        remainDate = s;
    }

    private String remainDate;
}
