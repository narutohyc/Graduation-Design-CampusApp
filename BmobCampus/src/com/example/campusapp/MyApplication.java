package com.example.campusapp;

import cn.bmob.v3.Bmob;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;
import com.baidu.mapapi.SDKInitializer;

/**
 * 
 * 完成第三方SDK的初始化
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
		// 初始化ORM框架
		ActiveAndroid.initialize(this);

		// 注意该方法要再setContentView方法之前实现
		// 在使用Intel X86测试应关闭
		// SDKInitializer.initialize(this);

		Bmob.initialize(this, "4ab58f0656d690b0dd2032155a0cd36d");
	}
}
