package com.example.studycalendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义日历网格示例。
 *
 * ## 为什么不用平台的 CalendarView？
 * CalendarView 只暴露 focusedMonthDateColor / dateTextAppearance 这类「全局」样式，
 * 没有任何按单元格着色的钩子，所以以下需求它做不到：
 * - 周六周日红色
 * - 节假日标记
 * - 单元格自定义背景
 * 因此这里用 RecyclerView（GridLayoutManager 7 列）自己拼日历。
 *
 * ## 实现要点
 * 1. 固定 6 行 × 7 列 = 42 格，换月时高度不跳动；
 * 2. 本月 1 号是周几 → 决定前面补几个「上月」格子；
 * 3. 大号月份数字作为水印画在网格下层（FrameLayout 叠层，单元格背景透明）；
 * 4. 文字颜色优先级：非本月(灰) > 选中(白) > 周末(红) > 普通(近黑)。
 */
public class MainActivityCalendar extends AppCompatActivity {

    /** 一周 7 列 */
    private static final int COLUMN_COUNT = 7;

    /** 固定 6 行 × 7 列，避免换月时高度跳动 */
    private static final int CELL_COUNT = 42;

    /**
     * 节假日表：key = "月-日"（公历，1-based）。
     *
     * 这里只列「公历日期固定」的节日。春节 / 清明 / 端午 / 中秋走农历，
     * 每年公历日期都不同，必须靠农历转换库或逐年录入数据，不能写死。
     * 要补充就按同样格式 put 一行，例如：
     *     HOLIDAYS.put("2-17", "春节");
     */
    private static final Map<String, String> HOLIDAYS = new HashMap<>();

    static {
        HOLIDAYS.put("1-1", "元旦");
        HOLIDAYS.put("5-1", "劳动节");
        HOLIDAYS.put("10-1", "国庆节");
    }

    private TextView tvMonthTitle;
    private TextView tvWatermark;
    private TextView tvCalendarResult;
    private DayAdapter adapter;

    /** 当前正在展示的年月（month 为 1-based） */
    private int viewYear;
    private int viewMonth;

    /** 当前选中的日期（month 为 1-based） */
    private int selectedYear;
    private int selectedMonth;
    private int selectedDay;

    /** 今天的日期。启动时取一次固定住，避免跨天时判断漂移 */
    private int todayYear;
    private int todayMonth;
    private int todayDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_calendar);

        // Edge-to-edge：给根布局加系统栏内边距，避免内容被状态栏/导航栏遮挡
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvMonthTitle = findViewById(R.id.tv_month_title);
        tvWatermark = findViewById(R.id.tv_watermark);
        tvCalendarResult = findViewById(R.id.tv_calendar_result);

        RecyclerView rvCalendar = findViewById(R.id.rv_calendar);
        rvCalendar.setLayoutManager(new GridLayoutManager(this, COLUMN_COUNT));
        adapter = new DayAdapter();
        rvCalendar.setAdapter(adapter);

        Calendar now = Calendar.getInstance();
        todayYear = now.get(Calendar.YEAR);
        todayMonth = now.get(Calendar.MONTH) + 1;// Calendar.MONTH 是 0-based
        todayDay = now.get(Calendar.DAY_OF_MONTH);

        // 初始：展示今天所在月，并选中今天
        selectedYear = todayYear;
        selectedMonth = todayMonth;
        selectedDay = todayDay;
        showMonth(todayYear, todayMonth);

        findViewById(R.id.btn_prev_month).setOnClickListener(v -> shiftMonth(-1));
        findViewById(R.id.btn_next_month).setOnClickListener(v -> shiftMonth(1));
    }

    // ==================== 月份切换 ====================

    /** 切换到指定年月并刷新整个界面 */
    private void showMonth(int year, int month) {
        viewYear = year;
        viewMonth = month;

        tvMonthTitle.setText(getString(R.string.month_title, year, month));
        // 水印只放月份数字
        tvWatermark.setText(String.valueOf(month));

        adapter.submit(buildDays());
        updateResultText();
    }

    /** 翻月。delta = -1 上月 / +1 下月，用 Calendar.add 自动处理跨年（12月 → 次年1月）*/
    private void shiftMonth(int delta) {
        Calendar cal = Calendar.getInstance();
        cal.set(viewYear, viewMonth - 1, 1);
        cal.add(Calendar.MONTH, delta);
        showMonth(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);
    }

    // ==================== 生成 42 格数据 ====================

    private List<CalendarDay> buildDays() {
        List<CalendarDay> list = new ArrayList<>(CELL_COUNT);

        Calendar cal = Calendar.getInstance();
        cal.set(viewYear, viewMonth - 1, 1);

        // 本月 1 号是星期几 → 决定第一行前面要补几个上月格子。
        // Calendar.DAY_OF_WEEK：1=周日 … 7=周六。表头第一列是「日」，所以减 SUNDAY(1) 得到列下标
        int leadingBlanks = cal.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        Calendar prev = Calendar.getInstance();
        prev.set(viewYear, viewMonth - 1, 1);
        prev.add(Calendar.MONTH, -1);

        Calendar next = Calendar.getInstance();
        next.set(viewYear, viewMonth - 1, 1);
        next.add(Calendar.MONTH, 1);

        for (int i = 0; i < CELL_COUNT; i++) {
            int dayNumber = i - leadingBlanks + 1;// 1 = 本月 1 号
            if (dayNumber < 1) {
                // 上月补位
                int d = prev.getActualMaximum(Calendar.DAY_OF_MONTH) + dayNumber;
                list.add(createCell(prev.get(Calendar.YEAR), prev.get(Calendar.MONTH) + 1, d, false));
            } else if (dayNumber > daysInMonth) {
                // 下月补位
                list.add(createCell(next.get(Calendar.YEAR), next.get(Calendar.MONTH) + 1,
                        dayNumber - daysInMonth, false));
            } else {
                list.add(createCell(viewYear, viewMonth, dayNumber, true));
            }
        }
        return list;
    }

    private CalendarDay createCell(int year, int month, int day, boolean inCurrentMonth) {
        CalendarDay cell = new CalendarDay();
        cell.year = year;
        cell.month = month;
        cell.day = day;
        cell.inCurrentMonth = inCurrentMonth;

        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, day);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        cell.weekend = (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);

        cell.today = (year == todayYear && month == todayMonth && day == todayDay);
        cell.selected = (year == selectedYear && month == selectedMonth && day == selectedDay);
        cell.holiday = HOLIDAYS.get(month + "-" + day);
        return cell;
    }

    // ==================== 点击 & 结果 ====================

    private void onDayClicked(CalendarDay d) {
        selectedYear = d.year;
        selectedMonth = d.month;
        selectedDay = d.day;

        if (d.inCurrentMonth) {
            adapter.submit(buildDays());// 重算 selected 标记
            updateResultText();
        } else {
            // 点到上下月的补位格 → 直接翻到那个月，操作更连贯
            showMonth(d.year, d.month);
        }
        Toast.makeText(this, formatResult(d), Toast.LENGTH_SHORT).show();
    }

    private void updateResultText() {
        CalendarDay d = new CalendarDay();
        d.year = selectedYear;
        d.month = selectedMonth;
        d.day = selectedDay;
        d.holiday = HOLIDAYS.get(selectedMonth + "-" + selectedDay);
        tvCalendarResult.setText(formatResult(d));
    }

    private String formatResult(CalendarDay d) {
        if (d.holiday != null) {
            return getString(R.string.calendar_result_holiday, d.year, d.month, d.day, d.holiday);
        }
        return getString(R.string.calendar_result, d.year, d.month, d.day);
    }

    // ==================== Adapter ====================

    private class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder> {

        private final List<CalendarDay> days = new ArrayList<>();

        void submit(List<CalendarDay> newDays) {
            days.clear();
            days.addAll(newDays);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_calendar_day, parent, false);
            return new DayViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
            holder.bind(days.get(position));
        }

        @Override
        public int getItemCount() {
            return days.size();
        }

        class DayViewHolder extends RecyclerView.ViewHolder {

            private final TextView tvDay;
            private final TextView tvHoliday;

            DayViewHolder(@NonNull View itemView) {
                super(itemView);
                tvDay = itemView.findViewById(R.id.tv_day);
                tvHoliday = itemView.findViewById(R.id.tv_holiday);
                itemView.setOnClickListener(v -> {
                    int pos = getBindingAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        onDayClicked(days.get(pos));
                    }
                });
            }

            void bind(CalendarDay d) {
                tvDay.setText(String.valueOf(d.day));

                // 1) 文字颜色。优先级：非本月 > 选中 > 周末 > 普通
                int colorRes;
                if (!d.inCurrentMonth) {
                    // 上下月补位格统一浅灰：已经灰掉了，就不再强调周末红
                    colorRes = R.color.calendar_other_month_text;
                } else if (d.selected) {
                    colorRes = R.color.calendar_selected_text;
                } else if (d.weekend) {
                    colorRes = R.color.calendar_weekend_text;
                } else {
                    colorRes = R.color.calendar_normal_text;
                }
                tvDay.setTextColor(ContextCompat.getColor(itemView.getContext(), colorRes));

                // 2) 圆形背景：选中 = 实心圆；今天 = 空心圆；其余无背景
                if (d.selected) {
                    tvDay.setBackgroundResource(R.drawable.bg_day_selected);
                } else if (d.today) {
                    tvDay.setBackgroundResource(R.drawable.bg_day_today);
                } else {
                    tvDay.setBackground(null);
                }

                // 3) 节假日名称
                if (d.holiday != null) {
                    tvHoliday.setText(d.holiday);
                    tvHoliday.setVisibility(View.VISIBLE);
                } else {
                    tvHoliday.setVisibility(View.GONE);
                }
            }
        }
    }
}
