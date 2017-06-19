package com.xiao.demo.recyclerview.picture;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.xiao.demo.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class PicassoListActivity extends AppCompatActivity {

    RecyclerView recycler;

    List<String> list = new ArrayList<>();

    public static void start(Context context) {
        Intent starter = new Intent(context, PicassoListActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_list);
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
        recycler = (RecyclerView) findViewById(R.id.picassolist_recycler);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setLayoutManager(new LinearLayoutManager(PicassoListActivity.this));
        recycler.setHasFixedSize(true);
        recycler.setItemViewCacheSize(8);
        recycler.setAdapter(new PicassoListAdapter());
    }

    class PicassoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(PicassoListActivity.this).inflate(R.layout.layout_poicassolist_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewholder = (ViewHolder) holder;
            Picasso.with(PicassoListActivity.this).load(list.get(position)).config(Bitmap.Config.RGB_565).transform(new CustomTransFormation()).into(viewholder.img);
//            Picasso.with(PicassoListActivity.this).load(list.get(position)).resize(120,120).placeholder(R.mipmap.ima_food_type).error(R.mipmap.ima_snacks_type).fit().into(viewholder.img);
            viewholder.img.setOnClickListener((v) -> {
                WatchPictureActivity.start(PicassoListActivity.this,list.get(position));
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView img;

            public ViewHolder(View itemView) {
                super(itemView);
                img = (ImageView) itemView.findViewById(R.id.picassolist_item_img);
            }
        }

        class CustomTransFormation implements Transformation{

            @Override
            public Bitmap transform(Bitmap source) {
                int size = Math.min(source.getWidth(), source.getHeight());
//                int x = (source.getWidth() - size) / 2;
//                int y = (source.getHeight() - size) / 2;
//                if (size <1080) return  source;
                int x = 512;
                int y = 512*source.getHeight()/source.getWidth();
//                2,048px × 3,072p
//                2048/1080*3072/1920
//                2049/3072 = 1080/height;
                Log.e(PicassoListActivity.class.getSimpleName(), "transform: source.getWidth "+source.getWidth()+" height "+source.getHeight()+" y "+y);
//                Bitmap result = Bitmap.createBitmap(source, 0,0, x, y);
                Bitmap result = Bitmap.createScaledBitmap(source,x,y,false);
                if (result != source) {
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "";
            }
        }
    }
}
