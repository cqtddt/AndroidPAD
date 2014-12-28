package com.shen.hso.task;

import java.io.File;
import java.util.List;

import android.os.AsyncTask;

import com.shen.hso.entity.Data;
import com.shen.hso.listener.AsyncTaskNoticeViewListener;
import com.shen.hso.util.CSVUtil;
import com.shen.hso.util.CommonUtil;
import com.shen.hso.util.Constant;

public class ExportCSVTask extends AsyncTask<Void, Void, Boolean> {

	private AsyncTaskNoticeViewListener mNoticeViewListener;
	private List<Data> mDataList;
	private String mFileDirectoryPath;
	private String mFileName;
	private String mCurrentTime;
	private Data mData;

	public ExportCSVTask(AsyncTaskNoticeViewListener mNoticeViewListener,
			List<Data> mDataList, String mCurrentTime,String fileName) {
		super();
		this.mNoticeViewListener = mNoticeViewListener;
		this.mDataList = mDataList;
		this.mCurrentTime = mCurrentTime;
		this.mFileName = fileName;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mNoticeViewListener.onInitViewPreExecute();
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		Boolean isSuccess = false;
		List<String> contentList = CSVUtil.convertDataToStringList(mDataList);

		if (null == contentList || contentList.isEmpty()) {
			return false;
		}
		if (CommonUtil.sdCardIsAvailable()) {
			File dirRoot = new File(Constant.SDCARD_ROOT + File.separator
					+ Constant.APP_DIRETORY);
			if (!dirRoot.exists()) {
				dirRoot.mkdir();
			}
			File dir = new File(dirRoot.getAbsolutePath() + File.separator
					+ Constant.APP_SAVE_DATA_DIRECTORY);

			if (dir.exists()) {
				dir.mkdir();
			}

			try {
				String fileNamePrefix;
				fileNamePrefix = Constant.EXPORT_FILE_PREFIX;
				String fileName = dir.toString() + fileNamePrefix
						+ mFileName + mCurrentTime + Constant.CSV_FILE_SUFFIX;
				File createFile = new File(fileName);
				if (!createFile.exists()) {
					if (!createFile.createNewFile()) {
						return false;
					}
				}
				isSuccess = CSVUtil.exportCSV(createFile, contentList, this);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return isSuccess;
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
		mNoticeViewListener.onRefreshViewProgressUpdate(values);
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		mNoticeViewListener.onUpdateViewPostExecute(result);
	}

	

	@Override
	protected void onCancelled(Boolean result) {
		super.onCancelled(result);
		mNoticeViewListener.onUpdateViewCancelled();
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
		mNoticeViewListener.onUpdateViewCancelled();
	}
	
	

}
