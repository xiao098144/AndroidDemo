package com.xiao.demo.recyclerview.picture;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.xiao.demo.recyclerview.R;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class WatchPictureActivity extends AppCompatActivity {

    PhotoView photoview;
    PhotoViewAttacher attacher;
    String url = "http://ww2.sinaimg.cn/large/7daf2138gw1f6fcxhk9dwj21kw2dc1kx.jpg";

    public static void start(Context context,String url) {
        Intent starter = new Intent(context, WatchPictureActivity.class);
        starter.putExtra("url",url);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_picture);
        photoview = (PhotoView) findViewById(R.id.watchpicture_photoview);
        attacher = new PhotoViewAttacher(photoview);
        url = getIntent().getStringExtra("url");
        usePicasso();
    }

    void usePicasso() {
        Picasso.with(WatchPictureActivity.this).load(url).into(photoview, new Callback() {
            @Override
            public void onSuccess() {
                attacher.update();
            }

            @Override
            public void onError() {

            }
        });
    }

    void useGlide() {
//        crossFade()
        Glide.with(WatchPictureActivity.this).load(url).animate(R.anim.extend).into(new GlideDrawableImageViewTarget(photoview) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                super.onResourceReady(resource, animation);
                attacher.update();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, Menu.NONE, "Use Picasso");
        menu.add(Menu.NONE, 1, Menu.NONE, "Use Glide");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                usePicasso();
                break;
            case 1:
                useGlide();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
