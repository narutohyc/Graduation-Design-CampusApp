package com.example.campusapp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.argu.Marguments;
import com.example.bean.ChatMessage;
import com.example.bean.ChatMessage.Type;
import com.example.bean.ChatLog;
import com.example.bean.Personnel;
import com.example.myadapter.ChatMessageAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ChatToStuActivity extends Activity
{
	private ListView msgListView;
	private TextView chatto;
	private ChatMessageAdapter mAdapter;
	private List<ChatMessage> mDatas;
	private EditText mInputMsg;
	private Button mSendMsg;
	private Personnel sendPersonnel;
	private Personnel receivePersonnel;
	// 本次聊天所需的参数
	private List<ChatLog> chatLogs = new ArrayList<ChatLog>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_chat);
		initView();
		initDatas();
		if (mDatas.size() == 0)
		{
			mAdapter = new ChatMessageAdapter(ChatToStuActivity.this, mDatas);
			msgListView.setAdapter(mAdapter);
		}
		initListener();
		new Thread(new ThreadShow()).start();
	}

	// 初始化事件
	@SuppressWarnings("unused")
	private void initListener()
	{
		mSendMsg.setOnClickListener(new OnClickListener()
		{
			@SuppressLint("SimpleDateFormat")
			@Override
			public void onClick(View v)
			{
				final String toMsg = mInputMsg.getText().toString();
				if (TextUtils.isEmpty(toMsg))
				{
					Toast.makeText(ChatToStuActivity.this, "发送消息不能为空！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
				String str = formatter.format(curDate);
				ChatMessage toMessage = new ChatMessage(sendPersonnel, toMsg,
						str, Type.OUTCOMING);

				mDatas.add(toMessage);
				mAdapter.notifyDataSetChanged();
				msgListView.setSelection(mDatas.size() - 1);
				// 完成信息发送
				new Thread()
				{
					@Override
					public void run()
					{
						Gson gson = new Gson();
						String url = "http://" + Marguments.ip1
								+ "/sendmessage.php";
						HttpPost httpRequest = new HttpPost(url);

						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("from", sendPersonnel
								.getAccount()));
						params.add(new BasicNameValuePair("to",
								receivePersonnel.getAccount()));
						params.add(new BasicNameValuePair("msg", toMsg));
						params.add(new BasicNameValuePair("state", "未读"));

						try
						{
							HttpEntity httpEntity = new UrlEncodedFormEntity(
									params, "utf-8");
							httpRequest.setEntity(httpEntity);
							HttpClient httpClient = new DefaultHttpClient();
							HttpResponse httpResponse = httpClient
									.execute(httpRequest);
							if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
							{
								String result = EntityUtils
										.toString(httpResponse.getEntity());
								Log.i("save", result);
							} else
							{
								Log.i("save", "失败");
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
				mInputMsg.setText("");
			}
		});
	}

	private void initDatas()
	{
		receivePersonnel = Marguments.students.get(getIntent().getExtras()
				.getInt("who"));
		sendPersonnel = Marguments.currentpersonnel;
		mDatas = new ArrayList<ChatMessage>();
		gethistory();
		chatto.setText(receivePersonnel.getName());
	}

	private void gethistory()
	{
		new Thread()
		{
			@Override
			public void run()
			{
				Gson gson = new Gson();
				String url = "http://" + Marguments.ip1 + "/getchatlogs.php";
				HttpPost httpRequest = new HttpPost(url);

				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("from", sendPersonnel
						.getAccount()));
				params.add(new BasicNameValuePair("to", receivePersonnel
						.getAccount()));
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
						if (!"0 results".equals(result))
						{
							List<ChatLog> ps = gson.fromJson(result,
									new TypeToken<List<ChatLog>>()
									{
									}.getType());
							chatLogs.addAll(ps);
							Message msg = new Message();
							msg.obj = chatLogs;
							msg.what = 0;
							mHandler.sendMessage(msg);
						}
					} else
					{
						Log.i("save", "失败");
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

	private void initView()
	{
		msgListView = (ListView) findViewById(R.id.id_listview_msgs);
		mInputMsg = (EditText) findViewById(R.id.id_input_msg);
		mSendMsg = (Button) findViewById(R.id.id_send_msg);
		chatto = (TextView) findViewById(R.id.chatto);
	}

	// 线程类
	class ThreadShow implements Runnable
	{
		@Override
		public void run()
		{
			while (true)
			{
				try
				{
					Thread.sleep(1000);
					List<ChatLog> newchatLogs = new ArrayList<ChatLog>();
					Gson gson = new Gson();
					String url = "http://" + Marguments.ip1
							+ "/checkmessage.php";
					HttpPost httpRequest = new HttpPost(url);

					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("from", receivePersonnel
							.getAccount()));
					params.add(new BasicNameValuePair("to", sendPersonnel
							.getAccount()));
					try
					{
						HttpEntity httpEntity = new UrlEncodedFormEntity(
								params, "utf-8");
						httpRequest.setEntity(httpEntity);
						HttpClient httpClient = new DefaultHttpClient();
						HttpResponse httpResponse = httpClient
								.execute(httpRequest);
						if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
						{
							String result = EntityUtils.toString(httpResponse
									.getEntity());
							if (!"0 results".equals(result))
							{
								List<ChatLog> ps = gson.fromJson(result,
										new TypeToken<List<ChatLog>>()
										{
										}.getType());
								newchatLogs.addAll(ps);
								Message msg = new Message();
								msg.obj = newchatLogs;
								msg.what = 1;
								mHandler.sendMessage(msg);
							}
						} else
						{
							Log.i("save", "失败");
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
				} catch (Exception e)
				{
					Log.e("mm", "thread error...");
				}
			}
		}
	}

	@SuppressLint("HandlerLeak")
	Handler mHandler = new Handler()
	{
		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);

			switch (msg.what)
			{
			case 0:
				List<ChatLog> mchatLogs = new ArrayList<ChatLog>();
				mchatLogs.addAll((ArrayList<ChatLog>) msg.obj);
				if (mchatLogs.size() > 0)
				{
					for (int i = 0; i < mchatLogs.size(); i++)
					{
						if (mchatLogs.get(i).getFrom()
								.equals(sendPersonnel.getAccount()))
						{
							mDatas.add(new ChatMessage(sendPersonnel, mchatLogs
									.get(i).getMsg(), mchatLogs.get(i)
									.getSendDate(), Type.OUTCOMING));
						} else
						{
							mDatas.add(new ChatMessage(receivePersonnel,
									mchatLogs.get(i).getMsg(), mchatLogs.get(i)
											.getSendDate(), Type.INCOMING));
						}
					}
					Log.i("save", "mDates size is : " + mDatas.size());
					mAdapter = new ChatMessageAdapter(ChatToStuActivity.this,
							mDatas);
					msgListView.setAdapter(mAdapter);
					msgListView.setSelection(mDatas.size() - 1);
				} else
				{
					mAdapter = new ChatMessageAdapter(ChatToStuActivity.this,
							mDatas);
					msgListView.setAdapter(mAdapter);
				}
				break;
			case 1:
				List<ChatLog> mnewchatLogs = new ArrayList<ChatLog>();
				mnewchatLogs.addAll((ArrayList<ChatLog>) msg.obj);

				for (int i = 0; i < mnewchatLogs.size(); i++)
				{
					mDatas.add(new ChatMessage(receivePersonnel, mnewchatLogs
							.get(i).getMsg(),
							mnewchatLogs.get(i).getSendDate(), Type.INCOMING));
				}
				mAdapter.notifyDataSetChanged();
				msgListView.setSelection(mDatas.size() - 1);
				break;
			default:
				break;
			}
		}
	};

	@Override
	protected void onDestroy()
	{
		// 退出时清除缓存数据，以免对下次的查询造成影响
		mDatas.clear();
		super.onDestroy();
	}
}