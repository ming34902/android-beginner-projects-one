package com.example.kotlinstudyone.constants

/**
 * 全 app 共享的常量集合。
 *
 * 为什么用 object 包一层，而不是直接写文件级顶层常量？
 * - 有命名空间，调用处一眼能看出常量归属：AppConstants.IntentKeys.EXTRA_TEXT
 * - 常量变多时可以按用途再拆成多个 object（如 IntentKeys、Prefs、RequestCodes），互不干扰
 *
 * 为什么用 const val 而不是 val？
 * - const 表示「编译期常量」，只能修饰 String 和基本类型
 * - 它在编译期就会被内联到调用处，运行时没有额外开销
 *
 * 为什么再嵌一层 object 做「分组」？
 * - 调用处能一眼看出这个常量属于哪一类用途，而不是只看到一个大杂烩列表
 * - 不同用途可以使用同一个名字（IntentKeys.ID 与 Prefs 中完全可以再有一个 ID）
 * - 输入 AppConstants. 时 IDE 先给出分组名，找常量靠「分类」而不是靠「背前缀」
 *
 * 命名注意：这里刻意叫 IntentKeys 而不是 Intent。
 * 因为 java/kotlin 里已经有一个 android.content.Intent，
 * 如果某处 `import ...AppConstants.Intent`，就会把系统的 Intent 类遮蔽掉，
 * 代码里再写 Intent(this, XxxActivity::class.java) 就会莫名其妙报错。
 */
object AppConstants {

    /** Intent / Bundle 传参 key：发送方和接收方共用这一份定义 */
    object IntentKeys {
        /** MainActivity -> SecondActivity 传递文本用的 key */
        const val EXTRA_TEXT = "extra_text"
        const val EXTRA_USER_ID = "extra_user_id"
    }

    /** SharedPreferences key */
    object Prefs {
        const val TOKEN = "pref_token"
        const val USER_ID = "pref_user_id"
    }
}
