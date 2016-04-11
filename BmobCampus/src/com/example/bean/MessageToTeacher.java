package com.example.bean;

import cn.bmob.v3.BmobObject;

/**
 * 
 * 管理人员给教师发送信息
 * @author hyc
 *
 */
public class MessageToTeacher extends BmobObject
{
	// 消息的对象
	private String name;

	// 内容
	private String detail;

	// 发布的日期
	private String date;

	// 状态
	private String state;

	public MessageToTeacher()
	{

	}

	public MessageToTeacher(String name, String detail, String date,
			String state)
	{
		super();
		this.name = name;
		this.detail = detail;
		this.date = date;
		this.state = state;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDetail()
	{
		return detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}
}
