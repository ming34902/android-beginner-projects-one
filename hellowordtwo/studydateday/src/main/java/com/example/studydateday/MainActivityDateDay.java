package com.example.studydateday;

import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Locale;

/**
 * 滚筒式日期 / 时间选择示例。
 *
 * ## 为什么不用平台的 DatePicker / TimePicker？
 * - DatePicker：android:datePickerMode="spinner" 从 API 26 起已废弃并被忽略，
 *   实测在 API 37 上仍渲染日历网格，setSpinnersShown()/setCalendarViewShown() 同样失效。
 * - TimePicker：只有「时/分」，没有「秒」。
 * 所以两处都用 NumberPicker 自己拼，才能得到清一色的上下滚动栏。
 *
 * ## 联动逻辑
 * 2 月只有 28/29 天，所以「年」或「月」变化时必须重算「日」的上限，
 * 否则会选到 2 月 31 日这种不存在的日期。
 */
public class MainActivityDateDay extends AppCompatActivity {

    private static final int MIN_YEAR = 1970;
    private static final int MAX_YEAR = 2100;

    private NumberPicker npYear, npMonth, npDay;
    private NumberPicker npHour, npMinute, npSecond;

    /**
     * 是否正在由本类代码主动调整「日」的上限。
     * NumberPicker 在 setValue()/setMaxValue() 被程序调用时也会触发
     * OnValueChangeListener，没有这个标记会递归回到 clampDayToMonth()。
     */
    private boolean adjustingDay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_date_day);

        // Edge-to-edge：给根布局加系统栏内边距，避免内容被状态栏/导航栏遮挡
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        npYear = findViewById(R.id.np_year);
        npMonth = findViewById(R.id.np_month);
        npDay = findViewById(R.id.np_day);
        npHour = findViewById(R.id.np_hour);
        npMinute = findViewById(R.id.np_minute);
        npSecond = findViewById(R.id.np_second);

        Calendar calendar = Calendar.getInstance();
        setupDatePickers(calendar);
        setupTimePickers(calendar);
    }

    // ==================== 年月日 ====================

    private void setupDatePickers(Calendar calendar) {
        npYear.setMinValue(MIN_YEAR);
        npYear.setMaxValue(MAX_YEAR);
        npYear.setValue(calendar.get(Calendar.YEAR));

        // 月份直接用 1~12 给用户看，无需 +1 转换（Calendar 才是 0-based）
        npMonth.setMinValue(1);
        npMonth.setMaxValue(12);
        npMonth.setValue(calendar.get(Calendar.MONTH) + 1);

        // 顺序很重要：先设好 min/max，再把值设成今天，避免首帧越界
        npDay.setMinValue(1);
        clampDayToMonth();
        npDay.setValue(calendar.get(Calendar.DAY_OF_MONTH));

        NumberPicker.OnValueChangeListener dateListener = (picker, oldVal, newVal) -> {
            if (adjustingDay) {
                return;// 是我们自己触发的调整，忽略
            }
            if (picker == npYear || picker == npMonth) {
                // 年或月变了，日的范围可能要从 31 收缩到 28/29/30
                clampDayToMonth();
            }
            Toast.makeText(this,
                    getString(R.string.date_result,
                            npYear.getValue(), npMonth.getValue(), npDay.getValue()),
                    Toast.LENGTH_SHORT).show();
        };
        npYear.setOnValueChangedListener(dateListener);
        npMonth.setOnValueChangedListener(dateListener);
        npDay.setOnValueChangedListener(dateListener);
    }

    /**
     * 按当前「年 / 月」重算「日」的最大值（28/29/30/31）。
     *
     * getActualMaximum(DAY_OF_MONTH) 会自动处理闰年，不必自己写 4/100/400 的整除判断。
     */
    private void clampDayToMonth() {
        int year = npYear.getValue();
        int month = npMonth.getValue();// 1-based

        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        if (npDay.getMaxValue() == maxDay) {
            return;
        }

        adjustingDay = true;
        try {
            // 顺序很重要：先把当前值压到合法范围，再收紧上限，
            // 否则 NumberPicker 会处于 value > maxValue 的非法状态
            if (npDay.getValue() > maxDay) {
                npDay.setValue(maxDay);
            }
            npDay.setMaxValue(maxDay);
        } finally {
            adjustingDay = false;
        }
    }

    // ==================== 时分秒 ====================

    private void setupTimePickers(Calendar calendar) {
        npHour.setMinValue(0);
        npHour.setMaxValue(23);
        npHour.setValue(calendar.get(Calendar.HOUR_OF_DAY));

        npMinute.setMinValue(0);
        npMinute.setMaxValue(59);
        npMinute.setValue(calendar.get(Calendar.MINUTE));

        npSecond.setMinValue(0);
        npSecond.setMaxValue(59);
        npSecond.setValue(calendar.get(Calendar.SECOND));

        // 补零显示（08 而不是 8），三栏宽度更整齐
        npHour.setFormatter(TWO_DIGITS);
        npMinute.setFormatter(TWO_DIGITS);
        npSecond.setFormatter(TWO_DIGITS);

        NumberPicker.OnValueChangeListener timeListener = (picker, oldVal, newVal) ->
                Toast.makeText(this,
                        getString(R.string.time_result,
                                npHour.getValue(), npMinute.getValue(), npSecond.getValue()),
                        Toast.LENGTH_SHORT).show();
        npHour.setOnValueChangedListener(timeListener);
        npMinute.setOnValueChangedListener(timeListener);
        npSecond.setOnValueChangedListener(timeListener);
    }

    /** 数字补零到两位，如 8 -> "08"、59 -> "59" */
    private static final NumberPicker.Formatter TWO_DIGITS = value ->
            String.format(Locale.getDefault(), "%02d", value);
}
