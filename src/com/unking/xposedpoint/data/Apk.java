// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.data;

import com.unking.xposedpoint.task.net.Utils;
import com.unking.xposedpoint.utils.ObjectPropertyUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Apk
    implements Serializable
{

    public Apk()
    {
        day1Remain = "0";
        day3Remain = "0";
        day7Remain = "0";
        day14Remain = "0";
        dayRemainRatio = "60";
        uninstallTimes = 0;
        enable = "false";
        uninstallTotal = "0";
        maxDownload = "0";
        remainRule = "";
        residenceTime = "0";
        task = "com.unking.xposedpoint.task.impl.TaskImpl";
        isInstallNotRun = "0";
        isNeedUninstall = "0";
    }

    public Apk(String s)
    {
        day1Remain = "0";
        day3Remain = "0";
        day7Remain = "0";
        day14Remain = "0";
        dayRemainRatio = "60";
        uninstallTimes = 0;
        enable = "false";
        uninstallTotal = "0";
        maxDownload = "0";
        remainRule = "";
        residenceTime = "0";
        task = "com.unking.xposedpoint.task.impl.TaskImpl";
        isInstallNotRun = "0";
        isNeedUninstall = "0";
        String as[] = s.split("[ ]");
        int i = 0;
        do
        {
            if(i >= as.length)
                return;
            ObjectPropertyUtils.setProperty(this, propertiesName[i], as[i]);
            i++;
        } while(true);
    }

    public int dayRemain(int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 1: // '\001'
            return Utils.parseInt(day1Remain, 0);

        case 3: // '\003'
            return Utils.parseInt(day3Remain, 0);

        case 7: // '\007'
            return Utils.parseInt(day7Remain, 0);

        case 14: // '\016'
            return Utils.parseInt(day14Remain, 0);
        }
    }

    public int dayRemainRatio()
    {
        return Utils.parseInt(dayRemainRatio, 0);
    }

    public String getActivity()
    {
        if(activity.indexOf(".") > 0)
            return activity;
        else
            return (new StringBuilder(String.valueOf(packageName))).append(".").append(activity).toString();
    }

    /**
     * 双周留存率
     * @return
     */
    public String getDay14Remain()
    {
        return day14Remain;
    }

    /**
     * 次日留存
     * @return
     */
    public String getDay1Remain()
    {
        return day1Remain;
    }

    /**
     * 三日留存
     * @return
     */
    public String getDay3Remain()
    {
        return day3Remain;
    }

    /**
     * 七日留存
     * @return
     */
    public String getDay7Remain()
    {
        return day7Remain;
    }


    /**
     * 留存占比
     * @return
     */
    public String getDayRemainRatio()
    {
        return dayRemainRatio;
    }

    public String getEnable()
    {
        return enable;
    }

    public String getFile()
    {
        return file;
    }

    public int getId()
    {
        return id;
    }


    public String getMaxDownload()
    {
        return maxDownload;
    }

    public String getName()
    {
        return name;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public String getRemainRule()
    {
        return remainRule;
    }

    public String getResidenceTime()
    {
        return residenceTime;
    }

    public ArrayList getStartCmds()
    {
        return startCmds;
    }

    public ArrayList getStopCmds()
    {
        return stopCmds;
    }

    public long getStopTime()
    {
        return stopTime;
    }

    public String getTask()
    {
        return task;
    }

    /**
     * 卸载量
     * @return
     */
    public int getUninstallTimes()
    {
        return uninstallTimes;
    }

    public String getUninstallTotal()
    {
        return uninstallTotal;
    }

    public boolean isEnable()
    {
        return "true".equals(enable);
    }

    public int maxDownload()
    {
        return Utils.parseInt(maxDownload, 0);
    }

    public int residenceTime()
    {
        return Utils.parseInt(residenceTime, 0);
    }

    public void setActivity(String s)
    {
        activity = s;
    }

    public void setDay14Remain(String s)
    {
        day14Remain = s;
    }

    public void setDay1Remain(String s)
    {
        day1Remain = s;
    }

    public void setDay3Remain(String s)
    {
        day3Remain = s;
    }

    public void setDay7Remain(String s)
    {
        day7Remain = s;
    }

    public void setDayRemainRatio(String s)
    {
        dayRemainRatio = s;
    }

    public void setEnable(String s)
    {
        enable = s;
    }

    public void setEnable(boolean flag)
    {
        enable = String.valueOf(flag);
    }

    public void setFile(String s)
    {
        file = s;
    }

    public void setId(int i)
    {
        id = i;
    }

    public void setMaxDownload(String s)
    {
        maxDownload = s;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setPackageName(String s)
    {
        packageName = s;
    }

    public void setRemainRule(String s)
    {
        remainRule = s;
    }

    public void setResidenceTime(String s)
    {
        residenceTime = s;
    }

    public void setStartCmds(ArrayList arraylist)
    {
        startCmds = arraylist;
    }

    public void setStopCmds(ArrayList arraylist)
    {
        stopCmds = arraylist;
    }

    public void setStopTime(long l)
    {
        stopTime = l;
    }

    public void setTask(String s)
    {
        task = s;
    }

    public void setUninstallTimes(int i)
    {
        uninstallTimes = i;
    }

    public void setUninstallTotal(String s)
    {
        uninstallTotal = s;
    }

    public String taskClass()
    {
        return (new StringBuilder("com.unking.xposedpoint.task.impl.")).append(task).toString();
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        String as[] = propertiesName;
        int i = as.length;
        int j = 0;
        do
        {
            if(j >= i)
                return stringbuffer.toString().trim();
            stringbuffer.append(ObjectPropertyUtils.getString(this, as[j])).append(" ");
            j++;
        } while(true);
    }

    public int uninstallTotal()
    {
        return Utils.parseInt(uninstallTotal, 0);
    }

    public void update(Apk paramApk)
    {
        if (equals(paramApk))
            return;
        this.day14Remain = paramApk.day14Remain;
        this.day1Remain = paramApk.day1Remain;
        this.day3Remain = paramApk.day3Remain;
        this.day7Remain = paramApk.day7Remain;
        this.enable = paramApk.enable;
        this.maxDownload = paramApk.maxDownload;
        this.remainRule = paramApk.remainRule;
        this.residenceTime = paramApk.residenceTime;
        this.uninstallTotal = paramApk.uninstallTotal;
        this.dayRemainRatio = paramApk.dayRemainRatio;
        if (paramApk.getStartCmds() != null)
            this.startCmds = new ArrayList(paramApk.getStartCmds());
        if (paramApk.getStopCmds() != null)
            this.stopCmds = new ArrayList(paramApk.getStopCmds());
        ArrayList localArrayList = new ArrayList();
        localArrayList.add("day1Remain");
        localArrayList.add("day3Remain");
        localArrayList.add("day7Remain");
        localArrayList.add("day14Remain");
        String[] arrayOfString=null;
        int j=0;
        int i=0;
        if (!Utils.isEmpty(this.remainRule))
        {
            arrayOfString = this.remainRule.split("[,]");
             i = arrayOfString.length;
            j = 0;

        }
        while (true)
        {
            Iterator localIterator = localArrayList.iterator();
            while (localIterator.hasNext())
            {
                ObjectPropertyUtils.setProperty(paramApk, (String)localIterator.next(), "0");
            break;}
            String str = arrayOfString[j];
            localArrayList.remove("day" + str + "Remain");
            j++;
            if (j < i){  break ;}
            paramApk.dayRemainRatio = "0";
        }
    }

    private static final long serialVersionUID = 0x710a2ba596d397bfL;
    private String activity;
    private String day14Remain;
    private String day1Remain;
    private String day3Remain;
    private String day7Remain;
    private String dayRemainRatio;
    private String enable;
    private String file;
    private int id;
    private String maxDownload;
    private String name;
    private String packageName;
    private String propertiesName[] = {
        "name", "task", "packageName", "activity", "remainRule", "isInstallNotRun", "isNeedUninstall", "residenceTime", "maxDownload", "enable", "day1Remain", "day3Remain", 
        "day7Remain", "day14Remain", "dayRemainRatio", "uninstallTotal"
    };
    private String remainRule;
    private String residenceTime;
    private ArrayList startCmds;
    private ArrayList stopCmds;
    private long stopTime;
    private String task;
    private int uninstallTimes;
    private String uninstallTotal;
    private String isInstallNotRun;
    private String isNeedUninstall;
	public String getIsInstallNotRun() {
		return isInstallNotRun;
	}

	public void setIsInstallNotRun(String isInstallNotRun) {
		this.isInstallNotRun = isInstallNotRun;
	}

	public String getIsNeedUninstall() {
		return isNeedUninstall;
	}

	public void setIsNeedUninstall(String isNeedUninstall) {
		this.isNeedUninstall = isNeedUninstall;
	}
}
