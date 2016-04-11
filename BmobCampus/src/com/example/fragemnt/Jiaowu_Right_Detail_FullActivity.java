package com.example.fragemnt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.campusapp.R;

public class Jiaowu_Right_Detail_FullActivity extends Activity
{
	private ImageView iv_back;
	private WebView mfullwebview;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jiaowu__right__detail__full);
		InitUrl();
	}

	private void InitUrl()
	{
		mfullwebview = (WebView) findViewById(R.id.mfullwebview);
		Setmfullwebview();
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

	private void Setmfullwebview()
	{
		mfullwebview.getSettings().setJavaScriptEnabled(true);
		// 设置可以支持缩放
		mfullwebview.getSettings().setSupportZoom(true);
		// 设置出现缩放工具
		mfullwebview.getSettings().setBuiltInZoomControls(true);
		// 扩大比例的缩放
		mfullwebview.getSettings().setUseWideViewPort(true);
		// 设置重新排版
		mfullwebview.getSettings().setLayoutAlgorithm(
				LayoutAlgorithm.SINGLE_COLUMN);
		mfullwebview.getSettings().setLoadWithOverviewMode(true);
		mfullwebview.getSettings().setDefaultTextEncodingName("gbk");
		// 设置自动排版
		mfullwebview.getSettings().setLayoutAlgorithm(
				LayoutAlgorithm.NARROW_COLUMNS);
		// 支持网页继续在此处浏览，而不是调用内置浏览器
		mfullwebview.setWebViewClient(new WebViewClient()
		{
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl)
			{
			}
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url)
			{
				view.loadUrl(url);
				return true;
			}
		});
		// 初始化窗口的大小，让其适应手机屏幕
		WindowManager wm = (WindowManager) getSystemService(Activity.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		if (width > 650)
		{
			this.mfullwebview.setInitialScale(210);
		} else if (width > 520)
		{
			this.mfullwebview.setInitialScale(180);
		} else if (width > 450)
		{
			this.mfullwebview.setInitialScale(160);
		} else if (width > 300)
		{
			this.mfullwebview.setInitialScale(140);
		} else
		{
			this.mfullwebview.setInitialScale(120);
		}
		mfullwebview.loadUrl(this.getIntent().getExtras().getString("url"));
	}
}
