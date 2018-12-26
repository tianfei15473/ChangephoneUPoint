package com.unking.xposedpoint.cmd;

public class StringUtils {

	public static String uniteString(String[] paramArrayOfString) {
		StringBuilder localStringBuilder = new StringBuilder();
		int i = paramArrayOfString.length;
		for (int j = 0;; j++) {
			if (j >= i)
				return localStringBuilder.toString();
			localStringBuilder.append(paramArrayOfString[j]);
		}
	}

}
