package com.example.fragemnt;

import com.example.argu.Marguments;
import com.example.campusapp.R;
import com.example.myadapter.Jiaowu_Right_Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class JiaowuRightContextFragment extends Fragment
{
	private static TextView tv_context_title = null;
	private static Fragment jiaowurightdetailFragment = null;
	private static ListView lv_context;
	private static FragmentActivity activity = null;
	private static FragmentManager fragmentManager = null;
	private static int context_position = 0;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.jiaowu_right_context, container,
				false);
		InitUI(view);
		return view;
	}

	private void InitUI(View view)
	{
		lv_context = (ListView) view.findViewById(R.id.lv_context);
		lv_context.setAdapter(new Jiaowu_Right_Adapter(getActivity(), 0));
		activity = getActivity();
		context_position = 0;
		fragmentManager = getFragmentManager();
		lv_context.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				if (jiaowurightdetailFragment == null)
				{
					jiaowurightdetailFragment = new JiaowuRightDetailtFragment();
				}
				Marguments.position = context_position;
				Marguments.column = position;
				getFragmentManager()
						.beginTransaction()
						.addToBackStack(null)
						.replace(R.id.jiaowu_right_content,
								jiaowurightdetailFragment).commit();
			}
		});
	}

	public static void update(int position)
	{
		lv_context.setAdapter(new Jiaowu_Right_Adapter(activity, position));
		// tv_context_title.setText(title[position]);
		context_position = position;
		lv_context.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				if (jiaowurightdetailFragment == null)
				{
					jiaowurightdetailFragment = new JiaowuRightDetailtFragment();
				}
				Marguments.position = context_position;
				Marguments.column = position;
				fragmentManager
						.beginTransaction()
						.addToBackStack(null)
						.replace(R.id.jiaowu_right_content,
								jiaowurightdetailFragment).commit();
			}
		});
	}
}
