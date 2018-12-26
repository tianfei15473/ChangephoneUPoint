// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.motion.zte.u985;

import com.unking.xposedpoint.motion.action.Action;
import com.unking.xposedpoint.motion.action.Command;
import com.unking.xposedpoint.motion.action.Root;

import java.util.ArrayList;

public class TouchAction
    implements Action
{

    public TouchAction()
    {
    }

    //sanxing I9250
   /* private void touch(String paramString)
    {
        if ((paramString == null) || (paramString.trim().length() == 0))
            return;

        String[] arrayOfString = paramString.split("[,]");
        
        ArrayList localArrayList = new ArrayList();
        localArrayList.add("sendevent /dev/input/event1 3 57 439");
        localArrayList.add("sendevent /dev/input/event1 3 48 13");
        localArrayList.add("sendevent /dev/input/event1 3 58 157");
        localArrayList.add("sendevent /dev/input/event1" + " 3 53 " + arrayOfString[0]);
        localArrayList.add("sendevent /dev/input/event1" + " 3 54 " + arrayOfString[1]);
        localArrayList.add("sendevent /dev/input/event1 0 0 0");
        localArrayList.add("sendevent /dev/input/event1 3 57 4294967295");
        localArrayList.add("sendevent /dev/input/event1 0 0 0");
       
        Root.exec((String[]) localArrayList.toArray(new String[localArrayList.size()]));
        localArrayList.clear();
    }*/
    
    //中兴U960
   /* private void touch(String paramString)
    {
        if ((paramString == null) || (paramString.trim().length() == 0))
            return;

        String[] arrayOfString = paramString.split("[,]");
        
        ArrayList localArrayList = new ArrayList();
        localArrayList.add("sendevent /dev/input/event3 3 57 172");
        localArrayList.add("sendevent /dev/input/event3 3 53 " + arrayOfString[0]);
        localArrayList.add("sendevent /dev/input/event3 3 54 " + arrayOfString[1]);
        localArrayList.add("sendevent /dev/input/event3 1 330 1");
        localArrayList.add("sendevent /dev/input/event3 0 0 0");
        localArrayList.add("sendevent /dev/input/event3 3 57 4294967295");
        localArrayList.add("sendevent /dev/input/event3 1 330 0");
        localArrayList.add("sendevent /dev/input/event3 0 0 0");
        
        Root.exec((String[]) localArrayList.toArray(new String[localArrayList.size()]));
        localArrayList.clear();
    }*/
    
    
    /*U985*/
  private void touch(String paramString)
    {
        if ((paramString == null) || (paramString.trim().length() == 0))
            return;

        ArrayList localArrayList = new ArrayList();
        localArrayList.add("sendevent /dev/input/event0" + " 1 330 1");
        String[] arrayOfString = paramString.split("[,]");
        int i = 0;

        while (true)
        {
            try
            {
                if (i >= arrayOfString.length){
                    localArrayList.add("sendevent /dev/input/event0" + " 1 330 0");
                    localArrayList.add("sendevent /dev/input/event0" + " 0 2 0");
                    localArrayList.add("sendevent /dev/input/event0" + " 0 0 0");
                    Root.exec((String[]) localArrayList.toArray(new String[localArrayList.size()]));
                    localArrayList.clear();
                    return;
                } else{
                    String str1 = arrayOfString[i];
                    if (i < -1 + arrayOfString.length);
                    for (String str2 = arrayOfString[(i + 1)]; ; str2 = null)
                    {
                        localArrayList.add("sendevent /dev/input/event0" + " 3 58 20");
                        localArrayList.add("sendevent /dev/input/event0" + " 3 53 " + str1);
                        if ((str2 != null) && (str2.trim().length() > 0))
                            localArrayList.add("sendevent /dev/input/event0" + " 3 54 " + str2);
                        localArrayList.add("sendevent /dev/input/event0" + " 0 2 0");
                        localArrayList.add("sendevent /dev/input/event0" + " 0 0 0");
                        i += 2;
                        break;
                    }
                }
            } catch (Exception localException) {
                localException.printStackTrace();
            }
        }
    }
    
    private void touch1(String paramString)
    {
        if ((paramString == null) || (paramString.trim().length() == 0))
            return;

        ArrayList localArrayList = new ArrayList();
        localArrayList.add("sendevent /dev/input/event0" + " 1 330 1");
        String[] arrayOfString = paramString.split("[,]");
        int i = 0;

        while (true)
        {
            try
            {
                if (i >= arrayOfString.length){
                    localArrayList.add("sendevent /dev/input/event0" + " 1 330 0");
                    localArrayList.add("sendevent /dev/input/event0" + " 0 2 0");
                    localArrayList.add("sendevent /dev/input/event0" + " 0 0 0");
                    Root.exec((String[]) localArrayList.toArray(new String[localArrayList.size()]));
                    localArrayList.clear();
                    return;
                } else{
                    String str1 = arrayOfString[i];
                    if (i < -1 + arrayOfString.length);
                    for (String str2 = arrayOfString[(i + 1)]; ; str2 = null)
                    {
                        localArrayList.add("sendevent /dev/input/event0" + " 3 58 20");
                        localArrayList.add("sendevent /dev/input/event0" + " 3 53 " + str1);
                        if ((str2 != null) && (str2.trim().length() > 0))
                            localArrayList.add("sendevent /dev/input/event0" + " 3 54 " + str2);
                        localArrayList.add("sendevent /dev/input/event0" + " 0 2 0");
                        localArrayList.add("sendevent /dev/input/event0" + " 0 0 0");
                        i += 2;
                        break;
                    }
                }
            } catch (Exception localException) {
                localException.printStackTrace();
            }
        }
    }

    public void handle(Command command)
    {
        touch(command.getMessage());
    }
}
