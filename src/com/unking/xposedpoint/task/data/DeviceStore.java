// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.data;

import com.unking.xposedpoint.data.Apk;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class DeviceStore extends Hashtable
{

    public DeviceStore()
    {
    }

    public void add(Apk apk, Device device)
    {
        try
        {
            ArrayList localArrayList = (ArrayList)get(apk);
            if (localArrayList == null)
            {
                localArrayList = new ArrayList();
                put(apk, localArrayList);
            }
            localArrayList.add(device);
            return;
        }catch (Exception e){
            
        }
    }

    protected void finalize()
        throws Throwable
    {
        Iterator iterator = keySet().iterator();
        do
        {
            if(!iterator.hasNext())
            {
                clear();
                super.finalize();
                return;
            }
            Apk apk = (Apk)iterator.next();
            DeviceFile.saveRun(apk, (ArrayList)get(apk), date);
        } while(true);
    }

    public void save()
    {
        Iterator iterator = keySet().iterator();
        while(iterator.hasNext()) {
            Apk apk = (Apk)iterator.next();
            DeviceFile.saveRun(apk, (ArrayList)get(apk), date);
        }
    }

    public boolean save(Apk apk)
    {
        ArrayList arraylist;
        for(arraylist = (ArrayList)get(apk); arraylist == null || arraylist.size() <= 10;)
            return false;

        DeviceFile.saveRun(apk, arraylist, date);
        return true;
    }

    public void setDate(String s)
    {
        if(!s.equals(date))
        {
            save();
            clear();
        }
        date = s;
    }

    private static final long serialVersionUID = 0xa49d31277dda838cL;
    private String date = "";
}
