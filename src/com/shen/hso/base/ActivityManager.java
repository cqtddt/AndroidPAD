package com.shen.hso.base;

import java.util.Stack;

import android.R.integer;
import android.app.Activity;

public class ActivityManager {
	/*
	 * 由于静态单例对象没有作为Singleton的成员变量直接实例化，因此类加载时不会实例化Singleton，第一次调用getInstance()
	 * 时将加载内部类HolderClass
	 * ，在该内部类中定义了一个static类型的变量instance，此时会首先初始化这个成员变量，由Java虚拟机来保证其线程安全性
	 * ，确保该成员变量只能初始化一次。由于getInstance()方法没有任何线程锁定，因此其性能不会造成任何影响。
	 * 通过使用IoDH，我们既可以实现延迟加载
	 * ，又可以保证线程安全，不影响系统性能，不失为一种最好的Java语言单例模式实现方式（其缺点是与编程语言本身的特性相关
	 * ，很多面向对象语言不支持IoDH）。
	 */
	private ActivityManager() {

	}

	private Stack<Activity> activityStack;

	private static class HolderClass {
		private static ActivityManager instance = new ActivityManager();
	}

	public static ActivityManager getInstance() {

		return HolderClass.instance;
	}

	public void addActivity(Activity activity) {
		if (null != activityStack) {
			activityStack.add(activity);
		} else {
			activityStack = new Stack<Activity>();
			activityStack.add(activity);
		}
	}

	public Activity currentActivity() {
		Activity activity = null;
		if (null != activityStack && activityStack.size() != 0) {
			activity = activityStack.lastElement();
		} else {

		}
		return activity;
	}

	public void finishCurrentActivity() {
		currentActivity().finish();
		activityStack.remove(currentActivity());
	}

	public void finishActivity(Activity activity) {
		if (null != activity) {

			activityStack.remove(activity);
			// activity.finish();
		}
	}

	public void finishActivity(Class<?> cls) {
		if (null == cls) {
			return;
		}
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	public void finishAllActivity() {
		for (int i = 0; i < activityStack.size(); i++) {
			activityStack.get(i).finish();
			activityStack.remove(i);
		}
		activityStack.clear();
	}

	public void removeActivityFromStack(Activity activity) {
		activityStack.remove(activity);
	}

	public void appExit() {
		finishAllActivity();
		System.exit(0);
	}
	
	public int getActivityCount()
	{
		if(null != activityStack)
		{
			return activityStack.size();
		}
		return 0;
	}
}
