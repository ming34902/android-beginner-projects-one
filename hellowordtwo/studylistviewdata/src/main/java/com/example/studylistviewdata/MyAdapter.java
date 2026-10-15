package com.example.studylistviewdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * ListView 的适配器，支持增 / 删 / 清空等数据操作。
 *
 * 关键点：数据变更后必须调用 notifyDataSetChanged()，
 * 否则 ListView 不知道数据变了，界面不会刷新。
 * （RecyclerView 里对应的是 notifyItemInserted / notifyItemRemoved 等更精确的通知）
 */
public class MyAdapter extends BaseAdapter {

    private final List<DataOne> mData;

    /** LayoutInflater 只需在构造时取一次，不必每次 getView 都调用 from() */
    private final LayoutInflater mInflater;

    public MyAdapter(List<DataOne> mData, Context context) {
        // 不要直接持有外部传入的引用就完事：如果调用方换掉了自己的 List，
        // 这里就会指向旧对象。用字段初始化时明确赋值，语义更清晰。
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public DataOne getItem(int position) {
        // 返回真正的数据对象，而不是 null。
        // 外面会用 getItem(position) 取数据，返回 null 会导致调用方拿不到东西。
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            // 只有第一次（或回收池为空）才真正创建 View 并查找子控件
            convertView = mInflater.inflate(R.layout.item_list, parent, false);
            holder = new ViewHolder();
            holder.imgIcon = convertView.findViewById(R.id.img_icon);
            holder.txtContent = convertView.findViewById(R.id.txt_content);
            convertView.setTag(holder);
        } else {
            // 复用滑出屏幕的旧 View，直接取缓存的子控件引用
            holder = (ViewHolder) convertView.getTag();
        }

        DataOne data = mData.get(position);
        holder.imgIcon.setImageResource(data.getImgId());
        holder.txtContent.setText(data.getContent());
        return convertView;
    }

    /** 缓存一行的子 View 引用，避免每次 getView 都走 findViewById */
    private static class ViewHolder {
        ImageView imgIcon;
        TextView txtContent;
    }

    // ==================== 数据操作 ====================

    /** 追加到末尾 */
    public void add(DataOne data) {
        mData.add(data);
        notifyDataSetChanged();
    }

    /** 插入到指定位置 */
    public void add(int position, DataOne data) {
        mData.add(position, data);
        notifyDataSetChanged();
    }

    /** 按对象删除（删除第一个 equals 匹配的项） */
    public void remove(DataOne data) {
        mData.remove(data);
        notifyDataSetChanged();
    }

    /** 按位置删除 */
    public void remove(int position) {
        mData.remove(position);
        notifyDataSetChanged();
    }

    /** 清空全部 */
    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    /*
     * 说明：原代码里还有一个 updateListItem(int, Data) 方法，
     * 它直接访问了 Activity 的 list_one 字段和未定义的 Data 类型，无法编译，已移除。
     *
     * 那个方法想做的事是：改动某一项后只刷新「屏幕上可见的那一行」，而不重建整个列表 —— 
     * 这属于优化手段。但适配器不应该知道 ListView 的存在（职责越界），
     * 正确做法是：
     *   - 简单可靠：改完数据调 notifyDataSetChanged()（本类现在的做法）；
     *   - 想要精细控制：改用 RecyclerView + notifyItemChanged(position)，
     *     它天然支持只刷新某一项，不需要适配器反向持有列表控件。
     */
}
