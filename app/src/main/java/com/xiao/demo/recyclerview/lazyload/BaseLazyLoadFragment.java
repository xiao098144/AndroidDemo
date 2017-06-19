package com.xiao.demo.recyclerview.lazyload;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.xiao.demo.recyclerview.util.TimeUtil;

/**
 * Description：懒加载  暂时只处理ViewPager自动管理的Fragment
 * Created on 2017/4/7
 * Author : 萧
 */
public abstract class BaseLazyLoadFragment extends Fragment {

    private static final String TAG = "BaseLazyLoadFragment";

    // 视图加载完毕
    protected boolean isViewInited;
    // 数据加载完毕
    protected boolean isDataInited;
    // 是否强制加载
    protected boolean isForceLoad;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: " + TimeUtil.getInstance().getStandardDate("HH:mm:ss.S"));
        parseIntentData();
    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(getLayoutRes(), container, false);
//        ButterKnife.bind(this, view);
//        isViewInited = true;
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        if (isVisible() && isViewInited && isForceLoad) {
//            Log.e(TAG, "onViewCreated: 强制加载");
//            onLoadData();
//        }
//    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(TAG, "setUserVisibleHint: isVisibleToUser = " + isVisibleToUser + " isViewInited = " + isViewInited + " isVisible() = " + isVisible() + " isDataInited = " + isDataInited + " isForceLoad = " + isForceLoad);
        if ((isVisibleToUser && isViewInited && isVisible() && !isDataInited)) {
            Log.e(TAG, "setUserVisibleHint: isVisibleToUser = " + isVisibleToUser + " 符合要求请求数据 " + " isForceLoad = " + isForceLoad);
            lazyLoad();
        }
    }

    public abstract void parseIntentData();

    private void lazyLoad() {
        isDataInited = true;
        onLoadData();
    }

    public abstract void onLoadData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewInited = false;
        isDataInited = false;
        Log.e(TAG, "onDestroyView: isViewInited " + isViewInited + " isVisible() = " + isVisible() + " isDataInited = " + isDataInited);
        Log.e(TAG, "onDestroyView: " + TimeUtil.getInstance().getStandardDate("HH:mm:ss.S"));
    }

    public boolean isForceLoad() {
        return isForceLoad;
    }

    public void setForceLoad(boolean forceLoad) {
        isForceLoad = forceLoad;
    }

    public abstract int getLayoutRes();

}
