package com.example.myadapter;

import com.example.campusapp.R;
import android.R.integer;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu_Adapter extends BaseAdapter
{
	public static String[] menu_text = { "个人", "设置", "位置", "校历", "关于"};
	public static int[] menu_logo = { R.drawable.menu_geren,
			R.drawable.menu_tongzhi, R.drawable.menu_weizhi,
			R.drawable.menu_xiaoli, R.drawable.menu_guanyu};
	private LayoutInflater layoutInflater;

	public Menu_Adapter(Context context)
	{
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount()
	{
		return menu_text.length;
	}

	@Override
	public Object getItem(int position)
	{
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder=new ViewHolder();
		if (convertView == null)
		{ 
			convertView = layoutInflater.inflate(R.layout.row, null);
			holder.tv_menutext = (TextView) convertView
					.findViewById(R.id.tv_menutext);
			holder.iv_menulogo01 = (ImageView) convertView
					.findViewById(R.id.iv_menulogo01);
			holder.iv_menulogo02 = (ImageView) convertView
					.findViewById(R.id.iv_menulogo02);
			convertView.setTag(holder);

		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_menutext.setText(menu_text[position]);
		holder.iv_menulogo01.setImageResource(menu_logo[position]);
		holder.iv_menulogo02.setImageResource(R.drawable.enter);
		if (position == 0)
			holder.iv_menulogo02.setImageResource(-1);
		return convertView;
	}

	static class ViewHolder
	{
		TextView tv_menutext;
		ImageView iv_menulogo01, iv_menulogo02;
	}

}
