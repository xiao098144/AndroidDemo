package com.xiao.demo.recyclerview.ui.activity;

import android.app.Application;
import android.graphics.Bitmap;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * FileName:com.xiao.demo.recyclerview.ui.activity.MyApplication.java
 * Created on 2016/8/15
 * Version V1.0
 */
public class MyApplication  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ImagePipelineConfig build = ImagePipelineConfig.newBuilder(this).setBitmapsConfig(Bitmap.Config.RGB_565).setDecodeMemoryFileEnabled(true).setDownsampleEnabled(true).setMainDiskCacheConfig(DiskCacheConfig.newBuilder(this).setBaseDirectoryName("studioDemo/cache").build()).build();
        Fresco.initialize(this,build);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
