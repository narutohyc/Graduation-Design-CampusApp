package com.example.bean;

import cn.bmob.v3.BmobObject;

public class Mark extends BmobObject
{
	private String account;

	private String term;

	private String subject;

	private String credict;

	private String score;

	private String point;

	private String obtaincredict;

	public Mark()
	{
	}

	public Mark(String account, String term, String subject, String credict,
			String score, String point, String obtaincredict)
	{
		setAccount(account);
		setTerm(term);
		setSubject(subject);
		setCredict(credict);
		setScore(score);
		setPoint(point);
		setObtaincredict(obtaincredict);
	}

	public String getAccount()
	{
		return account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

	public String getTerm()
	{
		return term;
	}

	public void setTerm(String term)
	{
		this.term = term;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getCredict()
	{
		return credict;
	}

	public void setCredict(String credict)
	{
		this.credict = credict;
	}

	public String getScore()
	{
		return score;
	}

	public void setScore(String score)
	{
		this.score = score;
	}

	public String getPoint()
	{
		return point;
	}

	public void setPoint(String point)
	{
		this.point = point;
	}

	public String getObtaincredict()
	{
		return obtaincredict;
	}

	public void setObtaincredict(String obtaincredict)
	{
		this.obtaincredict = obtaincredict;
	}

	@Override
	public String toString()
	{
		return "Mark [account=" + account + ", term=" + term + ", subject="
				+ subject + ", credict=" + credict + ", score=" + score
				+ ", point=" + point + ", obtaincredict=" + obtaincredict + "]";
	}
}
