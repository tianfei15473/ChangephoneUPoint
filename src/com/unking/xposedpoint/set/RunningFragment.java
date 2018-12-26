// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.set;

import android.support.v4.app.ListFragment;

import com.unking.xposedpoint.data.ApkQueue;

/**
 * 配置 -> 正在运行
 */
public class RunningFragment extends ListFragment
{

    public RunningFragment()
    {
    }

    public void onResume()
    {
        super.onResume();
        setListAdapter(new ListApkAdapter(getActivity(), ApkQueue.getInstance().runall()));
    }
}
