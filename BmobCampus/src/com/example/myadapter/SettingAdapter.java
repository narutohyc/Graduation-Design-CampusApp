package com.example.myadapter;

import com.example.campusapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingAdapter extends BaseAdapter
{
	public static String[] setting = { "检查更新", "清除缓存", "意见反馈", "校历", "关于" };
	public static int[] setting_logo = { R.drawable.jianchanyugengxin,
			R.drawable.qingchuhuancun, R.drawable.yijianyufankui,
			R.drawable.menu_xiaoli, R.drawable.menu_guanyu };
	private LayoutInflater layoutInflater;
	public static class ViewHolder
	{
		TextView textView;
		ImageView imageView1, imageView2;
	}

	public SettingAdapter(Context context)
	{
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount()
	{
		return setting.length;
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
			holder.textView = (TextView) convertView
					.findViewById(R.id.tv_menutext);
			holder.imageView1 = (ImageView) convertView
					.findViewById(R.id.iv_menulogo01);
			holder.imageView2 = (ImageView) convertView
					.findViewById(R.id.iv_menulogo02);
			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.textView.setText(setting[position]);
		holder.imageView1.setImageResource(setting_logo[position]);
		holder.imageView2.setImageResource(R.drawable.enter);
		if (position < 2)
			holder.imageView2.setImageResource(-1);
		return convertView;
	}
}
