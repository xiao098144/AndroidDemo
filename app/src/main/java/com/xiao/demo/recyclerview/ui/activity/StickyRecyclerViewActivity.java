package com.xiao.demo.recyclerview.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.util.SingleChoiceAdapter;
import com.xiao.demo.recyclerview.widget.StickyRecyclerAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName:com.xiao.demo.recyclerview.ui.activity.StickyRecyclerViewActivity.java
 * Created on 2017/2/21
 * Version V1.0
 */

public class StickyRecyclerViewActivity extends Activity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private static final String TAG = "Sticky";

    @BindView(R.id.sticky_recycler_rg)
    protected RadioGroup rg_head;

    @BindView(R.id.sticky_recycler_rb0)
    protected RadioButton rb0;
    @BindView(R.id.sticky_recycler_rb1)
    protected RadioButton rb1;
    @BindView(R.id.sticky_recycler_rb2)
    protected RadioButton rb2;
    @BindView(R.id.sticky_recycler_rb3)
    protected RadioButton rb3;
    @BindView(R.id.sticky_recycler_rb4)
    protected RadioButton rb4;
    @BindView(R.id.sticky_recycler_rb5)
    protected RadioButton rb5;
    @BindView(R.id.sticky_recycler_rb6)
    protected RadioButton rb6;

    RadioButton[] rbs = new RadioButton[7];

    FrameLayout layouts[] = new FrameLayout[7];

    @BindView(R.id.sticky_layout0)
    FrameLayout layout0;
    @BindView(R.id.sticky_layout1)
    FrameLayout layout1;
    @BindView(R.id.sticky_layout2)
    FrameLayout layout2;
    @BindView(R.id.sticky_layout3)
    FrameLayout layout3;
    @BindView(R.id.sticky_layout4)
    FrameLayout layout4;
    @BindView(R.id.sticky_layout5)
    FrameLayout layout5;
    @BindView(R.id.sticky_layout6)
    FrameLayout layout6;

    @BindView(R.id.grid)
    protected GridView gridView;

    @BindView(R.id.sticky_recycler_rv)
    protected RecyclerView recyclerView;

    List<TestBean> list = new ArrayList<>();
    StickyRecyclerAdapter adapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, StickyRecyclerViewActivity.class);
        context.startActivity(starter);
    }

    int idx = 1;

    private List<TestBean> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stickyrecyclerview);
        ButterKnife.bind(this);
        rbs[0] = rb0;
        rbs[1] = rb1;
        rbs[2] = rb2;
        rbs[3] = rb3;
        rbs[4] = rb4;
        rbs[5] = rb5;
        rbs[6] = rb6;
        layouts[0] = layout0;
        layouts[1] = layout1;
        layouts[2] = layout2;
        layouts[3] = layout3;
        layouts[4] = layout4;
        layouts[5] = layout5;
        layouts[6] = layout6;
        Log.e(TAG, "onCreate: rg_head.getId() = " + rg_head.getId());
        for (RadioButton rb : rbs) {
            Log.e(TAG, "onCreate: rb.getId = " + rb.getId());
        }
        for (FrameLayout layout : layouts) {
            Log.e(TAG, "onCreate: layout.getId = " + layout.getId());
        }

        formatData(generateListData());

        initRecycler();
        rg_head.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.e(TAG, "onCheckedChanged: checkId = " + checkedId + " group  " + group.getCheckedRadioButtonId());
            }
        });
        rb0.setOnCheckedChangeListener(this);
        rb1.setOnCheckedChangeListener(this);
        rb2.setOnCheckedChangeListener(this);
        rb3.setOnCheckedChangeListener(this);
        rb4.setOnCheckedChangeListener(this);
        rb5.setOnCheckedChangeListener(this);
        rb6.setOnCheckedChangeListener(this);
        layout0.setOnClickListener(this);
        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        layout4.setOnClickListener(this);
        layout5.setOnClickListener(this);
        layout6.setOnClickListener(this);

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
            dataList.add(t);
        }
        Adapter1 adapter1 = new Adapter1(this);
        adapter1.setSelected(0);
        gridView.setNumColumns(dataList.size());
        gridView.setAdapter(adapter1);
        adapter1.setData(dataList);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SparseBooleanArray isSelected = adapter1.getIsSelected();
                if (isSelected != null && !isSelected.get(position))
                    adapter1.setSelected(position);
            }
        });
    }

    private void initRecycler() {
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new StickyRecyclerAdapter(this);
            recyclerView.setAdapter(adapter);
            adapter.updateItems(list);
        }
    }

    private void formatData(List<TestBean> data) {
        Log.e(TAG, "formatData: data = " + data);
        Collections.sort(data);
        Log.e(TAG, "formatData: after Sort data = " + data);
        for (int i = 0; i < data.size(); i++) {
            TestBean testBean = data.get(i);
            if (i == 0 || (i >= 1 && !testBean.getDate().equals(data.get(i - 1).getDate()))) {
                TestBean t = new TestBean();
                t.setDate(testBean.getDate());
                t.setTime("");
                t.setTitle("");
                list.add(t);
                testBean.setTag("1");
            }
            Log.e(TAG, "formatData:  i = " + i + " testBean " + testBean);
            list.add(testBean);
        }
        Collections.sort(list);
        Log.e(TAG, "formatData: " + list);
    }

    public String formatnum(double num, String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(num);
    }

    private List<TestBean> generateListData() {
        Random random = new Random();
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            TestBean test = new TestBean();
            test.setDate("2017-2-" + formatnum((1 + random.nextInt(26)), "#00"));
            test.setTime(formatnum((random.nextInt(22) + 1), "#00") + ":" + formatnum((1 + random.nextInt(58)), "#00") + ":" + formatnum((1 + random.nextInt(58)), "#00"));
            test.setTitle("节目标题" + i);
            test.setDes(" 描述描述描述描述描述描述描述描述描述描述des for " + i);
            test.setOnline(random.nextInt(20000));
            test.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487767411741&di=7a2414c1dc68bf1bbf1f558e55fe26ed&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F12%2F13%2F96%2F45bOOOPICe8_1024.jpg");
            list.add(test);
        }
        return list;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        ((RadioButton) (rg_head.getChildAt(idx))).setChecked(false);
        Log.e(TAG, "onCheckedChanged: rg_head.getCheckedRadioButtonId() = " + rg_head.getCheckedRadioButtonId() + "  buttonView.getId() = " + buttonView.getId());
//        if (rg_head.getCheckedRadioButtonId() == buttonView.getId()) return;
        switch (buttonView.getId()) {
            case R.id.sticky_recycler_rb0:
            case R.id.sticky_layout0:
                rb0.setChecked(false);
//                ((RadioButton) (rg_head.getChildAt(0))).setChecked(true);
                break;
            case R.id.sticky_recycler_rb1:
            case R.id.sticky_layout1:
                rb1.setChecked(false);
//                ((RadioButton) (rg_head.getChildAt(1))).setChecked(true);
                break;
            case R.id.sticky_recycler_rb2:
            case R.id.sticky_layout2:
                rb2.setChecked(false);
//                ((RadioButton) (rg_head.getChildAt(2))).setChecked(true);
                break;
            case R.id.sticky_recycler_rb3:
            case R.id.sticky_layout3:
                idx = 3;
                rb3.setChecked(false);
//                ((RadioButton) (rg_head.getChildAt(3))).setChecked(true);
                break;
            case R.id.sticky_recycler_rb4:
            case R.id.sticky_layout4:
                idx = 4;
                rb4.setChecked(false);
//                ((RadioButton) (rg_head.getChildAt(4))).setChecked(true);
                break;
            case R.id.sticky_recycler_rb5:
            case R.id.sticky_layout5:
                idx = 5;
                rb5.setChecked(false);
//                ((RadioButton) (rg_head.getChildAt(5))).setChecked(true);
                break;
            case R.id.sticky_recycler_rb6:
            case R.id.sticky_layout6:
                idx = 6;
                rb6.setChecked(false);
//                ((RadioButton) (rg_head.getChildAt(6))).setChecked(true);
                break;
        }
        switch (buttonView.getId()) {
            case R.id.sticky_recycler_rb0:
            case R.id.sticky_layout0:
                idx = 0;
                rb0.setChecked(true);
//                ((RadioButton) (rg_head.getChildAt(0))).setChecked(true);
                break;
            case R.id.sticky_layout1:
            case R.id.sticky_recycler_rb1:
                idx = 1;
                rb1.setChecked(true);
//                ((RadioButton) (rg_head.getChildAt(1))).setChecked(true);
                break;
            case R.id.sticky_layout2:
            case R.id.sticky_recycler_rb2:
                idx = 2;
                rb2.setChecked(true);
//                ((RadioButton) (rg_head.getChildAt(2))).setChecked(true);
                break;
            case R.id.sticky_layout3:
            case R.id.sticky_recycler_rb3:
                idx = 3;
                rb3.setChecked(true);
//                ((RadioButton) (rg_head.getChildAt(3))).setChecked(true);
                break;
            case R.id.sticky_layout4:
            case R.id.sticky_recycler_rb4:
                idx = 4;
                rb4.setChecked(true);
//                ((RadioButton) (rg_head.getChildAt(4))).setChecked(true);
                break;
            case R.id.sticky_layout5:
            case R.id.sticky_recycler_rb5:
                idx = 5;
                rb5.setChecked(true);
//                ((RadioButton) (rg_head.getChildAt(5))).setChecked(true);
                break;
            case R.id.sticky_layout6:
            case R.id.sticky_recycler_rb6:
                idx = 6;
                rb6.setChecked(true);
//                ((RadioButton) (rg_head.getChildAt(6))).setChecked(true);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sticky_layout0:
                if (idx == 0) return;
                rbs[idx].setChecked(false);
                idx = 0;
                rbs[idx].setChecked(true);
                break;
            case R.id.sticky_layout1:
                if (idx == 1) return;
                rbs[idx].setChecked(false);
                idx = 1;
                rbs[idx].setChecked(true);
                break;
            case R.id.sticky_layout2:
                if (idx == 2) return;
                rbs[idx].setChecked(false);
                idx = 2;
                rbs[idx].setChecked(true);
                break;
            case R.id.sticky_layout3:
                if (idx == 3) return;
                rbs[idx].setChecked(false);
                idx = 3;
                rbs[idx].setChecked(true);
                break;
            case R.id.sticky_layout4:
                if (idx == 4) return;
                rbs[idx].setChecked(false);
                idx = 4;
                rbs[idx].setChecked(true);
                break;
            case R.id.sticky_layout5:
                if (idx == 5) return;
                rbs[idx].setChecked(false);
                idx = 5;
                rbs[idx].setChecked(true);
                break;
            case R.id.sticky_layout6:
                if (idx == 6) return;
                rbs[idx].setChecked(false);
                idx = 6;
                rbs[idx].setChecked(true);
                break;
        }
    }


    public class TestBean implements Comparable<TestBean> {
        String date;
        String time;
        String title;
        String des;
        int online;
        String url;
        String tag;


        public TestBean() {
        }

        @Override
        public String toString() {
            return "TestBean{" +
                    "date='" + date + '\'' +
                    ", time='" + time + '\'' +
                    ", title='" + title + '\'' +
                    ", online=" + online +
                    ", tag='" + tag + '\'' +
                    '}';
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getOnline() {
            return online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getTime() {
            return time;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public TestBean(String time, String date, String title) {

            this.time = time;
            this.date = date;
            this.title = title;
        }

        public TestBean(String time, String date, String title, String tag) {
            this.time = time;
            this.date = date;
            this.title = title;
            this.tag = tag;
        }

        @Override
        public int compareTo(TestBean o) {
            int i = this.date.compareTo(o.date);
            if (i == 0) {
                int j = this.time.compareTo(o.time);
                if (j == 0) {
                    return this.title.compareTo(o.title);
                }
                return j;
            }
            return i;
        }
    }


    class Adapter1 extends SingleChoiceAdapter<TestBean> {

        public Adapter1(Context context) {
            super(context);
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
            holder.tv1.setText(dataList.get(position).getDes());
            holder.tv2.setText(dataList.get(position).getTitle());
            Log.e(TAG, "getView: dataList.get(position) = " + dataList.get(position) + "  holder.tv1.gettext " + holder.tv1.getText() + "  holder.tv1.getVisibility() = " + holder.tv1.getVisibility() + " holder.tv2.gettext = " + holder.tv2.getText() + " holder.tv2.getVisibility() = " + holder.tv2.getVisibility());
            try {
                if (isSelected.get(position)) {
                    holder.tv2.setBackgroundResource(R.drawable.green_oval_bg);
                } else holder.tv2.setBackgroundResource(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return convertView;
        }

        class ViewHolder {
            TextView tv1, tv2;
        }
    }

}
