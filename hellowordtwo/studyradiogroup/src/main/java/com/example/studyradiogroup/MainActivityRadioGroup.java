package com.example.studyradiogroup;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * 单选组（RadioGroup）与复选组（CheckBox）示例。
 * 两组各自把选中的值显示在对应按钮的后面，多选用英文逗号连接。
 *
 * 注意：一个 .java 文件里只允许有一个 public 类，且类名必须与文件名一致，
 * 所以复选组的逻辑也写在本类里，不要再另起一个 public class MainActivity。
 */
public class MainActivityRadioGroup extends AppCompatActivity
        implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    /** 多选结果的连接符：英文逗号 */
    private static final String SEPARATOR = ",";

    private RadioGroup radgroup;
    private Button btnpost;
    private TextView tvRadioResult;

    private CheckBox cbOne, cbTwo, cbThree;
    private Button btnSend;
    private TextView tvCheckboxResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_radio_group);

        // Edge-to-edge：给根布局加系统栏内边距，避免内容被状态栏/导航栏遮挡
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        radgroup = findViewById(R.id.radioGroup);
        btnpost = findViewById(R.id.btnpost);
        tvRadioResult = findViewById(R.id.tv_radio_result);

        cbOne = findViewById(R.id.cb_one);
        cbTwo = findViewById(R.id.cb_two);
        cbThree = findViewById(R.id.cb_three);
        btnSend = findViewById(R.id.btn_send);
        tvCheckboxResult = findViewById(R.id.tv_checkbox_result);

        // 单选项组：组级监听，选中项一变就把值显示到「提交」按钮后面
        radgroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radbtn = findViewById(checkedId);
            tvRadioResult.setText(radbtn == null ? "" : radbtn.getText());
        });

        // 两个按钮的点击都由本类处理，靠 id 区分
        btnpost.setOnClickListener(this);
        btnSend.setOnClickListener(this);

        // 复选项组：每个 CheckBox 各自监听，勾选状态一变就刷新按钮后面的值
        cbOne.setOnCheckedChangeListener(this);
        cbTwo.setOnCheckedChangeListener(this);
        cbThree.setOnCheckedChangeListener(this);

        // 初始状态：单选默认选中「男」（XML 里 checked="true" 不会触发上面的监听，这里手动同步一次），
        // 多选默认全部未勾选，结果为空
        RadioButton defaultChecked = findViewById(radgroup.getCheckedRadioButtonId());
        tvRadioResult.setText(defaultChecked == null ? "" : defaultChecked.getText());
        refreshCheckBoxResult();
    }

    /** 复选项组：勾选状态变化时回调 */
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        refreshCheckBoxResult();
    }

    /** 两个提交按钮共用的点击处理（View.OnClickListener 的实现） */
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnpost) {
            submitRadioGroup();
        } else if (id == R.id.btn_send) {
            submitCheckBoxGroup();
        }
    }

    /** 遍历 RadioGroup 的子 View，找出被选中的那个 RadioButton */
    private void submitRadioGroup() {
        for (int i = 0; i < radgroup.getChildCount(); i++) {
            RadioButton rd = (RadioButton) radgroup.getChildAt(i);
            if (rd.isChecked()) {
                tvRadioResult.setText(rd.getText());
                Toast.makeText(this,
                        getString(R.string.toast_radio_submit, rd.getText()),
                        Toast.LENGTH_LONG).show();
                return;
            }
        }
        // RadioGroup 允许多个都不选，这里做个兜底提示
        tvRadioResult.setText("");
        Toast.makeText(this, R.string.toast_radio_none, Toast.LENGTH_SHORT).show();
    }

    /** 收集所有被勾选的 CheckBox，拼好后显示在按钮后面 */
    private void submitCheckBoxGroup() {
        refreshCheckBoxResult();
        String choose = tvCheckboxResult.getText().toString();
        if (choose.isEmpty()) {
            Toast.makeText(this, R.string.toast_hobby_none, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,
                    getString(R.string.toast_hobby_submit, choose),
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 刷新复选结果：把当前勾选的爱好用英文逗号连接后写入按钮后面的 TextView。
     * TextUtils.join() 在所有 API 级别都可用（String.join() 需要 API 26）。
     */
    private void refreshCheckBoxResult() {
        List<String> hobbies = new ArrayList<>();
        for (CheckBox cb : new CheckBox[]{cbOne, cbTwo, cbThree}) {
            if (cb.isChecked()) {
                hobbies.add(cb.getText().toString());
            }
        }
        tvCheckboxResult.setText(TextUtils.join(SEPARATOR, hobbies));
    }
}
