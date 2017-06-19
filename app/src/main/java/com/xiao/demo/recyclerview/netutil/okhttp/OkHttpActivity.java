package com.xiao.demo.recyclerview.netutil.okhttp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.xiao.demo.recyclerview.R;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class OkHttpActivity extends AppCompatActivity {

    private static final String TAG = "OkHttpActivity";

    @BindView(R.id.okhttp_btn_about)
    Button btn_about;

    @BindView(R.id.okhttp_btn_knowledge)
    Button btn_knowledeg;

    @BindView(R.id.okhttp_btn_uploadfile)
    Button btn_upload;

    // 13910388910    ddoctor2016

    @BindView(R.id.okhttp_tv_result)
    TextView tv_result;

    MyCallBack mCallback;

    private ArrayList<String> mSelectedData;

    public static void start(Context context) {
        Intent starter = new Intent(context, OkHttpActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);
        parseIntent();
        mCallback = new MyCallBack();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri uri = intent.getData();
            tv_result.setText("scheme = " + uri.getScheme() + "\nhost = " + uri.getHost() + "\npath = " + uri.getPath() + "\n" + uri.toString());
        }
    }

    class MyCallBack extends OKHttpCallBack {

        @Override
        public void onFailure(int code, Exception e, String message, Object tag) {
            Log.e(TAG, "onFailure: code = " + code + " message = " + message + " tag = " + tag);
            if (tv_result != null)
                tv_result.append(message);
            e.printStackTrace();
        }

        @Override
        public <T> void onSuccess(T t, Object tag) {
            Log.e(TAG, "onSuccess: t = " + t + " tag = " + tag);
        }
    }

    private void doUpload() {
        MultiImageSelector.create().showCamera(true).count(9).multi().origin(mSelectedData).start(OkHttpActivity.this, 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 200) {
                if (mSelectedData != null) {
                    mSelectedData = new ArrayList<>();
                }
                mSelectedData = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
//                Log.e(TAG, "onActivityResult: selectedData "+mSelectedData.toString());
                File file = new File(mSelectedData.get(0));

                Luban.get(OkHttpActivity.this).load(file).putGear(Luban.FIRST_GEAR).setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        Log.e(TAG, "onStart: file.size " + file.getTotalSpace() + " " + file.getAbsolutePath());
                    }

                    @Override
                    public void onSuccess(File files) {
                        Log.e(TAG, "onSuccess: file.size " + files.getTotalSpace() + " " + files.getAbsolutePath());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }).launch();
//                for (String strnig : mSelectedData) {
//
//                    uploadFile(strnig);
//                    Log.e(TAG, "onActivityResult: string = " + strnig + " " + (new File(strnig).getAbsolutePath()));
//                }
            }
        }
    }

    private String getFileSuffix(String path) {
        if (TextUtils.isEmpty(path)) return "";
        File file = new File(path);
        if (!file.exists()) return "";
        int i = path.lastIndexOf(".");
        if (i == -1) return "";
        String s = path.substring(i);
        Log.e(TAG, "getFileSuffix: s = " + s);
        return s;
    }

}
