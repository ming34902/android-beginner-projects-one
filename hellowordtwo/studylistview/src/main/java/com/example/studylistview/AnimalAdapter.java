package com.example.studylistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * ListView 的适配器。
 *
 * 关键点：getView() 是 ListView 的性能核心。
 * 屏幕只能显示有限几项，ListView 会回收滑出屏幕的 itemView 并把它作为
 * convertView 重新传回来（这就是「Recycler」的由来）。
 * 于是：
 *   - convertView == null  → 才需要 inflate 新的 View  + findViewById
 *   - convertView != null  → 直接复用，从 tag 里取出缓存的子 View 引用
 * 这个「复用 convertView + ViewHolder」的组合是 ListView 的标准写法，
 * 不做的话每次滚动都要重新 inflate + 查找所有子 View，长列表会明显掉帧。
 */
public class AnimalAdapter extends BaseAdapter {

    private final List<Animal> mData;

    /** LayoutInflater 只需在构造时取一次，不必每次 getView 都调用 from() */
    private final LayoutInflater mInflater;

    public AnimalAdapter(List<Animal> mData, Context context) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
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
            // 只有第一次（或回收池里没有可用 View）才真正创建
            convertView = mInflater.inflate(R.layout.item_list_animal, parent, false);
            holder = new ViewHolder();
            holder.imgIcon = convertView.findViewById(R.id.img_icon);
            holder.txtName = convertView.findViewById(R.id.txt_aName);
            holder.txtSpeak = convertView.findViewById(R.id.txt_aSpeak);
            // 把子 View 引用挂在 convertView 上，下次复用时直接取
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Animal animal = mData.get(position);
        // 用 setImageResource 而不是 setBackgroundResource：
        // 后者设置的是「背景层」，会拉伸铺满并丢弃 scaleType，
        // 前者设置的才是 ImageView 真正要显示的内容。
        holder.imgIcon.setImageResource(animal.getaIcon());
        holder.txtName.setText(animal.getaName());
        holder.txtSpeak.setText(animal.getaSpeak());
        return convertView;
    }

    /** 缓存一行的子 View 引用，避免每次 getView 都走 findViewById */
    private static class ViewHolder {
        ImageView imgIcon;
        TextView txtName;
        TextView txtSpeak;
    }
}
