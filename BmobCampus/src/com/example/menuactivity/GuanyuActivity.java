package com.example.menuactivity;

import com.example.campusapp.R;
import com.example.campusapp.R.id;
import com.example.campusapp.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class GuanyuActivity extends Activity implements OnTouchListener
{
	private ImageView iv_back;
	
	//������ָ�������¼����ж��Ƿ񷵻�
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
		setContentView(R.layout.activity_guanyu);
		iv_back = (ImageView)findViewById(R.id.iv_back);
		iv_back.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}
	
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
