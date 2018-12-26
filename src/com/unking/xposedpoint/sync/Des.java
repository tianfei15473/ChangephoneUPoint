// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.unking.xposedpoint.sync;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class Des
{

    public Des()
    {
        DES = "DES";
    }

    private int byteArray2int(byte abyte0[])
    {
        byte abyte1[] = new byte[4];
        int i = -1 + abyte1.length;
        int j = -1 + abyte0.length;
        do
        {
            if(i < 0)
            {
                int k = (0xff & abyte1[0]) << 24;
                int l = (0xff & abyte1[1]) << 16;
                int i1 = (0xff & abyte1[2]) << 8;
                return (0xff & abyte1[3]) + (i1 + (k + l));
            }
            if(j >= 0)
                abyte1[i] = abyte0[j];
            else
                abyte1[i] = 0;
            i--;
            j--;
        } while(true);
    }

    private SecretKey generateKey(byte abyte0[])
        throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException
    {
        DESKeySpec deskeyspec = new DESKeySpec(abyte0);
        return SecretKeyFactory.getInstance(DES).generateSecret(deskeyspec);
    }

    private byte[] int2byteArray(int i)
    {
        byte abyte0[] = new byte[4];
        abyte0[0] = (byte)(i >>> 24);
        abyte0[1] = (byte)(i >>> 16);
        abyte0[2] = (byte)(i >>> 8);
        abyte0[3] = (byte)i;
        return abyte0;
    }

    public byte[] decrypt(byte abyte0[])
    {
        Cipher cipher;
        byte [] byteFina = null ;
        try {
            cipher = Cipher.getInstance ( "DES" );
            cipher.init(Cipher. DECRYPT_MODE , generateKey(abyte0) );
            byteFina = cipher.doFinal(abyte0);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        } finally {
            cipher = null ;
        }
        return byteFina;
    }

    public byte[] encrypt(byte byteS[])
    {
        byte [] byteFina = null ;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance ( "DES" );
            cipher.init(Cipher. ENCRYPT_MODE , generateKey(byteS) );
            byteFina = cipher.doFinal(byteS);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        } finally {
            cipher = null ;
        }
        return byteFina;
    }

    private String DES;
}
