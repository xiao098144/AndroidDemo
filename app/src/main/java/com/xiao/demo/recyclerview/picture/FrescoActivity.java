package com.xiao.demo.recyclerview.picture;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.xiao.demo.recyclerview.R;

public class FrescoActivity extends AppCompatActivity {

    String url = "http://ww2.sinaimg.cn/large/7daf2138gw1f6l52khzi2j21kw2dce81.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);
        

    }
}
