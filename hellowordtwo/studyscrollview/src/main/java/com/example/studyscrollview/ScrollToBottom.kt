package com.example.studyscrollview

import android.view.View
import androidx.core.view.doOnLayout

/**
 * 把 [scroll] 滚动到底部。
 *
 * ## 要点一：必须等布局完成
 * onCreate 里 setText() 之后布局还没经过 measure/layout，
 * 此时 inner.measuredHeight 和 scroll.height 都是 0，算出的 offset 为 0，滚不动。
 * 常见写法是 post 一个 Runnable，但那条消息通常在第一次布局遍历之前就执行了，
 * 实测仍然失败。所以这里用 androidx.core 的 doOnLayout：
 * 已布局完成则立即执行，否则等下一次 layout 回调。
 *
 * ## 要点二：必须扣掉 ScrollView 自身的 padding
 * ScrollView 的 padding 会缩小可视区高度，而 ScrollView.scrollTo() 内部
 * 允许的最大滚动量是：
 *     child.height - (view.height - paddingTop - paddingBottom)
 * 如果只写成 `inner.measuredHeight - scroll.height`，就会比最大值小
 * (paddingTop + paddingBottom)，结果是差一个 padding 滚不到底、最后一行被截断。
 *
 * @param scroll ScrollView（或任何可滚动的 View）
 * @param inner  ScrollView 内部承载完整内容高度的直接子 View
 */
fun scrollToBottom(scroll: View?, inner: View?) {
    if (scroll == null || inner == null) {
        return
    }
    scroll.doOnLayout {
        // 可视区高度 = 控件高度 - 上下 padding
        val viewport = scroll.height - scroll.paddingTop - scroll.paddingBottom
        val offset = (inner.measuredHeight - viewport).coerceAtLeast(0)
        scroll.scrollTo(0, offset)
    }
}

/**

 */
