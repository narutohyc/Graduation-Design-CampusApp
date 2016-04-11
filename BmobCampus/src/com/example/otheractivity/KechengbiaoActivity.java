package com.example.otheractivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.argu.Marguments;
import com.example.bean.Course;
import com.example.campusapp.R;
import com.example.campusapp.R.id;
import com.example.campusapp.R.layout;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class KechengbiaoActivity extends Activity
{
	private ImageView back;
	private Button item[][] = { { null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null } };

	private static List<MessageSet> messageSets = new ArrayList<MessageSet>();
	private MessageSet messageSet;
	private TextView title;

	private static Builder builder = null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kechengbiao);
		InitUI();
	}

	private void InitUI()
	{
		title = (TextView) findViewById(R.id.title);
		title.setText(Marguments.currentpersonnel.getName() + "的课程表");

		builder = new Builder(KechengbiaoActivity.this);

		Course course = new Course();
		back = (ImageView) findViewById(R.id.iv_back);
		back.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		item[0][0] = (Button) findViewById(R.id.item00);
		item[0][1] = (Button) findViewById(R.id.item01);
		item[0][2] = (Button) findViewById(R.id.item02);
		item[0][3] = (Button) findViewById(R.id.item03);
		item[0][4] = (Button) findViewById(R.id.item04);
		item[0][5] = (Button) findViewById(R.id.item05);
		item[0][6] = (Button) findViewById(R.id.item06);

		item[1][0] = (Button) findViewById(R.id.item10);
		item[1][1] = (Button) findViewById(R.id.item11);
		item[1][2] = (Button) findViewById(R.id.item12);
		item[1][3] = (Button) findViewById(R.id.item13);
		item[1][4] = (Button) findViewById(R.id.item14);
		item[1][5] = (Button) findViewById(R.id.item15);
		item[1][6] = (Button) findViewById(R.id.item16);

		item[2][0] = (Button) findViewById(R.id.item20);
		item[2][1] = (Button) findViewById(R.id.item21);
		item[2][2] = (Button) findViewById(R.id.item22);
		item[2][3] = (Button) findViewById(R.id.item23);
		item[2][4] = (Button) findViewById(R.id.item24);
		item[2][5] = (Button) findViewById(R.id.item25);
		item[2][6] = (Button) findViewById(R.id.item26);

		item[3][0] = (Button) findViewById(R.id.item30);
		item[3][1] = (Button) findViewById(R.id.item31);
		item[3][2] = (Button) findViewById(R.id.item32);
		item[3][3] = (Button) findViewById(R.id.item33);
		item[3][4] = (Button) findViewById(R.id.item34);
		item[3][5] = (Button) findViewById(R.id.item35);
		item[3][6] = (Button) findViewById(R.id.item36);

		item[4][0] = (Button) findViewById(R.id.item40);
		item[4][1] = (Button) findViewById(R.id.item41);
		item[4][2] = (Button) findViewById(R.id.item42);
		item[4][3] = (Button) findViewById(R.id.item43);
		item[4][4] = (Button) findViewById(R.id.item44);
		item[4][5] = (Button) findViewById(R.id.item45);
		item[4][6] = (Button) findViewById(R.id.item46);
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 7; j++)
			{
				item[i][j].setOnClickListener(onClickListener);
			}

		String temp = Marguments.currentAccount;

		// 为了方便与简化代码，stu1及stu2以外的学生显示stu1成绩
		if (!Marguments.currentAccount.equals("stu1")
				&& !Marguments.currentAccount.equals("stu2"))
			Marguments.currentAccount = "stu1";

		for (int i = 0; i < Marguments.courses.size(); i++)
		{
			course = Marguments.courses.get(i);
			// 判断是否找到当前的用户课表
			if (course.getAccount().equals(Marguments.currentAccount))
			{
				// 根据数据库设计的对应关系，将当前用户的课表信息加以显示
				int m = Integer.parseInt(course.getTime()) - 1;// 代表时间
				int n = Integer.parseInt(course.getWeek()) - 1; // 代表星期
				item[m][n].setText(course.getSubject() + "\n"
						+ course.getClassroom());
				item[m][n]
						.setBackgroundResource(Marguments.randomcolorbg[Marguments
								.randnum()]);
				// 记录当前
				messageSet = new MessageSet(item[m][n].getId(), course);
				messageSets.add(messageSet);
			}
		}
		Marguments.currentAccount = temp;
	}

	private OnClickListener onClickListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			int len = messageSets.size(), vid = v.getId();
			for (int i = 0; i < len; i++)
			{
				messageSet = messageSets.get(i);
				if (vid == messageSet.getLocation())
				{
					builder.setMessage(
							"教师:" + messageSet.getCourse().getTeacher()
									+ "    教室:"
									+ messageSet.getCourse().getClassroom()
									+ "\n科目:"
									+ messageSet.getCourse().getSubject()
									+ "\n学分:"
									+ messageSet.getCourse().getCredict()
									+ "\n起讫周序:"
									+ messageSet.getCourse().getWeeknum())
							.setTitle("科目详情");
					builder.create().show();
				}
			}
		}
	};

	// 创建内部类，用于点击课表查看详情使用
	// 建立每个科目坐标的对应课程信息
	private class MessageSet
	{
		private int location;
		private Course course;

		public MessageSet()
		{
		}

		public MessageSet(int location, Course course)
		{
			setLocation(location);
			setCourse(course);
		}

		public int getLocation()
		{
			return location;
		}

		public void setLocation(int location)
		{
			this.location = location;
		}

		public Course getCourse()
		{
			return course;
		}

		public void setCourse(Course course)
		{
			this.course = course;
		}

	}

	
}
