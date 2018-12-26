// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.data;

import android.util.Xml;

import com.unking.xposedpoint.Global;
import com.unking.xposedpoint.motion.action.Command;
import com.unking.xposedpoint.sync.Des;
import com.unking.xposedpoint.utils.StreamUtils;

import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ApkParser extends DefaultHandler
{

    public ApkParser()
    {
    }

    private void copy(XmlPullParser xmlpullparser, Object obj)
    {
        int i = xmlpullparser.getAttributeCount();
        int j = 0;
        do
        {
            if(j >= i)
                return;
            String s = xmlpullparser.getAttributeName(j);
            String s1 = xmlpullparser.getAttributeValue(j);
            try
            {
                String s2 = (new StringBuilder("set")).append(s).toString();
                obj.getClass().getDeclaredMethod(s2, new Class[] {
                    String.class
                }).invoke(obj, new Object[] {
                    s1
                });
            }
            catch(Exception exception) { }
            j++;
        } while(true);
    }

    private List<Apk> parse(InputStream inputstream)
        throws XmlPullParserException, IOException
    {
        ArrayList localArrayList1 = new ArrayList();
        XmlPullParser localXmlPullParser = Xml.newPullParser();
        localXmlPullParser.setInput(inputstream, "GBK");
        Apk localApk = null;
        ArrayList localArrayList2 = null;
        int i = localXmlPullParser.getEventType();
        if (i == 1)
            return localArrayList1;
        switch (i)
        {
            case 0:
            case 1:
            default:
            case 2:
        }
        while (true)
        {
            i = localXmlPullParser.next();

            if (localXmlPullParser.getName().equalsIgnoreCase("apk"))
            {
                localApk = new Apk();
                localArrayList1.add(localApk);
                copy(localXmlPullParser, localApk);
            }
            else if (localXmlPullParser.getName().equals("stop"))
            {
                localArrayList2 = new ArrayList();
                localApk.setStopCmds(localArrayList2);
            }
            else if (localXmlPullParser.getName().equals("start"))
            {
                localArrayList2 = new ArrayList();
                localApk.setStartCmds(localArrayList2);
            }
            else if (localXmlPullParser.getName().equals("cmd"))
            {
                Command localCommand = new Command();
                localArrayList2.add(localCommand);
                copy(localXmlPullParser, localCommand);
                localCommand.setMessage(localXmlPullParser.nextText());
            }
            break;
        }
        return localArrayList2;
    }

    public List parse()
        throws Exception
    {
        File file = new File((new StringBuilder("/sdcard-ext/point/")).append(Global.PHONE).append("/point_apk.xml").toString());
        if(!file.exists())
        {
            return new ArrayList();
        } else
        {
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            StreamUtils.closeAfterWrite(bytearrayoutputstream, new FileInputStream(file));
            return parse(((InputStream) (new ByteArrayInputStream((new Des()).decrypt(bytearrayoutputstream.toByteArray())))));
        }
    }
}
