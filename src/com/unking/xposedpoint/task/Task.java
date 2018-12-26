// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task;

import android.content.Context;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.task.data.Device;

public interface Task
{

    public abstract Device getDevice();

    public abstract boolean isRunning();

    public abstract void setDevice(Device device);

    public abstract void start(Context context, Apk apk);

    public abstract void stop(Context context, Apk apk);
}
