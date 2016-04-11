package com.example.campusapp;

import com.example.argu.Marguments;
import com.example.bean.Personnel;
import com.example.myadapter.StudentListAdapter;
import com.example.otheractivity.ChengjiActivity;
import com.example.otheractivity.KechengbiaoActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 
 * 展示学生的信息
 * @author hyc
 *
 */

public class StudentListActivity extends Activity
{
	private ListView listView;
	private ImageView backImageView;
	// 暂时存储当前的用户
	private Personnel temppersonnel = Marguments.currentpersonnel;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_info_list);
		InitView();
	}

	private void InitView()
	{
		listView = (ListView) findViewById(R.id.lv_student);
		listView.setAdapter(new StudentListAdapter(this));
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				Marguments.currentpersonnel = Marguments.personnels
						.get(position % 7 + 8);
				Marguments.currentAccount = Marguments.currentpersonnel
						.getAccount();
				switch (Marguments.CourseOrMark)
				{
				case 1:
					startActivity(new Intent(getBaseContext(),
							KechengbiaoActivity.class));
					break;
				case 2:
					startActivity(new Intent(getBaseContext(),
							ChengjiActivity.class));
					break;
				default:
					break;
				}
			}
		});
		backImageView = (ImageView) findViewById(R.id.iv_back);
		backImageView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 结束当前页面时，用户恢复
				Marguments.currentpersonnel = temppersonnel;
				finish();
			}
		});
	}
}
