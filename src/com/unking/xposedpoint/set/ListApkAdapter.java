// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.set;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.unking.xposedpoint.R;
import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.data.ApkManager;

import java.util.ArrayList;
import java.util.List;

/**
 * APK列表
 */
public class ListApkAdapter extends BaseAdapter
{
    private class ChangeBackgroud
        implements Runnable
    {

        public void run()
        {
            c.setBackgroundDrawable(d);
        }

        private View c;
        private Drawable d;


        private ChangeBackgroud(View view, Drawable drawable)
        {   super();
            c = view;
            d = drawable;
        }

        ChangeBackgroud(View view, Drawable drawable, ChangeBackgroud changebackgroud)
        {
            this(view, drawable);
        }
    }

    private class Holder
    {
        TextView desc;
        ImageView icon;
        TextView text;

        private Holder() {
            super();
        }

        Holder(Holder holder) {
            this();
        }
    }


    public ListApkAdapter(FragmentActivity fragmentactivity, List list)
    {
        mListData = new ArrayList();
        mInflater = null;
        activity = null;
        handler = new Handler();
        activity = fragmentactivity;
        mInflater = (LayoutInflater)fragmentactivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mListData = list;

    }

    public int getCount()
    {
        return mListData.size();
    }

    public Object getItem(int i)
    {
        return mListData.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
      final Apk news = (Apk)mListData.get(i);
        Holder holder;
        if(view == null)
        {
            view = mInflater.inflate(R.layout.apk_item, null);
            holder = new Holder(null);
            holder.text = (TextView)view.findViewById(R.id.appname_tv);
            holder.icon = (ImageView)view.findViewById(R.id.appicon_iv);
            holder.desc = (TextView)view.findViewById(R.id.packagename_tv);
            view.setTag(holder);
        } else
        {
            holder = (Holder)view.getTag();
        }
        holder.text.setText(news.getName());
        holder.text.getPaint().setFakeBoldText(true);
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN ) {
            holder.icon.setBackgroundDrawable(ApkManager.getInstance().getIcon(news));
        }
        else {
            holder.icon.setBackground(ApkManager.getInstance().getIcon(news));
        }
        holder.desc.setText(news.getPackageName());
        view.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view1)
            {
                Drawable drawable = view1.getBackground();
                view1.setBackgroundColor(Color.WHITE);
                Intent intent = new Intent(view1.getContext(), ApkDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("apk", news);
                intent.putExtras(bundle);
                activity.startActivityForResult(intent, 0);
                handler.postDelayed(new ChangeBackgroud(view1, drawable, null), 200L);
            }


        }
);
        return view;
    }

    private FragmentActivity activity;
    private Handler handler;
    private LayoutInflater mInflater;
    private List mListData;


}
