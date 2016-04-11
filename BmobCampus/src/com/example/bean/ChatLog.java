package com.example.bean;

import cn.bmob.v3.BmobObject;

@SuppressWarnings("serial")
public class ChatLog extends BmobObject
{
	private String to;
	private String from;
	private String sendDate;
	private String msg;
	private String state;

	public ChatLog()
	{
		super();

	}

	public ChatLog(String to, String from, String sendDate, String msg,
			String state)
	{
		super();
		this.to = to;
		this.from = from;
		this.sendDate = sendDate;
		this.msg = msg;
		this.state = state;
	}

	public ChatLog(String to, String sendDate, String msg, String state)
	{
		super();
		this.to = to;
		this.sendDate = sendDate;
		this.msg = msg;
		this.state = state;
	}

	public String getFrom()
	{
		return from;
	}

	public void setFrom(String from)
	{
		this.from = from;
	}

	public ChatLog(String to, String sendDate, String msg)
	{
		this(to, sendDate, msg, "Î´¶Á");
	}

	public String getTo()
	{
		return to;
	}

	public void setTo(String to)
	{
		this.to = to;
	}

	public String getSendDate()
	{
		return sendDate;
	}

	public void setSendDate(String sendDate)
	{
		this.sendDate = sendDate;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	@Override
	public String toString()
	{
		return "ChatLog [to=" + to + ", from=" + from + ", sendDate="
				+ sendDate + ", msg=" + msg + ", state=" + state + "]";
	}
}
