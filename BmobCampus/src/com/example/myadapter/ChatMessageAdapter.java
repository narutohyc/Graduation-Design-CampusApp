package com.example.myadapter;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

import com.example.campusapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.argu.Marguments;
import com.example.bean.ChatMessage;
import com.example.bean.ChatMessage.Type;

public class ChatMessageAdapter extends BaseAdapter
{
	private LayoutInflater inflater;
	private List<ChatMessage> datas;

	public ChatMessageAdapter(Context context, List<ChatMessage> datas)
	{
		inflater = LayoutInflater.from(context);
		this.datas = datas;
	}

	@Override
	public int getCount()
	{
		return datas.size();
	}

	@Override
	public Object getItem(int position)
	{
		return datas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public int getItemViewType(int position)
	{
		ChatMessage chatMessage = datas.get(position);
		if (chatMessage.getType() == Type.INCOMING)
		{
			return 0;
		}
		return 1;
	}

	@Override
	public int getViewTypeCount()
	{
		return 2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ChatMessage chatMessage = datas.get(position);
		ViewHolder viewHolder = null;
		if (convertView == null)
		{
			// 通过ItemType设置不同的布局
			if (getItemViewType(position) == 0)
			{
				convertView = inflater.inflate(R.layout.item_from_msg, parent,
						false);
				viewHolder = new ViewHolder();
				viewHolder.date = (TextView) convertView
						.findViewById(R.id.id_form_msg_date);
				viewHolder.msg = (TextView) convertView
						.findViewById(R.id.id_from_msg_info);
				viewHolder.portrait = (ImageView) convertView
						.findViewById(R.id.from_portrait);
				viewHolder.name = (TextView) convertView
						.findViewById(R.id.from_name);
			} else
			{
				convertView = inflater.inflate(R.layout.item_to_msg, parent,
						false);
				viewHolder = new ViewHolder();
				viewHolder.date = (TextView) convertView
						.findViewById(R.id.id_to_msg_date);
				viewHolder.msg = (TextView) convertView
						.findViewById(R.id.id_to_msg_info);
				viewHolder.portrait = (ImageView) convertView
						.findViewById(R.id.to_portrait);
				viewHolder.name = (TextView) convertView
						.findViewById(R.id.to_name);
			}
			convertView.setTag(viewHolder);
		} else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// 设置数据
		viewHolder.date.setText(chatMessage.getDate());
		viewHolder.msg.setText(chatMessage.getMsg());
		viewHolder.portrait.setImageResource(Marguments.portraits[Integer
				.parseInt(chatMessage.getInfo().getPortraitid())]);
		viewHolder.name.setText(chatMessage.getInfo().getName());
		return convertView;
	}
	
	private final class ViewHolder
	{
		TextView date;
		TextView msg;
		ImageView portrait;
		TextView name;
	}
}
