#!/usr/bin/env kotlin

package derry.s3


//  replace 完成加密解码操作
// ABCDEFGHIJKMNOPQRSTUVWXYZ
fun main () {

    val sourcePwd = "ABCDEFGHIJKMNOPQRSTUVWXYZ"


    // 加密操作 就是把字符替换打乱
    val newPwd = sourcePwd.replace(Regex("[AKMNO]")) {
        it.value
        when (it.value) {
            // 这里的每一个字符 A B C D 进行遍历
            "A" -> "9"
            "K" -> "3"
            "M" -> "5"
            "N" -> "1"
            "O" -> "4"
            else -> it.value // 如果 不是AKMNO等字符，则啥也不做，直接返回字符
        }
    }
    println("加密后pwd:$newPwd")


    val sourcePwdNew = newPwd.replace(Regex("[93514]")) {
        when (it.value) {
            // 这里的每一个字符 A B C D 进行遍历
            "9" -> "A"
            "3" -> "K"
            "5" -> "M"
            "1" -> "N"
            "4" -> "O"
            else -> it.value // 如果 不是93514等字符，则啥也不做，直接返回字符
        }
    }
    println("解密后pwd:$sourcePwdNew")
}

