package com.example.myadapter;

import java.util.ArrayList;
import java.util.List;

import com.example.argu.Marguments;
import com.example.bean.Personnel;
import com.example.campusapp.R;
import com.example.myadapter.StudentListAdapter.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Chat_teaAdapter extends BaseAdapter
{
	private LayoutInflater layoutInflater;
	private Context context;
	public List<Personnel> teachers = new ArrayList<Personnel>();

	public Chat_teaAdapter(Context context, List<Personnel> teachers)
	{
		super();
		this.context = context;
		this.teachers = teachers;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount()
	{
		return teachers.size();
	}

	@Override
	public Object getItem(int position)
	{
		return teachers.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
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
		holder.name.setText(teachers.get(position).getName());
		holder.portrait.setImageResource(Marguments.portraits[Integer
				.parseInt(teachers.get(position).getPortraitid())]);
		holder.other.setImageResource(R.drawable.enter);
		return convertView;
	}

	private class ViewHolder
	{
		TextView name;
		ImageView portrait, other;
	}
}
