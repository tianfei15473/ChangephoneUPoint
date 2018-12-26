package com.unking.xposedpoint.utils;

import java.net.InetAddress;

public class IPv4Util {

	private final static int INADDRSZ = 4;

	/**
	 * ��IP��ַת��Ϊ�ֽ�����
	 * 
	 * @param ipAddr
	 * @return byte[]
	 */
	public static byte[] ipToBytesByInet(String ipAddr) {
		try {
			return InetAddress.getByName(ipAddr).getAddress();
		} catch (Exception e) {
			throw new IllegalArgumentException(ipAddr + " is invalid IP");
		}
	}

	/**
	 * ��IP��ַת��Ϊint
	 * 
	 * @param ipAddr
	 * @return int
	 */
	public static byte[] ipToBytesByReg(String ipAddr) {
		byte[] ret = new byte[4];
		try {
			String[] ipArr = ipAddr.split("\\.");
			ret[0] = (byte) (Integer.parseInt(ipArr[0]) & 0xFF);
			ret[1] = (byte) (Integer.parseInt(ipArr[1]) & 0xFF);
			ret[2] = (byte) (Integer.parseInt(ipArr[2]) & 0xFF);
			ret[3] = (byte) (Integer.parseInt(ipArr[3]) & 0xFF);
			return ret;
		} catch (Exception e) {
			throw new IllegalArgumentException(ipAddr + " is invalid IP");
		}

	}

	/**
	 * �ֽ�����ת��ΪIP
	 * 
	 * @param bytes
	 * @return int
	 */
	public static String bytesToIp(byte[] bytes) {
		return new StringBuffer().append(bytes[0] & 0xFF).append('.')
				.append(bytes[1] & 0xFF).append('.').append(bytes[2] & 0xFF)
				.append('.').append(bytes[3] & 0xFF).toString();
	}

	/**
	 * ���λ����� byte[] -> int
	 * 
	 * @param bytes
	 * @return int
	 */
	public static int bytesToInt(byte[] bytes) {
		int addr = bytes[0] & 0xFF;
		addr |= ((bytes[1] << 8) & 0xFF00);
		addr |= ((bytes[2] << 16) & 0xFF0000);
		addr |= ((bytes[3] << 24) & 0xFF000000);
		return addr;
	}

	/**
	 * ��IP��ַת��Ϊint
	 * 
	 * @param ipAddr
	 * @return int
	 */
	public static int ipToInt(String ipAddr) {
		try {
			return bytesToInt(ipToBytesByInet(ipAddr));
		} catch (Exception e) {
			throw new IllegalArgumentException(ipAddr + " is invalid IP");
		}
	}

	/**
	 * ipInt -> byte[]
	 * 
	 * @param ipInt
	 * @return byte[]
	 */
	public static byte[] intToBytes(int ipInt) {
		byte[] ipAddr = new byte[INADDRSZ];
		ipAddr[0] = (byte) ((ipInt >>> 24) & 0xFF);
		ipAddr[1] = (byte) ((ipInt >>> 16) & 0xFF);
		ipAddr[2] = (byte) ((ipInt >>> 8) & 0xFF);
		ipAddr[3] = (byte) (ipInt & 0xFF);
		return ipAddr;
	}

	/**
	 * ��int->ip��ַ
	 * 
	 * @param ipInt
	 * @return String
	 */
	public static String intToIp(int i) {
		return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
				+ "." + (i >> 24 & 0xFF);
	}

	/**
	 * ��192.168.1.1/24 ת��Ϊint���鷶Χ
	 * 
	 * @param ipAndMask
	 * @return int[]
	 */
	public static int[] getIPIntScope(String ipAndMask) {

		String[] ipArr = ipAndMask.split("/");
		if (ipArr.length != 2) {
			throw new IllegalArgumentException("invalid ipAndMask with: "
					+ ipAndMask);
		}
		int netMask = Integer.valueOf(ipArr[1].trim());
		if (netMask < 0 || netMask > 31) {
			throw new IllegalArgumentException("invalid ipAndMask with: "
					+ ipAndMask);
		}
		int ipInt = IPv4Util.ipToInt(ipArr[0]);
		int netIP = ipInt & (0xFFFFFFFF << (32 - netMask));
		int hostScope = (0xFFFFFFFF >>> netMask);
		return new int[] { netIP, netIP + hostScope };

	}

	/**
	 * ��192.168.1.1/24 ת��ΪIP���鷶Χ
	 * 
	 * @param ipAndMask
	 * @return String[]
	 */
	public static String[] getIPAddrScope(String ipAndMask) {
		int[] ipIntArr = IPv4Util.getIPIntScope(ipAndMask);
		return new String[] { IPv4Util.intToIp(ipIntArr[0]),
				IPv4Util.intToIp(ipIntArr[0]) };
	}

	/**
	 * ���IP �������루192.168.1.1 255.255.255.0��ת��ΪIP��
	 * 
	 * @param ipAddr
	 *            ipAddr
	 * @param mask
	 *            mask
	 * @return int[]
	 */
	public static int[] getIPIntScope(String ipAddr, String mask) {

		int ipInt;
		int netMaskInt = 0, ipcount = 0;
		try {
			ipInt = IPv4Util.ipToInt(ipAddr);
			if (null == mask || "".equals(mask)) {
				return new int[] { ipInt, ipInt };
			}
			netMaskInt = IPv4Util.ipToInt(mask);
			ipcount = IPv4Util.ipToInt("255.255.255.255") - netMaskInt;
			int netIP = ipInt & netMaskInt;
			int hostScope = netIP + ipcount;
			return new int[] { netIP, hostScope };
		} catch (Exception e) {
			throw new IllegalArgumentException("invalid ip scope express  ip:"
					+ ipAddr + "  mask:" + mask);
		}

	}

	/**
	 * ���IP �������루192.168.1.1 255.255.255.0��ת��ΪIP��
	 * 
	 * @param ipAddr
	 *            ipAddr
	 * @param mask
	 *            mask
	 * @return String[]
	 */
	public static String[] getIPStrScope(String ipAddr, String mask) {
		int[] ipIntArr = IPv4Util.getIPIntScope(ipAddr, mask);
		return new String[] { IPv4Util.intToIp(ipIntArr[0]),
				IPv4Util.intToIp(ipIntArr[0]) };
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String ipAddr = "192.168.8.1";

		byte[] bytearr = IPv4Util.ipToBytesByInet(ipAddr);

		StringBuffer byteStr = new StringBuffer();

		for (byte b : bytearr) {
			if (byteStr.length() == 0) {
				byteStr.append(b);
			} else {
				byteStr.append("," + b);
			}
		}
		System.out.println("IP: " + ipAddr + " ByInet --> byte[]: [ " + byteStr
				+ " ]");

		bytearr = IPv4Util.ipToBytesByReg(ipAddr);
		byteStr = new StringBuffer();

		for (byte b : bytearr) {
			if (byteStr.length() == 0) {
				byteStr.append(b);
			} else {
				byteStr.append("," + b);
			}
		}
		System.out.println("IP: " + ipAddr + " ByReg  --> byte[]: [ " + byteStr
				+ " ]");

		System.out.println("byte[]: " + byteStr + " --> IP: "
				+ IPv4Util.bytesToIp(bytearr));

		int ipInt = IPv4Util.ipToInt(ipAddr);

		System.out.println("IP: " + ipAddr + "  --> int: " + ipInt);

		System.out.println("int: " + ipInt + " --> IP: "
				+ IPv4Util.intToIp(ipInt));

		String ipAndMask = "192.168.1.1/24";

		int[] ipscope = IPv4Util.getIPIntScope(ipAndMask);
		System.out.println(ipAndMask + " --> int��ַ�Σ�[ " + ipscope[0] + ","
				+ ipscope[1] + " ]");

		System.out.println(ipAndMask + " --> IP ��ַ�Σ�[ "
				+ IPv4Util.intToIp(ipscope[0]) + ","
				+ IPv4Util.intToIp(ipscope[1]) + " ]");

		String ipAddr1 = "192.168.1.1", ipMask1 = "255.255.255.0";

		int[] ipscope1 = IPv4Util.getIPIntScope(ipAddr1, ipMask1);
		System.out.println(ipAddr1 + " , " + ipMask1 + "  --> int��ַ�� ��[ "
				+ ipscope1[0] + "," + ipscope1[1] + " ]");

		System.out.println(ipAddr1 + " , " + ipMask1 + "  --> IP��ַ�� ��[ "
				+ IPv4Util.intToIp(ipscope1[0]) + ","
				+ IPv4Util.intToIp(ipscope1[1]) + " ]");

	}

}
