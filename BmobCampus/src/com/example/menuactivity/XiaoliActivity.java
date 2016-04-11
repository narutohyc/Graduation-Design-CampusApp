package com.example.menuactivity;

import com.example.campusapp.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.Button;
import android.widget.ImageView;

public class XiaoliActivity extends Activity
{
	private ImageView iv_back;
	private Button first, second;
	private WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xiaoli);
		InitView();
	}

	private void InitView()
	{
		first = (Button) findViewById(R.id.xia);
		second = (Button) findViewById(R.id.shang);
		webView = (WebView) findViewById(R.id.webview);

		// 支持javascript
		webView.getSettings().setJavaScriptEnabled(true);
		// 设置可以支持缩放
		webView.getSettings().setSupportZoom(true);
		// 设置出现缩放工具
		webView.getSettings().setBuiltInZoomControls(true);
		// 扩大比例的缩放
		webView.getSettings().setUseWideViewPort(true);
		// 设置重新排版
		webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setDefaultTextEncodingName("gbk");
		// 设置自动排版
		webView.getSettings()
				.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
		// 支持网页继续在此处浏览，而不是调用内置浏览器
		webView.setWebViewClient(new WebViewClient()
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

		webView.loadUrl(" file:///android_asset/xiaolixia.html ");
		this.webView.setInitialScale(180);

		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(onClickListener);
		first.setOnClickListener(onClickListener);
		second.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.xia:
				first.setBackgroundColor(getResources().getColor(
						R.color.universal_bg));
				second.setBackgroundColor(getResources().getColor(
						R.color.title_bg));
				webView.loadUrl(" file:///android_asset/xiaolixia.html ");
				break;
			case R.id.shang:
				first.setBackgroundColor(getResources().getColor(
						R.color.title_bg));
				second.setBackgroundColor(getResources().getColor(
						R.color.universal_bg));
				webView.loadUrl(" file:///android_asset/xiaolishang.html ");
				break;
			case R.id.iv_back:
				finish();
				break;

			default:
				break;
			}
		}
	};
}
