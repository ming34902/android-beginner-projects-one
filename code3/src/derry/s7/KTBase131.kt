#!/usr/bin/env kotlin




// jvmName 与 kotlin
@file:JvmName("MyUtils")  // 必须写在 package 包名前面
package derry.s7

fun getStudentNameInfo(str: String) = println(str)

fun main () {
    println("Hello from Kotlin")
}
/*
* public final class KtBase131xx {
*   public static final void getStudentNameInfo(@NotNull String str) {
*       System.out.println(str)
*   }
*
    public static final void main() { }

    public static  void main(String[] args) {
        main()
    }
* }
*
*
* */

