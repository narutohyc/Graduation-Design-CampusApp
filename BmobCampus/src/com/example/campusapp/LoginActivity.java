package com.example.campusapp;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.argu.Marguments;
import com.example.bean.Course;
import com.example.bean.Mark;
import com.example.bean.Personnel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LoginActivity extends Activity
{
	private Button clickLogin;
	private EditText account, passward, ip;
	private Button acquireip;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		InitUI();
		InitData2();
	}

	/**
	 * 
	 * 初始化各项的数据
	 * 
	 */
	// 来自本地服务器的数据
	private void InitData2()
	{
		final Gson gson = new Gson();
		Marguments.personnels.clear();
		new Thread()
		{
			@Override
			public void run()
			{
				String url = "http://" + Marguments.ip1 + "/getpersonnels.php";
				HttpResponse httpResponse = null;
				HttpGet httpGet = new HttpGet(url);
				try
				{
					httpResponse = new DefaultHttpClient().execute(httpGet);
					if (httpResponse.getStatusLine().getStatusCode() == 200)
					{
						String result = EntityUtils.toString(httpResponse
								.getEntity());
						List<Personnel> ps = gson.fromJson(result,
								new TypeToken<List<Personnel>>()
								{
								}.getType());
						for (int i = 0; i < ps.size(); i++)
						{
							Marguments.personnels.add(ps.get(i));
						}
					}
				} catch (ClientProtocolException e)
				{
					Log.e("http", "ClientProtocolException");
				} catch (IOException e)
				{
					Log.e("http", "IOEsception");
				}
				super.run();
			}
		}.start();

		Marguments.courses.clear();
		new Thread()
		{
			@Override
			public void run()
			{
				String url = "http://" + Marguments.ip1 + "/getcourses.php";
				HttpResponse httpResponse = null;
				HttpGet httpGet = new HttpGet(url);
				try
				{
					httpResponse = new DefaultHttpClient().execute(httpGet);
					if (httpResponse.getStatusLine().getStatusCode() == 200)
					{
						String result = EntityUtils.toString(httpResponse
								.getEntity());
						List<Course> ps = gson.fromJson(result,
								new TypeToken<List<Course>>()
								{
								}.getType());
						for (int i = 0; i < ps.size(); i++)
						{
							Marguments.courses.add(ps.get(i));
						}
					}
				} catch (ClientProtocolException e)
				{
					Log.e("http", "ClientProtocolException");
				} catch (IOException e)
				{
					Log.e("http", "IOEsception");
				}
				super.run();
			}
		}.start();

		Marguments.marks.clear();
		new Thread()
		{
			@Override
			public void run()
			{
				String url = "http://" + Marguments.ip1 + "/getmarks.php";
				HttpResponse httpResponse = null;
				HttpGet httpGet = new HttpGet(url);
				try
				{
					httpResponse = new DefaultHttpClient().execute(httpGet);
					if (httpResponse.getStatusLine().getStatusCode() == 200)
					{
						String result = EntityUtils.toString(httpResponse
								.getEntity());
						List<Mark> ps = gson.fromJson(result,
								new TypeToken<List<Mark>>()
								{
								}.getType());
						for (int i = 0; i < ps.size(); i++)
						{
							Marguments.marks.add(ps.get(i));
						}
					}
				} catch (ClientProtocolException e)
				{
					Log.e("http", "ClientProtocolException");
				} catch (IOException e)
				{
					Log.e("http", "IOEsception");
				}
				super.run();
			}
		}.start();
	}

	private void InitUI()
	{
		account = (EditText) findViewById(R.id.account);
		passward = (EditText) findViewById(R.id.passward);

		ip = (EditText) findViewById(R.id.ip);
		textView = (TextView) findViewById(R.id.textView1);
		acquireip = (Button) findViewById(R.id.acquireip);
//		ip.setVisibility(View.GONE);
//		textView.setVisibility(View.GONE);
//		acquireip.setVisibility(View.GONE);
		acquireip.setOnClickListener(new OnClickListener()
		{
			@SuppressWarnings("unused")
			@Override
			public void onClick(View v)
			{
				Marguments.ip1 = ip.getText().toString();
				InitData2();
				Toast.makeText(getBaseContext(), "IP地址修改成功",
						Toast.LENGTH_LONG).show();
			}
		});

		Marguments.currentAccount = account.getText().toString();
		Marguments.currentPassward = passward.getText().toString();

		clickLogin = (Button) findViewById(R.id.clicklogin);
		clickLogin.setOnClickListener(new OnClickListener()
		{
			@SuppressWarnings("unused")
			@Override
			public void onClick(View v)
			{

				Marguments.currentAccount = account.getText().toString();
				Marguments.currentPassward = passward.getText().toString();
				if (Marguments.find())
				{
					Marguments.currentPortraitId = Marguments.currentpersonnel
							.getPortraitid();
					startActivity(new Intent(LoginActivity.this,
							HomepageActivity.class));
					LoginActivity.this.finish();
				} else
				{
					Toast.makeText(getBaseContext(), "密码错误或账号不存在",
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
