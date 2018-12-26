// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.data;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;

public class ApkManager
{
    private class App
    {

        private String file;
        private Drawable icon;
        final ApkManager this$0;




        private App(String s, Drawable drawable)
        {   super();
            this$0 = ApkManager.this;

            file = s;
            icon = drawable;
        }

        App(String s, Drawable drawable, App app)
        {
            this(s, drawable);
        }
    }


    private ApkManager()
    {
        apps = new HashMap();
    }

    public static final ApkManager getInstance()
    {
        return MANAGER;
    }

    public Drawable getIcon(Apk apk)
    {
        App app = (App)apps.get(apk.getPackageName());
        if(app != null)
            return app.icon;
        else
            return null;
    }

    public void load(Context context)
    {
        File file = new File("/sdcard-ext/point/apks");
        if(file.exists())
        {
            File afile[] = file.listFiles();
            int i = afile.length;
            int j = 0;
            while(j < i) 
            {
                File file1 = afile[j];
                if(file1.isFile() && file1.getName().toLowerCase(Locale.getDefault()).endsWith(".apk"))
                {
                    PackageManager packagemanager = context.getPackageManager();
                    PackageInfo packageinfo = packagemanager.getPackageArchiveInfo(file1.getAbsolutePath(), 1);
                    if(packageinfo != null)
                    {
                        ApplicationInfo applicationinfo = packageinfo.applicationInfo;
                        App app = new App(file1.getAbsolutePath(), null, null);
                        applicationinfo.sourceDir = app.file;
                        applicationinfo.publicSourceDir = app.file;
                        app.icon = applicationinfo.loadIcon(packagemanager);
                        apps.put(applicationinfo.packageName, app);
                    }
                }
                j++;
            }
        }
    }

    public void readPackageInfo(Apk apk)
    {
        App app = (App)apps.get(apk.getPackageName());
        if(app != null)
            apk.setFile(app.file);
    }

    private static final ApkManager MANAGER = new ApkManager();
    private HashMap apps;

}
