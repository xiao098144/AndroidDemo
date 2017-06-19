package com.xiao.demo.recyclerview.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.util.UrlStatusBean;


public class WebviewActivity extends AppCompatActivity {

    String TAG = "WebviewActivity";

    WebView wv;

//    String url = "file:///android_asset/index1.html";
    String url = "http://cmstest.ddoctor.cn/tyscms/events/yiRan/";

    SparseArray<UrlStatusBean> urlArray = new SparseArray<>();

    public static void start(Context context) {
        Intent starter = new Intent(context, WebviewActivity.class);
        context.startActivity(starter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Log.e(TAG, "onBackPressed:  urlArray " + urlArray + " wv.getUrl() " + wv.getUrl());

            if (wv != null && wv.canGoBack()) {
                UrlStatusBean urlStatusBean1 = urlArray.get(urlArray.size() - 1);
                int key = urlStatusBean1.getIndex();
                key -= 1;
                Log.e(TAG, "onOptionsItemSelected: key " + key);

                if (key >= 0) {
                    int resultIndex = -1;
                    for (int i = key; i >= 0; i--) {
                        int keyAt = urlArray.keyAt(i);
                        UrlStatusBean urlStatusBean = urlArray.get(keyAt);
                        Log.e(TAG, "onOptionsItemSelected: second for cycle i" + i + " keyAt " + keyAt + " urlStatusBean " + urlStatusBean.toString());
                        if (urlStatusBean.isRedirect()) continue;
                        else {
                            resultIndex = keyAt;
                            break;
                        }
                    }
                    Log.e(TAG, "onOptionsItemSelected: resultIndex " + resultIndex);
                    if (resultIndex >= 0) {
                        wv.loadUrl(urlArray.get(resultIndex).getUrl());
                        for (int i = resultIndex; i < urlArray.size(); i++) {
                            urlArray.remove(i);
                        }
                    } else finish();
                } else finish();
            } else finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        wv = (WebView) findViewById(R.id.webview);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(url);
        urlArray.put(urlArray.size(), new UrlStatusBean(url, null, urlArray.size(), false));
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                WebView.HitTestResult hit = view.getHitTestResult();

                if (url.contains("wpa.qq")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                } else {
                    view.loadUrl(url);
                }
                if (hit == null) {
                    int key = urlArray.size() - 1 < 0 ? 0 : urlArray.size() - 1;
                    UrlStatusBean urlStatusBean = urlArray.get(key);
                    if (urlStatusBean != null) {
                        urlStatusBean.setRedirect(true);
                        urlArray.put(key, urlStatusBean);
                    }
                }
                urlArray.put(urlArray.size(), new UrlStatusBean(url, null, urlArray.size(), false));
                Log.e(TAG, "shouldOverrideUrlLoading: url " + url + " hit " + (hit == null ? "hit is null " : hit));
                return true;
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                Log.e(TAG, "onPageCommitVisible: url " + url);
                super.onPageCommitVisible(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Log.e(TAG, "onReceivedError: errorCode " + errorCode + " description " + description + " failingUrl " + failingUrl);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.e(TAG, "onPageFinished: url " + url + "  urlArray " + urlArray);

                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
                Log.e(TAG, "onReceivedLoginRequest: realm " + realm + " account " + account + " args " + args);
                super.onReceivedLoginRequest(view, realm, account, args);
            }
        });
        wv.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.e(TAG, "onProgressChanged: newProgress "+newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.e(TAG, "onReceivedTitle: title "+title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }

            @Override
            public void getVisitedHistory(ValueCallback<String[]> callback) {
                super.getVisitedHistory(callback);
                Log.e(TAG, "getVisitedHistory: ");
            }
        });
    }

    @Override
    public void onBackPressed() {

            Log.e(TAG, "onBackPressed:  urlArray " + urlArray + " wv.getUrl() " + wv.getUrl());

            if (wv != null && wv.canGoBack()) {
                UrlStatusBean urlStatusBean1 = urlArray.get(urlArray.size() - 1);
                int key = urlStatusBean1.getIndex();
                key -= 1;
                Log.e(TAG, "onBackPressed: key " + key);

                if (key >= 0) {
                    int resultIndex = -1;
                    for (int i = key; i >= 0; i--) {
                        int keyAt = urlArray.keyAt(i);
                        UrlStatusBean urlStatusBean = urlArray.get(keyAt);
                        Log.e(TAG, "onBackPressed: second for cycle i" + i + " keyAt " + keyAt + " urlStatusBean " + urlStatusBean.toString());
                        if (urlStatusBean.isRedirect()) continue;
                        else {
                            resultIndex = keyAt;
                            break;
                        }
                    }
                    Log.e(TAG, "onBackPressed: resultIndex " + resultIndex);
                    if (resultIndex >= 0) {
                        wv.loadUrl(urlArray.get(resultIndex).getUrl());
                        for (int i = resultIndex; i < urlArray.size(); i++) {
                            urlArray.remove(i);
                        }
                    } else finish();
                } else finish();
            } else finish();

        super.onBackPressed();
    }


}
