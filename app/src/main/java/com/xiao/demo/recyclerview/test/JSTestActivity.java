package com.xiao.demo.recyclerview.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.util.FileUtil;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JSTestActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, JSTestActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.btn_add)
    Button btn_add;
    @BindView(R.id.webview)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jstest);
        ButterKnife.bind(this);
        initWebView();
        loadChart();

        btn_add.setOnClickListener(v -> mWebView.loadUrl("javascript:abc("+System.currentTimeMillis()+","+(new Random().nextInt(50)+1)+");"));

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView()
    {
//        mWebView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setEnabled(false);
        mWebView.setClickable(false);
        mWebView.setWebViewClient(new WebViewClient()
        {
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                mWebView.setVisibility(View.VISIBLE);
            }
        });
    }

    private static final String TAG = "JSTestActivity";

    public void loadChart(){
        String html = FileUtil.getFromAssets(getResources(), "chart.html");
        html = html.replace("{$AndroidContainerWidth}", "" + 1280);// _webView.getWidth());//
        html = html.replace("{$AndroidContainerHeight}", "" + 400);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(System.currentTimeMillis());
        sb.append(",");
        sb.append(20);
        sb.append("]");
        html = html.replace("{$dataArray}",sb.toString());
        Log.e(TAG, "loadChart: html = "+html);
        mWebView.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "utf-8", "");
//        mWebView.loadData(html,"text/html", "UTF-8");
//        mWebView.loadUrl("javascript:init("+System.currentTimeMillis()+","+20+");");
    }
}
