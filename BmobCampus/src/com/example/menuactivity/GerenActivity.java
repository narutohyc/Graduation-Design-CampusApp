package com.example.menuactivity;

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
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.argu.Marguments;
import com.example.campusapp.R;

public class GerenActivity extends Activity
{
	private ImageView iv_back, portrait;
	private TextView name, info, change;
	private static Builder builder = null;
	private Activity activity = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_geren);
		activity = this;
		InitUI();
	}

	private void InitUI()
	{
		builder = new Builder(GerenActivity.this);
		portrait = (ImageView) findViewById(R.id.portrait);
		portrait.setImageResource(Marguments.portraits[Integer
				.parseInt(Marguments.currentPortraitId)]);

		// ���ݲ�ͬ��������ɸ��Ե������Ϣ
		String identity = null;
		switch (Integer.parseInt(Marguments.currentPageId))
		{
		case 1:
			identity = new String("ϵͳ����Ա");
			break;
		case 2:
			identity = new String("��ʦ");
			break;
		case 3:
			identity = new String("ѧ��");
			break;
		default:
			break;
		}

		name = (TextView) findViewById(R.id.name);
		name.setText("������" + Marguments.currentpersonnel.getName() + "\n\n��ݣ�"
				+ identity);

		info = (TextView) findViewById(R.id.info);
		info.setText("\nѧУ�����ݴ�ѧ����ѧԺ\n\nϵ��"
				+ Marguments.currentpersonnel.getDepartment() + "\n\nרҵ��"
				+ Marguments.currentpersonnel.getSpecialty() + "\n\n�Ա�"
				+ Marguments.currentpersonnel.getSex() + "\n\n���壺����");

		change = (TextView) findViewById(R.id.change);
		change.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				builder.setView(createDialog());
				builder.create().show();
			}

			private View createDialog()
			{
				LinearLayout mainlayout = new LinearLayout(GerenActivity.this);
				mainlayout.setOrientation(LinearLayout.VERTICAL);

				LinearLayout childlayout1 = new LinearLayout(GerenActivity.this);
				childlayout1.setOrientation(LinearLayout.HORIZONTAL);
				TextView textView1 = new TextView(getBaseContext());
				textView1.setText("������    :");
				final EditText newpassward1 = new EditText(getBaseContext());
				newpassward1.setWidth(200);
				newpassward1.setSingleLine();
				newpassward1.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
				childlayout1.addView(textView1);
				childlayout1.addView(newpassward1);

				LinearLayout childlayout2 = new LinearLayout(GerenActivity.this);
				childlayout2.setOrientation(LinearLayout.HORIZONTAL);
				TextView textView2 = new TextView(getBaseContext());
				textView2.setText("�ٴ�����:");
				final EditText newpassward2 = new EditText(getBaseContext());
				newpassward2.setWidth(200);
				newpassward2.setSingleLine();
				newpassward2.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
				childlayout2.addView(textView2);
				childlayout2.addView(newpassward2);

				LinearLayout childlayout3 = new LinearLayout(GerenActivity.this);
				childlayout3.setOrientation(LinearLayout.HORIZONTAL);
				Button acquire = new Button(getBaseContext());
				Button clear = new Button(getBaseContext());
				acquire.setText("ȷ��");
				clear.setText("���");
				acquire.setOnClickListener(new OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						if (newpassward1.getText().toString()
								.equals(newpassward2.getText().toString()))
						{
							SaveDataToPhp(Marguments.currentAccount, newpassward1.getText().toString());
							Toast.makeText(getBaseContext(), "�޸�����ɹ������μ���������",
									Toast.LENGTH_LONG).show();
						} else
						{
							Toast.makeText(getBaseContext(), "�������벻һ��",
									Toast.LENGTH_LONG).show();
						}
					}
				});
				clear.setOnClickListener(new OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						newpassward1.setText("");
						newpassward2.setText("");
					}
				});
				childlayout3.addView(acquire);
				childlayout3.addView(clear);

				mainlayout.addView(childlayout1);
				mainlayout.addView(childlayout2);
				mainlayout.addView(childlayout3);

				return mainlayout;
			}
		});

		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}
	
	//�������ݿ⵱�е�����
	public static void SaveDataToPhp(final String account,final String newpassward)
	{
		new Thread()
		{
			@Override
			public void run()
			{
				String url = "http://" + Marguments.ip1 + "/updatepassward.php";
				HttpPost httpRequest = new HttpPost(url);

				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("account", account));
				params.add(new BasicNameValuePair("newpassward", newpassward));
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