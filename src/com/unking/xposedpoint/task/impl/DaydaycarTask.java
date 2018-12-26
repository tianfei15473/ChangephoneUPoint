package com.unking.xposedpoint.task.impl;

import java.util.Random;
import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.task.Motion;

public class DaydaycarTask extends DefaultTask {

	private Random ran;

	protected void start(Apk apk) {
		ran = new Random();

		Motion.sleep(8000);

		Motion.touch(391,723, 1000);
		
		//Motion.touch(345, 327, 5000);	//广告关闭
		
		Motion.randomSleep(10000);
	}

}
