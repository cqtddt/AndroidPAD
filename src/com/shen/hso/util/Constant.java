package com.shen.hso.util;

import android.os.Environment;

public class Constant {

	public static final String SDCARD_ROOT = Environment.getExternalStorageDirectory().toString();

	public static final int TOPO_BASE_INFO_INDEX = 0xaa0001;
	public static final int TOPO_NOT_IN_LIST_INDEX = 0x1988;
	public static final int TOP0_NOT_IN_LIST_REFRESH = 0;
	public static final int TOPO_NOT_IN_LIST_READ = 1;
	public static final int NOTIFY_OPERATION_ERROR = 2;
	
	
	public static final String APP_DIRETORY = "HSO_PAD";
	
	public static final String APP_SAVE_DATA_DIRECTORY = "HSO_DATA";
	public static final String EXPORT_FILE_PREFIX = "Data";
	public static final String CSV_FILE_SUFFIX = ".csv";
}
