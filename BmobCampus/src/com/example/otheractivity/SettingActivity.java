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

	// 监听手指滑动的事件，判断是否返回
	// 手指向右滑动时的最小速度 ，手指向右滑动时的最小距离
	private static final int XSPEED_MIN = 200, XDISTANCE_MIN = 150;
	// 记录手指按下时的横坐标 ， 记录手指移动时的横坐标
	private float xDown, xMove;
	// 用于计算手指滑动的速度。
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
				Toast.makeText(SettingActivity.this, "已经是最新版!",
						Toast.LENGTH_LONG).show();
				break;
			case 1:
				Toast.makeText(SettingActivity.this, "清楚缓存成功!",
						Toast.LENGTH_LONG).show();
				break;
			case 2:
				Toast.makeText(SettingActivity.this, "等待下次更新",
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
			// 活动的距离
			int distanceX = (int) (xMove - xDown);
			// 获取顺时速度
			int xSpeed = getScrollVelocity();
			// 当滑动的距离大于我们设定的最小距离且滑动的瞬间速度大于我们设定的速度时，返回到上一个activity
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
	 * 创建VelocityTracker对象，并将触摸content界面的滑动事件加入到VelocityTracker当中。
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
	 * 回收VelocityTracker对象。
	 */
	private void recycleVelocityTracker()
	{
		mVelocityTracker.recycle();
		mVelocityTracker = null;
	}

	/**
	 * 获取手指在content界面滑动的速度。
	 * 
	 * @return 滑动速度，以每秒钟移动了多少像素值为单位。
	 */
	private int getScrollVelocity()
	{
		mVelocityTracker.computeCurrentVelocity(1000);
		int velocity = (int) mVelocityTracker.getXVelocity();
		return Math.abs(velocity);
	}
}
