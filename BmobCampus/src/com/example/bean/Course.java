package com.example.bean;

import cn.bmob.v3.BmobObject;

public class Course extends BmobObject
{
	private String account;

	private String teacher;

	private String classroom;

	private String subject;

	// 1表示8:00-9:40
	// 2表示10:00-11:40
	// 3表示14:00-15:40
	// 4表示16:00-17:40
	// 5表示19:00-9:40

	private String time;

	private String week;

	private String credict;

	private String weeknum;

	public Course()
	{
	}

	public Course(String account, String teacher, String classroom,
			String subject, String time, String week, String credict,
			String weeknum)
	{
		setAccount(account);
		setTeacher(teacher);
		setClassroom(classroom);
		setSubject(subject);
		setTime(time);
		setWeek(week);
		setCredict(credict);
		setWeeknum(weeknum);
	}

	public String getAccount()
	{
		return account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

	public String getTeacher()
	{
		return teacher;
	}

	public void setTeacher(String teacher)
	{
		this.teacher = teacher;
	}

	public String getClassroom()
	{
		return classroom;
	}

	public void setClassroom(String classroom)
	{
		this.classroom = classroom;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public String getWeek()
	{
		return week;
	}

	public void setWeek(String week)
	{
		this.week = week;
	}

	public String getCredict()
	{
		return credict;
	}

	public void setCredict(String credict)
	{
		this.credict = credict;
	}

	public String getWeeknum()
	{
		return weeknum;
	}

	public void setWeeknum(String weeknum)
	{
		this.weeknum = weeknum;
	}

	@Override
	public String toString()
	{
		return "Course [account=" + account + ", teacher=" + teacher
				+ ", classroom=" + classroom + ", subject=" + subject
				+ ", time=" + time + ", week=" + week + ", credict=" + credict
				+ ", weeknum=" + weeknum + "]";
	}
}
