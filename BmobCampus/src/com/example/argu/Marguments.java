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
 * ���ݲ���ʱʹ�ã��������ⲿ�����㴫��
 * 
 * @author hyc
 * 
 */

public class Marguments
{
	public static String ip1="10.0.2.2:80";
	public static String ip2="192.168.1.100";
	
	// position,column�ֱ��ʾ�������к�,���ڽ���Fragment
	public static int position = 0, column = 0;

	// ��½�ߵ��˺ţ����룬ͷ��ID��ҳ��ID
	public static String currentAccount = "-1", currentPassward = "-1";
	public static String currentPortraitId = "-1", currentPageId = "-1";
	public static Personnel currentpersonnel = null;

	// ��ǰ���ݿ��е�Person
	public static List<Personnel> personnels = new ArrayList<Personnel>();
	// ��ǰ���ݿ��е�Course
	public static List<Course> courses = new ArrayList<Course>();
	// ��ǰ���ݿ��е�Mark
	public static List<Mark> marks = new ArrayList<Mark>();

	// ����ʦ��ݵ�½ʱ�������ж��ǿα�ĵ�����ǳɼ��ĵ��
	// 1Ϊ���Կεĵ�� 2Ϊ���Գɼ��ĵ��
	public static int CourseOrMark = 1;

	// ͷ������
	public static int[] portraits = { R.drawable.system, R.drawable.stutea1,
			R.drawable.stutea2, R.drawable.stutea3, R.drawable.stutea4,
			R.drawable.stutea5, R.drawable.stutea6, R.drawable.stutea7};

	// ����γ̱�ͳɼ�����������ͼƬ��Դ����
	public static int randomcolorbg[] = { R.drawable.randombg1,
			R.drawable.randombg2, R.drawable.randombg3, R.drawable.randombg4,
			R.drawable.randombg5, R.drawable.randombg6, R.drawable.randombg7,
			R.drawable.randombg8 };

	//��������ϵͳ����Ա����Ϣ
	public static List<MessageToTeacher> receivefromsystem = new ArrayList<MessageToTeacher>(); 
	
	//����ʱ��¼��ʦ������Ϣ
	public static List<Personnel> students = new ArrayList<Personnel>();
	public static List<Personnel> teachers = new ArrayList<Personnel>();
		
	public static List<ChatMessage> TeaToStu_OutMessages = new ArrayList<ChatMessage>();
	public static List<ChatMessage> TeaToStu_InMessages = new ArrayList<ChatMessage>();
	
	
	// ����0-7���������������������ͼƬ��Դʹ��
	public static int randnum()
	{
		Random random = new Random();
		return random.nextInt(8) % 8;
	}

	// ����û��Ƿ�Ϸ�
	public static Boolean find()
	{
		Personnel personnel;
		for (int i = 0; i < personnels.size(); i++)
		{
			personnel = personnels.get(i);
			// ���û�Ϊ�Ϸ���Աʱ����¼��ǰ�û�����Ϣ
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
