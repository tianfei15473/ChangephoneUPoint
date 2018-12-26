// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.data;

import java.util.ArrayList;
import java.util.Random;

public class DeviceRandom extends ArrayList
{

    public DeviceRandom()
    {
    }

    public ArrayList random(int i)
    {
        Random random1 = new Random();
        if(i >= size())
            return this;
        ArrayList arraylist = new ArrayList();
        int j = 0;
        do
        {
            if(j >= i)
                return arraylist;
            arraylist.add((Device)remove(random1.nextInt(size())));
            j++;
        } while(true);
    }

    private static final long serialVersionUID = 0x4a005bc44f86d4e9L;
}
