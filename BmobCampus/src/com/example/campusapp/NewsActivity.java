package com.example.campusapp;

import java.io.InputStream;

import org.apache.http.util.EncodingUtils;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * 展示教务首页下拉菜单的详情
 * @author hyc
 *
 */
public class NewsActivity extends Activity
{
	private TextView detail_text,title;
	private ImageView detail_news, back;
	private int catagoryid = 1;
	private int[] image = { R.drawable.jiaowuleft_1, R.drawable.jiaowuleft_2,
			R.drawable.jiaowuleft_3, R.drawable.jiaowuleft_4,
			R.drawable.jiaowuleft_5 };
	private String[] titles = {"会议报告","学院规定","学生活动","学习导向","科技时代"}; 
		
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		Bundle bundle = this.getIntent().getExtras();
		catagoryid = bundle.getInt("catagoryid");
		InitView();
	}

	private void InitView()
	{
		detail_text = (TextView) findViewById(R.id.detail_text);
		detail_news = (ImageView) findViewById(R.id.detail_news);
		
		title = (TextView)findViewById(R.id.title);
		title.setText(titles[catagoryid-1]);
		
		detail_news.setImageResource(image[catagoryid-1]);
		detail_text.setText(readFromAsset("detailnews" + catagoryid + ".txt"));
		back = (ImageView) findViewById(R.id.iv_back);
		back.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
	}

	// 从文件中读入文本
	public String readFromAsset(String fileName)
	{
		String res = "";
		try
		{
			InputStream in = getResources().getAssets().open(fileName);
			int length = in.available();
			byte[] buffer = new byte[length];
			in.read(buffer);
			res = EncodingUtils.getString(buffer, "UTF-8");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
}
