package com.xiao.demo.recyclerview.design;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.util.DataBean;
import com.xiao.demo.recyclerview.widget.RecyclerViewDecoration;

import java.util.ArrayList;
import java.util.List;

public class CardViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    List<DataBean> list = new ArrayList<>();

    public static void startActivityForResult(Activity context) {
        Intent starter = new Intent(context, CardViewActivity.class);
        context.startActivityForResult(starter, 1);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, CardViewActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        for (int i = 0; i < 10; i++) {
            DataBean data = new DataBean();
            data.setName("name-" + i);
            data.setName2("name2-" + i);
            data.setName3("name3-" + i);
            list.add(data);
        }
        recyclerView = (RecyclerView) findViewById(R.id.cardview_recyclerview);
        recyclerView.setHasFixedSize(true);
        CardDecoration card = new CardDecoration();
        RecyclerViewDecoration decoration = new RecyclerViewDecoration(CardViewActivity.this, LinearLayoutManager.VERTICAL, Color.GREEN);
        CardViewAdapter adapter = new CardViewAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CardViewActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(card);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);
    }

    static class CardDecoration extends RecyclerView.ItemDecoration {
        public CardDecoration() {
            super();
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);

        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
        }

    }

    class CardViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        LayoutInflater inflater;

        public CardViewAdapter() {
            inflater = LayoutInflater.from(CardViewActivity.this);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.layout_cardviewitem, parent, false);
            return new CardViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            CardViewHolder cardHolder = (CardViewHolder) holder;
            cardHolder.tv_name1.setText(list.get(position).getName());
            cardHolder.tv_name2.setText(list.get(position).getName2());
            cardHolder.tv_name3.setText(list.get(position).getName3());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class CardViewHolder extends RecyclerView.ViewHolder {

            TextView tv_name1, tv_name2, tv_name3;

            public CardViewHolder(View itemView) {
                super(itemView);
                tv_name1 = (TextView) itemView.findViewById(R.id.cardview_item_tv_name);
                tv_name2 = (TextView) itemView.findViewById(R.id.cardview_item_tv_name2);
                tv_name3 = (TextView) itemView.findViewById(R.id.cardview_item_tv_name3);
            }
        }
    }

}
