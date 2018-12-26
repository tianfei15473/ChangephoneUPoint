// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.sync.SyncRunner;
import com.unking.xposedpoint.task.TaskRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BootService extends Service
{
    public BootService()
    {
        super();
        running = false;
        sync = null;
        valid = null;
        mReflectFlg = false;
        mSetForegroundArgs = new Object[1];
        mStartForegroundArgs = new Object[2];
        mStopForegroundArgs = new Object[1];
    }

    private void createThread()
    {
       /* sync = new SyncRunner();
        valid = new TaskRunner(getApplicationContext());*/
    }

    private void invokeMethod(Method method, Object aobj[])
    {
        try
        {
            method.invoke(this, aobj);
            return;
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            Log.w("ApiDemos", "Unable to invoke method", invocationtargetexception);
            return;
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            Log.w("ApiDemos", "Unable to invoke method", illegalaccessexception);
        }
    }

    private void start()
    {
       /* createThread();
        running = true;
        sync.start();
        valid.start();*/
    }

    @SuppressLint("NewApi")
	private void startForeground()
    {
        mNM = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder;
        try
        {
            mStartForeground = getClass().getMethod("startForeground", mStartForegroundSignature);
            mStopForeground = getClass().getMethod("stopForeground", mStopForegroundSignature);
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            mStopForeground = null;
            mStartForeground = null;
        }
        try
        {
            mSetForeground = getClass().getMethod("setForeground", mSetForegroundSignature);
        }
        catch(NoSuchMethodException nosuchmethodexception1)
        {
            throw new IllegalStateException("OS doesn't have Service.startForeground OR Service.setForeground!");
        }
        builder = new Notification.Builder(this);
        builder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, BootActivity.class), 0));
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setTicker("Foreground Service Start");
        builder.setContentTitle("Foreground Service");
        builder.setContentText("Make this service run in the foreground.");
        startForegroundCompat(1, builder.getNotification());
    }

    private void startForegroundCompat(int i, Notification notification)
    {
        if(mReflectFlg)
            if(mStartForeground != null)
            {
                mStartForegroundArgs[0] = Integer.valueOf(i);
                mStartForegroundArgs[1] = notification;
                invokeMethod(mStartForeground, mStartForegroundArgs);
                return;
            } else
            {
                mSetForegroundArgs[0] = Boolean.TRUE;
                invokeMethod(mSetForeground, mSetForegroundArgs);
                mNM.notify(i, notification);
                return;
            }
        if(Build.VERSION.SDK_INT >= 5)
        {
            startForeground(i, notification);
            return;
        } else
        {
            mSetForegroundArgs[0] = Boolean.TRUE;
            invokeMethod(mSetForeground, mSetForegroundArgs);
            mNM.notify(i, notification);
            return;
        }
    }

    private void stop()
    {
        if(sync != null)
        {
            sync.interrupt();
            sync = null;
        }
        if(valid != null)
        {
            valid.interrupt();
            valid = null;
        }
        running = false;
        LLogger.error("bootService", "stop");
    }

    private void stopForegroundCompat(int i)
    {
        if(mReflectFlg)
            if(mStopForeground != null)
            {
                mStopForegroundArgs[0] = Boolean.TRUE;
                invokeMethod(mStopForeground, mStopForegroundArgs);
                return;
            } else
            {
                mNM.cancel(i);
                mSetForegroundArgs[0] = Boolean.FALSE;
                invokeMethod(mSetForeground, mSetForegroundArgs);
                return;
            }
        if(Build.VERSION.SDK_INT >= 5)
        {
            stopForeground(true);
            return;
        } else
        {
            mNM.cancel(i);
            mSetForegroundArgs[0] = Boolean.FALSE;
            invokeMethod(mSetForeground, mSetForegroundArgs);
            return;
        }
    }

    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public void onCreate()
    {
        super.onCreate();
        startForeground();
        createThread();
        IntentFilter intentfilter;
       /* try {
            Build.class.getMethod("load", new Class[0]).invoke(this.getClass(), new Object[0]); //没有这个类，先注释掉  (add by yaosansi 20141216
        } catch(Exception exception)
        {
            Log.e("restart", "error", exception);
        }*/
        intentfilter = new IntentFilter();
        intentfilter.addAction("android.intent.action.SCREEN_OFF");
        intentfilter.addAction("android.intent.action.USER_PRESENT");
        registerReceiver(mBatInfoReceiver, intentfilter);
        LLogger.error("bootService", "create");
    }

    public void onDestroy()
    {
        LLogger.error("bootService", "Destroy");
        stop();
        super.onDestroy();
        if(mBatInfoReceiver != null)
            try
            {
                unregisterReceiver(mBatInfoReceiver);
            }
            catch(Exception exception) { }
        stopForegroundCompat(1);
    }

    public void onStart(Intent intent, int i)
    {
        if(running)
        {
            return;
        } else
        {
            LLogger.error("bootService", "start");
            start();
            return;
        }
    }

    private static final int NOTIFICATION_ID = 1;
    private static final Class mSetForegroundSignature[];
    private static final Class mStartForegroundSignature[];
    private static final Class mStopForegroundSignature[];
    private final BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent)
        {
            if("android.intent.action.SCREEN_OFF".equals(intent.getAction()))
                stop();
        }


    }
;
    private NotificationManager mNM;
    private boolean mReflectFlg;
    private Method mSetForeground;
    private Object mSetForegroundArgs[];
    private Method mStartForeground;
    private Object mStartForegroundArgs[];
    private Method mStopForeground;
    private Object mStopForegroundArgs[];
    private boolean running;
    private Thread sync;
    private Thread valid;

    static
    {
        Class aclass[] = new Class[1];
        aclass[0] = Boolean.TYPE;
        mSetForegroundSignature = aclass;
        Class aclass1[] = new Class[2];
        aclass1[0] = Integer.TYPE;
        aclass1[1] = Notification.class;
        mStartForegroundSignature = aclass1;
        Class aclass2[] = new Class[1];
        aclass2[0] = Boolean.TYPE;
        mStopForegroundSignature = aclass2;
    }

}
