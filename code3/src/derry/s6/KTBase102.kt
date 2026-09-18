#!/usr/bin/env kotlin

package derry.s6

abstract class BaseActivity {
    fun onCreate() {
        setContentView(getLayoutID())

        initView()
        initXXXX()
    }

    private fun setContentView(layoutID: Int) = println("加载{$layoutID}布局xml中")

    abstract fun getLayoutID(): Int
    abstract fun initView()
    abstract fun initXXXX()
}

class MainActivity: BaseActivity() {
    override fun getLayoutID(): Int = 4554

    override fun initView() = println("初始化view")

    override fun initXXXX() {
        TODO("Not yet implemented")
    }

    fun show() {
        super.onCreate()
    }
}
// 接口抽象类
fun main () {

}


