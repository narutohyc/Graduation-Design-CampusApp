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

	// 用于展开菜单的图片
	private int[] image = { R.id.homepage_open, R.id.homepage_a,
			R.id.homepage_b, R.id.homepage_c, R.id.homepage_d, R.id.homepage_e,
			R.id.homepage_news };
	// 可展开菜单的状态 true表示未展开
	private boolean flag = true;
	// 根据不同的身份信息，展示不同的Fragment
	private int pageid = 1;
	private String[] detailnewsStrings = {
			"\n        2015年12月28日19点，福州大学至诚学院-英国南威尔士大学留学项目宣讲会于东阶梯报告厅成功举行。",
			"\n        为了规范学院年度考核工作，建立健全科学的考核激励机制，严明纪律，奖励先进，处罚落后，调动广大教职工积极性，维护正常的工作秩序，结合我院实际，制定本补充规定",
			"\n        2009年在至诚大学创立见习与创业协会，任第一届会长。2010年受聘福建省省旅假日旅游有限公司市场部经理职位，组建了覆盖10所大学100多人的团队，当年出游人数达到2.4万人次 ",
			"\n        介绍几个学习C语言必备的装置和书籍：A）开发环境例如turbo C 2.0,这个曾经占据了DOS时代开发程序的大半个江山。但是现在windows时代，用turbo...",
			"\n        我们进入智能手机时代已有7年，看起来是时候问些新的问题了。我们过去几年提出的或探讨的问题现在基本上都有了答案，或者都变得无关紧要。新问题和新疑问在不断出现。" };
	// 用于展示公告信息的控件
	private TextView title1, detail1, data1, title2, detail2, data2, title3,
			detail3, data3, title4, detail4, data4, title5, detail5, data5;
	private ImageView icon1_1, icon1_2, icon2_1, icon2_2, icon3_1, icon3_2,
			icon4_1, icon4_2, icon5_1, icon5_2;

	// 公告的查询
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
				// 完成主界面更新,拿到数据
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

	// 初始化公告数据，来自本地服务器
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

		// 只要不是系统管理员便隐藏操作按钮
		add = (Button) view.findViewById(R.id.add);
		ly_edit = (LinearLayout) view.findViewById(R.id.ly_edit);
		add.setOnClickListener(onClickListener);
		// 只有当系统管理人员或测试人员才会显示编辑公告的按钮
		if (!Marguments.currentAccount.equals("0")
				&& !Marguments.currentAccount.equals("system"))
		{
			add.setVisibility(View.GONE);
			ly_edit.setVisibility(View.GONE);
		}
		findView(view);
	}

	// 查找公告控件
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

	// 用于操作公告的图标是否显示
	// 1 表示教师信息
	// 2 表示学生信息
	// 3 表示教师和学生信息
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

	// 进入教务的详情
	private void startNewsActivity()
	{
		Intent intent = new Intent(getActivity(), NewsActivity.class);

		Bundle bundle = new Bundle();
		bundle.putInt("catagoryid", pageid);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	// 开启动画--展开列表項
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

	// 关闭动画--收起列表項
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
