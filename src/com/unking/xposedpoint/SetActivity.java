// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

import com.unking.xposedpoint.set.AllFragment;
import com.unking.xposedpoint.set.RunningFragment;

/**
 * 配置主界面
 */
public class SetActivity extends FragmentActivity
{
    private FragmentTabHost mTabHost;
    public SetActivity()
    {
        mTabHost = null;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.set);
        setTitle((new StringBuilder(String.valueOf(getResources().getString(R.string.app_name)))).append("(手机:").append(Build.MODEL).append(")").toString());
        mTabHost = (FragmentTabHost)findViewById(R.id.tabhost);

        mTabHost.setup(this, getSupportFragmentManager(), R.id.tabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("set_running").setIndicator("正在运行"), RunningFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("all").setIndicator("未运行"), AllFragment.class, null);
    }


}
