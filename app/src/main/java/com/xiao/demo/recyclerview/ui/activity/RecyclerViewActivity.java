package com.xiao.demo.recyclerview.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xiao.demo.recyclerview.ui.adapter.ListAdapter;
import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.widget.RecyclerViewDecoration;
import com.xiao.demo.recyclerview.widget.autoscrollviewpager.AutoScrollViewPager;
import com.xiao.demo.recyclerview.widget.autoscrollviewpager.CircleIndicator;
import com.xiao.demo.recyclerview.widget.autoscrollviewpager.ImagePagerAdapter;
import com.xiao.demo.recyclerview.picture.PicassoActivity;
import com.xiao.demo.recyclerview.util.DataBean;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    Button btn_add, btn_delete, btn_clear, btn_change;

    RecyclerViewDecoration mItemDecoration;

    /**
     * 默认隐藏
     */
    private RelativeLayout layout_banner;

    /**
     * 广告图
     */
    private AutoScrollViewPager viewPager;

    ListAdapter adapter;

    List<DataBean> list = new ArrayList<>();

    GridLayoutManager gridLayoutManager;

    private void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(this).inflate(R.layout.layout_banner_viewpager, view, false);
        layout_banner = (RelativeLayout) header.findViewById(R.id.layout_banner);
        viewPager = (AutoScrollViewPager) layout_banner.findViewById(R.id.viewPager);
        int width = 1920;
        int height = width * 140 / 1080; // 按设计比例计算高度

        AbsListView.LayoutParams rlp = new AbsListView.LayoutParams(width, height);
        layout_banner.setLayoutParams(rlp);
        ArrayList<Drawable> list = new ArrayList<>();
        Drawable drawable = getResources().getDrawable(R.mipmap.ima_bean_type);
        list.add(drawable);
        drawable = getResources().getDrawable(R.mipmap.ima_protein_type);
        list.add(drawable);
        drawable = getResources().getDrawable(R.mipmap.ima_food_type);
        list.add(drawable);
        drawable = getResources().getDrawable(R.mipmap.ima_nut_type);
        list.add(drawable);
        drawable = getResources().getDrawable(R.mipmap.ima_snacks_type);
        list.add(drawable);
        drawable = getResources().getDrawable(R.mipmap.ima_fruit_type);
        list.add(drawable);


        viewPager.setAdapter(new ImagePagerAdapter(RecyclerViewActivity.this, list).setInfiniteLoop(true));

        viewPager.setInterval(2000);
        viewPager.startAutoScroll();

        viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % list.size());

        LinearLayout pageDotGroup = (LinearLayout) layout_banner.findViewById(R.id.viewGroup);

        layout_banner.setVisibility(View.VISIBLE);

        CircleIndicator indicator = new CircleIndicator(RecyclerViewActivity.this);
        indicator.setViewPager(viewPager);
        indicator.setPageDotGroup(pageDotGroup);
        indicator.setItemsCount(list.size());
        indicator.init();
        adapter.setHeaderView(header);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, RecyclerViewActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 18; i++) {
            DataBean dataBean = new DataBean("name" + i);
            if (i == 0) dataBean.setRes(R.mipmap.ima_bean_type);
            if (i == 1) dataBean.setRes(R.mipmap.ima_food_type);
            if (i == 2) dataBean.setRes(R.mipmap.ima_fruit_type);
            if (i == 3) dataBean.setRes(R.mipmap.ima_nut_type);
            if (i == 4) dataBean.setRes(R.mipmap.ima_protein_type);
            if (i == 5) dataBean.setRes(R.mipmap.ima_snacks_type);
            list.add(dataBean);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_change = (Button) findViewById(R.id.btn_change);
        btn_add.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_change.setOnClickListener(this);
        adapter = new ListAdapter(list, RecyclerViewActivity.this);
        recyclerView.setHasFixedSize(true);
        mItemDecoration = new RecyclerViewDecoration(RecyclerViewActivity.this, LinearLayoutManager.VERTICAL, Color.GREEN);
        //mItemDecoration = new RecyclerViewDecoration(RecyclerViewActivity.this,LinearLayoutManager.VERTICAL, getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(mItemDecoration);
        recyclerView.setLayoutManager(new GridLayoutManager(RecyclerViewActivity.this, 3));

        recyclerView.setAdapter(adapter);
        setHeader(recyclerView);

        adapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, long id) {
                RoundProgressActivity.start(RecyclerViewActivity.this);
                Toast.makeText(RecyclerViewActivity.this, "OnItemClickListener.onItemClick position " + position + " " + list.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position, long id) {
                Toast.makeText(RecyclerViewActivity.this, "OnItemClickListener.onItemLongClick position " + position + " " + list.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnItemClick((view, position, id) -> {
            if (position % 2 == 0) {
                PicassoActivity.start(RecyclerViewActivity.this);
            } else if (position % 3 == 0) {
                FlexBoxActivity.start(RecyclerViewActivity.this);
            } else {
                RoundProgressActivity.start(RecyclerViewActivity.this);
            }
            Toast.makeText(RecyclerViewActivity.this, "OnItemClick.onItemClick position " + position + " " + list.get(position), Toast.LENGTH_SHORT).show();
        });

        adapter.setOnItemLongClick((view, position, id) -> {
            if (position % 2 == 0) {
                WebviewActivity.start(RecyclerViewActivity.this);
            } else {
                RoundProgressActivity.start(RecyclerViewActivity.this);
            }

            Toast.makeText(RecyclerViewActivity.this, "OnItemLongClick.onItemLongClick position " + position + " " + list.get(position), Toast.LENGTH_SHORT).show();
        });

    }

    boolean falg = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                list.add(0, new DataBean("btn_add add new one"));
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_clear:
                list.clear();
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_delete:
                list.remove(0);
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_change:
                falg = !falg;
                if (falg) {
                    mItemDecoration.setmOrientation(RecyclerViewDecoration.ORIENTATION_HYBIRD);
//                    mItemDecoration.setmDrawable(getResources().getDrawable(R.drawable.divider));
                    recyclerView.setLayoutManager(new GridLayoutManager(RecyclerViewActivity.this, 4));

                } else {
                    mItemDecoration.setmOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this));
                }
                break;
            default:
                break;
        }
    }


}
