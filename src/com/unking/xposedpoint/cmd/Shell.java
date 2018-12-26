package com.unking.xposedpoint.cmd;

import android.annotation.SuppressLint;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@SuppressLint("SdCardPath")
public class Shell {

	public static Process localProcess;
	public static OutputStream localOutputStream;
	public static DataOutputStream localDataOutputStream;
	public static InputStream localInputStream;
	public static DataInputStream localDataInputStream;

	public DataOutputStream dos = null;

	private static Shell instance;

	public Shell() throws IOException {
		// TODO Auto-generated constructor stub
		Shell.localProcess = Runtime.getRuntime().exec("su" + "\n");
		Shell.localOutputStream = Shell.localProcess.getOutputStream();
		Shell.localInputStream = Shell.localProcess.getInputStream();
		Shell.localOutputStream.write("echo \"executed su command.\"\n"
				.getBytes());
	}

	public static Shell getInstance() {
		try {
			if (instance == null) {
				instance = new Shell();
			}
			return instance;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

    public void point(String as[]) {
        int i = 0;
        int j = as.length;
        while (i < j) {
            execRootCmdSilent(as[i] + " \n ");
            i++;
        }
    }


	public void execRootCmdSilent(String paramString) {
		try {
			if (paramString != null) {
				Shell.localOutputStream.write(paramString.getBytes());
				Shell.localOutputStream.write("echo \"\"\n".getBytes());

				Shell.localOutputStream.flush();
			}

		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	public void reboot() {

		String[] arrayOfString = new String[1];
		arrayOfString[0] = "su -c reboot; \n";
		try {
			Runtime.getRuntime().exec("su -c \"/system/bin/reboot\"");
		} catch (IOException e) {
			e.printStackTrace();
		}
		execRootCmdSilent(StringUtils.uniteString(arrayOfString));

	}

    public void copyFromTo() {
        try {
            String cmd = "mount -o remount /dev/block/platform/sdhci-tegra.3/by-name/APP /system" + " \n " +
                    "chmod 777 /system" + " \n";
            execRootCmdSilent(cmd);
            Thread.sleep(2000);

            File from_file = new File("/sdcard-ext/build.prop");
            if (from_file.exists()) {
                FileInputStream fosfrom = new FileInputStream(from_file);

                File toFile = new File("/system/build.prop");
                if (toFile.exists()) {
                    toFile.delete();
                }

                FileOutputStream fosto = new FileOutputStream(toFile, false);

                byte bt[] = new byte[1024 * 4];
                int c;
                while ((c = fosfrom.read(bt)) > 0) {
                    fosto.write(bt, 0, c);
                }
                fosfrom.close();
                fosto.close();

                String c_str = "chmod 777 /system/build.prop " + " \n";
                execRootCmdSilent(c_str);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
