package com.example.fragemnt;

import com.example.argu.Marguments;
import com.example.campusapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.ImageView;

public class JiaowuRightDetailtFragment extends Fragment
{
	private ImageView iv_back, iv_full;
	private WebView mWebView;
	private String[][] detailStrings = {
			{
					"file:///android_asset/jiaoxuegaige1.html ",
					"file:///android_asset/jiaoxuegaige2.html ",
					"http://210.34.52.11/news/news_view.asp?news_id=201572719575173499&class_id=20",
					"http://210.34.52.11/news/news_view.asp?news_id=2015727200173499&class_id=20",
					"http://210.34.52.11/news/news_view.asp?news_id=2015727202273499&class_id=20",
					"http://210.34.52.11/news/news_view.asp?news_id=2015727202273499&class_id=20",
					"http://210.34.52.11/news/news_view.asp?news_id=201572719494673499&class_id=20",
					"http://210.34.52.11/news/news_view.asp?news_id=201572719523373499&class_id=20" },
			{
					"http://210.34.52.11/news/news_view.asp?news_id=2014122615224373499&class_id=91",
					"http://210.34.52.11/news/news_view.asp?news_id=2014122615274773499&class_id=91",
					"http://210.34.52.11/news/news_view.asp?news_id=2014122614565373499&class_id=91",
					"http://210.34.52.11/news/news_view.asp?news_id=2014122615131173499&class_id=91",
					"http://210.34.52.11/news/news_view.asp?news_id=2014122616174373499&class_id=91",
					"http://210.34.52.11/news/news_view.asp?news_id=201412309334573499&class_id=92",
					"http://210.34.52.11/news/news_view.asp?news_id=201412309362573499&class_id=92",
					"http://210.34.52.11/news/news_view.asp?news_id=201412309395873499&class_id=92",
					"http://210.34.52.11/news/news_view.asp?news_id=201412309511473499&class_id=92",
					"http://210.34.52.11/news/news_view.asp?news_id=201412309592873499&class_id=92" },
			{
					"http://210.34.52.11/news/news_view.asp?news_id=201512311650573499&class_id=1",
					"http://210.34.52.11/news/news_view.asp?news_id=2015122915433773499&class_id=1",
					"http://210.34.52.11/news/news_view.asp?news_id=2015122819432673499&class_id=1",
					"http://210.34.52.11/news/news_view.asp?news_id=201512248425973499&class_id=1",
					"http://210.34.52.11/news/news_view.asp?news_id=201512111741173499&class_id=1",
					"http://210.34.52.11/news/news_view.asp?news_id=201512911185973499&class_id=1",
					"http://210.34.52.11/news/news_view.asp?news_id=2015112710125073499&class_id=1",
					"http://210.34.52.11/news/news_view.asp?news_id=2015112611115373499&class_id=1",
					"http://210.34.52.11/news/news_view.asp?news_id=201511131450073499&class_id=1" },
			{ "file:///android_asset/liuyanzhongxin.html" },
			{ "http://www.fdzcxy.com/read.asp?id=6090",
					"http://www.fdzcxy.com/read.asp?id=6089",
					"http://www.fdzcxy.com/read.asp?id=6088",
					"http://www.fdzcxy.com/read.asp?id=6087",
					"http://www.fdzcxy.com/read.asp?id=6080",
					"http://www.fdzcxy.com/read.asp?id=6079",
					"http://www.fdzcxy.com/read.asp?id=6078",
					"http://www.fdzcxy.com/read.asp?id=6077",
					"http://www.fdzcxy.com/read.asp?id=6076",
					"http://www.fdzcxy.com/read.asp?id=6075",
					"http://www.fdzcxy.com/read.asp?id=6075",
					"http://www.fdzcxy.com/read.asp?id=6074" } };
	private static int position = 0, colunm = 0;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.jiaowu_right_detail, container,
				false);
		InitUI(view);
		return view;
	}

	private void InitUI(View view)
	{
		iv_back = (ImageView) view.findViewById(R.id.iv_back);
		iv_back.setOnClickListener(onClickListener);

		iv_full = (ImageView) view.findViewById(R.id.full);
		iv_full.setOnClickListener(onClickListener);

		mWebView = (WebView) view.findViewById(R.id.mwebview);
		SetmWebView();
	}

	private OnClickListener onClickListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.iv_back:
				getFragmentManager().popBackStack();
				break;
			case R.id.full:
				Bundle bundle = new Bundle();
				bundle.putString("url",
						detailStrings[Marguments.position][Marguments.column]);

				Intent intent = new Intent(getActivity(),
						Jiaowu_Right_Detail_FullActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
				break;

			default:
				break;
			}
		}
	};

	private void SetmWebView()
	{
		// 支持javascript
		mWebView.getSettings().setJavaScriptEnabled(true);
		// 设置可以支持缩放
		mWebView.getSettings().setSupportZoom(true);
		// 设置出现缩放工具
		mWebView.getSettings().setBuiltInZoomControls(true);
		// 扩大比例的缩放
		mWebView.getSettings().setUseWideViewPort(true);
		// 设置重新排版
		mWebView.getSettings()
				.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		mWebView.getSettings().setLoadWithOverviewMode(true);
		mWebView.getSettings().setDefaultTextEncodingName("gbk");
		// 设置自动排版
		mWebView.getSettings().setLayoutAlgorithm(
				LayoutAlgorithm.NARROW_COLUMNS);
		// 支持网页继续在此处浏览，而不是调用内置浏览器
		mWebView.setWebViewClient(new WebViewClient()
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

		mWebView.loadUrl(detailStrings[Marguments.position][Marguments.column]);
		this.mWebView.setInitialScale(180);
	}
}
