package com.unking.xposedpoint.sync;

import com.unking.xposedpoint.log.LLogger;
import com.unking.xposedpoint.task.net.Utils;
import com.unking.xposedpoint.utils.StreamUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Enumeration;

/**
 * 同步服务器36890端口
 */
public class SyncChannel extends Channel
{
    public SyncChannel()
    {
        port = 36890;
        HOST = getServerHost();
    }

    private boolean checkIp(String paramString)
    {
        try
        {
            InetSocketAddress localInetSocketAddress = new InetSocketAddress(paramString, this.port);
            this.socket = new Socket();
            this.socket.connect(localInetSocketAddress, 1000);
            this.out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            writeln("exit");
            return true;
        }
        catch (Exception localException)
        {
            LLogger.error("checkIp", paramString);
            return false;
        }
        finally
        {
            disconnect();
        }
    }

    /**
     * 通过本机WIFI地址获取局域网中的服务器地址（固定113结尾
     * @return
     */
    private String getServerHost()
    {
        String ipAddress = null;
        try
        {
            Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
            while (localEnumeration1.hasMoreElements())
            {
                NetworkInterface localNetworkInterface = (NetworkInterface)localEnumeration1.nextElement();
                if ("wlan0".equals(localNetworkInterface.getDisplayName()))
                {
                    Enumeration localEnumeration2 = localNetworkInterface.getInetAddresses();
                    while (localEnumeration2.hasMoreElements())
                    {
                        InetAddress localInetAddress = (InetAddress)localEnumeration2.nextElement();
                        if (!localInetAddress.isLoopbackAddress())
                        {
                            String str = localInetAddress.getHostAddress();
                            ipAddress = str;
                        }
                    }

                    String[] arrayOfString = ipAddress.split("[.]");
                    return arrayOfString[0] + "." + arrayOfString[1] + "." + arrayOfString[2] + "." + "113";
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ipAddress;
    }

    public void checkIp()
    {
        try
        {
            if ((!Utils.isEmpty(HOST)) && (checkIp(HOST)))
                return;
            String str = getServerHost();
            for (int i = 0; i < 255; i++)
            {
                HOST = str + i;
                boolean bool = checkIp(HOST);
                if (bool)
                    break;
            }
        }
        catch (Exception localException)
        {
            LLogger.debug("error", localException);
        }
    }

    /**
     * 尝试连接服务器的36890端口,超时5000毫秒
     * @throws Exception
     */
    public void connect()
        throws Exception
    {
            InetSocketAddress inetsocketaddress = new InetSocketAddress(HOST, port);
            socket = new Socket();
            socket.connect(inetsocketaddress, 5000);
            socket.setSoTimeout(5000);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    public void disconnect()
    {
        StreamUtils.close(out);
        StreamUtils.close(in);
        try
        {
            socket.close();
            return;
        }
        catch(Exception exception)
        {
            return;
        }
    }

    public static String HOST;
    private int port;
    private Socket socket;
}
