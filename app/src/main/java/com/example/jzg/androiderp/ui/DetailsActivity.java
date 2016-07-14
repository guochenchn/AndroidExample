package com.example.jzg.androiderp.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.jzg.androiderp.Base.BaseActivity;
import com.example.jzg.androiderp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * /**
 * author: guochen
 * date: 2016/6/14 11:34
 * email:
 * 详情
 */
public class DetailsActivity extends BaseActivity {
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.psb)
    ProgressBar psb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        String url = getIntent().getStringExtra("url");
        webview.getSettings().setJavaScriptEnabled(true);
        //这就表明当需要从一个网页跳转到另一个网页时，我们希望目标网页仍然在当前 WebView 中显示，而不是打开系统浏览器。
        webview.setWebViewClient(new WebViewClient());
        if (url != null) {
            webview.loadUrl(url);
            webview.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    psb.setVisibility(View.VISIBLE);
                }
            });

            webview.setWebChromeClient(new WebChromeClient() {

                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    super.onProgressChanged(view, newProgress);
                    psb.setProgress(newProgress);
                    if (newProgress == 100) {
                        psb.setVisibility(View.GONE);
                    }
                }
            });
        }
    }
}
