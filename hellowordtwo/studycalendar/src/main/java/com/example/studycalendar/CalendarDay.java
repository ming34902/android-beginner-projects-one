package com.example.studycalendar;

/**
 * 日历里的一格数据。
 *
 * 为什么需要这个模型？
 * 平台 CalendarView 只暴露「全局」文字样式，没法区分周六周日、也没法标记节假日。
 * 自己建一个模型，就能把「这一格该怎么画」的判据全部集中在这里，
 * 由 Adapter 统一读取。
 */
public class CalendarDay {

    /** 公历年份，如 2026 */
    public int year;

    /** 月份，1~12（注意：这里是 1-based，与 Calendar.MONTH 的 0-based 不同） */
    public int month;

    /** 日，1~31 */
    public int day;

    /** 是否属于当前正在展示的月份（false = 上下月的补位格） */
    public boolean inCurrentMonth;

    /** 是否周六或周日 */
    public boolean weekend;

    /** 是否今天 */
    public boolean today;

    /** 是否被选中 */
    public boolean selected;

    /** 节假日名称，没有则为 null */
    public String holiday;

    /** 用于调试/日志的 "yyyy-M-d" 形式 */
    public String key() {
        return year + "-" + month + "-" + day;
    }
}
