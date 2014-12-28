package com.shen.hso.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import com.shen.hso.entity.TopoNotInListData;
import com.shen.hso.util.Constant;

public class NotInListModel extends BaseModel {

	private List<TopoNotInListData> datas = null;
	private String[] name = new String[] { "张三", "李四", "王五", "赵六", "田七", "孙八",
			"周九", "吴十", "申一" };
	private String[] job = new String[] { "程序员", "工程师", "教师", "学生", "农民", "工人",
			"销售员", "总裁", "主席" };
	private String[] company = new String[] { "google", "apple", "IBM",
			"Microsoft", "facebook", "you tube", "Samsung", "HTC", "LG" };
	private String[] phone = new String[] { "182340828", "132632054",
			"162632054", "192632054", "192380828", "188340828", "166660828",
			"182349999", "182555828" };
	private String family = "北京市海淀区北清路中关村环保科技园";
	private String email = "121123";

	private boolean isOver = false;
	public NotInListModel() {

	}

	public NotInListModel(Handler mInfoHandler) {
		super(mInfoHandler);
	}

	@Override
	public void run() {
		super.run();
		while (!isOver) {
			Random random = new Random();
			TopoNotInListData data = null;
			Message msg = null;
			switch (mModelState) {
			case start:
				SystemClock.sleep(300);
				break;
			case refresh:
				if (null == datas) {
					datas = new ArrayList<TopoNotInListData>();
				} else {
					datas.clear();
				}
				int size = random.nextInt(1000);
				for (int i = 0; i < size; i++) {
					data = new TopoNotInListData();
					data.setId(String.valueOf(i));
					data.setName(name[i % 9] + i);
					data.setEmail(email + random.nextInt(1000) + "@qq.com");
					data.setFamilyAddress(family + i + "号");
					datas.add(data);
				}
				mModelState = ModelState.start;
				msg = Message.obtain();
				msg.what = Constant.TOPO_NOT_IN_LIST_INDEX;
				msg.arg1 = Constant.TOP0_NOT_IN_LIST_REFRESH;
				msg.obj = datas;
				getHandler().sendMessage(msg);
				
				break;
			case read:
				if (null == mListToRead || mListToRead.size() <= 0) {
					mModelState = ModelState.start;
					msg = Message.obtain();
					msg.what = Constant.TOPO_NOT_IN_LIST_INDEX;
					msg.arg1 = Constant.NOTIFY_OPERATION_ERROR;
					msg.obj = data;
					getHandler().sendMessage(msg);
					break;
				}
				for (int i = 0; i < mListToRead.size(); i++) {
					int value = random.nextInt(100);
					data = (TopoNotInListData) mListToRead.get(i);
					data.setJobName(job[value%9]);
					data.setCompanyName(company[random.nextInt(9)]);
					if (value < 10) {
						data.setPhoneNum(phone[value % 9] + "0" + value);
					} else {
						data.setPhoneNum(phone[value % 9] + value);
					}
					mModelState = ModelState.start;
					msg = Message.obtain();
					msg.what = Constant.TOPO_NOT_IN_LIST_INDEX;
					msg.arg1 = Constant.TOPO_NOT_IN_LIST_READ;
					msg.obj = data;
					getHandler().sendMessage(msg);
					SystemClock.sleep(300);
				}
				break;

			case over:
				isOver = true;
				mModelState = ModelState.none;
				break;
			default:
				break;
			}
		}

	}

	@Override
	public void start() {
		super.start();
		new Thread(this).start();
		mModelState = ModelState.start;

	}

	@Override
	public void stop() {
		super.stop();
		mModelState = ModelState.over;
	}

	@Override
	public void refresh() {
		super.refresh();
		if(mModelState == ModelState.none){
			start();
		}
		mModelState = ModelState.refresh;
	}

	@Override
	public void read(List<?> list) {
		super.read(list);
		mModelState = ModelState.read;
	}
}
