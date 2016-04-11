package com.example.bean;

import cn.bmob.v3.BmobObject;

public class Personnel extends BmobObject
{
	private String mid;

	private String identity;

	private String name;

	private String account;

	private String passward;

	private String portraitid;// 记录头像的Id

	private String pageid;// 记录该显示的页面

	private String department;

	private String specialty;

	private String sex;

	public Personnel()
	{
	}

	public Personnel(String mid, String identity, String name, String account,
			String passward, String portraitid, String pageid,
			String department, String specialty, String sex)
	{
		setMid(mid);
		setIdentity(identity);
		setName(name);
		setAccount(account);
		setPassward(passward);
		setPortraitid(portraitid);
		setPageid(pageid);
		setDepartment(department);
		setSpecialty(specialty);
		setSex(sex);
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getMid()
	{
		return mid;
	}

	public void setMid(String mid)
	{
		this.mid = mid;
	}

	public String getIdentity()
	{
		return identity;
	}

	public void setIdentity(String identity)
	{
		this.identity = identity;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAccount()
	{
		return account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

	public String getPassward()
	{
		return passward;
	}

	public void setPassward(String passward)
	{
		this.passward = passward;
	}

	public String getPortraitid()
	{
		return portraitid;
	}

	public void setPortraitid(String portraitid)
	{
		this.portraitid = portraitid;
	}

	public String getPageid()
	{
		return pageid;
	}

	public void setPageid(String pageid)
	{
		this.pageid = pageid;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public String getSpecialty()
	{
		return specialty;
	}

	public void setSpecialty(String specialty)
	{
		this.specialty = specialty;
	}

	@Override
	public String toString()
	{
		return "Personnel [mid=" + mid + ", identity=" + identity + ", name="
				+ name + ", account=" + account + ", passward=" + passward
				+ ", portraitid=" + portraitid + ", pageid=" + pageid
				+ ", department=" + department + ", specialty=" + specialty
				+ ", sex=" + sex + "]";
	}
}
