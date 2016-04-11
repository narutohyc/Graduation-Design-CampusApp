package com.example.otheractivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.campusapp.HomepageActivity;
import com.example.campusapp.LoginActivity;
import com.example.campusapp.R;
import com.example.menuactivity.GuanyuActivity;
import com.example.menuactivity.XiaoliActivity;
import com.example.myadapter.SettingAdapter;

public class SettingActivity extends Activity implements OnTouchListener
{
	private ListView listView;
	private ImageView iv_back;
	private TextView logout;

	// ������ָ�������¼����ж��Ƿ񷵻�
	// ��ָ���һ���ʱ����С�ٶ� ����ָ���һ���ʱ����С����
	private static final int XSPEED_MIN = 200, XDISTANCE_MIN = 150;
	// ��¼��ָ����ʱ�ĺ����� �� ��¼��ָ�ƶ�ʱ�ĺ�����
	private float xDown, xMove;
	// ���ڼ�����ָ�������ٶȡ�
	private VelocityTracker mVelocityTracker;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		InitUI();
	}

	private void InitUI()
	{
		listView = (ListView) findViewById(R.id.lv_setting);
		listView.setAdapter(new SettingAdapter(this));
		listView.setOnItemClickListener(onItemClickListener);

		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		logout = (TextView) findViewById(R.id.tv_logout);
		logout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();

				startActivity(new Intent(getBaseContext(), LoginActivity.class));
				HomepageActivity.finishapp();
			}
		});

	}

	private OnItemClickListener onItemClickListener = new OnItemClickListener()
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id)
		{
			switch (position)
			{
			case 0:
				Toast.makeText(SettingActivity.this, "�Ѿ������°�!",
						Toast.LENGTH_LONG).show();
				break;
			case 1:
				Toast.makeText(SettingActivity.this, "�������ɹ�!",
						Toast.LENGTH_LONG).show();
				break;
			case 2:
				Toast.makeText(SettingActivity.this, "�ȴ��´θ���",
						Toast.LENGTH_LONG).show();
				break;
			case 3:
				startActivity(new Intent(SettingActivity.this,
						XiaoliActivity.class));
				break;
			case 4:
				startActivity(new Intent(SettingActivity.this,
						GuanyuActivity.class));
				break;

			default:
				break;
			}
		}
	};

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		createVelocityTracker(event);
		switch (event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			xDown = event.getRawX();
			break;
		case MotionEvent.ACTION_MOVE:
			xMove = event.getRawX();
			// ��ľ���
			int distanceX = (int) (xMove - xDown);
			// ��ȡ˳ʱ�ٶ�
			int xSpeed = getScrollVelocity();
			// �������ľ�����������趨����С�����һ�����˲���ٶȴ��������趨���ٶ�ʱ�����ص���һ��activity
			if (distanceX > XDISTANCE_MIN && xSpeed > XSPEED_MIN)
			{
				finish();
			}
			break;
		case MotionEvent.ACTION_UP:
			recycleVelocityTracker();
			break;
		default:
			break;
		}
		return true;
	}

	/**
	 * ����VelocityTracker���󣬲�������content����Ļ����¼����뵽VelocityTracker���С�
	 * 
	 * @param event
	 * 
	 */
	private void createVelocityTracker(MotionEvent event)
	{
		if (mVelocityTracker == null)
		{
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);
	}

	/**
	 * ����VelocityTracker����
	 */
	private void recycleVelocityTracker()
	{
		mVelocityTracker.recycle();
		mVelocityTracker = null;
	}

	/**
	 * ��ȡ��ָ��content���滬�����ٶȡ�
	 * 
	 * @return �����ٶȣ���ÿ�����ƶ��˶�������ֵΪ��λ��
	 */
	private int getScrollVelocity()
	{
		mVelocityTracker.computeCurrentVelocity(1000);
		int velocity = (int) mVelocityTracker.getXVelocity();
		return Math.abs(velocity);
	}
}
