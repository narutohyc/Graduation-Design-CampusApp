package com.example.campusapp;

import cn.bmob.v3.Bmob;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;
import com.baidu.mapapi.SDKInitializer;

/**
 * 
 * ��ɵ�����SDK�ĳ�ʼ��
 * 
 * @author hyc
 * 
 */
public class MyApplication extends Application
{
	@Override
	public void onCreate()
	{
		super.onCreate();
		// ��ʼ��ORM���
		ActiveAndroid.initialize(this);

		// ע��÷���Ҫ��setContentView����֮ǰʵ��
		// ��ʹ��Intel X86����Ӧ�ر�
		// SDKInitializer.initialize(this);

		Bmob.initialize(this, "4ab58f0656d690b0dd2032155a0cd36d");
	}
}
