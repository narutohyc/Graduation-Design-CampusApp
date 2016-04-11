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

	// ������ָ�������¼����ж��Ƿ񷵻�
	// ��ָ���һ���ʱ����С�ٶ� ����ָ���һ���ʱ����С����
	private static final int XSPEED_MIN = 200, XDISTANCE_MIN = 150;
	// ��¼��ָ����ʱ�ĺ����� �� ��¼��ָ�ƶ�ʱ�ĺ�����
	private float xDown, xMove;
	// ���ڼ�����ָ�������ٶȡ�
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
		title.setText(Marguments.currentpersonnel.getName() + "�ĳɼ���");

		linerLayout = (LinearLayout) findViewById(R.id.linerLayout);
		String temp = Marguments.currentAccount;

		// Ϊ�˷�����򻯴��룬stu1��stu2�����ѧ����ʾstu1�ɼ�
		if (!Marguments.currentAccount.equals("stu1")
				&& !Marguments.currentAccount.equals("stu2"))
			Marguments.currentAccount = "stu1";
		Mark mark;
		int j = 0;
		for (int i = 0; i < Marguments.marks.size(); i++)
		{
			mark = Marguments.marks.get(i);
			// �ж��Ƿ��ҵ���ǰ���û�����Ŀ�ɼ�
			if (mark.getAccount().equals(Marguments.currentAccount))
			{
				// �������ݿ���ƵĶ�Ӧ��ϵ������ǰ�û��Ŀ�Ŀ�ɼ�������ʾ
				textView = new TextView(ChengjiActivity.this);
				textView.setText("\n" + mark.getTerm() + "     "
						+ mark.getSubject() + "\nѧ��:" + mark.getCredict()
						+ "   �ɼ�:" + mark.getScore() + "   ����:"
						+ mark.getPoint() + "   ��ü���:"
						+ mark.getObtaincredict() + "\n");
				textView.setWidth(80);
				// ÿ��һ�в�����ͬ��ɫ����������鿴
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
