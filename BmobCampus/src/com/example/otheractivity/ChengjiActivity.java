package com.example.otheractivity;

import java.util.Random;

import com.example.argu.Marguments;
import com.example.bean.Mark;
import com.example.campusapp.R;
import com.example.campusapp.R.id;
import com.example.campusapp.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChengjiActivity extends Activity
{
	private LinearLayout linerLayout;
	private ImageView back;
	private TextView textView, title;
	private Random random = new Random(System.currentTimeMillis());

	// 监听手指滑动的事件，判断是否返回
	// 手指向右滑动时的最小速度 ，手指向右滑动时的最小距离
	private static final int XSPEED_MIN = 200, XDISTANCE_MIN = 150;
	// 记录手指按下时的横坐标 ， 记录手指移动时的横坐标
	private float xDown, xMove;
	// 用于计算手指滑动的速度。
	private VelocityTracker mVelocityTracker;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chengji);
		InitUI();
	}

	private void InitUI()
	{
		title = (TextView) findViewById(R.id.title);
		title.setText(Marguments.currentpersonnel.getName() + "的成绩表");

		linerLayout = (LinearLayout) findViewById(R.id.linerLayout);
		String temp = Marguments.currentAccount;

		// 为了方便与简化代码，stu1及stu2以外的学生显示stu1成绩
		if (!Marguments.currentAccount.equals("stu1")
				&& !Marguments.currentAccount.equals("stu2"))
			Marguments.currentAccount = "stu1";
		Mark mark;
		int j = 0;
		for (int i = 0; i < Marguments.marks.size(); i++)
		{
			mark = Marguments.marks.get(i);
			// 判断是否找到当前的用户各科目成绩
			if (mark.getAccount().equals(Marguments.currentAccount))
			{
				// 根据数据库设计的对应关系，将当前用户的科目成绩加以显示
				textView = new TextView(ChengjiActivity.this);
				textView.setText("\n" + mark.getTerm() + "     "
						+ mark.getSubject() + "\n学分:" + mark.getCredict()
						+ "   成绩:" + mark.getScore() + "   绩点:"
						+ mark.getPoint() + "   获得绩点:"
						+ mark.getObtaincredict() + "\n");
				textView.setWidth(80);
				// 每隔一行产生不同颜色背景，方便查看
				if (j % 2 == 0)
				{
					textView.setBackgroundResource(Marguments.randomcolorbg[Marguments
							.randnum()]);
				}
				linerLayout.addView(textView);
				j++;
			}
		}
		Marguments.currentAccount = temp;

		back = (ImageView) findViewById(R.id.iv_back);
		back.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}

}
