package com.xiao.demo.recyclerview.lazyload;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.util.TimeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description：
 * Created on 2017/4/7
 * Author : 萧
 */
public class LazyFragment extends BaseLazyLoadFragment {

    private static final String TAG = "LazyFragment";

    String title;
    int type;

    @BindView(R.id.tv1)
    TextView tv1;

    @BindView(R.id.tv2)
    TextView tv2;

    public static LazyFragment newInstance(String title, int type) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("type", type);
        LazyFragment fragment = new LazyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        ButterKnife.bind(this, view);
        isViewInited = true;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv1.setText(title + "-" + type);
        if (isVisible() && isViewInited && isForceLoad) {
            Log.e(TAG, "onViewCreated: 强制加载");
            onLoadData();
        }
    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        tv1.setText(title + "-" + type);
//        Log.e(TAG, "onViewCreated: ");
//    }

    @Override
    public void parseIntentData() {
        Bundle bundle = getArguments();
        title = bundle.getString("title");
        type = bundle.getInt("type");
        Log.e(TAG, "parseIntentData: title=" + title);
    }

    @Override
    public void onLoadData() {
        tv2.setText("title=" + title + " " + TimeUtil.getInstance().getStandardDate("HH:mm:ss.S"));
        Log.e(TAG, "onLoadData: title=" + title);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.layout_lazyload_fragment;
    }
}
