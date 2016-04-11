package com.example.campusapp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
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
import com.example.argu.Marguments;
import com.example.bean.Bulletin;
import com.google.gson.Gson;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditBulletinActivity extends Activity
{
	private EditText title, content;
	private Spinner object;
	private Button fabu, cancel;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_bulletin);
		InitView();
	}

	private void InitView()
	{
		title = (EditText) findViewById(R.id.title);
		content = (EditText) findViewById(R.id.content);
		object = (Spinner) findViewById(R.id.object);
		fabu = (Button) findViewById(R.id.fabu);
		cancel = (Button) findViewById(R.id.cancel);
		fabu.setOnClickListener(onClickListener);
		cancel.setOnClickListener(onClickListener);
	}

	@SuppressLint("SimpleDateFormat") private OnClickListener onClickListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			// 发布新的公告信息
			case R.id.fabu:
				if (!Check())
				{
					Toast.makeText(EditBulletinActivity.this, "标题不能为空",
							Toast.LENGTH_SHORT).show();
					break;
				} else
				{
					Bulletin bulletin = new Bulletin();
					bulletin.setTitle(title.getText().toString());
					bulletin.setDetail(content.getText().toString());
					bulletin.setOther("null");
					bulletin.setObject(object.getSelectedItemId() + 1 + "");
					SimpleDateFormat sDateFormat = new SimpleDateFormat(
							"yyyy-MM-dd");
					String date = sDateFormat.format(new java.util.Date());
					bulletin.setData(date);
					SaveDataToPhp(bulletin);
					Toast.makeText(EditBulletinActivity.this, "发布新通知成功!!!",
							Toast.LENGTH_SHORT).show();
				}
				break;
			// 取消发布信息
			case R.id.cancel:
				finish();
				break;
			default:
				break;
			}
		}

		private boolean Check()
		{
			if (title.equals(""))
				return false;
			if (content.equals(" "))
				return false;
			return true;
		}
	};

	/**
	 * 
	 * 描述 向后台发送user数据
	 * 
	 * @param user
	 */
	public static void SaveDataToPhp(final Bulletin bulletin)
	{
		new Thread()
		{
			@Override
			public void run()
			{
				Gson gson = new Gson();
				String url = "http://" + Marguments.ip1 + "/insertbulletin.php";
				HttpPost httpRequest = new HttpPost(url);

				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("newbulletin", gson
						.toJson(bulletin)));
				try
				{
					HttpEntity httpEntity = new UrlEncodedFormEntity(params,
							"utf-8");
					httpRequest.setEntity(httpEntity);
					HttpClient httpClient = new DefaultHttpClient();
					HttpResponse httpResponse = httpClient.execute(httpRequest);
					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
					{
						String result = EntityUtils.toString(httpResponse
								.getEntity());
						Log.i("save", result);
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
}
