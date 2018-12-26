package com.unking.xposedpoint.task.impl;

import java.util.HashMap;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.motion.action.Command;
import com.unking.xposedpoint.task.Application;
import com.unking.xposedpoint.task.Motion;
import com.unking.xposedpoint.task.io.RFile;

public class AppStore360Task extends DefaultTask {
	private HashMap<String, Command> p_map = new HashMap<String, Command>();

	protected void start(Apk apk) {
		// TODO Auto-generated method stub
		/*Motion.touch(990, 162, 1000); // 点击搜索

		Motion.sleep(4000);

		Motion.touch(976, 572, 1000); // 下载

		Motion.sleep(75000);*/
		
		Motion.touch(501,1797, 3000);
		
		Motion.touch(1020,1770, 3000);
		
		Motion.touch(269,1163, 3000);
	}

	protected void finish(Apk apk) {
		RFile arfile[] = new RFile[1];
        arfile[0] = RFile.create("/sdcard-ext/360Download", "*");
        Application.delete(arfile);
	}
}
