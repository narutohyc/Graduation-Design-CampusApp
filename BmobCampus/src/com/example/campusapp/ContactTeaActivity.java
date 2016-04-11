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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.argu.Marguments;
import com.example.bean.Bulletin;
import com.example.bean.MessageToTeacher;
import com.example.myadapter.TeacherListAdapter;
import com.google.gson.Gson;

/**
 * 
 * 系统管理员联系教师员工
 * 
 * @author hyc
 * 
 */
public class ContactTeaActivity extends Activity
{
	private ListView listView;
	private ImageView back;
	private TeacherListAdapter teacherListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_contact_tea);
		InitView();
	}

	private void InitView()
	{
		listView = (ListView) findViewById(R.id.tealist);
		teacherListAdapter = new TeacherListAdapter(this);
		listView.setAdapter(teacherListAdapter);
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				newMessage(teacherListAdapter.getTeacherName(position),
						position);
			}
		});

		back = (ImageView) findViewById(R.id.iv_back);
		back.setOnClickListener(onClickListener);
	}

	/**
	 * 产生消息
	 * 
	 */

	protected void newMessage(String to, int position)
	{
		final LinearLayout linearLayout = (LinearLayout) getLayoutInflater()
				.inflate(R.layout.messagetoteacher, null);
		final String receiver = to;
		new AlertDialog.Builder(this)
				.setIcon(
						Marguments.portraits[Integer
								.parseInt(teacherListAdapter
										.getTeacherportrait(position))])
				.setTitle(teacherListAdapter.getTeacherName(position))
				.setView(linearLayout)
				.setPositiveButton("发送", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						EditText editText;
						editText = (EditText) linearLayout
								.findViewById(R.id.editmessage);

						MessageToTeacher messageToTeacher = new MessageToTeacher();
						messageToTeacher.setName(receiver);
						messageToTeacher.setState("未读");
						SimpleDateFormat sDateFormat = new SimpleDateFormat(
								"yyyy-MM-dd");
						String date = sDateFormat.format(new java.util.Date());
						messageToTeacher.setDate(date);
						messageToTeacher.setDetail(editText.getText()
								.toString());

						SaveDataToPhp(messageToTeacher);
						
						Toast.makeText(ContactTeaActivity.this, "消息已发送",
								Toast.LENGTH_LONG).show();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
					}
				}).show();
	}

	public static void SaveDataToPhp(final MessageToTeacher messageToTeacher)
	{
		new Thread()
		{
			@Override
			public void run()
			{
				Gson gson = new Gson();
				String url = "http://" + Marguments.ip1 + "/messagetoteacher.php";
				HttpPost httpRequest = new HttpPost(url);

				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("message", gson
						.toJson(messageToTeacher)));
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

	private OnClickListener onClickListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.iv_back:
				finish();
				break;
			default:
				break;
			}
		}
	};
}
