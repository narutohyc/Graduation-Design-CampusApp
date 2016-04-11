package com.example.bean;

import cn.bmob.v3.BmobObject;

public class Bulletin extends BmobObject
{
	// �������
	private String title;

	// ����Ķ���
	private String object;

	// ��������
	private String detail;

	// ����������
	private String data;

	// ���������ű���
	private String other;

	public Bulletin()
	{

	}

	public Bulletin(String title, String object, String detail, String data,
			String other)
	{
		super();
		this.title = title;
		this.object = object;
		this.detail = detail;
		this.data = data;
		this.other = other;
	}

	public String getObject()
	{
		return object;
	}

	public String getTitle()
	{
		return title;
	}

	public String getDetail()
	{
		return detail;
	}

	public String getData()
	{
		return data;
	}

	public String getOther()
	{
		return other;
	}

	public void setObject(String object)
	{
		this.object = object;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	public void setData(String data)
	{
		this.data = data;
	}

	public void setOther(String other)
	{
		this.other = other;
	}

	@Override
	public String toString()
	{
		return "Bulletin [title=" + title + ", object=" + object + ", detail="
				+ detail + ", data=" + data + ", other=" + other + "]";
	}
}
