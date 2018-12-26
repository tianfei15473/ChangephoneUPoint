// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task;

import android.content.Context;
import android.util.Log;

import com.unking.xposedpoint.cmd.CommandExec;
import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.motion.action.ActionFactory;
import com.unking.xposedpoint.motion.action.Command;
import com.unking.xposedpoint.task.net.Utils;

import java.lang.reflect.Method;
import java.util.Random;

public class Motion
{
    protected class Action
    {

        protected Apk apk;
        protected Command cmd;
        protected Context context;
        final Motion this$0;

        private Action(Context context1, Apk apk1, Command command)
        {   super();
            this$0 = Motion.this;
            context = context1;
            apk = apk1;
            cmd = command;
        }

        Action(Context context1, Apk apk1, Command command, Action action)
        {
            this(context1, apk1, command);
        }
    }


    private Motion()
    {
    }

    public static final void clearPutString(Action paramAction)
    {
        String str1 = paramAction.cmd.getMessage();
        int i=0;
        int j=0;
        do
            try
            {
                String[] arrayOfString = str1.split("[,]");
                i = arrayOfString.length;
                String str2 = arrayOfString[j];
                android.provider.Settings.System.putString(paramAction.context.getContentResolver(), str2, "");
                j++;
            }
            catch (Exception localException)
            {
                continue;
            }
        while (j < i);
    }

    private static void exec(String s, long l)
    {
        if(stop)
        {
            return;
        } else
        {
            Command command = Command.build(s);
            ActionFactory.create(command.getAction()).handle(command);
            sleep(l);
            return;
        }
    }

    public static final void execute(Context context, Apk apk, Command command)
    {
        try
        {
            Method method = Motion.class.getDeclaredMethod(command.getAction(), new Class[] {
                    Action.class
            });
            Object aobj[] = new Object[1];
            Motion motion = new Motion();
            motion.getClass();
            aobj[0] = motion. new Action(context, apk, command, null);
            method.invoke(Action.class, aobj);
            return;
        }
        catch(Exception exception)
        {
            LLogger.error("Motion", exception);
        }
    }

    public static final void exit(Action action)
    {
        touch(274, 1967, 2000);
        touch(274, 1967, 100);
        touch(274, 1967, 1000);
    }

    public static final void install(Apk apk, long l)
    {
        if(Utils.isEmpty(apk.getFile()))
        {
            return;
        } else
        {
        	System.out.println("----------->" + apk.getFile());
            exec((new StringBuilder("pm install ")).append(apk.getFile()).toString(), l);
            return;
        }
    }

    public static final void install(Action action)
    {
        install(action.apk, action.cmd.getSleep());
    }

    private static final void move(int i, int j, int k, int l, int i1)
    {
        int j1 = (i - k) / 5;
        int k1 = (j - l) / 5;
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(i).append(",").append(j);
        int l1 = 0;
        do
        {
            if(l1 >= 5)
            {
                stringbuffer.append(",").append(k).append(",").append(l);
                exec("touch " + stringbuffer.toString(), i1);
                return;
            }
            i -= j1;
            j -= k1;
            stringbuffer.append(",").append(i).append(",").append(j);
            l1++;
        } while(true);
    }

    public static final void random(int i)
    {
        Random random1 = new Random();
        int j = random1.nextInt(i);
        if(j == 0)
            j = i;
        do
        {
            if(j <= 0)
                return;
            int k = Math.min(j, random1.nextInt(15));
            j -= k;
            randomTouch(k * 1000);
        } while(true);
    }

    public static final void random(int i, int j)
    {
        Random random1 = new Random();
        int k = random1.nextInt(i);
        if(k < j)
            k += j;
        do
        {
            if(k <= 0)
                return;
            int l = Math.min(k, random1.nextInt(15));
            k -= l;
            randomTouch(l * 1000);
        } while(true);
    }

    public static final void random(Action action)
    {
        random((int)action.cmd.getSleep());
    }

    public static final void randomSleep(int i)
    {
        sleep((new Random()).nextInt(i));
    }

    public static final void randomTouch(int i)
    {
        Random random1 = new Random();
        touch(100 + random1.nextInt(1000), 100 + random1.nextInt(1000), i);
    }

    public static final void randomTouch(Action action)
    {
        randomTouch((int)action.cmd.getSleep());
    }

    public static final void returnLast()
    {
        touch(274, 1967, 1000);
    }

    public static final void right2left(int i)
    {
        move(973, 747, 197, 747, i);
    }
    
    public static final void downRight2Left(int i) {
    	 move(973, 927, 197, 927, i);
    }
    
    public static final void downLeft2Right(int i) {
    	move(197, 927, 973, 927, i);
    }

    public static final void right2left(int i, int j)
    {
        move(973, i, 197, i, j);
    }

    public static final void right2left(Action action)
    {
        move(973, 1447, 197, 1447, (int)action.cmd.getSleep());
    }

    public static final void rm(Action action)
    {
        try
        {
            CommandExec commandexec = new CommandExec();
            String as[] = action.cmd.getMessage().split("[ ]");
            if(as.length == 2)
            {
                String as1[] = new String[2];
                as1[0] = (new StringBuilder("cd ")).append(as[0]).toString();
                as1[1] = (new StringBuilder("rm -r ")).append(as[1]).toString();
                commandexec.execute(as1);
            }
            return;
        }
        catch(Exception exception)
        {
            LLogger.error("RmAction", exception);
        }
    }

    public static final void send(String s, int i)
    {
        StringBuffer stringbuffer = new StringBuffer("send ");
        char ac[] = s.toCharArray();
        int j = ac.length;
        int k = 0;
        do
        {
            if(k >= j)
            {
                exec(stringbuffer.toString(), i);
                return;
            }
            String s1 = Integer.toHexString(ac[k]);
            if(s1.length() == 1)
                stringbuffer.append("000");
            if(s1.length() == 2)
                stringbuffer.append("00");
            if(s1.length() == 3)
                stringbuffer.append("0");
            stringbuffer.append(s1);
            k++;
        } while(true);
    }

    public static void sleep(long l)
    {
        while(stop || l <= 0L) 
            return;
        try
        {
            Thread.sleep(l);
            return;
        }
        catch(Exception exception)
        {
            return;
        }
    }

    public static final void sleep(Action action)
    {
        sleep(action.cmd.getSleep());
    }

    public static final void touch(int i, int j, int k)
    {
        Log.d("feitian", i + "," + j + "," + k + "   点击");
        exec("touch " + (new StringBuilder(String.valueOf(i))).append(",").append(String.valueOf(j)).toString(), k);
    }

    public static final void touch(Action action)
    {
        exec("touch " + action.cmd.getMessage(), action.cmd.getSleep());
    }

    public static final void uninstall(Apk apk, long l)
    {
        exec((new StringBuilder("pm uninstall ")).append(apk.getPackageName()).toString(), l);
    }

    public static final void uninstall(String packagename, long l)
    {
        exec((new StringBuilder("pm uninstall ")).append(packagename).toString(), l);
    }
    
    public static final void uninstall(Action action)
    {
        uninstall(action.apk, action.cmd.getSleep());
    }

    public static final void up2down(int i)
    {
        move(522, 396, 522, 1532, i);
    }
    
    public static final void down2up(int i) {
    	move(522, 1532, 522, 396, i);
    }

    public static final void up2down(Action action)
    {
        up2down((int)action.cmd.getSleep());
    }

    public static boolean stop = false;

}
