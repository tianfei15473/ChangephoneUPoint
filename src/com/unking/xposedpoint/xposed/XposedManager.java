package com.unking.xposedpoint.xposed;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.setStaticObjectField;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ContentResolver;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.view.Display;

import com.unking.xposedpoint.task.data.DataStore;
import com.unking.xposedpoint.utils.IPv4Util;
import com.unking.xposedpoint.utils.SdCardUtil;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class XposedManager implements IXposedHookLoadPackage {
	private JSONObject json;

	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		/*
		 * if (lpparam.packageName.equals("com.example.testpoint") ||
		 * lpparam.packageName.equals("com.itings.myradio") ||
		 * lpparam.packageName.equals("com.ifeng.news2") ||
		 * lpparam.packageName.equals("com.baidu.browser.apps")) {
		 */
		System.out.println("lpparam.packageName------------>"
				+ lpparam.packageName);
		if (!lpparam.packageName.equals("de.robv.android.xposed.installer") && !lpparam.packageName.equals("com.android.phone")
				&& !lpparam.packageName.equals("org.codeaurora.btmultisim")
				&& !lpparam.packageName
						.equals("com.android.providers.telephony")
				//!lpparam.packageName.equals("de.robv.android.xposed.installer")
				/*&& !lpparam.packageName.equals("com.android.phone")
				&& !lpparam.packageName
						.equals("com.android.providers.telephony")
				&& !lpparam.packageName.equals("com.android.dialer")
				&& !lpparam.packageName.equals("com.oppo.virusdetect")
				&& !lpparam.packageName.equals("com.oppo.gesture")
				&& !lpparam.packageName.equals("com.nearme.themespacelib")
				&& !lpparam.packageName.equals("com.qualcomm.gsmtuneaway")
				&& !lpparam.packageName.equals("org.codeaurora.btmultisim")*/

		) {
			File temp_prop = new File((new StringBuilder()).append(SdCardUtil.getSDPath())
	                .append("/point/prop/").append("temp_device.properties")
	                .toString());
			if (!temp_prop.exists()) {
				System.out.println("no------------------");
				return;
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(temp_prop)));

			StringBuffer sb = new StringBuffer();
			String s = "";
			while ((s = reader.readLine()) != null) {
				sb.append(s);
			}

			reader.close();
			System.out.println("111111111lpparam.packageName------------>"
					+ lpparam.packageName);
			json = new JSONObject(sb.toString());

			// 加载完JSON 设置设备信息
			setProductData();

			Object[] v0 = new Object[0];
			// 系统属性值serialno
			Object[] v5 = new Object[2];
			v5[0] = String.class;
			v5[1] = String.class;
			this.addHook("android.os.SystemProperties", lpparam.classLoader,
					"get", v5);
			this.addHook("android.os.SystemProperties", lpparam.classLoader,
					"get", new Object[] { String.class });

			// IMEI=DeviceId
			this.addHook(TelephonyManager.class.getName(), lpparam.classLoader,
					"getDeviceId", v0);
			// SubscriberId
			this.addHook(TelephonyManager.class.getName(), lpparam.classLoader,
					"getSubscriberId", v0);

			// android_id
			Object[] v2 = new Object[2];
			v2[0] = ContentResolver.class.getName();
			v2[1] = String.class.getName();
			this.addHook(Settings.Secure.class.getName(), lpparam.classLoader,
					"getString", v2);
			// 修改Mac
			addHook(WifiInfo.class.getName(), lpparam.classLoader,
					"getMacAddress", v0);

			// 修改SSID
			addHook(WifiInfo.class.getName(), lpparam.classLoader, "getSSID",
					v0);

			// 修改BSSID
			addHook(WifiInfo.class.getName(), lpparam.classLoader, "getBSSID",
					v0);

			// 修改Ip
			addHook(WifiInfo.class.getName(), lpparam.classLoader,
					"getIpAddress", v0);

			// ro.build.id 版本ID
			// ro.build.display.id 版本号

			// 获取手机宽高 及分辨率
			this.addHook(Display.class.getName(), lpparam.classLoader,
					"getMetrics",
					new Object[] { DisplayMetrics.class.getName() });
			// 精度
			this.addHook(Location.class.getName(), lpparam.classLoader,
					"getLatitude", v0);
			// 维度
			this.addHook(Location.class.getName(), lpparam.classLoader,
					"getLongitude", v0);
			// 手机号
			this.addHook(TelephonyManager.class.getName(), lpparam.classLoader,
					"getLine1Number", v0);
			// 网络类型
			this.addHook(TelephonyManager.class.getName(), lpparam.classLoader,
					"getNetworkType", v0);

			// ro.networktype.name
			this.addHook(TelephonyManager.class.getName(), lpparam.classLoader,
					"hasIccCard", v0);
			// CHINA MOBILE
			this.addHook(TelephonyManager.class.getName(), lpparam.classLoader,
					"getNetworkOperatorName", v0);

			// 46000
			this.addHook(TelephonyManager.class.getName(), lpparam.classLoader,
					"getNetworkOperator", v0);

			// 获取SIM状态
			this.addHook(TelephonyManager.class.getName(), lpparam.classLoader,
					"getSimState", v0);

			this.addHook(NetworkInfo.class.getName(), lpparam.classLoader,
					"getTypeName", v0);

			this.addHook(GsmCellLocation.class.getName(), lpparam.classLoader,
					"getLac", v0);
			this.addHook(GsmCellLocation.class.getName(), lpparam.classLoader,
					"getCid", v0);

			this.addHook(TelephonyManager.class.getName(), lpparam.classLoader,
					"getSimSerialNumber", v0);

			// 获取网络状态
			this.addHook(ConnectivityManager.class.getName(),
					lpparam.classLoader, "getNetworkInfo",
					new Object[] { Integer.TYPE.getName() });

			this.addHook("android.app.ApplicationPackageManager",
					lpparam.classLoader, "getInstalledPackages",
					new Object[] { Integer.TYPE.getName() });
			this.addHook(ActivityManager.class.getName(), lpparam.classLoader,
					"getRunningAppProcesses", new Object[0]);
			this.addHook("android.app.ApplicationPackageManager",
					lpparam.classLoader, "getInstalledApplications",
					new Object[] { Integer.TYPE.getName() });
			/*---------------------------------------------*/

			
			//this.addHook(NetworkInfo.class.getName(), lpparam.classLoader, "getSubTypeName", v0);
			
			/*this.addHook(NetworkInfo.class.getName(), lpparam.classLoader,
					"getSubType", v0);*/

			this.addHook(TelephonyManager.class.getName(), lpparam.classLoader,
					"getPhoneType", v0);

			this.addHook(TelephonyManager.class.getName(), lpparam.classLoader,
					"getSimCountryIso", v0);
			this.addHook(TelephonyManager.class.getName(), lpparam.classLoader,
					"getSimOperator", v0);
			this.addHook(TelephonyManager.class.getName(), lpparam.classLoader,
					"getSimOperatorName", v0);
			this.addHook(TelephonyManager.class.getName(), lpparam.classLoader,
					"getNetworkCountryIso", v0);

			this.addHook(Build.class.getName(), lpparam.classLoader,
					"getRadioVersion", v0);

			this.addHook(CdmaCellLocation.class.getName(), lpparam.classLoader,
					"getNetworkId", v0);
			this.addHook(CdmaCellLocation.class.getName(), lpparam.classLoader,
					"getBaseStationId", v0);
			this.addHook(NeighboringCellInfo.class.getName(),
					lpparam.classLoader, "getLac", v0);
			this.addHook(NeighboringCellInfo.class.getName(),
					lpparam.classLoader, "getCid", v0);

			this.addHook(WifiManager.class.getName(), lpparam.classLoader,
					"getScanResults", v0);

			this.addHook("android.app.ApplicationPackageManager",
					lpparam.classLoader, "getInstalledPackages",
					new Object[] { Integer.TYPE.getName() });
			this.addHook(ActivityManager.class.getName(), lpparam.classLoader,
					"getRunningAppProcesses", new Object[0]);
			this.addHook("android.app.ApplicationPackageManager",
					lpparam.classLoader, "getInstalledApplications",
					new Object[] { Integer.TYPE.getName() });

			/*
			 * XposedBridge.hookAllMethods(XposedHelpers.findClass(
			 * "android.os.SystemProperties", lpparam.classLoader), "get", new
			 * XC_MethodHook() {
			 * 
			 * @Override protected void afterHookedMethod(MethodHookParam param)
			 * throws Throwable { if
			 * ("ro.product.brand".equals(param.args[0].toString())) {
			 * param.setResult(json.getString("Brand")); } else if
			 * ("ro.product.model".equals(param.args[0].toString())) {
			 * param.setResult(json.getString("Model")); } } });
			 */

		}
	}
	
	private void addHook(final String className, ClassLoader classLoader,
			String methodName, Object[] parameterTypesAndCallback) {

		XC_MethodHook v1 = new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(MethodHookParam param)
					throws Throwable {
				// TODO Auto-generated method stub
				super.afterHookedMethod(param);

				/*
				 * System.out.println("after---classname---->" + className);
				 * System.out.println("after---param.method---->" +
				 * param.method.getName()); if (param.args.length > 0) for (int
				 * i = 0; i < param.args.length; i++) {
				 * System.out.println("before---param.args" + i + "------>" +
				 * param.args[i]); }
				 */

				if (className.equals("android.app.ApplicationPackageManager")) {
					if (param.method.getName().equals("getInstalledPackages")) {
						if (param.getResult() == null)
							return;
						List<PackageInfo> p_list = (List<PackageInfo>) param
								.getResult();
						if (p_list != null)
							for (int i = 0; i < p_list.size(); i++) {
								PackageInfo info = p_list.get(i);
								if ("de.robv.android.xposed.installer"
										.equals(info.packageName)
										|| "com.unking.xposedpoint"
												.equals(info.packageName))
									p_list.remove(i);
							}

						param.setResult(p_list);

					} else if (param.method.getName().equals(
							"getInstalledApplications")) {
						if (param.getResult() == null)
							return;
						List<ApplicationInfo> a_list = (List<ApplicationInfo>) param
								.getResult();
						if (a_list != null)
							for (int i = 0; i < a_list.size(); i++) {
								ApplicationInfo info = a_list.get(i);
								if ("de.robv.android.xposed.installer"
										.equals(info.packageName)
										|| "com.unking.xposedpoint"
												.equals(info.packageName))
									a_list.remove(i);
							}

						param.setResult(a_list);
					}
				} else if (className.equals(ActivityManager.class.getName())) {
					if (param.method.getName().equals("getRunningAppProcesses")) {
						if (param.getResult() == null)
							return;
						List<RunningAppProcessInfo> r_list = (List<RunningAppProcessInfo>) param
								.getResult();
						if (r_list != null)
							for (int i = 0; i < r_list.size(); i++) {
								RunningAppProcessInfo info = r_list.get(i);
								if ("de.robv.android.xposed.installer"
										.equals(info.processName)
										|| "com.unking.xposedpoint"
												.equals(info.processName))
									r_list.remove(i);
							}

						param.setResult(r_list);
					}
				}

			}

			@Override
			protected void beforeHookedMethod(MethodHookParam param)
					throws Throwable {
				// TODO Auto-generated method stub
				super.beforeHookedMethod(param);
				
				System.out.println("before---classname---->" + className);
				System.out.println("before---param.method---->"
						+ param.method.getName());
				if (param.args.length > 0)
					for (int i = 0; i < param.args.length; i++) {
						System.out.println("before---param.args" + i
								+ "------>" + param.args[i]);
					}

				if (className.equals(TelephonyManager.class.getName())) {
					if (param.method.getName().equals("getDeviceId")) {
						Object obj = json.getString("Imei");
						param.setResult(obj);
						
						System.out.println("deviceId---->" + json.getString("Imei"));
					} else if (param.method.getName().equals("getSubscriberId")) {
						Object obj = json.getString("Carrier");
						param.setResult(obj);
					} else if (param.method.getName().equals("getLine1Number")) {
						Object obj = json.getString("Line1number");
						param.setResult(obj);
					} else if (param.method.getName().equals("getNetworkType")) {
						Object obj = Integer.parseInt(json
								.getString("NetworkType"));
						param.setResult(obj);
					} else if (param.method.getName().equals("hasIccCard")) {
						Object obj = new Random().nextInt(2) == 0 ? true
								: false;
						param.setResult(obj);
					} else if (param.method.getName().equals(
							"getNetworkOperatorName")) {
						Object obj = json.getString("NetworkOperatorName");
						param.setResult(obj);
					} else if (param.method.getName().equals(
							"getNetworkOperator")) {
						Object obj = json.getString("NetworkOperator");
						param.setResult(obj);
					} else if (param.method.getName().equals("getSimState")) {
						int ran = new Random().nextInt(2) == 0 ? 0 : 5;
						Object obj = ran;
						param.setResult(obj);
					} else if (param.method.getName().equals(
							"getSimSerialNumber")) {
						Object obj = json.getString("Iccid");
						param.setResult(obj);
					}
				} else if (className.equals("android.os.SystemProperties")) {
					if (param.args.length > 0) {
						if (param.args[0].equals("ro.serialno")) {
							Object obj = json.getString("Serialno");
							param.setResult(obj);
						} else if (param.args[0].equals("ro.build.id")) {
							Object obj = json.getString("Id");
							param.setResult(obj);
						} else if (param.args[0].equals("ro.build.display.id")) {
							Object obj = json.getString("Id");
							param.setResult(obj);
						} else if (param.args[0].equals("ro.networktype.name")) {
							Object obj = (String) DataStore.getInstance().networkMap
									.get(Integer.parseInt(json
											.getString("NetworkType")));
							param.setResult(obj);
						}
					}
				} else if (className.equals(Settings.Secure.class.getName())) {
					if (param.args.length <= 1) {
						return;
					}

					if (param.args[1].equals("android_id")) {
						Object obj = json.getString("AndroidId");
						param.setResult(obj);
						return;
					}
				} else if (className.equals(WifiInfo.class.getName())) {
					if (param.method.getName().equals("getMacAddress")) {
						Object obj = json.getString("Mac");
						param.setResult(obj);
					} else if (param.method.getName().equals("getSSID")) {
						String networkinfo = json.getString("NetworkInfo");
						Object obj;
						if (networkinfo != null) {
							if ("1".equals(networkinfo))
								obj = json.getString("Ssid");
							else
								obj = "<unknow ssid>";
						} else
							obj = json.getString("Ssid");

						param.setResult(obj);
					} else if (param.method.getName().equals("getBSSID")) {
						String networkinfo = json.getString("NetworkInfo");
						Object obj;
						if (networkinfo != null) {
							if ("1".equals(networkinfo))
								obj = json.getString("Bssid");
							else
								obj = null;
						} else
							obj = json.getString("Ssid");

						param.setResult(obj);
					} else if (param.method.getName().equals("getIpAddress")) {
						Object obj = IPv4Util.ipToInt(json.getString("Ip"));
						param.setResult(obj);
					}
				} else if (className.equals(Display.class.getName())) {
					/*
					 * if (param.method.getName().equals("getMetrics")) { if
					 * (param.args.length <= 0) return;
					 * 
					 * param.getResult(); Object v3 = param.args[0];
					 * 
					 * ((DisplayMetrics) v3).heightPixels = Integer
					 * .parseInt(json.getString("Height")); ((DisplayMetrics)
					 * v3).widthPixels = Integer
					 * .parseInt(json.getString("Width")); ((DisplayMetrics)
					 * v3).density = Float.parseFloat(json
					 * .getString("Density"));
					 * 
					 * ((DisplayMetrics) v3).scaledDensity = Float
					 * .parseFloat(json.getString("Density")); ((DisplayMetrics)
					 * v3).xdpi = Float.parseFloat(json .getString("Xdip"));
					 * ((DisplayMetrics) v3).ydpi = Float.parseFloat(json
					 * .getString("Ydip")); ((DisplayMetrics) v3).densityDpi =
					 * Integer.parseInt(json .getString("DensityDpi"));
					 * param.setResult(v3);
					 * 
					 * }
					 */
				} else if (className.equals(Location.class.getName())) {
					if (param.method.getName().equals("getLatitude")) {
						Object obj = Double.parseDouble(json
								.getString("Latitude"));
						param.setResult(obj);
					} else if (param.method.getName().equals("getLongitude")) {
						Object obj = Double.parseDouble(json
								.getString("Longitude"));
						param.setResult(obj);
					}
				} else if (className.equals(NetworkInfo.class.getName())) {
					if (param.method.getName().equals("getTypeName")) {
						String networkinfo = json.getString("NetworkInfo");
						Object obj;
						if ("1".equals(networkinfo))
							obj = "WIFI";
						else if ("0".equals(networkinfo))
							obj = "MOBILE";
						else
							obj = "WIFI";

						param.setResult(obj);
					} else if (param.method.getName().equals("getType")) {
						Object obj = Integer.parseInt(json
								.getString("NetworkInfo"));
						param.setResult(obj);
					}
				} else if (className.equals(GsmCellLocation.class.getName())) {
					if (param.method.getName().equals("getLac")) {
						Object obj = new Random().nextInt(64435);
						param.setResult(obj);
					} else if (param.method.getName().equals("getCid")) {
						Object obj = new Random().nextInt(64435);
						param.setResult(obj);
					}
				} else if (className
						.equals(ConnectivityManager.class.getName())) {
					if (param.method.getName().equals("getNetworkInfo")) {
						if (param.method.getName().equals("getNetworkInfo")) {
							if (param.args.length <= 0)
								return;

							int nettypeinfo = Integer.valueOf(json
									.getString("NetworkInfo"));
							if (param.args[0].equals(Integer.valueOf(1))) { // wifi
								if (nettypeinfo == 0)
									param.setResult(null);
							} else if (param.args[0].equals(Integer.valueOf(0))) { // 数据网络
								if (nettypeinfo == 1)
									param.setResult(null);
							}
						}
					}
				}
			}
		};

		Object[] v0 = new Object[parameterTypesAndCallback.length + 1];
		for (int v2 = 0; v2 < parameterTypesAndCallback.length; ++v2) {
			v0[v2] = parameterTypesAndCallback[v2];
		}

		v0[parameterTypesAndCallback.length] = v1;
		findAndHookMethod(className, classLoader, methodName, v0);
	}

	private void setProductData() {
		try {

			Random random = new Random();
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2012);
			calendar.set(Calendar.MONTH, 10);
			calendar.set(Calendar.DATE, 11);
			calendar.add(Calendar.DAY_OF_MONTH, random.nextInt(365));
			String s = (new StringBuilder())
					.append(calendar.get(calendar.YEAR))
					.append(String.valueOf(100 + calendar.get(calendar.MONTH))
							.substring(1))
					.append(String.valueOf(100 + calendar.get(calendar.DATE))
							.substring(1)).toString();

			// 手机型号
			XposedHelpers.setStaticObjectField(android.os.Build.class, "BRAND",
					json.getString("Brand"));
			XposedHelpers.setStaticObjectField(android.os.Build.class, "MODEL",
					json.getString("Model"));

			XposedHelpers.setStaticObjectField(android.os.Build.class,
					"PRODUCT", json.getString("Product"));

			// android机型
			XposedHelpers.setStaticObjectField(android.os.Build.class,
					"MANUFACTURER", json.getString("Manufacturer"));

			XposedHelpers.setStaticObjectField(android.os.Build.class,
					"DEVICE", "enterprise_" + json.getString("Model"));

			XposedHelpers.setStaticObjectField(android.os.Build.VERSION.class,
					"RELEASE", json.getString("Release"));

			XposedHelpers.setStaticObjectField(android.os.Build.VERSION.class,
					"SDK", json.getString("Sdk"));

			// setStaticObjectField(Build.class, "CPU_ABI", v1[0]);
			// setStaticObjectField(Build.class, "CPU_ABI2", v1[1]);

			XposedHelpers.setStaticObjectField(
					android.os.Build.class,
					"FINGERPRINT",
					new StringBuilder(json.getString("Product"))
							.append("/enterprise_")
							.append(json.getString("Model"))
							.append("/enterprise_")
							.append(json.getString("Model"))
							.append(":4.0.4/IMM76L/user.td.").append(s)
							.append(".").append(random.nextInt(999999))
							.append(":user/release-keys").toString());

			XposedHelpers.setStaticObjectField(android.os.Build.class,
					"HARDWARE", json.getString("Hardware"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
