#!/usr/bin/env kotlin

package derry.s4

class KTBase79 {
    // 普通方式，饿汉式加载
//    val dataBaseData1 : String = readSQlServerDatabaseAction()

    // by lazy 懒加载
    val dataBaseData2 by lazy { readSQlServerDatabaseAction() }

    private fun readSQlServerDatabaseAction(): String {
        println("加载读取数据库数据中.....")
//        TODO("Not yet implemented")
        return "database data load success ok"
    }

}

// 惰性加载
// 1.不使用惰性初始化   dataBaseData1 = readSQlSercerDatabaseAction()
// 2.使用惰性初始化  dataBaseData1 by lazy
// 3。 ktBase82() 睡眠
fun main () {

    val p = KTBase79()

    Thread.sleep(5000)

    println("---strat")
    println("最终返回：${p.dataBaseData2}")
}


