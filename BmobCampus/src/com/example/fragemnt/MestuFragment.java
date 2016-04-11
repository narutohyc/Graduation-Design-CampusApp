package com.example.fragemnt;

import com.example.argu.Marguments;
import com.example.campusapp.ChatToTea_ListActivity;
import com.example.campusapp.R;
import com.example.menuactivity.GerenActivity;
import com.example.otheractivity.ChengjiActivity;
import com.example.otheractivity.KechengbiaoActivity;
import com.example.otheractivity.SettingActivity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MestuFragment extends Fragment
{
	private ImageView iv_kechengbiao, portrait, setting, chengji, exit;
	private TextView name;
	private Button contactButton;
	private static Activity activity = null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.mestu_fragment, container, false);

		InitView(view);

		return view;
	}

	private void InitView(View view)
	{

		contactButton = (Button) view.findViewById(R.id.conatct);
		contactButton.setOnClickListener(onClickListener);
		activity = getActivity();
		name = (TextView) view.findViewById(R.id.name);
		name.setText(Marguments.currentpersonnel.getName());

		iv_kechengbiao = (ImageView) view.findViewById(R.id.iv_kechengbiao);
		iv_kechengbiao.setOnClickListener(onClickListener);

		portrait = (ImageView) view.findViewById(R.id.portrait);
		portrait.setImageResource(Marguments.portraits[Integer
				.parseInt(Marguments.currentPortraitId)]);
		portrait.setOnClickListener(onClickListener);

		setting = (ImageView) view.findViewById(R.id.img3);
		setting.setOnClickListener(onClickListener);

		chengji = (ImageView) view.findViewById(R.id.img2);
		chengji.setOnClickListener(onClickListener);

		exit = (ImageView) view.findViewById(R.id.img4);
		exit.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener()
	{

		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.portrait:
				startActivity(new Intent(getActivity(), GerenActivity.class));
				break;
				
			case R.id.conatct:
				startActivity(new Intent(getActivity(), ChatToTea_ListActivity.class));
				break;
			case R.id.iv_kechengbiao:
				startActivity(new Intent(getActivity(),
						KechengbiaoActivity.class));
				break;
			case R.id.img2:
				startActivity(new Intent(getActivity(), ChengjiActivity.class));
				break;
			case R.id.img3:
				startActivity(new Intent(getActivity(), SettingActivity.class));
				break;
			case R.id.img4:
				Builder build = new Builder(getActivity());
				build.setTitle("注意")
						.setMessage("确定要退出吗？")
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener()
								{
									@Override
									public void onClick(DialogInterface dialog,
											int which)
									{
										MestuFragment.finishapp();
									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener()
								{
									@Override
									public void onClick(DialogInterface dialog,
											int which)
									{
									}
								}).show();
				break;

			default:
				break;
			}
		}
	};

	// 提供activity结束方法，主要供切换账号使用
	public static void finishapp()
	{
		activity.finish();
	}
}
