package com.example.bean;

public class ChatMessage
{
	//��Ϣ�����ߵ���Ϣ
	private Personnel info;
	//��Ϣ����
	private String msg;
	//��Ϣ�ķ�������
	private String date;
	//��Ϣ������
	private Type type;

	public ChatMessage(Personnel info, String msg, String date, Type type)
	{
		super();
		this.info = info;
		this.msg = msg;
		this.date = date;
		this.type = type;
	}

	public enum Type
	{
		INCOMING, OUTCOMING
	}

	public ChatMessage()
	{
	}

	public Personnel getInfo()
	{
		return info;
	}

	public void setInfo(Personnel info)
	{
		this.info = info;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	@Override
	public String toString()
	{
		return "ChatMessage [getInfo()=" + getInfo() + ", getMsg()=" + getMsg()
				+ ", getType()=" + getType() + ", getDate()=" + getDate() + "]";
	}
}
