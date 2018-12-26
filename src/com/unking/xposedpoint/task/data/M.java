// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.task.data;

import org.json.JSONObject;

import java.lang.reflect.Method;

public class M {

	public M() {
		count = "0";
		remain = "false";
	}

	public M(String s) {
		count = "0";
		remain = "false";
		String as[] = s.split("[,]");

		if (as.length <= 0 || props.length <= 0)
			return;

		try {
			for (int i = 0; i < as.length; i++) {
				Method method = getClass().getMethod(
						(new StringBuilder("set")).append(props[i]).toString(),
						new Class[] { String.class });
				Object aobj[] = new Object[1];
				aobj[0] = as[i];
				method.invoke(this, aobj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void count() {
		setCount((new StringBuilder()).append(1 + Integer.parseInt(count))
				.toString());
	}

	public boolean equals(Object obj) {
		while (obj == null || !(obj instanceof M))
			return false;
		return imei.equals(((M) obj).imei);
	}

	public String getAccountName() {
		return accountName;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public String getAndroidId() {
		return androidId;
	}

	public String getBluetoothMac() {
		return bluetoothMac;
	}

	public String getBrand() {
		return brand;
	}

	public String getBssid() {
		return bssid;
	}

	public String getCarrier() {
		return carrier;
	}

	public String getCodename() {
		return codename;
	}

	public String getCount() {
		return count;
	}

	public String getFile() {
		return file;
	}

	public String getHardware() {
		return hardware;
	}

	public String getHeight() {
		return height;
	}

	public String getId() {
		return id;
	}

	public String getImei() {
		return imei;
	}

	public String getImeisv() {
		return imeisv;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLine1number() {
		return line1number;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getMac() {
		return mac;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public String getModel() {
		return model;
	}

	public String getNetworkType() {
		return networkType;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getProduct() {
		return (new StringBuilder(String.valueOf(brand))).append(" ")
				.append(model).toString();
	}

	public String getRelease() {
		return release;
	}

	public String getRemain() {
		return remain;
	}

	public String getSdk() {
		return sdk;
	}

	public String getSdkInt() {
		return sdkInt;
	}

	public String getSerialno() {
		return serialno;
	}

	public String getSharedNames() {
		return sharedNames;
	}

	public String getSsid() {
		return ssid;
	}

	public String getType() {
		return type;
	}

	public String getWidth() {
		return width;
	}

	public void setAccountName(String s) {
		accountName = s;
	}

	public void setAccountPassword(String s) {
		accountPassword = s;
	}

	public void setAndroidId(String s) {
		androidId = s;
	}

	public void setBluetoothMac(String s) {
		bluetoothMac = s;
	}

	public void setBrand(String s) {
		brand = s;
	}

	public void setBssid(String s) {
		bssid = s;
	}

	public void setCarrier(String s) {
		carrier = s;
	}

	public void setCodename(String s) {
		codename = s;
	}

	public void setCount(String s) {
		count = s;
	}

	public void setFile(String s) {
		file = s;
	}

	public void setHardware(String s) {
		hardware = s;
	}

	public void setHeight(String s) {
		height = s;
	}

	public void setId(String s) {
		id = s;
	}

	public void setImei(String s) {
		imei = s;
	}

	public void setImeisv(String s) {
		imeisv = s;
	}

	public void setLatitude(String s) {
		latitude = s;
	}

	public void setLine1number(String s) {
		line1number = s;
	}

	public void setLongitude(String s) {
		longitude = s;
	}

	public void setMac(String s) {
		mac = s;
	}

	public void setManufacturer(String s) {
		manufacturer = s;
	}

	public void setModel(String s) {
		model = s;
	}

	public void setNetworkType(String s) {
		networkType = s;
	}

	public void setPackageName(String s) {
		packageName = s;
	}

	public void setProduct(String s) {
	}

	public void setRelease(String s) {
		release = s;
	}

	public void setRemain(String s) {
		remain = s;
	}

	public void setSdk(String s) {
		sdk = s;
	}

	public void setSdkInt(String s) {
		sdkInt = s;
	}

	public void setSerialno(String s) {
		serialno = s;
	}

	public void setSharedNames(String s) {
		sharedNames = s;
	}

	public void setSsid(String s) {
		ssid = s;
	}

	public void setType(String s) {
		type = s;
	}

	public void setWidth(String s) {
		width = s;
	}

	public String toString() {
		JSONObject obj = new JSONObject();
		String as[] = this.props;
		int i = as.length;
		for (int j = 0; j < i; j++) {
			String s = as[j];
			try {
				String s1 = (String) getClass().getMethod("get" + s,
						new Class[0]).invoke(this, new Object[0]);
				if (s1 == null) {
					s1 = "";
				}
				obj.put(s, s1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return obj.toString();
	}

	private String accountName;
	private String accountPassword;
	private String adCode;
	private String androidId;
	private String bluetoothMac;
	private String brand;
	private String bssid;
	private String carrier;
	private String city;
	private String cityCode;
	private String codename;
	private String count;
	private String district;
	private String file;
	private String hardware;
	private String height;
	private String id;
	private String imei;
	private String imeisv;
	private String latitude;
	private String line1number;
	private String longitude;
	private String mac;
	private String manufacturer;
	private String model;

	/**
	 * 返回移动终端的类型
	 * 
	 * 2 PHONE_TYPE_CDMA 手机制式为CDMA，电信 1 PHONE_TYPE_GSM 手机制式为GSM，移动和联通 0
	 * PHONE_TYPE_NONE 手机制式未知
	 */
	private String networkType;

	private String packageName;
	private String props[] = { "Line1number", "AndroidId", "BluetoothMac",
			"Mac", "Width", "Height", "Imei", "Imeisv", "Serialno", "Model",
			"Manufacturer", "Brand", "Hardware", "Type", "Release", "Sdk",
			"SdkInt", "Codename", "Product", "Count", "Remain", "Latitude",
			"Longitude", "Ssid", "Bssid", "AccountName", "AccountPassword",
			"Id", "NetworkType", "PackageName", "SharedNames", "Density",
			"DensityDpi", "Xdip", "Ydip", "Carrier", "NetworkOperatorName",
			"Iccid", "NetworkOperator", "NetworkInfo", "Ip", "Starttime", "Starttime_str"};
	private String province;
	private String release;
	private String remain;
	private String sdk;
	private String sdkInt;
	private String serialno;
	private String sharedNames;

	public void setDensityDpi(String densityDpi) {
		this.densityDpi = densityDpi;
	}

	public void setYdip(String ydip) {
		this.ydip = ydip;
	}

	public void setXdip(String xdip) {
		this.xdip = xdip;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	private String ssid;
	private String type;
	private String width;

	/**
	 * 获取网络类型
	 * 
	 * NETWORK_TYPE_CDMA 网络类型为CDMA NETWORK_TYPE_EDGE 网络类型为EDGE
	 * NETWORK_TYPE_EVDO_0 网络类型为EVDO0 NETWORK_TYPE_EVDO_A 网络类型为EVDOA
	 * NETWORK_TYPE_GPRS 网络类型为GPRS NETWORK_TYPE_HSDPA 网络类型为HSDPA
	 * NETWORK_TYPE_HSPA 网络类型为HSPA NETWORK_TYPE_HSUPA 网络类型为HSUPA
	 * NETWORK_TYPE_UMTS 网络类型为UMTS
	 * 
	 * 在中国，联通的3G为UMTS或HSDPA，移动和联通的2G为GPRS或EGDE，电信的2G为CDMA，电信的3G为EVDO
	 */
	private String networkOperatorName;
	private String networkOperator;

	public String getNetworkOperator() {
		return networkOperator;
	}

	public void setNetworkOperator(String networkOperator) {
		this.networkOperator = networkOperator;
	}

	public String getNetworkOperatorName() {
		return networkOperatorName;
	}

	public void setNetworkOperatorName(String networkOperatorName) {
		this.networkOperatorName = networkOperatorName;
	}

	public String getDensity() {
		return density;
	}

	public String getXdip() {
		return xdip;
	}

	public String getYdip() {
		return ydip;
	}

	public String getDensityDpi() {
		return densityDpi;
	}

	private String density; // 屏幕密度（0.75 / 1.0 / 1.5）
	private String xdip;
	private String ydip;
	private String densityDpi; // 屏幕密度DPI（120 / 160 / 240）
	private String iccid; // ICCID为IC卡的唯一识别号码
	private String networkInfo; // 1wifi 0数据网络
	private String ip;
	private String starttime;
	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	private String starttime_str;

	public String getStarttime_str() {
		return starttime_str;
	}

	public void setStarttime_str(String starttime_str) {
		this.starttime_str = starttime_str;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNetworkInfo() {
		return networkInfo;
	}

	public void setNetworkInfo(String networkInfo) {
		this.networkInfo = networkInfo;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

}
