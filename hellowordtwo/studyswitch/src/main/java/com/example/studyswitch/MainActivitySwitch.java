package com.example.studyswitch;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * ToggleButton 与 Switch 示例。
 *
 * 两个控件都是 CompoundButton 的子类，所以可以共用一个 OnCheckedChangeListener，
 * 回调里用 getId() 区分是哪一个。
 */
public class MainActivitySwitch extends AppCompatActivity
        implements CompoundButton.OnCheckedChangeListener {

    private ToggleButton tbtn_open;
    private Switch swh_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_switch);

        // Edge-to-edge：给根布局加系统栏内边距，避免内容被状态栏/导航栏遮挡
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tbtn_open = findViewById(R.id.tbtn_open);
        swh_status = findViewById(R.id.swh_status);
        tbtn_open.setOnCheckedChangeListener(this);
        swh_status.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        // 注意：这里不能用 switch(R.id.xxx) —— AGP 9 默认把 R 资源 id 生成为非 final 常量，
        // switch 的 case 要求「常量表达式」，用 R.id 会编译报错。if/else 是正确写法。
        int id = compoundButton.getId();
        if (id == R.id.tbtn_open) {
            if (isChecked) {
                Toast.makeText(this, R.string.toggle_on, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.toggle_off, Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.swh_status) {
            if (isChecked) {
                Toast.makeText(this, R.string.switch_on, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.switch_off, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
