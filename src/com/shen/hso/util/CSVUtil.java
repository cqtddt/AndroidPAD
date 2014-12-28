package com.shen.hso.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import android.os.AsyncTask;

import com.shen.hso.R;
import com.shen.hso.entity.Data;
import com.shen.hso.entity.ExportData;

public class CSVUtil {
	
	public static List<String> convertDataToStringList(List<Data> dataList)
	{
		String csvSeperaterString = ",";
		List<String> strList = new ArrayList<String>();
		//String exportDataTile = StringUtil.getStringFromXml(R.string.export_data_title);
		String exportDataTile = "编号,姓名,年龄,";
		strList.add(exportDataTile);
		for(Data data : dataList)
		{
			StringBuffer sbBuffer = new StringBuffer();
			String csvStr = data.toCSVString();
			sbBuffer.append(csvStr).append(csvSeperaterString);
			strList.add(sbBuffer.toString());
		}
		return strList;
	}

	public static Boolean exportCSV(File file, List<String> contentList,
			AsyncTask<?, ?, ?> asyncTask) {
		
		String enterChar = "\r\n";
		boolean isSuccess = false;
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		int fileSize = 0 ;
		boolean isCanceled = false;
		
		for(String str:contentList)
		{
			isCanceled = asyncTask.isCancelled();
			if(isCanceled){
				return false;
			}
			 fileSize += StringUtil.getWordCount(str);
		}
		 if(!CommonUtil.enoughSpaceOnSdCard(fileSize))
		 {
			 isSuccess = false;
			 return isSuccess;
		 }
		 try {
			if(!file.exists())
				file.createNewFile();
			
			fos = new FileOutputStream(file);
			osw = new OutputStreamWriter(fos,"GBK");
			bw = new BufferedWriter(osw);
			if(null != contentList&&!contentList.isEmpty())
			{
				for (String string :contentList)
				{
					isCanceled = asyncTask.isCancelled();
					if(isCanceled){
						return false;
					}
					bw.append(string).append(enterChar);
				}
				isSuccess = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		 finally{
			try {
				if(null != bw)
				{
					bw.flush();
					bw.close();
					bw = null;
				}
				if(null != osw)
				{
					osw.close();
					osw = null;
				}
				if(null != fos)
				{
					fos.close();
					fos = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
		 }
		return isSuccess;
	}

	public static Data readCsv(File file)
	{
		BufferedReader br = null;
		Data data = new Data() {
			
			@Override
			public String toCSVString() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		//List<RowData> dataList;
		List<String> column;
		//RowData row;
		int maxColumn = 0;
		int no = 0;
		
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
			String lineString = "";
			while ((lineString = br.readLine()) != null) {
				no ++;
				StringTokenizer st = new StringTokenizer(lineString,",");
				column = new ArrayList<String>();
				while (st.hasMoreTokens()) {
					column.add(st.nextToken());
				}
				maxColumn = ((maxColumn > column.size()) ? maxColumn:column.size());
			}
			return data;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return data;
	}
}
