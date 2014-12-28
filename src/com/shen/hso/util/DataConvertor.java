package com.shen.hso.util;

import android.R.integer;

public class DataConvertor {

	public static byte[] int2ByteArray(int res) {
		byte[] result = new byte[4];
		result[0] = (byte) (res & 0xff);
		result[1] = (byte) ((res >> 8) & 0xff);
		result[2] = (byte) ((res >> 16) & 0xff);
		result[3] = (byte) ((res >>> 24) & 0xff);
		return result;
	}

	public static byte[] short2ByteArray(short res) {
		byte[] result = new byte[2];
		result[0] = (byte) (res & 0xff);
		result[1] = (byte) ((res >> 8) & 0xff);
		return result;
	}

	public static int byteArray2Int(byte[] b, int iOffset) {
		if (b.length == 0 || iOffset + 4 > b.length) {
			return 0;
		}
		int value = 0;
		for (int i = 0; i < 4; i++) {
			int shift = i * 8;
			value += (b[i + iOffset] & 0x000000ff) << shift;
		}
		return value;
	}

	public static short byteArray2Short(byte[] b, int iOffset) {
		if (b.length == 0 || iOffset + 2 > b.length) {
			return 0;
		}
		short value = 0;
		for (int i = 0; i < 2; i++) {
			int shift = i * 8;
			value += (b[i + iOffset] & 0x00ff) << shift;
		}
		return value;
	}
	
	public static byte[] shortArray2ByteArray(short[] asRes)
	{
		byte[] result = new byte[asRes.length*2];
		for(int i=0;i<result.length;i =+2)
		{
			short sRes = asRes[i/2];
			result[i] = (byte)(sRes&0xff);
			result[i+1] = (byte)((sRes>>8)&0xff);
		}
		return result;
	}
	
	public static byte[] intArray2ByteArray(int[] aiRes)
	{
		byte[] result = new byte[aiRes.length*4];
		for(int i=0;i<result.length;i=+4)
		{
			int iRes = aiRes[i/4];
			result[i] = (byte)(iRes&0xff);
			result[i+1] = (byte)((iRes>>8)&0xff);
			result[i+2] = (byte)((iRes>>16)&0xff);
			result[i+3] = (byte)(iRes>>>24);
		}
		return result;
	}
	
	public static int[] byteArray2IntArray(byte[] b,int iOffset,int iLenght)
	{
		if (((b.length - iOffset)/4)<iLenght){
			return null;
		}
		int[] result = new int[iLenght];
		for(int i=iOffset;i< iOffset + iLenght;i++)
		{
			int shift = (i-iOffset)%4*8;
			result[(i-iOffset)/4] += (b[i]&0x000000ff)<<shift;
			
		}
		return result;
	}
	
	public static short[] byteArray2ShortArray(byte[] b,int iOffset,int iLenght)
	{
		if (((b.length - iOffset)/2)<iLenght){
			return null;
		}
		short[] result = new short[iLenght];
		for(int i=iOffset;i< iOffset + iLenght;i++)
		{
			int shift = (i-iOffset)%2*8;
			result[(i-iOffset)/2] += (b[i]&0x00ff)<<shift;
			
		}
		return result;
	}
}
