package com.example.studyscrollview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * ScrollView 滚动示例。
 *
 * 演示两种滚到底部的方式：
 * 1. onClick 里点按钮时用 ScrollView.fullScroll(FOCUS_DOWN) —— 此时布局已测量完成，直接可用。
 * 2. onCreate 里 setText 之后想立刻滚到底 —— 布局还没测量，fullScroll 会失效，
 *    这时要用 ScrollToBottom.kt 里 post 到下一帧的写法。
 */
public class MainActivityScrollView extends AppCompatActivity implements View.OnClickListener {

    /** 生成的文字行数 */
    private static final int LINE_COUNT = 100;

    private Button btnDown;
    private Button btnUp;
    private ScrollView scrollView;
    private TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_scroll_view);

        // Edge-to-edge：给根布局加系统栏内边距，避免内容被状态栏/导航栏遮挡
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bindViews();
    }

    private void bindViews() {
        btnDown = findViewById(R.id.btn_down);
        btnUp = findViewById(R.id.btn_up);
        scrollView = findViewById(R.id.scrollView);
        txtShow = findViewById(R.id.txt_show);

        btnDown.setOnClickListener(this);
        btnUp.setOnClickListener(this);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= LINE_COUNT; i++) {
            sb.append(getString(R.string.line_item, i)).append('\n');
        }
        txtShow.setText(sb.toString());

        // 打开页面就自动滚到最后一行。
        // 注意：这里不能直接写 scrollView.fullScroll(FOCUS_DOWN)，
        // 因为 setText 之后布局尚未测量，fullScroll 会失效。
        // ScrollToBottomKt 是 ScrollToBottom.kt 里顶层函数生成的 JVM 类，
        // 同包下无需 import 即可调用（Kotlin/Java 互操作）。
        ScrollToBottomKt.scrollToBottom(scrollView, txtShow);
    }

    @Override
    public void onClick(View v) {
        // 注意：AGP 9 里 R.id 不再是编译期常量，switch 的 case 要求常量表达式，
        // 所以这里必须用 if/else，不能写成 switch (v.getId()) { case R.id.xxx: }
        int id = v.getId();
        if (id == R.id.btn_down) {
            // 滚动到底部
            scrollView.fullScroll(ScrollView.FOCUS_DOWN);
        } else if (id == R.id.btn_up) {
            // 滚动到顶部
            scrollView.fullScroll(ScrollView.FOCUS_UP);
        }
    }
}
