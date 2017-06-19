package com.xiao.demo.recyclerview.ui.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.util.DataBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sks on 2016/7/14.
 */
public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 1;

    private static final int TYPE_GRID = 2;

    private static final int TYPE_NORMAL = 3;


    List<DataBean> list = new ArrayList<>();

    Context context;

    LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;

    private OnItemClick onItemClick;

    private OnItemLongClick onItemLongClick;

    private View mHeaderView;

    public ListAdapter() {
    }

    public ListAdapter(List<DataBean> list) {
        this.list = list;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            GridLayoutManager manager = (GridLayoutManager) layoutManager;
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){

                @Override
                public int getSpanSize(int position) {
                    int itemViewType = getItemViewType(position);
                    switch (itemViewType){
                        case TYPE_HEADER:
                            return  3;
                        case TYPE_NORMAL:
                            return  3;
                        case TYPE_GRID:
                            return  1;
                        default:
                            return  3;
                    }
                }
            });
        }
    }

    public ListAdapter(List<DataBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView != null && position == 0) {
            return TYPE_HEADER;
        } else {
            return list.get(mHeaderView!=null?position-1:position).getRes() != 0 ? TYPE_GRID : TYPE_NORMAL;
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeaderViewHolder(mHeaderView);
        } else if (viewType == TYPE_GRID) {
            View view = inflater.inflate(R.layout.layout_grid_item, parent, false);
            return new GridViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.layout_list_item, parent, false);
            return new ViewHolder(view);
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;
        int pos = getRealPosition(viewHolder);
        if (viewHolder instanceof ViewHolder) {
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.tv.setText(list.get(pos).getName());
            if (onItemClickListener != null) {
                if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1 && holder.itemView.hasOnClickListeners()) || (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)) {
                    holder.itemView.setOnClickListener((v) -> {
                        EventBus.getDefault().post(new DataBean());
                        onItemClickListener.onItemClick(v, pos, 0);
                    });
                    holder.itemView.setOnLongClickListener((v) -> {
                        onItemClickListener.onItemLongClick(v, pos, 0);
                        return true;
                    });
//                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View v) {
//                        int pos = holder.getPosition();
//                        onItemClickListener.onItemLongClick(v, pos,0);
//                        return true;
//                    }
//                });
                }
            }
            if (onItemClick != null) {
                holder.itemView.setOnClickListener((v) -> {
                    onItemClick.onItemClick(v, pos, 0);
                });
            }
            if (onItemLongClick != null) {
                holder.itemView.setOnLongClickListener((v) -> {
                    onItemLongClick.onItemLongClick(v, pos, 0);
                    return true;
                });
            }
        } else if (viewHolder instanceof GridViewHolder) {
            GridViewHolder holder = (GridViewHolder) viewHolder;
            holder.tv_name.setText(list.get(position).getName());
            holder.img.setImageResource(list.get(position).getRes());
            if (onItemClickListener != null) {
                if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1 && holder.itemView.hasOnClickListeners()) || (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)) {
                    holder.itemView.setOnClickListener((v) -> {
                        onItemClickListener.onItemClick(v, pos, 0);
                    });
                    holder.itemView.setOnLongClickListener((v) -> {
                        onItemClickListener.onItemLongClick(v, pos, 0);
                        return true;
                    });
//                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View v) {
//                        int pos = holder.getPosition();
//                        onItemClickListener.onItemLongClick(v, pos,0);
//                        return true;
//                    }
//                });
                }
            }
            if (onItemClick != null) {
                holder.itemView.setOnClickListener((v) -> {
                    onItemClick.onItemClick(v,pos, 0);
                });
            }
            if (onItemLongClick != null) {
                holder.itemView.setOnLongClickListener((v) -> {
                    onItemLongClick.onItemLongClick(v, pos, 0);
                    return true;
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return (mHeaderView == null ? 0 : 1) + (list == null ? 0 : list.size());
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        ImageView img;

        public GridViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setOnItemLongClick(OnItemLongClick onItemLongClick) {
        this.onItemLongClick = onItemLongClick;
    }

    public interface OnItemClick {
        void onItemClick(View view, int position, long id);
    }

    public interface OnItemLongClick {
        void onItemLongClick(View view, int position, long id);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, long id);

        void onItemLongClick(View view, int position, long id);
    }
}
