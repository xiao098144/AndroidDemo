package com.xiao.demo.recyclerview.picture;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xiao.demo.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class GlideListActivity extends AppCompatActivity {

    RecyclerView recycler;

    List<String> list = new ArrayList<>();

    public static void start(Context context) {
        Intent starter = new Intent(context, GlideListActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_list);
        list.add("http://ww2.sinaimg.cn/large/7daf2138gw1f6fcxhk9dwj21kw2dc1kx.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcxj7wjij21kw2dc1kx.jpg");
        list.add("http://ww1.sinaimg.cn/large/7daf2138gw1f6fcxlj0gpj21kw2dcqtn.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcxn696oj21kw2dcu04.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcxq1x1nj21kw2dcx61.jpg");
        list.add("http://ww1.sinaimg.cn/large/7daf2138gw1f6fcxrte13j21kw2dc1kx.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcxublp2j21kw2dc4qp.jpg");
        list.add("http://ww3.sinaimg.cn/large/7daf2138gw1f6fcxw2b8oj21kw2dc4qp.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcy0bt92j21kw2dchdj.jpg");
        list.add("http://ww2.sinaimg.cn/large/7daf2138gw1f6fcy46t8ej21kw2dc4qp.jpg");
        list.add("http://ww2.sinaimg.cn/large/7daf2138gw1f6fczirwddj21kw2dc7wh.jpg");
        list.add("http://ww2.sinaimg.cn/large/7daf2138gw1f6fcxhk9dwj21kw2dc1kx.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcxj7wjij21kw2dc1kx.jpg");
        list.add("http://ww1.sinaimg.cn/large/7daf2138gw1f6fcxlj0gpj21kw2dcqtn.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcxn696oj21kw2dcu04.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcxq1x1nj21kw2dcx61.jpg");
        list.add("http://ww1.sinaimg.cn/large/7daf2138gw1f6fcxrte13j21kw2dc1kx.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcxublp2j21kw2dc4qp.jpg");
        list.add("http://ww3.sinaimg.cn/large/7daf2138gw1f6fcxw2b8oj21kw2dc4qp.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcy0bt92j21kw2dchdj.jpg");
        list.add("http://ww2.sinaimg.cn/large/7daf2138gw1f6fcy46t8ej21kw2dc4qp.jpg");
        list.add("http://ww2.sinaimg.cn/large/7daf2138gw1f6fczirwddj21kw2dc7wh.jpg");
        list.add("http://ww2.sinaimg.cn/large/7daf2138gw1f6fcxhk9dwj21kw2dc1kx.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcxj7wjij21kw2dc1kx.jpg");
        list.add("http://ww1.sinaimg.cn/large/7daf2138gw1f6fcxlj0gpj21kw2dcqtn.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcxn696oj21kw2dcu04.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcxq1x1nj21kw2dcx61.jpg");
        list.add("http://ww1.sinaimg.cn/large/7daf2138gw1f6fcxrte13j21kw2dc1kx.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcxublp2j21kw2dc4qp.jpg");
        list.add("http://ww3.sinaimg.cn/large/7daf2138gw1f6fcxw2b8oj21kw2dc4qp.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcy0bt92j21kw2dchdj.jpg");
        list.add("http://ww2.sinaimg.cn/large/7daf2138gw1f6fcy46t8ej21kw2dc4qp.jpg");
        list.add("http://ww2.sinaimg.cn/large/7daf2138gw1f6fczirwddj21kw2dc7wh.jpg");
        list.add("http://ww2.sinaimg.cn/large/7daf2138gw1f6fcxhk9dwj21kw2dc1kx.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcxj7wjij21kw2dc1kx.jpg");
        list.add("http://ww1.sinaimg.cn/large/7daf2138gw1f6fcxlj0gpj21kw2dcqtn.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcxn696oj21kw2dcu04.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcxq1x1nj21kw2dcx61.jpg");
        list.add("http://ww1.sinaimg.cn/large/7daf2138gw1f6fcxrte13j21kw2dc1kx.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcxublp2j21kw2dc4qp.jpg");
        list.add("http://ww3.sinaimg.cn/large/7daf2138gw1f6fcxw2b8oj21kw2dc4qp.jpg");
        list.add("http://ww4.sinaimg.cn/large/7daf2138gw1f6fcy0bt92j21kw2dchdj.jpg");
        list.add("http://ww2.sinaimg.cn/large/7daf2138gw1f6fcy46t8ej21kw2dc4qp.jpg");
        list.add("http://ww2.sinaimg.cn/large/7daf2138gw1f6fczirwddj21kw2dc7wh.jpg");
        recycler = (RecyclerView) findViewById(R.id.glidelist_recycler);
        recycler.setHasFixedSize(true);
        recycler.setItemViewCacheSize(8);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setLayoutManager(new LinearLayoutManager(GlideListActivity.this));
        recycler.setAdapter(new GlideListAdapter());
        // Layoutmanager
        // ItemDecoration
        // ItemAnimation
        // Adapter

    }

    class GlideListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

        public GlideListAdapter() {

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(GlideListActivity.this).inflate(R.layout.layout_poicassolist_item,parent,false);
            return new GlideViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            GlideViewHolder glideViewHolder = (GlideViewHolder) holder;
//            Glide.with(GlideListActivity.this).load(list.get(position)).into(glideViewHolder.img);
            Glide.with(GlideListActivity.this).load(list.get(position)).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    if (resource != null) {
                        int x = 512;
                        int y = 512 * resource.getHeight() / resource.getWidth();
                        ViewGroup.LayoutParams params = glideViewHolder.img.getLayoutParams();
                        params.width = x;
                        params.height = y;
                        glideViewHolder.img.setLayoutParams(params);
                        glideViewHolder.img.setScaleType(ImageView.ScaleType.FIT_XY);
                        glideViewHolder.img.setImageBitmap(resource);
                    }
                }
            });
            glideViewHolder.img.setOnClickListener((v) -> {
                WatchPictureActivity.start(GlideListActivity.this,list.get(position));
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class GlideViewHolder extends  RecyclerView.ViewHolder{

            ImageView img;

            public GlideViewHolder(View itemView) {
                super(itemView);
                img = (ImageView) itemView.findViewById(R.id.picassolist_item_img);
            }
        }

    }
//    int x = 512;
//    int y = 512*source.getHeight()/source.getWidth();
//    class CustomGlideImageRequest implements  Images


}
