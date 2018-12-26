// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.data;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.data.ApkQueue;
import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.task.net.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;

public class DeviceRemain extends Hashtable
{

    public DeviceRemain()
    {
    }

    private void build(Apk paramApk, int paramInt1, int paramInt2) {
        Calendar localCalendar = Calendar.getInstance(Locale.getDefault());
        localCalendar.setTime(new Date());
        localCalendar.add(Calendar.DAY_OF_MONTH, 0 - paramInt1);//first param is 6 (add by yaosansi 20141213
        String str1 = Utils.formatDate(localCalendar.getTime(), "yyyyMMdd");
        File localFile = DeviceFile.file(paramApk, "point_run." + str1);
        DeviceRandom localDeviceRandom = new DeviceRandom();
        if (!localFile.exists())
            return;
        try {
            BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(localFile), "gb2312"));
            try {
                while (true) {
                    String str2 = localBufferedReader.readLine();
                    if (str2 == null) {
                        add(paramApk.getPackageName(), localDeviceRandom.random(paramInt2));
                        LLogger.error("DeviceRemain.build", get(paramApk.getPackageName()).size() + "=" + paramInt1 + "=" + paramApk.getPackageName());
                        break;
                    }
                    if (str2.trim().length() != 0) {
                        Device localDevice = new Device(str2);
                        localDevice.setRemainDate(String.valueOf(paramInt1));
                        if (!"true".equalsIgnoreCase(localDevice.getRemain())) {
                            localDeviceRandom.add(localDevice);
                            if (localDeviceRandom.size() == 100) {
                                add(paramApk.getPackageName(), localDeviceRandom.random(paramInt2));
                                localDeviceRandom.clear();
                            }
                        }
                    }
                }
            } catch (Exception localException1) {
                LLogger.error("DeviceRemain.build", localException1);
            }
        } catch (Exception localException2) {
            LLogger.error("DeviceRemain.build", localException2);
        }

    }

    private void init(Apk paramApk, int paramInt)
    {
        try
        {
        File localFile = DeviceFile.file(paramApk, "point_remain." + this.date + "." + paramInt);
        if (!localFile.exists())
            return;

                BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(localFile), "gb2312"));
                String str = "";
                    while ((str = localBufferedReader.readLine()) != null)
                    {
                        if (str.trim().length() != 0)
                        {
                            Device localDevice = new Device(str);
                            localDevice.setRemainDate(String.valueOf(paramInt));
                            add(paramApk.getPackageName(), localDevice);
                        }
                    }
     } catch (Exception localException1) {
                    LLogger.error("DeviceRemain.build", localException1);
                }


    }

    public synchronized void add(String s, Device device) {
        try {
            ArrayList arraylist = get(s);
            if (arraylist == null) {
                arraylist = new ArrayList();
                put(s, arraylist);
            }
            boolean flag = arraylist.contains(device);
            if (!flag) {
                arraylist.add(device);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void add(String s, ArrayList arraylist)
    {
        try {
            ArrayList arraylist1 = get(s);
            if (arraylist1 == null) {
                arraylist1 = new ArrayList();
                put(s, arraylist1);
            }
            arraylist1.addAll(arraylist);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void build(Apk apk)
    {
        if(!Utils.isEmpty(apk.getRemainRule()))
        {
            boolean flag = containsKey(apk.getPackageName());
            if(!flag) {
                String as[];
                int i;
                as = apk.getRemainRule().split("[,]");
                i = as.length;
                for(int j=0;j<i;j++) {
                    int k;
                    int l;
                    k = Integer.parseInt(as[j]);
                    l = apk.dayRemain(k);
                    if (l <= 0)
                        break;
                    build(apk, k, l);
                }
            }
        }

    }

    public synchronized boolean containsKey(Object obj) {
        try {
            boolean flag = super.containsKey((new StringBuilder(String.valueOf((obj)))).append(date).toString());
            return flag;
        } catch (Exception e) {
            return false;
        }
    }

    protected void finalize()
        throws Throwable
    {
        Iterator iterator = ApkQueue.getInstance().runall().iterator();
        do
        {
            if(!iterator.hasNext())
            {
                super.finalize();
                return;
            }
            save((Apk)iterator.next());
        } while(true);
    }



    public synchronized ArrayList get(Object obj) {
        try {
            ArrayList arraylist = (ArrayList) super.get((new StringBuilder(String.valueOf((obj)))).append(date).toString());
            return arraylist;
        } catch (Exception e) {
        	return null;
        }
    }

    public synchronized void init(Apk paramApk) {
        String[] arrayOfString = paramApk.getRemainRule().split("[,]");

        int i = arrayOfString.length;
        for (int j = 0; j < i ; j++) {
            try {
                if (!Utils.isEmpty(paramApk.getRemainRule())) {
                    boolean bool = containsKey(paramApk.getPackageName());

                    if (!bool) {
                        int k = Integer.parseInt(arrayOfString[j]);
                        if (paramApk.dayRemain(k) > 0) {
                            init(paramApk, k);
                        }
                    }
                } else {
                    return;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    public  Object put(Object obj, Object obj1)
    {
        return put((String)obj, (ArrayList)obj1);
    }

    public synchronized ArrayList put(String s, ArrayList arraylist)
    {
        try {
            ArrayList arraylist1 = (ArrayList) super.put((new StringBuilder(String.valueOf(s))).append(date).toString(), arraylist);
            return arraylist1;
        } catch (Exception exception) {
            return null;
        }
    }

    public synchronized void save() {
        Iterator iterator = keySet().iterator();
        try {
            while (iterator.hasNext()) {
                String s = (String) iterator.next();
                DeviceFile.saveRemain(ApkQueue.getInstance().getApk(s), get(s), date);
            }
        } catch (Exception exception) {
           
        }
    }

    public synchronized void save(Apk apk)
    {
       try {
           DeviceFile.saveRemain(apk, get(apk.getPackageName()), date);
           return;
       }catch (Exception exception) {
           
       }
    }

    public void setDate(String s)
    {
        if(!s.equalsIgnoreCase(date))
        {
            save();
            clear();
        }
        date = s;
    }

    private static final long serialVersionUID = 0xa315f33e870600e5L;
    private String date;
}
