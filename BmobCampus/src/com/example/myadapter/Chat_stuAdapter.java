package com.example.myadapter;

import java.util.ArrayList;
import java.util.List;

import com.example.argu.Marguments;
import com.example.bean.Personnel;
import com.example.campusapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Chat_stuAdapter extends BaseAdapter
{
	private LayoutInflater layoutInflater;
	private Context context;
	public List<Personnel> students = new ArrayList<Personnel>();

	public Chat_stuAdapter(Context context, List<Personnel> students)
	{
		super();
		this.context = context;
		this.students = students;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount()
	{
		return students.size();
	}

	@Override
	public Object getItem(int position)
	{
		return students.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	// 获取学生对象
	public Personnel mgetItem(int position)
	{
		return students.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = new ViewHolder();
		if (convertView == null)
		{
			convertView = layoutInflater.inflate(R.layout.row, null);
			holder.name = (TextView) convertView.findViewById(R.id.tv_menutext);
			holder.portrait = (ImageView) convertView
					.findViewById(R.id.iv_menulogo01);
			holder.other = (ImageView) convertView
					.findViewById(R.id.iv_menulogo02);
			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.name.setText(students.get(position).getName());
		holder.portrait.setImageResource(Marguments.portraits[Integer
				.parseInt(students.get(position).getPortraitid())]);
		holder.other.setImageResource(R.drawable.enter);
		return convertView;
	}

	private class ViewHolder
	{
		TextView name;
		ImageView portrait, other;
	}
}
