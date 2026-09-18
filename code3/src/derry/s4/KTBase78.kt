#!/usr/bin/env kotlin

package derry.s4

class KTBase78 {

    // lateinit val XX1; // XX1 后面再无法修改
    lateinit var responseResultInfo: String // 等会再来初始化此处，先定义后赋值


    fun loadRequset() {
        // 延时初始化
        responseResultInfo = "服务器加载成功"
    }

    fun showResponseResult() {
        if (responseResultInfo == null) {
            // 由于你没有给他初始化，所以只有用到它，它就会崩溃
            if (::responseResultInfo.isInitialized) {
                println("responseResultInfo:$responseResultInfo")
            } else {
                println("你都没有初始化加载，你是不是忘记加载了")
            }
        }
    }
}

// lateinit学习
// 1.lateinit responseResultInfo 定义
// 2.request 懒加载
// 3. showResponseResult
// 4. main 先请求再显示
fun main () {
    val p = KTBase78()

    // 在使用它之前，加载一下，用到它才加载，就属于懒加载
    p.loadRequset()

    p.showResponseResult()
}


