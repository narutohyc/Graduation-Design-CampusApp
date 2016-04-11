package com.example.menuactivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.campusapp.MyOrientationListener;
import com.example.campusapp.R;
import com.example.campusapp.MyOrientationListener.OnOrientationListener;
import com.example.campusapp.R.drawable;
import com.example.campusapp.R.id;
import com.example.campusapp.R.layout;

public class MapActivity extends Activity implements OnTouchListener
{
	private MapView mMapView = null;
	private ImageView back;
	private BaiduMap mBaiduMap;
	private Button bt_mylocation;
	private Context context;

	// 定位相关
	private LocationClient mLocationClient;
	private MyLocationListener mLocationListener;
	private boolean isFirstIn = true;
	private double mLatitude;
	private double mLongtitude;

	// 自定义定位图标
	private BitmapDescriptor mIconLocation;
	private MyOrientationListener myOrientationListener;
	private float mCurrentX;
	private LocationMode mLocationMode;

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
		setContentView(R.layout.activity_map);
		this.context = this;
		InitView();
		// 初始化定位
		initLocation();
	}

	private void initLocation()
	{

		mLocationMode = LocationMode.NORMAL;
		mLocationClient = new LocationClient(this);
		mLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mLocationListener);

		// LocationClientOption option = new LocationClientOption();
		// // 坐标类型
		// option.setCoorType("bd09ll");
		// option.setIsNeedAddress(true);
		// option.setOpenGps(true);
		// option.setScanSpan(1000);
		// mLocationClient.setLocOption(option);

		// 初始化图标
		mIconLocation = BitmapDescriptorFactory
				.fromResource(R.drawable.navi_map_gps_locked);
		myOrientationListener = new MyOrientationListener(context);

		myOrientationListener
				.setOnOrientationListener(new OnOrientationListener()
				{
					@Override
					public void onOrientationChanged(float x)
					{
						mCurrentX = x;
					}
				});
	}

	private void InitView()
	{
		// 获取地图控件引用
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		// 控制地图的缩放比例 大致为500米
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(18.0f);
		mBaiduMap.setMapStatus(msu);
		// 普通地图
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

		back = (ImageView) findViewById(R.id.iv_back);
		back.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		bt_mylocation = (Button) findViewById(R.id.bt_mylocation);
		bt_mylocation.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				centerToMyLocation();
			}
		});
	}

	/**
	 * 定位到我的位置
	 */
	private void centerToMyLocation()
	{
		LatLng latLng = new LatLng(mLatitude, mLongtitude);
		MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
		mBaiduMap.animateMapStatus(msu);
	}

	private class MyLocationListener implements BDLocationListener
	{
		// 定位成功后的回调
		@Override
		public void onReceiveLocation(BDLocation location)
		{
			// 使用Build设置一系列的参数
			MyLocationData data = new MyLocationData.Builder()//
					.direction(mCurrentX)//
					.accuracy(location.getRadius())//
					.latitude(location.getLatitude())//
					.longitude(location.getLongitude())//
					.build();

			mBaiduMap.setMyLocationData(data);
			// 设置自定义图标 MyLocationConfiguration(定位模式,是否适用自定义图标，自定义图标)
			MyLocationConfiguration config = new MyLocationConfiguration(
					mLocationMode, true, mIconLocation);
			mBaiduMap.setMyLocationConfigeration(config);

			// 更新经纬度
			mLatitude = location.getLatitude();
			mLongtitude = location.getLongitude();

			if (isFirstIn)
			{
				LatLng latLng = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
				mBaiduMap.animateMapStatus(msu);
				isFirstIn = false;

				Toast.makeText(context, location.getAddrStr(),
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		// 开启定位
		mBaiduMap.setMyLocationEnabled(true);
		if (!mLocationClient.isStarted())
			mLocationClient.start();
		// 开启方向传感器
		myOrientationListener.start();
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		// 停止定位
		mBaiduMap.setMyLocationEnabled(false);
		mLocationClient.stop();
		// 停止方向传感器
		myOrientationListener.stop();
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMapView.onDestroy();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
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
