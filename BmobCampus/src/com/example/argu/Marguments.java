package com.example.argu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.bean.ChatMessage;
import com.example.bean.Course;
import com.example.bean.Mark;
import com.example.bean.MessageToTeacher;
import com.example.bean.Personnel;
import com.example.campusapp.R;

/**
 * 
 * 传递参数时使用，定义在外部，方便传参
 * 
 * @author hyc
 * 
 */

public class Marguments
{
	public static String ip1="10.0.2.2:80";
	public static String ip2="192.168.1.100";
	
	// position,column分别表示类型与行号,用于教务Fragment
	public static int position = 0, column = 0;

	// 登陆者的账号，密码，头像ID，页面ID
	public static String currentAccount = "-1", currentPassward = "-1";
	public static String currentPortraitId = "-1", currentPageId = "-1";
	public static Personnel currentpersonnel = null;

	// 当前数据库中的Person
	public static List<Personnel> personnels = new ArrayList<Personnel>();
	// 当前数据库中的Course
	public static List<Course> courses = new ArrayList<Course>();
	// 当前数据库中的Mark
	public static List<Mark> marks = new ArrayList<Mark>();

	// 当教师身份登陆时，用于判断是课表的点击还是成绩的点击
	// 1为来自课的点击 2为来自成绩的点击
	public static int CourseOrMark = 1;

	// 头像数组
	public static int[] portraits = { R.drawable.system, R.drawable.stutea1,
			R.drawable.stutea2, R.drawable.stutea3, R.drawable.stutea4,
			R.drawable.stutea5, R.drawable.stutea6, R.drawable.stutea7};

	// 定义课程表和成绩表的随机背景图片资源数组
	public static int randomcolorbg[] = { R.drawable.randombg1,
			R.drawable.randombg2, R.drawable.randombg3, R.drawable.randombg4,
			R.drawable.randombg5, R.drawable.randombg6, R.drawable.randombg7,
			R.drawable.randombg8 };

	//接受来自系统管理员的信息
	public static List<MessageToTeacher> receivefromsystem = new ArrayList<MessageToTeacher>(); 
	
	//聊天时记录下师生的信息
	public static List<Personnel> students = new ArrayList<Personnel>();
	public static List<Personnel> teachers = new ArrayList<Personnel>();
		
	public static List<ChatMessage> TeaToStu_OutMessages = new ArrayList<ChatMessage>();
	public static List<ChatMessage> TeaToStu_InMessages = new ArrayList<ChatMessage>();
	
	
	// 产生0-7的随机整数，结合随机背景图片资源使用
	public static int randnum()
	{
		Random random = new Random();
		return random.nextInt(8) % 8;
	}

	// 检查用户是否合法
	public static Boolean find()
	{
		Personnel personnel;
		for (int i = 0; i < personnels.size(); i++)
		{
			personnel = personnels.get(i);
			// 当用户为合法人员时，记录当前用户的信息
			if (personnel.getAccount().equals(currentAccount)
					&& personnel.getPassward().equals(currentPassward))
			{
				currentpersonnel = personnel;
				currentPageId = personnel.getPageid();
				currentPortraitId = personnel.getPortraitid();
				return true;
			}
		}
		return false;
	}
}
