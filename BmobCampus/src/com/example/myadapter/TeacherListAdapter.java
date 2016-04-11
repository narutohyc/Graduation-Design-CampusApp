package com.example.myadapter;

import java.util.ArrayList;
import java.util.List;

import com.example.argu.Marguments;
import com.example.campusapp.R;
import com.example.myadapter.Menu_Adapter.ViewHolder;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TeacherListAdapter extends BaseAdapter
{
	private LayoutInflater layoutInflater;
	private Context context;
	public List<String> info = new ArrayList<String>();
	public List<String> portrait = new ArrayList<String>();
	public List<String> nameList = new ArrayList<String>();

	public static int currentposition = 0;

	public TeacherListAdapter(Context context)
	{
		Initinfo();
		this.context = context;
		layoutInflater = LayoutInflater.from(context);
	}

	private void Initinfo()
	{
		int len = Marguments.personnels.size();
		for (int i = 0; i < len; i++)
		{
			// 判断加入教师信息
			if (Marguments.personnels.get(i).getPageid().equals("2"))
			{
				info.add(Marguments.personnels.get(i).getDepartment() + "  "
						+ Marguments.personnels.get(i).getSpecialty() + "   "
						+ Marguments.personnels.get(i).getName());
				nameList.add(Marguments.personnels.get(i).getName());
				portrait.add(Marguments.personnels.get(i).getPortraitid());
			}
		}
	}

	public String getTeacherName(int position)
	{
		return nameList.get(position);
	}

	public String getTeacherportrait(int position)
	{
		return portrait.get(position);
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return info.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = new ViewHolder();
		if (convertView == null)
		{
			convertView = layoutInflater.inflate(R.layout.row, null);
			holder.stuinfo = (TextView) convertView
					.findViewById(R.id.tv_menutext);
			holder.img1 = (ImageView) convertView
					.findViewById(R.id.iv_menulogo01);
			holder.img2 = (ImageView) convertView
					.findViewById(R.id.iv_menulogo02);
			holder.relativeLayout = (RelativeLayout) convertView
					.findViewById(R.id.rl_layout);
			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}

		if (currentposition++ % 2 == 0)
			holder.relativeLayout
					.setBackgroundResource(Marguments.randomcolorbg[Marguments
							.randnum()]);
		else
		{
			holder.relativeLayout.setBackgroundResource(R.drawable.randombg0);
		}
		holder.stuinfo.setText(info.get(position % info.size()));
		holder.img1.setImageResource(Marguments.portraits[Integer
				.parseInt(portrait.get(position))]);
		LayoutParams lp;
		lp = holder.img1.getLayoutParams();
		lp.width = 60;
		lp.height = 60;
		holder.img1.setLayoutParams(lp);

		holder.img2.setImageResource(R.drawable.enter);
		return convertView;
	}

	static class ViewHolder
	{
		TextView stuinfo;
		ImageView img1, img2;
		RelativeLayout relativeLayout;
	}
}
