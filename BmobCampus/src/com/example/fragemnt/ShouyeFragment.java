package com.example.fragemnt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;

import com.example.argu.Marguments;
import com.example.bean.Bulletin;
import com.example.campusapp.EditBulletinActivity;
import com.example.campusapp.NewsActivity;
import com.example.campusapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ShouyeFragment extends Fragment
{
	private ImageView[] img = { null, null, null, null, null, null, null };
	private TextView detailnews;
	private Button add;
	private LinearLayout ly_edit;

	// ����չ���˵���ͼƬ
	private int[] image = { R.id.homepage_open, R.id.homepage_a,
			R.id.homepage_b, R.id.homepage_c, R.id.homepage_d, R.id.homepage_e,
			R.id.homepage_news };
	// ��չ���˵���״̬ true��ʾδչ��
	private boolean flag = true;
	// ���ݲ�ͬ�������Ϣ��չʾ��ͬ��Fragment
	private int pageid = 1;
	private String[] detailnewsStrings = {
			"\n        2015��12��28��19�㣬���ݴ�ѧ����ѧԺ-Ӣ��������ʿ��ѧ��ѧ��Ŀ�������ڶ����ݱ������ɹ����С�",
			"\n        Ϊ�˹淶ѧԺ��ȿ��˹�����������ȫ��ѧ�Ŀ��˼������ƣ��������ɣ������Ƚ���������󣬵�������ְ�������ԣ�ά�������Ĺ������򣬽����Ժʵ�ʣ��ƶ�������涨",
			"\n        2009�������ϴ�ѧ������ϰ�봴ҵЭ�ᣬ�ε�һ��᳤��2010����Ƹ����ʡʡ�ü����������޹�˾�г�������ְλ���齨�˸���10����ѧ100���˵��Ŷӣ�������������ﵽ2.4���˴� ",
			"\n        ���ܼ���ѧϰC���Աر���װ�ú��鼮��A��������������turbo C 2.0,�������ռ����DOSʱ����������Ĵ�����ɽ����������windowsʱ������turbo...",
			"\n        ���ǽ��������ֻ�ʱ������7�꣬��������ʱ����Щ�µ������ˡ����ǹ�ȥ��������Ļ�̽�ֵ��������ڻ����϶����˴𰸣����߶�����޹ؽ�Ҫ����������������ڲ��ϳ��֡�" };
	// ����չʾ������Ϣ�Ŀؼ�
	private TextView title1, detail1, data1, title2, detail2, data2, title3,
			detail3, data3, title4, detail4, data4, title5, detail5, data5;
	private ImageView icon1_1, icon1_2, icon2_1, icon2_2, icon3_1, icon3_2,
			icon4_1, icon4_2, icon5_1, icon5_2;

	// ����Ĳ�ѯ
	private BmobQuery<Bulletin> query = new BmobQuery<Bulletin>();
	private List<Bulletin> bulletins = new ArrayList<Bulletin>();

	Handler mHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);

			switch (msg.what)
			{
			case 0:
				// ������������,�õ�����
				List<Bulletin> mbulletins = new ArrayList<Bulletin>();
				mbulletins.addAll((ArrayList<Bulletin>) msg.obj);
				if (mbulletins.size() > 0)
				{
					Log.e("http", "mbulletins Size is " + mbulletins.size());
					title1.setText(mbulletins.get(0).getTitle());
					detail1.setText(mbulletins.get(0).getDetail());
					data1.setText(mbulletins.get(0).getData());
					showOrhide_Icon(mbulletins.get(0).getObject(), icon1_1,
							icon1_2);

					title2.setText(mbulletins.get(1).getTitle());
					detail2.setText(mbulletins.get(1).getDetail());
					data2.setText(mbulletins.get(1).getData());
					showOrhide_Icon(mbulletins.get(1).getObject(), icon2_1,
							icon2_2);

					title3.setText(mbulletins.get(2).getTitle());
					detail3.setText(mbulletins.get(2).getDetail());
					data3.setText(mbulletins.get(2).getData());
					showOrhide_Icon(mbulletins.get(2).getObject(), icon3_1,
							icon3_2);

					title4.setText(mbulletins.get(3).getTitle());
					detail4.setText(mbulletins.get(3).getDetail());
					data4.setText(mbulletins.get(3).getData());
					showOrhide_Icon(mbulletins.get(3).getObject(), icon4_1,
							icon4_2);

					title5.setText(mbulletins.get(4).getTitle());
					detail5.setText(mbulletins.get(4).getDetail());
					data5.setText(mbulletins.get(4).getData());
					showOrhide_Icon(mbulletins.get(4).getObject(), icon5_1,
							icon5_2);
				}
				break;
			default:
				break;
			}
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	// ��ʼ���������ݣ����Ա��ط�����
	private void InitBulletinDatas2()
	{
		final Gson gson = new Gson();
		new Thread()
		{
			@Override
			public void run()
			{
				String url = "http://" + Marguments.ip1 + "/getbulletins.php";
				HttpResponse httpResponse = null;
				HttpGet httpGet = new HttpGet(url);
				try
				{
					httpResponse = new DefaultHttpClient().execute(httpGet);
					if (httpResponse.getStatusLine().getStatusCode() == 200)
					{
						String result = EntityUtils.toString(httpResponse
								.getEntity());

						List<Bulletin> ps = gson.fromJson(result,
								new TypeToken<List<Bulletin>>()
								{
								}.getType());
						for (int i = 0; i < ps.size(); i++)
						{
							bulletins.add(ps.get(i));
						}

						Message msg = new Message();
						msg.obj = bulletins;
						msg.what = 0;
						Log.e("http", "bulletins  size  " + bulletins.size());
						mHandler.sendMessage(msg);
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater
				.inflate(R.layout.shouye_fragment, container, false);
		InitView(view);
		bulletins.clear();
		
		InitBulletinDatas2();
		return view;
	}

	private void InitView(View view)
	{
		for (int i = 0; i < img.length; i++)
		{
			img[i] = (ImageView) view.findViewById(image[i]);
			img[i].setOnClickListener(onClickListener);
		}
		detailnews = (TextView) view.findViewById(R.id.detailnews);

		// ֻҪ����ϵͳ����Ա�����ز�����ť
		add = (Button) view.findViewById(R.id.add);
		ly_edit = (LinearLayout) view.findViewById(R.id.ly_edit);
		add.setOnClickListener(onClickListener);
		// ֻ�е�ϵͳ������Ա�������Ա�Ż���ʾ�༭����İ�ť
		if (!Marguments.currentAccount.equals("0")
				&& !Marguments.currentAccount.equals("system"))
		{
			add.setVisibility(View.GONE);
			ly_edit.setVisibility(View.GONE);
		}
		findView(view);
	}

	// ���ҹ���ؼ�
	private void findView(View view)
	{
		title1 = (TextView) view.findViewById(R.id.title1);
		detail1 = (TextView) view.findViewById(R.id.detail1);
		data1 = (TextView) view.findViewById(R.id.data1);

		title2 = (TextView) view.findViewById(R.id.title2);
		detail2 = (TextView) view.findViewById(R.id.detail2);
		data2 = (TextView) view.findViewById(R.id.data2);

		title3 = (TextView) view.findViewById(R.id.title3);
		detail3 = (TextView) view.findViewById(R.id.detail3);
		data3 = (TextView) view.findViewById(R.id.data3);

		title4 = (TextView) view.findViewById(R.id.title4);
		detail4 = (TextView) view.findViewById(R.id.detail4);
		data4 = (TextView) view.findViewById(R.id.data4);

		title5 = (TextView) view.findViewById(R.id.title5);
		detail5 = (TextView) view.findViewById(R.id.detail5);
		data5 = (TextView) view.findViewById(R.id.data5);

		icon1_1 = (ImageView) view.findViewById(R.id.icon1_1);
		icon1_2 = (ImageView) view.findViewById(R.id.icon1_2);

		icon2_1 = (ImageView) view.findViewById(R.id.icon2_1);
		icon2_2 = (ImageView) view.findViewById(R.id.icon2_2);

		icon3_1 = (ImageView) view.findViewById(R.id.icon3_1);
		icon3_2 = (ImageView) view.findViewById(R.id.icon3_2);

		icon4_1 = (ImageView) view.findViewById(R.id.icon4_1);
		icon4_2 = (ImageView) view.findViewById(R.id.icon4_2);

		icon5_1 = (ImageView) view.findViewById(R.id.icon5_1);
		icon5_2 = (ImageView) view.findViewById(R.id.icon5_2);
	}

	// ���ڲ��������ͼ���Ƿ���ʾ
	// 1 ��ʾ��ʦ��Ϣ
	// 2 ��ʾѧ����Ϣ
	// 3 ��ʾ��ʦ��ѧ����Ϣ
	private void showOrhide_Icon(String object, ImageView icon1, ImageView icon2)
	{
		switch (Integer.parseInt(object))
		{
		case 1:
			icon1.setVisibility(View.VISIBLE);
			icon2.setVisibility(View.GONE);
			break;
		case 2:
			icon1.setVisibility(View.GONE);
			icon2.setVisibility(View.VISIBLE);
			break;
		case 3:
			icon1.setVisibility(View.VISIBLE);
			icon2.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

	private OnClickListener onClickListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.homepage_open:
				if (flag)
				{
					startAnim();
				} else
				{
					closeAnim();
				}
				break;
			case R.id.homepage_a:
				pageid = 1;
				detailnews.setText(detailnewsStrings[0]);
				img[6].setImageResource(R.drawable.jiaowuleft_1);
				break;
			case R.id.homepage_b:
				pageid = 2;
				detailnews.setText(detailnewsStrings[1]);
				img[6].setImageResource(R.drawable.jiaowuleft_2);
				break;
			case R.id.homepage_c:
				pageid = 3;
				detailnews.setText(detailnewsStrings[2]);
				img[6].setImageResource(R.drawable.jiaowuleft_3);
				break;
			case R.id.homepage_d:
				pageid = 4;
				detailnews.setText(detailnewsStrings[3]);
				img[6].setImageResource(R.drawable.jiaowuleft_4);
				break;
			case R.id.homepage_e:
				pageid = 5;
				detailnews.setText(detailnewsStrings[4]);
				img[6].setImageResource(R.drawable.jiaowuleft_5);
				break;
			case R.id.homepage_news:
				startNewsActivity();
				break;
			case R.id.add:
				Intent intent = new Intent(getActivity(),
						EditBulletinActivity.class);
				startActivity(intent);
				break;
			default:
				break;
			}
		}
	};

	// ������������
	private void startNewsActivity()
	{
		Intent intent = new Intent(getActivity(), NewsActivity.class);

		Bundle bundle = new Bundle();
		bundle.putInt("catagoryid", pageid);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	// ��������--չ���б��
	private void startAnim()
	{
		for (int i = 1; i < img.length - 1; i++)
		{
			ObjectAnimator animator = ObjectAnimator.ofFloat(img[i],
					"translationY", 0F, i * 80F);
			animator.setDuration(1000);
			animator.setInterpolator(new BounceInterpolator());
			animator.setStartDelay(i * 100);
			animator.start();
		}
		flag = false;
	}

	// �رն���--�����б��
	private void closeAnim()
	{
		for (int i = 1; i < img.length - 1; i++)
		{
			ObjectAnimator animator = ObjectAnimator.ofFloat(img[i],
					"translationY", i * 80F, 0F);
			animator.setDuration(1000);
			animator.setInterpolator(new BounceInterpolator());
			animator.setStartDelay(i * 100);
			animator.start();
		}
		flag = true;
	}
}
