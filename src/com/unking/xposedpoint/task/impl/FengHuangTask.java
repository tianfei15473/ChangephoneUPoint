package com.unking.xposedpoint.task.impl;

import java.util.Random;

import com.unking.xposedpoint.data.Apk;
import com.unking.xposedpoint.task.Motion;

public class FengHuangTask extends DefaultTask {
	private Random ran;

	private void touch_more() {
		Motion.touch(1038, 176, 3000);
		Motion.touch(1000, 168, 3000);
	}

	private void wodedingyue() {
		Motion.touch(276, 974, 3000);

		Motion.touch(79, 170, 3000);
	}

	private void gerenzhongxin() {
		Motion.touch(54, 107, 3000);

		Motion.touch(483, 120, 3000);

		Motion.touch(1036, 177, 5000);
	}

	private void faxian() {
		Motion.touch(828, 983, 4000);

		Motion.touch(995, 311, 4000);

		Motion.touch(102, 170, 4000);

		Motion.touch(95, 789, 4000);

		Motion.touch(98, 1131, 4000);

		Motion.touch(83, 180, 4000);

	}

	protected void start(Apk apk) {
		ran = new Random();

		// 点击图标
		// Motion.touch(967, 708, 1000);

		Motion.sleep(8000);

		// 取消更新
		Motion.touch(821, 1232, 3000);

		Motion.touch(54, 127, 3000);
		Motion.touch(157, 635, 3000);

		int isMore = ran.nextInt(100);
		if (isMore < 20)
			//touch_more();

		if (isMore > 80)
			wodedingyue();

		if (isMore < 70 && isMore > 50)
			gerenzhongxin();

		if (isMore > 20 && isMore < 30)
			faxian();

		int isLeftOrRight = ran.nextInt(14);
		if (isLeftOrRight < 3) {
			for (int i = isLeftOrRight; i >= 0; i--) {
				Motion.downLeft2Right(2000);
			}
		} else if (isLeftOrRight > 3) {
			for (int i = isLeftOrRight; i > 3; i--) {
				Motion.downRight2Left(2000);
			}
		}

		chakanxiangxi();

		// fanhui
		Motion.touch(240, 1930, 500);
		Motion.touch(240, 1930, 3000);

		Motion.randomSleep(15000);

		// next();

		/*
		 * if(ran.nextInt(2) == 1) next();
		 */
	}

	protected boolean next() {
		ran = new Random();

		// 点击图标
		// Motion.touch(967, 708, 1000);

		Motion.sleep(12000);

		int isMore = ran.nextInt(100);
		if (isMore < 20)
			//touch_more();

		if (isMore > 80)
			wodedingyue();

		if (isMore < 70 && isMore > 50)
			gerenzhongxin();

		if (isMore > 20 && isMore < 30)
			faxian();

		int isLeftOrRight = ran.nextInt(14);
		if (isLeftOrRight < 3) {
			for (int i = isLeftOrRight; i >= 0; i--) {
				Motion.downLeft2Right(2000);
			}
		} else if (isLeftOrRight > 3) {
			for (int i = isLeftOrRight; i > 3; i--) {
				Motion.downRight2Left(2000);
			}
		}

		chakanxiangxi();

		// fanhui
		Motion.touch(240, 1930, 500);
		Motion.touch(240, 1930, 10000);

		Motion.randomSleep(15000);
		
		if(ran.nextInt(2) == 1) 
			return true;
		return false;
	}

	private void chakanxiangxi() {
		int d_ran = ran.nextInt(5);
		for (int i = 0; i < d_ran; i++) {
			Motion.down2up(5000);

			Motion.touch(523, 690, 6000);
			Motion.down2up(7000);
			Motion.touch(105, 1780, 6000);
		}
	}
}
