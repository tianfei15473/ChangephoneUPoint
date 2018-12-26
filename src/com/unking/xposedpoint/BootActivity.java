// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.unking.xposedpoint.data.ApkManager;
import com.unking.xposedpoint.data.ApkQueue;
import com.unking.xposedpoint.task.data.DeviceManagerChangePhone;
import com.unking.xposedpoint.utils.SdCardUtil;

public class BootActivity extends Activity implements View.OnClickListener {

    private ImageView btn_baidu, btn_huawei, btn_oppo, btn_vivo;

    public BootActivity() {
        super();
    }

    protected void onActivityResult(int i, int j, Intent intent) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); 
       
        setContentView(R.layout.activity_main);

        btn_baidu = (ImageView) findViewById(R.id.btn_baidu);
        btn_huawei = (ImageView) findViewById(R.id.btn_huawei);
        btn_oppo = (ImageView) findViewById(R.id.btn_oppo);
        btn_vivo = (ImageView) findViewById(R.id.btn_vivo);

        btn_baidu.setOnClickListener(this);
        btn_huawei.setOnClickListener(this);
        btn_oppo.setOnClickListener(this);
        btn_vivo.setOnClickListener(this);

        setTitle(new StringBuilder().append("(手机:").append(Build.MODEL).append(")").toString());//Global.PHONE
        
        
        File temp_prop = new File((new StringBuilder()).append(SdCardUtil.getSDPath())
                .append("/point/prop/").append("temp_device.properties")
                .toString());
			
		if (!temp_prop.exists()) {
			System.out.println("1112222222222");
		} else {
			System.out.println("yyyyyyyyyyyyyyy");
		}
        
        /*SharedPreferences.Editor editor = getSharedPreferences("last_know_location", 0).edit();
        TelephonyManager _tmp = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        ApkManager.getInstance().load(getApplicationContext());
        ApkQueue.getInstance().load();*/
        
       /* AlertDialog.Builder builder = new AlertDialog.Builder(BootActivity.this);
                        //    设置Title的图标
                        builder.setIcon(R.drawable.ic_launcher);
                        //    设置Title的内容
                        builder.setTitle("提示");
                        //    设置Content来显示一个信息
                        builder.setMessage("点击确定加载数据...");
                        //    设置一个PositiveButton
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                new Thread(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										DeviceManagerChangePhone.getInstance().init();
										Looper.prepare();
										Toast.makeText(BootActivity.this, "数据加载完成", Toast.LENGTH_SHORT).show();
										Looper.loop();
									}
								}).start();
                            	
                            	
                            }
                        });
                        builder.show();*/
        //DeviceManagerChangePhone.getInstance();

    }

 
    

    protected void onStart() {
        super.onStart();
        try {
            Runtime.getRuntime().exec("su");
            return;
        } catch (Exception exception) {
            return;
        }
    }

    @Override
    public void onClick(View v) {
    	String product = "";
        switch (v.getId()) {
            case 0:
                return;
            case R.id.btn_baidu:
            	//startService(new Intent(BootActivity.this, BootService.class));//启动服务
            	product = DeviceManagerChangePhone.getInstance().random("baidu");
            	setTitle(new StringBuilder().append("(手机:").append(product).append(")").toString());//Global.PHONE
                return;
            case R.id.btn_huawei:
            	product = DeviceManagerChangePhone.getInstance().random("huawei");
            	setTitle(new StringBuilder().append("(手机:").append(product).append(")").toString());//Global.PHONE
                return;
            case R.id.btn_oppo:
            	product = DeviceManagerChangePhone.getInstance().random("oppo");
            	setTitle(new StringBuilder().append("(手机:").append(product).append(")").toString());//Global.PHONE
                return;
            case R.id.btn_vivo:
            	product = DeviceManagerChangePhone.getInstance().random("vivo");
            	setTitle(new StringBuilder().append("(手机:").append(product).append(")").toString());//Global.PHONE
                return;
        }
    }
}
