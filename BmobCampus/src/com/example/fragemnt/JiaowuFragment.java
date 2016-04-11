package com.example.fragemnt;

import com.example.campusapp.R;

import android.R.integer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class JiaowuFragment extends Fragment
{
	private Fragment jiaowurightcontextFragment;
	private Button[] button = { null, null, null, null, null };
	private int[] buttonId={R.id.bt1,R.id.bt2,R.id.bt3,R.id.bt4,R.id.bt5};
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater
				.inflate(R.layout.jiaowu_fragment, container, false);
		InitUI(view);
		return view;
	}

	private void InitUI(View view)
	{
		button[0] = (Button) view.findViewById(R.id.bt1);
		button[1] = (Button) view.findViewById(R.id.bt2);
		button[2] = (Button) view.findViewById(R.id.bt3);
		button[3] = (Button) view.findViewById(R.id.bt4);
		button[4] = (Button) view.findViewById(R.id.bt5);
		for (int i = 0; i < 5; i++)
		{
			button[i] = (Button) view.findViewById(buttonId[i]);
			button[i].setOnClickListener(onClickListener);
		}
		
		clearColor();
		button[0].setBackgroundColor(getResources().getColor(R.color.title_bg));
		if (jiaowurightcontextFragment == null)
		{
			jiaowurightcontextFragment = new JiaowuRightContextFragment();
		}
		getFragmentManager().beginTransaction()
				.add(R.id.jiaowu_right_content, jiaowurightcontextFragment)
				.commit();
	}

	

	private OnClickListener onClickListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.bt1:
				JiaowuRightContextFragment.update(0);
				clearColor();
				button[0].setBackgroundColor(getResources().getColor(
						R.color.title_bg));
				break;
			case R.id.bt2:
				JiaowuRightContextFragment.update(1);
				clearColor();
				button[1].setBackgroundColor(getResources().getColor(
						R.color.title_bg));
				break;
			case R.id.bt3:
				JiaowuRightContextFragment.update(2);
				clearColor();
				button[2].setBackgroundColor(getResources().getColor(
						R.color.title_bg));
				break;
			case R.id.bt4:
				JiaowuRightContextFragment.update(3);
				clearColor();
				button[3].setBackgroundColor(getResources().getColor(
						R.color.title_bg));
				break;
			case R.id.bt5:
				JiaowuRightContextFragment.update(4);
				clearColor();
				button[4].setBackgroundColor(getResources().getColor(
						R.color.title_bg));
				break;
			default:
				break;
			}
		}
	};

	private void clearColor()
	{
		// 将所有按钮的颜色全部清除
		for(int i=0; i<button.length; i++)
		{
			button[i].setBackgroundColor(getResources().getColor(
					R.color.universal_bg));
		}
	}
	
}
