package com.example.studydatetime;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Chronometer（计时器）示例。
 *
 * Chronometer 内部靠 SystemClock.elapsedRealtime() 计算「基准时间到现在」的差值，
 * 所以它计的是真实流逝时间，不受系统时间被修改的影响。
 *
 * 本类同时实现两个接口，因此既能接收按钮点击，也能接收计时器的每秒回调。
 */
public class MainActivityDateTime extends AppCompatActivity
        implements View.OnClickListener, Chronometer.OnChronometerTickListener {

    /** 计时达到该毫秒数时提示一次 */
    private static final long TEN_SECONDS_MS = 10_000L;

    private Chronometer chronometer;
    private Button btn_start, btn_stop, btn_base, btn_format;

    /**
     * 本次计时是否已经弹过「已计时 N 秒」的提示。
     *
     * 必须是「成员变量」而不是方法内的局部变量：
     * onChronometerTick 每秒被调用一次，如果 flag 声明在方法内部，
     * 每次进入方法都会重新初始化为 false，弹过之后又会立刻满足条件，
     * 结果就是 10 秒之后每秒弹一次。
     *
     * 用基本类型 boolean 而不是 Boolean，避免不必要的装箱。
     */
    private boolean hasShownElapsedToast = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_date_time);

        // Edge-to-edge：给根布局加系统栏内边距，避免内容被状态栏/导航栏遮挡
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
    }

    private void initView() {
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        btn_start = (Button) findViewById(R.id.btnStart);
        btn_stop = (Button) findViewById(R.id.btnStop);
        btn_base = (Button) findViewById(R.id.btnReset);
        btn_format = (Button) findViewById(R.id.btn_format);

        chronometer.setOnChronometerTickListener(this);
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_base.setOnClickListener(this);
        btn_format.setOnClickListener(this);

        // 显式把基准设为「现在」，保证初始显示 00:00，
        // 而不是从布局创建那一刻就已经开始计时。
        reset();
    }

    @Override
    public void onClick(View v) {
        // 注意：AGP 9 里 R.id 不再是编译期常量，switch 的 case 要求常量表达式，
        // 所以这里必须用 if/else，不能写成 switch (v.getId()) { case R.id.xxx: }
        int id = v.getId();
        if (id == R.id.btnStart) {
            chronometer.start();// 开始计时
        } else if (id == R.id.btnStop) {
            chronometer.stop();// 停止计时
        } else if (id == R.id.btnReset) {
            reset();// 复位
        } else if (id == R.id.btn_format) {
            chronometer.setFormat(getString(R.string.chronometer_format));// 更改时间显示格式
        }
    }

    /** 复位：停止计时、把基准时间重新设为「现在」，并清掉「已提示」标记 */
    private void reset() {
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
        // 复位提示标记，这样重新计时满 10 秒时还能再触发一次
        hasShownElapsedToast = false;
    }

    /**
     * 计时器每秒回调一次（start() 时会立即回调一次）。
     *
     * 用「基准时间到现在的时间差」判断是否满 10 秒，而不是比较屏幕文本：
     * 文本会随 setFormat() 改变（如 "Time：00:10"），文本比较会失效。
     *
     * 提示只弹第一次：靠成员变量 hasShownElapsedToast 记录状态。
     * 点「重置」后该标记会被清掉，下一次计时满 10 秒可再次提示。
     */
    @Override
    public void onChronometerTick(Chronometer chronometer) {
        long elapsed = SystemClock.elapsedRealtime() - chronometer.getBase();
        if (elapsed >= TEN_SECONDS_MS && !hasShownElapsedToast) {
            hasShownElapsedToast = true;
            Toast.makeText(this,
                    getString(R.string.toast_elapsed_seconds, TEN_SECONDS_MS / 1000),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
