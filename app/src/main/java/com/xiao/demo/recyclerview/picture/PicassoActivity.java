package com.xiao.demo.recyclerview.picture;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xiao.demo.recyclerview.R;

public class PicassoActivity extends AppCompatActivity {

    ImageView img;

    public static void start(Context context) {
        Intent starter = new Intent(context, PicassoActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        img = (ImageView) findViewById(R.id.img);
        Picasso.with(PicassoActivity.this)
                .load("http://ix.umei.cc//img2012/XR_VIP32/XR20160625N00551/cover_590.jpg")
                .placeholder(R.mipmap.ima_bean_type)
                .error(R.mipmap.ima_snacks_type)
                .into(img);
        img.setOnClickListener((v) -> {
            PicassoListActivity.start(PicassoActivity.this);
        });
        img.setOnLongClickListener((v) -> {
            GlideListActivity.start(PicassoActivity.this);
            return  true;
        });
    }
}
