package com.example.campusapp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.argu.Marguments;
import com.example.bean.MessageToTeacher;
import com.example.fragemnt.JiaowuFragment;
import com.example.fragemnt.MestuFragment;
import com.example.fragemnt.MesysFragment;
import com.example.fragemnt.MeteaFragment;
import com.example.fragemnt.ShouyeFragment;
import com.example.menuactivity.GerenActivity;
import com.example.menuactivity.GuanyuActivity;
import com.example.menuactivity.MapActivity;
import com.example.menuactivity.XiaoliActivity;
import com.example.myadapter.Menu_Adapter;
import com.example.otheractivity.SettingActivity;
import com.google.gson.Gson;

/**
 * 
 * @author hyc
 * 
 */
public class HomepageActivity extends FragmentActivity
{
	private static Activity activity;
	private RelativeLayout shouyeLayout, jiaowuLayout, meLayout;
	private LinearLayout linearLayout;
	private Fragment shouyeFragment, jiaowuFragment, currentFragment,
			meFragment;
	private ImageView shouyeImg, jiaowuImg, meImg, menuImg;
	private TextView shouyeTv, jiaowuTv, meTv;

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private static String currentname = "";
	private static List<MessageToTeacher> messageToTeachers = new ArrayList<MessageToTeacher>();
	private static String messsage = "";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homepage);
		activity = this;
		initUI();
		initTab();
		initDrawer();
		currentname = Marguments.currentpersonnel.getName();
		// 满足教师身份的则检测有无来自系统管理人员的信息
		if (Marguments.currentPageId.equals("2"))
		{
			IsMessage();
		}
	}

	private void IsMessage()
	{
		new Thread()
		{
			@Override
			public void run()
			{
				Gson gson = new Gson();
				String url = "http://" + Marguments.ip1 + "/isnewmessage.php";
				HttpPost httpRequest = new HttpPost(url);

				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("name", currentname));
				params.add(new BasicNameValuePair("state", "未读"));
				params.add(new BasicNameValuePair("newstate", "已读"));
				try
				{
					HttpEntity httpEntity = new UrlEncodedFormEntity(params,
							"utf-8");
					httpRequest.setEntity(httpEntity);
					HttpClient httpClient = new DefaultHttpClient();
					HttpResponse httpResponse = httpClient.execute(httpRequest);
					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
					{
						messsage = "";
						String result = EntityUtils.toString(httpResponse
								.getEntity());
						if (!"0 results".equals(result))
						{
							messsage = result;
							Message msg = new Message();
							msg.obj = result;
							msg.what = 0;
							mHandler.sendMessage(msg);
							Log.i("save", result);
						}
					} else
					{
						Log.i("save", "shibai ");
					}
				} catch (UnsupportedEncodingException e)
				{
					e.printStackTrace();
				} catch (ClientProtocolException e)
				{
					e.printStackTrace();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				super.run();
			}
		}.start();
	}

	// 初始化Drawer
	private void initDrawer()
	{
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		// 设置展开的图片和出现的方向
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// 构造drawer的适配器
		mDrawerList.setAdapter(new Menu_Adapter(this));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_mdrawer, R.string.drawer_open,
				R.string.drawer_close)
		{
			public void onDrawerClosed(View view)
			{
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView)
			{
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	// 初始化UI界面
	private void initUI()
	{
		shouyeLayout = (RelativeLayout) findViewById(R.id.rl_bottom_shouye);
		jiaowuLayout = (RelativeLayout) findViewById(R.id.rl_bottom_jiaowu);
		meLayout = (RelativeLayout) findViewById(R.id.rl_bottom_me);
		linearLayout = (LinearLayout) findViewById(R.id.content_layout);

		shouyeImg = (ImageView) findViewById(R.id.iv_shouye);
		jiaowuImg = (ImageView) findViewById(R.id.iv_jiaowu);
		meImg = (ImageView) findViewById(R.id.iv_me);
		menuImg = (ImageView) findViewById(R.id.iv_menu);

		shouyeTv = (TextView) findViewById(R.id.tv_shouye);
		jiaowuTv = (TextView) findViewById(R.id.tv_jiaowu);
		meTv = (TextView) findViewById(R.id.tv_me);

		shouyeLayout.setOnClickListener(onClickListener);
		jiaowuLayout.setOnClickListener(onClickListener);
		meLayout.setOnClickListener(onClickListener);
		menuImg.setOnClickListener(onClickListener);
	}

	// 初始化主容器的content
	private void initTab()
	{
		if (shouyeFragment == null)
		{
			shouyeFragment = new ShouyeFragment();
		}
		if (!shouyeFragment.isAdded())
		{
			getSupportFragmentManager().beginTransaction()
					.add(R.id.content_layout, shouyeFragment).commit();
			currentFragment = shouyeFragment;
			shouyeImg.setImageResource(R.drawable.btn_shouye_pre);
			shouyeTv.setTextColor(getResources().getColor(
					R.color.bottomtab_press));
			jiaowuImg.setImageResource(R.drawable.btn_jiaowu_nor);
			jiaowuTv.setTextColor(getResources().getColor(
					R.color.bottomtab_normal));
			meImg.setImageResource(R.drawable.btn_me_nor);
			meTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
		}
	}

	private void clickTab1Layout()
	{
		if (shouyeFragment == null)
		{
			shouyeFragment = new ShouyeFragment();
		}
		addOrShowFragment(getSupportFragmentManager().beginTransaction(),
				shouyeFragment);

		shouyeImg.setImageResource(R.drawable.btn_shouye_pre);
		shouyeTv.setTextColor(getResources().getColor(R.color.bottomtab_press));
		jiaowuImg.setImageResource(R.drawable.btn_jiaowu_nor);
		jiaowuTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
		meImg.setImageResource(R.drawable.btn_me_nor);
		meTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
	}

	private void clickTab2Layout()
	{
		if (jiaowuFragment == null)
		{
			jiaowuFragment = new JiaowuFragment();
		}
		addOrShowFragment(getSupportFragmentManager().beginTransaction(),
				jiaowuFragment);
		shouyeImg.setImageResource(R.drawable.btn_shouye_nor);
		shouyeTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
		jiaowuImg.setImageResource(R.drawable.btn_jiaowu_pre);
		jiaowuTv.setTextColor(getResources().getColor(R.color.bottomtab_press));
		meImg.setImageResource(R.drawable.btn_me_nor);
		meTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
	}

	private void clickTab3Layout()
	{
		// 根据当前用户的信息，装载不同的个人主页
		if (meFragment == null)
		{
			switch (Integer.parseInt(Marguments.currentPageId))
			{
			case 1:
				meFragment = new MesysFragment();
				break;
			case 2:
				meFragment = new MeteaFragment();
				break;
			case 3:
				meFragment = new MestuFragment();
				break;

			default:
				break;
			}
		}

		addOrShowFragment(getSupportFragmentManager().beginTransaction(),
				meFragment);
		shouyeImg.setImageResource(R.drawable.btn_shouye_nor);
		shouyeTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
		jiaowuImg.setImageResource(R.drawable.btn_jiaowu_nor);
		jiaowuTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
		meImg.setImageResource(R.drawable.btn_me_pre);
		meTv.setTextColor(getResources().getColor(R.color.bottomtab_press));
	}

	private void addOrShowFragment(FragmentTransaction transaction,
			Fragment fragment)
	{
		// 判断是否为当前fragment，若是则不更新
		if (currentFragment == fragment)
			return;

		if (!fragment.isAdded())
		{
			transaction.hide(currentFragment)
					.add(R.id.content_layout, fragment).commit();
		} else
		{
			transaction.hide(currentFragment).show(fragment).commit();
		}
		currentFragment = fragment;
	}

	private OnClickListener onClickListener = new OnClickListener()
	{
		@Override
		public void onClick(View view)
		{
			switch (view.getId())
			{
			case R.id.rl_bottom_shouye:
				clickTab1Layout();
				break;
			case R.id.rl_bottom_jiaowu:
				clickTab2Layout();
				break;
			case R.id.rl_bottom_me:
				clickTab3Layout();
				break;
			case R.id.iv_menu:
				mDrawerLayout.openDrawer(Gravity.LEFT);
				break;
			default:
				break;
			}
		}
	};

	private class DrawerItemClickListener implements OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id)
		{
			mDrawerLayout.closeDrawer(mDrawerList);
			switch ((int) id)
			{
			case 0:
				startActivity(new Intent(HomepageActivity.this,
						GerenActivity.class));
				break;
			case 1:
				startActivity(new Intent(HomepageActivity.this,
						SettingActivity.class));
				break;
			case 2:
				startActivity(new Intent(HomepageActivity.this,
						MapActivity.class));
				break;
			case 3:
				startActivity(new Intent(HomepageActivity.this,
						XiaoliActivity.class));
				break;
			case 4:
				startActivity(new Intent(HomepageActivity.this,
						GuanyuActivity.class));
				break;
			default:
				break;
			}
		}
	}

	Handler mHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);

			switch (msg.what)
			{
			case 0:
				final LinearLayout linearLayout = (LinearLayout) getLayoutInflater()
						.inflate(R.layout.receivemessage, null);
				TextView textView = (TextView) linearLayout
						.findViewById(R.id.detail);
				textView.setTextSize(16);
				textView.setText((String) msg.obj);
				new AlertDialog.Builder(HomepageActivity.this).setView(linearLayout)
				.show();
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
