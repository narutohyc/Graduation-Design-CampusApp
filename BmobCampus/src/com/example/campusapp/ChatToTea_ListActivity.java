package com.example.campusapp;

import java.util.ArrayList;
import java.util.List;

import com.example.argu.Marguments;
import com.example.bean.Personnel;
import com.example.myadapter.Chat_stuAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ChatToTea_ListActivity extends Activity
{
	private ListView teaList;
	private List<Personnel> teachers = new ArrayList<Personnel>();
	private static Chat_stuAdapter tempAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat_to_tea__list);
		InitView();
	}

	private void InitView()
	{
		teaList = (ListView) findViewById(R.id.lv_chat);
		InitList();
		Chat_stuAdapter adapter = new Chat_stuAdapter(
				ChatToTea_ListActivity.this, teachers);
		tempAdapter = adapter;

		// 启动聊天的记录，并传入聊天的对象ID
		teaList.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				Intent intent = new Intent(ChatToTea_ListActivity.this,
						ChatToTeaActivity.class);
				int mposition = 0;
				Bundle bundle = new Bundle();
				for (int i = 0; i < Marguments.teachers.size(); i++)
				{
					if (tempAdapter.mgetItem(position).getAccount()
							.equals(Marguments.teachers.get(i).getAccount()))
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
		teaList.setAdapter(adapter);
	}

	private void InitList()
	{
		for (int i = 0; i < Marguments.personnels.size(); i++)
		{
			if (Marguments.personnels.get(i).getPageid().equals("2"))
			{
				teachers.add(Marguments.personnels.get(i));
			}
		}
		Marguments.teachers.clear();
		Marguments.teachers.addAll(teachers);
	}
}