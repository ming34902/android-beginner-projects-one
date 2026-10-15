package com.example.studylistview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
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
 * ListView 示例。
 *
 * ## 为什么用 ListView 而不是 ScrollView？
 * ScrollView 会把所有子 View 一次性全部创建出来并常驻内存；
 * ListView 只创建屏幕可见的那几项，滑出屏幕的会被回收复用。
 * 所以「条数不确定的列表」要用 ListView / RecyclerView。
 *
 * ## 关于表头
 * addHeaderView() 会在内部把表头当成「第 0 项」插进去，
 * 因此点击回调里的 position 会比数据下标大 1（见下方 onItemClick）。
 * 而且 addHeaderView() 必须在 setAdapter() 之前调用，否则会抛
 * IllegalStateException: Cannot add header view to list -- setAdapter has already been called.
 */
public class MainActivityListView extends AppCompatActivity {

    private final List<Animal> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_list_view);

        // Edge-to-edge：给根布局加系统栏内边距，避免内容被状态栏/导航栏遮挡
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listAnimal = findViewById(R.id.list_animal);

        // 图标放在 res/drawable/ 而不是 res/mipmap/：
        // mipmap 只用于「应用图标」（各密度需各自提供，还要适配自适应图标），
        // 普通界面用的图标应放 drawable，用 vector 还能一套适配所有密度。
        mData.add(new Animal("狗说", "你是狗么?", R.drawable.ic_icon_dog));
        mData.add(new Animal("牛说", "你是牛么?", R.drawable.ic_icon_cow));
        mData.add(new Animal("鸭说", "你是鸭么?", R.drawable.ic_icon_duck));
        mData.add(new Animal("鱼说", "你是鱼么?", R.drawable.ic_icon_fish));
        mData.add(new Animal("马说", "你是马么?", R.drawable.ic_icon_horse));

        // 表头必须在 setAdapter() 之前添加
        View header = LayoutInflater.from(this).inflate(R.layout.view_header, listAnimal, false);
        listAnimal.addHeaderView(header);

        AnimalAdapter adapter = new AnimalAdapter(mData, this);
        listAnimal.setAdapter(adapter);

        listAnimal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 因为上面加了表头，position 会整体后移 1 位：
                // position 0 = 表头，position 1 = mData.get(0)，以此类推。
                // 直接拿 mData.get(position) 会越界 / 错位。
                // 用 parent.getAdapter().getItem(position) 可以自动跳过表头。
                Object item = parent.getAdapter().getItem(position);
                if (item instanceof Animal) {
                    Animal animal = (Animal) item;
                    Toast.makeText(MainActivityListView.this,
                            animal.getaName() + " —— " + animal.getaSpeak(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivityListView.this,
                            "点到了表头", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
