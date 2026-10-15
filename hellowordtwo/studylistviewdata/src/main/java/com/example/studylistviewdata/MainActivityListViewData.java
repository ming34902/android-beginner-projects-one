package com.example.studylistviewdata;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * ListView 数据动态增删示例。
 *
 * 演示 5 种数据操作，全部通过 {@link MyAdapter} 暴露的方法完成：
 * 追加、指定位置插入、按对象删除、按位置删除、清空。
 * 每个方法内部都会调 notifyDataSetChanged()，所以界面会自动刷新。
 */
public class MainActivityListViewData extends AppCompatActivity implements View.OnClickListener {

    /** 初始数据条数 */
    private static final int INITIAL_COUNT = 6;

    /** 「插入到第4位」用的下标（0-based，第 4 位 = 下标 3） */
    private static final int INSERT_POSITION = 3;

    /** 「删除第3项」用的下标（0-based，第 3 项 = 下标 2） */
    private static final int REMOVE_POSITION = 2;

    /** 三种图标轮流用，这样增删时一眼就能看出是哪一项变了 */
    private static final int[] ICONS = {
            R.drawable.ic_icon_qitao,
            R.drawable.ic_icon_star,
            R.drawable.ic_icon_heart,
    };

    private final List<DataOne> mData = new ArrayList<>();

    private MyAdapter mAdapter;

    /** 新增/插入时用来生成递增的序号 */
    private int flag = 1;

    /** 当前被点选的行下标，-1 表示未选中 */
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_list_view_data);

        // Edge-to-edge：给根布局加系统栏内边距，避免内容被状态栏/导航栏遮挡
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 先装好初始数据，再创建 Adapter
        for (int i = 0; i < INITIAL_COUNT; i++) {
            mData.add(newData());
        }

        ListView listOne = findViewById(R.id.list_one);
        mAdapter = new MyAdapter(mData, this);
        listOne.setAdapter(mAdapter);

        // 点击某一行 → 记录为「选中」，供「删除选中」使用
        listOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                Toast.makeText(MainActivityListViewData.this,
                        "已选中第 " + (position + 1) + " 项",
                        Toast.LENGTH_SHORT).show();
            }
        });

        int[] buttonIds = {
                R.id.btn_add,
                R.id.btn_add_at,
                R.id.btn_remove_selected,
                R.id.btn_remove_at,
                R.id.btn_clear,
        };
        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this);
        }
    }

    /** 生成一条新数据，序号自增 */
    private DataOne newData() {
        DataOne data = new DataOne(
                ICONS[(flag - 1) % ICONS.length],
                getString(R.string.item_text, flag));
        flag++;
        return data;
    }

    @Override
    public void onClick(View v) {
        // 注意：AGP 9 里 R.id 不再是编译期常量，switch 的 case 要求常量表达式，
        // 所以这里必须用 if/else，不能写成 switch (v.getId()) { case R.id.xxx: }
        int id = v.getId();
        if (id == R.id.btn_add) {
            addOne();
        } else if (id == R.id.btn_add_at) {
            addAtPosition();
        } else if (id == R.id.btn_remove_selected) {
            removeSelected();
        } else if (id == R.id.btn_remove_at) {
            removeAtPosition();
        } else if (id == R.id.btn_clear) {
            clearAll();
        }
    }

    // ==================== 五个操作 ====================

    private void addOne() {
        mAdapter.add(newData());
    }

    private void addAtPosition() {
        // 插入位置要夹在合法范围内，否则 add() 会抛 IndexOutOfBoundsException
        int position = Math.min(INSERT_POSITION, mData.size());
        mAdapter.add(position, newData());
        Toast.makeText(this, "已插入到第 " + (position + 1) + " 位", Toast.LENGTH_SHORT).show();
    }

    private void removeSelected() {
        if (selectedPosition < 0 || selectedPosition >= mData.size()) {
            Toast.makeText(this, R.string.toast_no_selection, Toast.LENGTH_SHORT).show();
            return;
        }
        String removed = mAdapter.getItem(selectedPosition).getContent();
        mAdapter.remove(selectedPosition);
        selectedPosition = -1;// 删掉之后原下标已失效，重置避免下次误删
        Toast.makeText(this, getString(R.string.toast_removed, removed), Toast.LENGTH_SHORT).show();
    }

    private void removeAtPosition() {
        if (mData.size() <= REMOVE_POSITION) {
            Toast.makeText(this, "列表不足 " + (REMOVE_POSITION + 1) + " 项，无法删除",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        mAdapter.remove(REMOVE_POSITION);
        selectedPosition = -1;
    }

    private void clearAll() {
        mAdapter.clear();
        selectedPosition = -1;
        Toast.makeText(this, R.string.toast_cleared, Toast.LENGTH_SHORT).show();
    }
}
