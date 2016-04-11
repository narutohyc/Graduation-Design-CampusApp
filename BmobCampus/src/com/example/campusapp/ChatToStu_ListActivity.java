package com.example.campusapp;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.listener.InitListener;

import com.example.argu.Marguments;
import com.example.bean.Personnel;
import com.example.myadapter.Chat_stuAdapter;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ChatToStu_ListActivity extends Activity
{
	private ListView stuList;
	private List<Personnel> students = new ArrayList<Personnel>();
	private static Chat_stuAdapter tempAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat_to_stu__list);
		InitView();
	}

	private void InitView()
	{
		stuList = (ListView) findViewById(R.id.lv_chat);
		InitList();
		Chat_stuAdapter adapter = new Chat_stuAdapter(
				ChatToStu_ListActivity.this, students);
		tempAdapter = adapter;
		// 启动聊天的记录，并传入聊天的对象ID
		stuList.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				Intent intent = new Intent(ChatToStu_ListActivity.this,
						ChatToStuActivity.class);
				int mposition = 0;
				Bundle bundle = new Bundle();
				for (int i = 0; i < Marguments.students.size(); i++)
				{
					if (tempAdapter.mgetItem(position).getAccount()
							.equals(Marguments.students.get(i).getAccount()))
					{
						mposition = i;
						break;
					}
				}
				bundle.putInt("who", mposition);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		stuList.setAdapter(adapter);
	}

	private void InitList()
	{
		for (int i = 0; i < Marguments.personnels.size(); i++)
		{
			if (Marguments.personnels.get(i).getPageid().equals("3"))
				students.add(Marguments.personnels.get(i));
		}
		Marguments.students.clear();
		Marguments.students.addAll(students);
	}
}