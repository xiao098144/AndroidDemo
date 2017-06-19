package com.xiao.demo.recyclerview.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.util.SingleChoiceAdapter;
import com.xiao.demo.recyclerview.widget.TestBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description：
 * Created on 2017/3/1
 * Author : 萧
 */
public class GridViewActivity extends Activity {

    private static final String TAG = "GridViewActivity";

    public static void start(Context context) {
        Intent starter = new Intent(context, GridViewActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.grid)
    GridView grid;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    List<TestBean> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        ButterKnife.bind(this);
        for (int i = 0; i < 7; i++) {
            TestBean t = new TestBean();
            t.setTitle("" + (12 + i));
            switch (i) {
                case 0:
                    t.setDes("日");
                    break;
                case 1:
                    t.setDes("一");
                    break;
                case 2:
                    t.setDes("二");
                    break;
                case 3:
                    t.setDes("三");
                    break;
                case 4:
                    t.setDes("四");
                    break;
                case 5:
                    t.setDes("五");
                    break;
                case 6:
                    t.setDes("六");
                    break;
            }
            list.add(t);
        }

        Adapter adapter = new Adapter(this);
        grid.setAdapter(adapter);
        adapter.setData(list, 0);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SparseBooleanArray isSelected = adapter.getIsSelected();
                Log.e(TAG, "onItemClick: " + isSelected);
                if (isSelected != null && !isSelected.get(position)) {
                    adapter.setSelected(position);
                }
            }
        });

    }

    static class Adapter extends SingleChoiceAdapter<TestBean> {

        public Adapter(Context context) {
            super(context);
        }

        public void setData(List<TestBean> dataList, int id) {
            this.setData(dataList);
            init(id);
        }

        private void init(int id) {
            isSelected = new SparseBooleanArray();

            if (dataList != null) {
                for (int i = 0; i < dataList.size(); i++) {
                    if (id == i) {
                        isSelected.put(i, true);
                    }
                }
            }
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_calendar, parent, false);
                holder = new ViewHolder();
                holder.tv1 = (TextView) convertView.findViewById(R.id.tv_calendar_week);
                holder.tv2 = (TextView) convertView.findViewById(R.id.tv_calendar_day);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv1.setText(dataList.get(position).getTitle());
            holder.tv2.setText(dataList.get(position).getDes());

            if (isSelected.get(position)) {
                holder.tv2.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.green_oval_bg));
            } else {
                holder.tv2.setBackgroundDrawable(null);
            }

            return convertView;
        }

        static class ViewHolder {
            TextView tv1;
            TextView tv2;
        }

    }

}
