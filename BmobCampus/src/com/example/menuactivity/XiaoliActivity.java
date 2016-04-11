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

		// ֧��javascript
		webView.getSettings().setJavaScriptEnabled(true);
		// ���ÿ���֧������
		webView.getSettings().setSupportZoom(true);
		// ���ó������Ź���
		webView.getSettings().setBuiltInZoomControls(true);
		// �������������
		webView.getSettings().setUseWideViewPort(true);
		// ���������Ű�
		webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setDefaultTextEncodingName("gbk");
		// �����Զ��Ű�
		webView.getSettings()
				.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
		// ֧����ҳ�����ڴ˴�����������ǵ������������
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
