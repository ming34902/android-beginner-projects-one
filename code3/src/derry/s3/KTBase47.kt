#!/usr/bin/env kotlin

package derry.s3


// 字符串遍历
// ABCDEFGHIJKMNOPQRSTUVWXYZ
fun main () {
    val str1 = "ABCDEFGHIJKMNOPQRSTUVWXYZ"
    str1.forEach { c ->
        // it == str每个遍历出的字符 A B C D
        println("$c")
    }
}
