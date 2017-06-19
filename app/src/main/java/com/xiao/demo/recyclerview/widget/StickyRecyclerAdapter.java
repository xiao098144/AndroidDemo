package com.xiao.demo.recyclerview.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiao.demo.recyclerview.ui.activity.BaseRecyclerAdapter;
import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.ui.activity.StickyRecyclerViewActivity;
import com.xiao.demo.recyclerview.util.AppUtil;
import com.xiao.demo.recyclerview.util.TimeUtil;

import java.util.List;

/**
 * FileName:com.xiao.demo.recyclerview.widget.StickyRecyclerAdapter.java
 * Created on 2017/2/22
 * Version V1.0
 */

public class StickyRecyclerAdapter extends BaseRecyclerAdapter<StickyRecyclerViewActivity.TestBean> {

    private static final String TAG = "StickyRecyclerAdapter";

    private static final int TYPE_CATEGORY = 1;

    private static final int TYPE_CONTENT = 2;

    LayoutInflater inflater;
    private int screen_width, screen_height;

    public StickyRecyclerAdapter(Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
//        screen_width = AppUtil.getScreenWidth(context);
        screen_height = AppUtil.getScreenHeight(context);
        Log.e(TAG, "StickyRecyclerAdapter: screen_height = " + screen_height + " screen_width = " + screen_width);
    }

    @Override
    public void updateItems(List<StickyRecyclerViewActivity.TestBean> items) {
        super.updateItems(items);
        Log.e(TAG, "updateItems: items = " + items);
    }

    @Override
    public void getView(int position, RecyclerView.ViewHolder viewHolder, int type, StickyRecyclerViewActivity.TestBean item) {
        super.getView(position, viewHolder, type, item);
    }

    @Override
    public int getItemViewType(int position) {
        StickyRecyclerViewActivity.TestBean item = getItem(position);
        if (item.getTitle() == null || item.getTitle().length() == 0) {
            return TYPE_CATEGORY;
        } else return TYPE_CONTENT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_CATEGORY) {
            View view = inflater.inflate(R.layout.layout_sticky_date, parent, false);
            return new DateViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.layout_sticky_content, parent, false);
            return new ContentViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        Log.e(TAG, "onBindViewHolder: position = " + position + " holder " + (holder instanceof DateViewHolder ? " holder is DateViewHolder " : (holder instanceof ContentViewHolder ? " holder is ContentViewHolder " : holder)) + " itemViewType = " + itemViewType);

        if (itemViewType == TYPE_CATEGORY) {
            if (holder instanceof DateViewHolder) {
                DateViewHolder holder1 = (DateViewHolder) holder;
                holder1.tv_date.setText(formatDate(mItems.get(position).getDate()));
            }

        } else {
            if (itemViewType == TYPE_CONTENT) {
                if (holder instanceof ContentViewHolder) {
                    StickyRecyclerViewActivity.TestBean testBean = mItems.get(position);
                    Log.e(TAG, "onBindViewHolder: TYPE_CONTENT testBean.getTag = " + testBean.getTag());
                    ContentViewHolder holder1 = (ContentViewHolder) holder;
                    holder1.tv_time.setText(formatTime(testBean.getTime()));
                    holder1.tv_title.setText(testBean.getTitle());
                    holder1.tv_des.setText(testBean.getDes());
                    holder1.tv_online.setText(String.format("%d人预约", testBean.getOnline()));
                    holder1.cut.setVisibility((testBean.getTag() == null) ? View.VISIBLE : View.GONE);
                    Glide.with(mContext).load(testBean.getUrl()).into(holder1.img);
//                    Glide.with(mContext).load(testBean.getUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                            if (resource != null) {
//                                int x = 107 * screen_width / 1080;
//                                int y = 80 * screen_height / 1920;
//                                Log.e(TAG, "onResourceReady: x = " + x + " y = " + y);
//                                ViewGroup.LayoutParams params = holder1.img.getLayoutParams();
//                                params.width = x;
//                                params.height = y;
//                                holder1.img.setLayoutParams(params);
//                                holder1.img.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                                holder1.img.setImageBitmap(resource);
//                            }
//                        }
//                    });
                }
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    private String formatTime(String time) {
        String s = TimeUtil.getInstance().changeDateFormat(time, "HH:mm:ss", "HH:mm");
        if (s.startsWith("0")) {
            s = s.substring(1);
        }
        return s;
    }

    /**
     * 格式化
     *
     * @param date yyyy-MM-dd
     * @return
     */
    private String formatDate(String date) {
        StringBuilder sb = new StringBuilder();
        String s = TimeUtil.getInstance().changeDateFormat(date, "yyyy-MM-dd", "MM月dd");
        if (s.startsWith("0")) {
            s = s.substring(1);
        }
        sb.append(s);
        sb.append(" ");
        boolean today = TimeUtil.getInstance().isToday(date);
        if (today) {
            sb.append("今天");
        } else {
            int dayOfWeek = TimeUtil.getInstance().getDayOfWeek(date);
            switch (dayOfWeek) {
                case 1:
                    sb.append("周日");
                    break;
                case 2:
                    sb.append("周一");
                    break;
                case 3:
                    sb.append("周二");
                    break;
                case 4:
                    sb.append("周三");
                    break;
                case 5:
                    sb.append("周四");
                    break;
                case 6:
                    sb.append("周五");
                    break;
                case 7:
                    sb.append("周六");
                    break;

            }
        }
        return sb.toString();
    }

    class DateViewHolder extends RecyclerView.ViewHolder {

        TextView tv_date;

        public DateViewHolder(View itemView) {
            super(itemView);
            tv_date = (TextView) itemView.findViewById(R.id.sticky_date_tv);
        }
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {

        TextView tv_time;
        ImageView img;
        ImageView img_type;
        TextView tv_length;
        TextView tv_title, tv_des, tv_online;
        ImageView img_order;
        FrameLayout cut;


        public ContentViewHolder(View itemView) {
            super(itemView);
            tv_time = (TextView) itemView.findViewById(R.id.sticky_content_tv_time);
            img = (ImageView) itemView.findViewById(R.id.sticky_content_img);
            img_type = (ImageView) itemView.findViewById(R.id.sticky_content_img_type);
            tv_length = (TextView) itemView.findViewById(R.id.sticky_content_tv_length);
            tv_title = (TextView) itemView.findViewById(R.id.sticky_content_tv_title);
            tv_des = (TextView) itemView.findViewById(R.id.sticky_content_tv_des);
            tv_online = (TextView) itemView.findViewById(R.id.sticky_content_tv_online);
            img_order = (ImageView) itemView.findViewById(R.id.sticky_content_img_order);
            cut = (FrameLayout) itemView.findViewById(R.id.sticky_content_cut);
        }
    }


}
