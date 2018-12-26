// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.motion.zte.u985;

import android.os.Environment;
import android.util.Log;

import com.unking.xposedpoint.io.PictureUtil;
import com.unking.xposedpoint.motion.action.Command;
import com.unking.xposedpoint.motion.action.Root;

import java.io.BufferedWriter;

public class ScreenAction
{

    public ScreenAction()
    {
    }

    public void handle(Command paramCommand, BufferedWriter paramBufferedWriter)
    {
        String str2=null;
        if ((paramCommand.getMessage() != null) && (paramCommand.getMessage().startsWith("/")))
            str2 = paramCommand.getMessage();
        while (true)
        {
            String[] arrayOfString = new String[1];
            arrayOfString[0] = ("screencap " + str2);
            Root.exec(arrayOfString);
            if (paramBufferedWriter != null);
            try
            {
                paramBufferedWriter.write(PictureUtil.bitmapToString(str2));
                paramBufferedWriter.newLine();
                paramBufferedWriter.flush();
              //  return;
                StringBuffer localStringBuffer = new StringBuffer(Environment.getDataDirectory().getPath()).append("/");
                if (paramCommand.getMessage() == null);
                for (String str1 = ".vnc_screen.png"; ; str1 = paramCommand.getMessage())
                {
                    str2 = str1;
                    break;
                }
            }
            catch (Exception localException)
            {
                Log.e("ScreenAction", "错误", localException);
            }
        }
    }
}
