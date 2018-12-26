// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.set;

import android.support.v4.app.ListFragment;
import android.util.Log;

import com.unking.xposedpoint.data.ApkQueue;

/**
 * 配置 -> 所有未运行界面
 */
public class AllFragment extends ListFragment
{

    public AllFragment()
    {
    }

    public void onResume()
    {
        super.onResume();
        Log.d("feitian-size", ApkQueue.getInstance().stopall().size() + "");
        setListAdapter(new ListApkAdapter(getActivity(), ApkQueue.getInstance().stopall()));
    }
}
