package com.xiao.demo.recyclerview.lazyload;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
 * Description：懒加载  生命周期监听
 * Created on 2017/4/6
 * Author : 萧
 */
public class LazyLoadFragment extends Fragment {

    private static final String TAG = "LazyLoadFragment";
    String title;
    int type;

    @BindView(R.id.tv1)
    TextView tv1;

    @BindView(R.id.tv2)
    TextView tv2;

    // 视图加载完毕
    boolean isViewInited;
    // 数据加载完毕
    boolean isDataInited;
    // 是否强制加载
    private boolean isForceLoad = false;

    public static LazyLoadFragment newInstance(String title, int type) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("type", type);
        LazyLoadFragment fragment = new LazyLoadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        title = bundle.getString("title");
        type = bundle.getInt("type");
        Log.e(TAG, "onCreate: title = " + title + " isViewInited " + isViewInited + " isVisible() = " + isVisible() + " isDataInited = " + isDataInited);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_lazyload_fragment, container, false);
        ButterKnife.bind(this, view);
        Log.e(TAG, "onCreateView: title = " + title + " isViewInited " + isViewInited + " isVisible() = " + isVisible() + " isDataInited = " + isDataInited);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated: title = " + title + " isViewInited " + isViewInited + " isVisible() = " + isVisible() + " isDataInited = " + isDataInited + " isForceLoad = " + isForceLoad);
        tv1.setText(title + "-" + type);
        isViewInited = true;
        if (isVisible() && isViewInited && isForceLoad) {
            Log.e(TAG, "onViewCreated: 强制加载");
            onLoadData();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: title = " + title + " isViewInited " + isViewInited + " isVisible() = " + isVisible() + " isDataInited = " + isDataInited);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: title = " + title + " isViewInited = " + isViewInited + " isVisible() = " + isVisible() + " isDataInited = " + isDataInited);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: title = " + title + " isViewInited = " + isViewInited + " isVisible() = " + isVisible() + " isDataInited = " + isDataInited);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewInited = false;
        isDataInited = false;
        Log.e(TAG, "onDestroyView: title = " + title + " isViewInited " + isViewInited + " isVisible() = " + isVisible() + " isDataInited = " + isDataInited);
    }

    public void setForceLoad(boolean forceLoad) {
        Log.e(TAG, "setForceLoad: forceLoad = " + forceLoad);
        isForceLoad = forceLoad;
    }

    private void onLoadData() {
        isDataInited = true;
        tv2.setText("title=" + title + " type=" + type + " " + TimeUtil.getInstance().getStandardDate("HH:mm:ss.S"));
        Log.e(TAG, "onLoadData: title = " + title + "isViewInited = " + isViewInited + " isVisible() = " + isVisible() + " isDataInited = " + isDataInited);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(TAG, "setUserVisibleHint: isVisibleToUser = " + isVisibleToUser + " title = " + title + " isViewInited = " + isViewInited + " isVisible() = " + isVisible() + " isDataInited = " + isDataInited + " isForceLoad = " + isForceLoad);
        if ((isVisibleToUser && isViewInited && isVisible() && !isDataInited)) {
            Log.e(TAG, "setUserVisibleHint: isVisibleToUser = " + isVisibleToUser + " title = " + title + " 符合要求请求数据 " + " isForceLoad = " + isForceLoad);
            onLoadData();
        }
    }

}
