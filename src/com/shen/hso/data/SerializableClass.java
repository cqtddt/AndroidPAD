package com.shen.hso.data;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Locale;

import android.util.SparseArray;

import com.shen.hso.util.DataConvertor;

public abstract class SerializableClass {

	public byte[] toByteArray() throws IllegalArgumentException,IllegalAccessException
	{
		byte[] abyField = null;
		byte[] abyRect = null;
		int iTotalSize = 0;
		Field[] fields = this.getClass().getDeclaredFields();
		byte[][] resultTmpList = new byte[fields.length][];
		int id = 0;
		sortField(fields);
		
		try {
			for(Field field : fields)
			{
				if(field.isAnnotationPresent(InstanceConvertor.class))
				{
					InstanceConvertor fieldMark = field.getAnnotation(InstanceConvertor.class);
					id = fieldMark.id();
					if(null == field.get(this))
					{
						continue;
					}
					if(fieldMark.type().toLowerCase(Locale.getDefault()).equals("int"))
					{
						int ifield = field.getInt(this);
						abyField = DataConvertor.int2ByteArray(ifield);
					}
					else if(fieldMark.type().toLowerCase(Locale.getDefault()).equals("short"))
					{
						short sfield = field.getShort(this);
						abyField = DataConvertor.short2ByteArray(sfield);
					}
					else if(fieldMark.type().toLowerCase(Locale.getDefault()).equals("byte"))
					{
						byte byField = field.getByte(this);
						abyField = new byte[1];
						abyField[0] = byField;
					}
					else if(fieldMark.type().toLowerCase(Locale.getDefault()).equals("ashort"))
					{
						short[] saField = (short[]) field.get(this);
						abyField = DataConvertor.shortArray2ByteArray(saField);
					}
					else if(fieldMark.type().toLowerCase(Locale.getDefault()).equals("abyte"))
					{
						abyField = (byte[])field.get(this);
					}
					else if(fieldMark.type().toLowerCase(Locale.getDefault()).equals("aint"))
					{
						int[] iaField = (int[]) field.get(this);
						abyField = DataConvertor.intArray2ByteArray(iaField);
					}
					else if(fieldMark.type().toLowerCase(Locale.getDefault()).equals("class"))
					{
						SerializableClass sclass = (SerializableClass) field.get(this);
						abyField = sclass.toByteArray();
					}
					else if(fieldMark.type().toLowerCase(Locale.getDefault()).equals("aclass"))
					{
						Object object = field.get(this);
						SerializableClass sClass = null;
						byte[] abyTemp;
						abyField = null;
						for(int i=0;i<getBufferSize();i++)
						{
							sClass = (SerializableClass) Array.get(object, i);
							abyTemp = sClass.toByteArray();
							if(null == abyField)
							{
								abyField = abyTemp;
							}
							else {
								int oldLenght = abyField.length;
								abyField = Arrays.copyOf(abyField, abyField.length+abyTemp.length);
								System.arraycopy(abyTemp, 0, abyField, oldLenght, abyTemp.length);
							}
						}
					}
					else {
						continue;
					}
					if(null != abyField)
					{
						iTotalSize += abyField.length;
						resultTmpList[id-1] = abyField;
					}
				}
			}
			
			
			abyRect = new byte[iTotalSize];
			int iOffset = 0;
			for(int i=0;i<resultTmpList.length;++i)
			{
				if(null != resultTmpList[i])
				{
					System.arraycopy(resultTmpList[i], 0, abyRect, iOffset, resultTmpList[i].length);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return abyRect;
	}
	
	public int toClass(byte[] buffer,int offset)
	{
		int ioffSizeValue = offset;
		int iOffSetBit = 0;
		int iNumBits = 0;
		Field[] fields = this.getClass().getDeclaredFields();
		sortField(fields);
		try {
			for(Field field:fields)
			{
				if(field.isAnnotationPresent(InstanceConvertor.class))
				{
					InstanceConvertor fieldMark = field.getAnnotation(InstanceConvertor.class);
					iNumBits = fieldMark.bit();
					if(fieldMark.type().toLowerCase(Locale.getDefault()).equals("int"))
					{
						int iValue = DataConvertor.byteArray2Int(buffer,ioffSizeValue);
						if(iNumBits !=0)
						{
							iValue = (iValue>>iOffSetBit)&((int)Math.pow(2.0, iNumBits)-1);
							iOffSetBit += iNumBits;
							if(iOffSetBit%32 == 0)
							{
								ioffSizeValue += 4;
								iOffSetBit = 0;
							}
						}
						else {
							ioffSizeValue += fieldMark.size();
							iOffSetBit = 0;
						}
						field.setInt(this, iValue);
					}
					else if(fieldMark.type().toLowerCase(Locale.getDefault()).equals("short"))
					{
						short sValue = DataConvertor.byteArray2Short(buffer,ioffSizeValue);
						if(iNumBits !=0)
						{
							sValue = (short) ((sValue>>iOffSetBit)&((int)Math.pow(2.0, iNumBits)-1));
							iOffSetBit += iNumBits;
							if(iOffSetBit%16 == 0)
							{
								ioffSizeValue += 2;
								iOffSetBit = 0;
							}
						}
						else {
							ioffSizeValue += fieldMark.size();
							iOffSetBit = 0;
						}
						field.setShort(this, sValue);
					}
					else if(fieldMark.type().toLowerCase(Locale.getDefault()).equals("byte"))
					{
						byte bValue = 0;
						if(ioffSizeValue < buffer.length)
							{
							bValue = buffer[ioffSizeValue];
							}
						if(iNumBits !=0)
						{
							bValue = (byte) ((bValue>>iOffSetBit)&((int)Math.pow(2.0, iNumBits)-1));
							iOffSetBit += iNumBits;
							if(iOffSetBit%8 == 0)
							{
								ioffSizeValue += 2;
								iOffSetBit = 0;
							}
						}
						else {
							ioffSizeValue += fieldMark.size();
							iOffSetBit = 0;
						}
						field.setByte(this, bValue);
					}
					else if(fieldMark.type().toLowerCase(Locale.getDefault()).equals("aShort")&&fieldMark.size()!=0)
					{
						short[] asValue = DataConvertor.byteArray2ShortArray(buffer,ioffSizeValue,fieldMark.size());
						if(asValue == null)
						{
							asValue = new short[fieldMark.size()];
							ioffSizeValue += asValue.length * 2;
						}
						field.set(this, asValue);
					}
					else if(fieldMark.type().toLowerCase(Locale.getDefault()).equals("abyte")&&fieldMark.size()!=0)
					{
						byte[] abValue = new byte[fieldMark.size()];
						if(ioffSizeValue + abValue.length <= buffer.length)
						{
							System.arraycopy(buffer, ioffSizeValue, abValue, 0, abValue.length);
						}
						field.set(this, abValue);
				}
					else if(fieldMark.type().toLowerCase(Locale.getDefault()).equals("class"))
					{
						SerializableClass sClass = (SerializableClass) field.getType().newInstance();
						ioffSizeValue += sClass.toClass(buffer, ioffSizeValue);
						field.set(this, sClass);
					}
					else if(fieldMark.type().toLowerCase(Locale.getDefault()).equals("aclass"))
					{
						SerializableClass sClass = null;
						int iBufferSize = getBufferSize();
						Object aClass = Array.newInstance(field.getType().getComponentType(),iBufferSize);
						for(int i=0;i<iBufferSize;i++)
							{
							sClass = (SerializableClass) field.getType().getComponentType().newInstance();
							ioffSizeValue += sClass.toClass(buffer, ioffSizeValue);
							Array.set(aClass, i, sClass);
							}
						
						field.set(this, aClass);
					}
					else if(fieldMark.type().toLowerCase(Locale.getDefault()).equals("abyte")&&fieldMark.size()==0)
					{
						int iBufferSize = getBufferSize();
						byte[] abyValue = new byte[iBufferSize];
						if(buffer.length - ioffSizeValue >= iBufferSize&& abyValue.length>= iBufferSize)
						{
							System.arraycopy(buffer, ioffSizeValue, abyValue, 0, iBufferSize);
						}
						field.set(this, abyValue);
					}
					else {
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ioffSizeValue - iOffSetBit;
	}
	
	
	public void sortField(Field[] fields)
	{
		SparseArray<Field> fieldMap = new SparseArray<Field>();
		for(int i=0;i<fields.length;i++)
		{
			if(fields[i].isAnnotationPresent(InstanceConvertor.class))
			{
				InstanceConvertor fieldMark = fields[i].getAnnotation(InstanceConvertor.class);
				fieldMap.put(fieldMark.id(), fields[i]);
			}
		}
		
		for(int i=0;i<fields.length;i++)
		{
			fields[i] = fieldMap.get(i+1);
		}
	}
	
	public abstract int getBufferSize();
}
