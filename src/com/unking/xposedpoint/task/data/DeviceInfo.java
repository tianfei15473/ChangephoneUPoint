// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.data;

import java.util.Random;

import android.telephony.TelephonyManager;

/**
 * 硬件信息
 */
public class DeviceInfo {

	public DeviceInfo() {
		ran = new Random();
	}

	/**
	 * 从字符串中读取硬件信息
	 * 
	 * @param s
	 *            含有硬件信息的字符串
	 */
	public DeviceInfo(String s) {
		System.out.println(s);
		ran = new Random();
		String as[] = s.split("[,]");
		if (as.length > 7) {
			imei = as[0];
			brand = as[2];
			model = as[3].replaceFirst(brand, "").trim();
			String as1[] = as[4].split("x");
			height = as1[0];
			width = as1[1];
			number = as[7].substring(0, 3);
			imsi = as[8].substring(0, 5);
			return;
		} else {
			imei = as[0];
			brand = as[1];
			imsi = as[2];
			networkOperator = as[2];
			model = as[3].contains(" ") ? as[3] : brand + as[3];
			number = as[4];
			height = as[5];
			width = as[6];
			return;
		}
	}

	private String randomAndroidId() {
		StringBuffer stringbuffer = new StringBuffer();
		int i = 0;
		do {
			if (i >= 16)
				return stringbuffer.toString();
			stringbuffer.append(Integer.toHexString(ran.nextInt(16)));
			i++;
		} while (true);
	}

	private String randomImei(String s) {
		return (new StringBuilder(String.valueOf(s))).append(randomMax(100))
				.append(randomMax(1000000)).append(randomMax(10)).toString();
	}

	private String randomImsi(String s) {
		return (new StringBuilder(String.valueOf(s))).append(randomMax(100))
				.append(randomMax(10000)).append(randomMax(10000)).toString();
	}

	private String randomNetworkOperator(String s) {
		return (new StringBuilder(String.valueOf(s))).append(randomMax(100))
				.append(randomMax(10000)).append(randomMax(10000)).toString();
	}

	private String randomLine1number(String s) {
		return (new StringBuilder(String.valueOf(s))).append(randomMax(10000))
				.append(randomMax(10000)).toString();
	}

	private String randomMac() {
		StringBuffer stringbuffer = new StringBuffer();
		int i = 0;
		do {
			if (i >= 6)
				return stringbuffer.toString();
			if (i > 0)
				stringbuffer.append(":");
			stringbuffer.append(Integer.toString((new Random()).nextInt(255),
					16));
			i++;
		} while (true);
	}
	private String randomDensityDpi() {
		try {
			if (Integer.parseInt(width) >= 240 && Integer.parseInt(width) < 320)
				return "120";
			else if (Integer.parseInt(width) >= 320
					&& Integer.parseInt(width) < 480) {
				return "160";
			} else if (Integer.parseInt(width) >= 480) {
				return "240";
			} else if (Integer.parseInt(width) >= 720)
				return "320";
			else
				return "240";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "240";
		}
	}
	
	private String randomDensity(String d) {
		return String.valueOf((Long.parseLong(width)/1.5) / Long.parseLong(d));
		
		/*try {
			if (Integer.parseInt(width) >= 240 && Integer.parseInt(width) < 320)
				return "0.75";
			else if (Integer.parseInt(width) >= 320
					&& Integer.parseInt(width) < 480) {
				return "1.0";
			} else if (Integer.parseInt(width) >= 480) {
				return "1.5";
			} else if (Integer.parseInt(width) >= 720)
				return "2.0";
			else
				return "2.0";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "1.5";
		}*/
	}

	private String randomXdip() {
		return "15" + String.valueOf(ran.nextInt(9)) + "."
				+ String.valueOf(ran.nextInt(100000));
	}

	private String randomYdip() {
		return "16" + String.valueOf(ran.nextInt(3)) + "."
				+ String.valueOf(ran.nextInt(100000));
	}

	private String randomMax(int i) {
		int j = ran.nextInt(i);
		if (j < i)
			j += i;
		return (new StringBuilder()).append(j).toString().substring(1);
	}

	private String randomByNum(int i) {
		StringBuffer sb = new StringBuffer();
		for (int j = 0; j < i; j++) {
			sb.append(ran.nextInt(10));
		}
		return sb.toString();
	}

	public boolean equals(Object obj) {
		while (obj == null || !(obj instanceof DeviceInfo))
			return false;
		return toString().equals(obj.toString());
	}

	/**
	 * 随机生机硬件信息
	 * 
	 * @return
	 */
	public Device random() {
		Device device = new Device();
		device.setMac(randomMac());
		device.setBluetoothMac(randomMac());
		device.setAndroidId(randomAndroidId());
		device.setBrand(brand);
		device.setCarrier(randomImsi(imsi));
		device.setHeight(height);
		device.setImei(randomImei(imei));
		device.setImeisv((new StringBuilder("0")).append(randomMax(10))
				.toString());
		device.setLine1number(randomLine1number(number));
		device.setModel(model);
		device.setProduct((new StringBuilder(String.valueOf(brand)))
				.append(" ").append(model).toString());
		device.setManufacturer(brand);
		device.setWidth(width);
		device.setSerialno(randomAndroidId());
		device.setBssid(randomMac());
		device.setDensityDpi(randomDensityDpi());
		device.setDensity(randomDensity(device.getDensityDpi()));
		device.setXdip(randomXdip());
		device.setYdip(randomYdip());
		device.setIccid(randomIccid(device.getLine1number()));
		device.setNetworkOperator(randomNetworkOperator(networkOperator));
		device.setNetworkOperatorName(getNetworkOperatorName(networkOperator));
		device.setNetworkInfo(randomNetworkInfo());
		device.setNetworkType(String.valueOf(randomNetworkType()));
		return device;
	}

	private int randomNetworkType() {
		/*
		 * NETWORK_TYPE_CDMA 网络类型为CDMA NETWORK_TYPE_EDGE 网络类型为EDGE
		 * NETWORK_TYPE_EVDO_0 网络类型为EVDO0 NETWORK_TYPE_EVDO_A 网络类型为EVDOA
		 * NETWORK_TYPE_GPRS 网络类型为GPRS NETWORK_TYPE_HSDPA 网络类型为HSDPA
		 * NETWORK_TYPE_HSPA 网络类型为HSPA NETWORK_TYPE_HSUPA 网络类型为HSUPA
		 * NETWORK_TYPE_UMTS 网络类型为UMTS
		 */

		// 在中国，联通的3G为UMTS或HSDPA，移动和联通的2G为GPRS或EGDE，电信的2G为CDMA，电信的3G为EVDO

		int[] n_types = { TelephonyManager.NETWORK_TYPE_1xRTT,
				TelephonyManager.NETWORK_TYPE_CDMA,
				TelephonyManager.NETWORK_TYPE_EDGE,
				TelephonyManager.NETWORK_TYPE_EHRPD,
				TelephonyManager.NETWORK_TYPE_EVDO_0,
				TelephonyManager.NETWORK_TYPE_EVDO_A,
				TelephonyManager.NETWORK_TYPE_EVDO_B,
				TelephonyManager.NETWORK_TYPE_GPRS,
				TelephonyManager.NETWORK_TYPE_HSDPA,
				TelephonyManager.NETWORK_TYPE_HSPA,
				TelephonyManager.NETWORK_TYPE_HSPAP,
				TelephonyManager.NETWORK_TYPE_IDEN,
				TelephonyManager.NETWORK_TYPE_LTE,
				TelephonyManager.NETWORK_TYPE_UMTS,
				TelephonyManager.NETWORK_TYPE_UNKNOWN };

		return n_types[ran.nextInt(n_types.length)];
	}

	private String randomNetworkInfo() {
		int ran = new Random().nextInt(100);
		if (ran < 70)
			return "1";
		else
			return "0";
	}

	private String getNetworkOperatorName(String operator) {
		String type = "";
		int is_two_card = ran.nextInt(1000);
		// int ranNum = ran.nextInt(11);
		if (is_two_card < 3) {
			String types[] = { "中国电信 中国移动",
					"China Mobile 4G | China Mobile 3G",
					"China Mobile 4G | China Mobile", "中国电信 中国移动", "中国联通 中国移动",
					"中国联通 中国联通", "中国电信 中国联通", "China Mobile 4G |", "中国移动 中国移动" };

			return types[ran.nextInt(types.length)];
		}
		if (operator.equals("46000") || operator.equals("46002")) {
			String types[] = { "中国移动", "中国移动 3G", "中国移动 2G", "中国移动 4G",
					"China Mobile", "阿里通信", "Suning@", "Vodafone", "CU",
					"vodafone IT", "Optus", "Chameleon" };

			type = types[ran.nextInt(types.length)];
		} else if (operator.equals("46001")) {
			// operatorName="中国联通";
			String types[] = { "中国联通", "中国联通 3G", "中国联通 2G", "中国联通 4G",
					"China Unicom", "阿里通信", "Suning@", "Vodafone", "CU",
					"vodafone IT", "Optus", "Chameleon", "one2free", "EVDO",
					"MY MAXIS", "中国联通 GD CMCC", "SKTelecom" };

			type = types[ran.nextInt(types.length)];

		} else if (operator.equals("46003")) {
			// operatorName="中国电信";
			String types[] = { "中国电信", "中国电信 3G", "中国电信 2G", "中国电信 4G",
					"China Telecom", "阿里通信", "Suning@", "Vodafone", "CU",
					"vodafone IT", "Optus", "Chameleon", "one2free", "EVDO",
					"MY MAXIS", "SKTelecom", "中華電信" };

			type = types[ran.nextInt(types.length)];
		}

		return type;
	}

	public String randomIccid(String phoneNum) {
		if (phoneNum.startsWith("159") || phoneNum.startsWith("158")
				|| phoneNum.startsWith("150") || phoneNum.startsWith("151")
				|| phoneNum.startsWith("134") || phoneNum.startsWith("135")
				|| phoneNum.startsWith("136") || phoneNum.startsWith("137")
				|| phoneNum.startsWith("138") || phoneNum.startsWith("139")
				|| phoneNum.startsWith("157") || phoneNum.startsWith("188")
				|| phoneNum.startsWith("182") || phoneNum.startsWith("183")
				|| phoneNum.startsWith("184") || phoneNum.startsWith("152")
				|| phoneNum.startsWith("147") || phoneNum.startsWith("187")
				|| phoneNum.startsWith("153") || phoneNum.startsWith("157")) { // 移动
			StringBuffer sb = new StringBuffer();
			sb.append("898600");
			/*
			 * 139,138,137,136,135,134(0-8),159,158,152,151,150
			 * 157,182,183,184,188,187 147
			 */
			if (phoneNum.startsWith("159"))
				sb.append("0");
			else if (phoneNum.startsWith("158"))
				sb.append("1");
			else if (phoneNum.startsWith("150"))
				sb.append("2");
			else if (phoneNum.startsWith("151"))
				sb.append("3");
			else if (phoneNum.startsWith("134"))
				sb.append("4");
			else if (phoneNum.startsWith("135"))
				sb.append("5");
			else if (phoneNum.startsWith("136"))
				sb.append("6");
			else if (phoneNum.startsWith("137"))
				sb.append("7");
			else if (phoneNum.startsWith("138"))
				sb.append("8");
			else if (phoneNum.startsWith("139"))
				sb.append("9");
			else if (phoneNum.startsWith("157"))
				sb.append("A");
			else if (phoneNum.startsWith("188"))
				sb.append("B");
			else if (phoneNum.startsWith("152"))
				sb.append("C");
			else if (phoneNum.startsWith("147"))
				sb.append("D");
			else if (phoneNum.startsWith("187"))
				sb.append("E");
			else
				sb.append("1");

			// 手机号第4位
			sb.append(phoneNum.charAt(3));

			// 省号
			int random_sh = ran.nextInt(32);
			if (random_sh == 0)
				sb.append("01");
			else if (random_sh > 0 && random_sh < 10)
				sb.append("0").append(random_sh);
			else if (random_sh >= 10)
				sb.append(random_sh);

			// 年份
			int randomInt = ran.nextInt(15);
			if (randomInt < 10)
				sb.append("0").append(randomInt);
			else
				sb.append(randomInt);

			// SIM卡供应商代码 0-9
			sb.append(ran.nextInt(10));

			sb.append(randomByNum(7));

			return sb.toString();
		} else if (phoneNum.startsWith("130") || phoneNum.startsWith("131")
				|| phoneNum.startsWith("132") || phoneNum.startsWith("145")
				|| phoneNum.startsWith("155") || phoneNum.startsWith("156")
				|| phoneNum.startsWith("185") || phoneNum.startsWith("186")
				|| phoneNum.startsWith("176")) {
			// 130,131,132,145,155,156, 186,185
			StringBuffer sb = new StringBuffer();
			sb.append("898601");

			// 年份
			int randomInt = ran.nextInt(15);
			if (randomInt < 10)
				sb.append("0").append(randomInt);
			else
				sb.append(randomInt);

			sb.append("8");

			String[] sh_str = { "10", "11", "13", "17", "18", "19", "30", "31",
					"34", "36", "38", "50", "51", "59", "70", "71", "74", "75",
					"76", "79", "81", "83", "84", "85", "86", "87", "88", "89",
					"90", "91", "97" };

			sb.append(sh_str[ran.nextInt(31)]);

			sb.append(randomByNum(9));
			return sb.toString();
		} else if (phoneNum.startsWith("133") || phoneNum.startsWith("153")
				|| phoneNum.startsWith("180") || phoneNum.startsWith("181")
				|| phoneNum.startsWith("189")) {
			// 133,153,180,181,189
			StringBuffer sb = new StringBuffer();
			sb.append("8986030");

			// 年份
			int randomInt = ran.nextInt(15);
			if (randomInt < 10)
				sb.append("0").append(randomInt);
			else
				sb.append(randomInt);

			String[] qh_str = { "010", "021", "022", "023", "311", "351",
					"471", "024", "431", "451", "025", "571", "551", "591",
					"791", "531", "371", "027", "731", "020", "771", "898",
					"028", "851", "871", "029", "891", "931", "971", "991",
					"852", "853" };

			sb.append(qh_str[ran.nextInt(32)]);

			sb.append(randomByNum(8));

			return sb.toString();
		}

		return "";
	}

	public String toString() {
		return (new StringBuilder(String.valueOf(imei))).append(",")
				.append(brand).append(",").append(imsi).append(",")
				.append(model).append(",").append(number).append(",")
				.append(height).append(",").append(width).toString();
	}

	private String brand;
	private String height;
	private String imei;
	private String imsi;
	private String model;
	private String number;
	private Random ran;
	private String width;
	private String networkOperator;
}
