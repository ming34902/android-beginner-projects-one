#!/usr/bin/env kotlin

package derry.s6

val myStr: String = "AAA"
/*
* public final class KtBase117kT {
    @NotOrNull
    private static final String myStr = "AAA"

    @NotOrNull
    private static final String getMyStr() {
        return myStr
    }
 }
* */
val String.myInfo: String
    get() = "Derry"
/*
* public final class KtBase117kT {
*     @NotOrNull
*       public static final String getMyInfo(@NotOrNull String $this$myInfo) {
*           Intrinsics.checkParameterIsNotNull($this$myInfo, "$this$myInfo")
*       }
* }
* */

// 打印输出 并且 链式调用 只有String有资格这样
fun String.showPrintln() :String {
    println(" 打印输出 并且 链式调用 只有String有资格这样:$this")
    return this
}

val String.stringAllInfoValueVal
    get() = "当前${System.currentTimeMillis()}这个时间点被调用了一次，当前值是$this"
// 扩展属性
fun main () {
    val str1 : String = "ABC"
    println(str1.myInfo)

    str1.showPrintln().showPrintln()

    str1.myInfo.showPrintln().showPrintln().stringAllInfoValueVal
}


