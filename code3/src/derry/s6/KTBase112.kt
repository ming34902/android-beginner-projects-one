#!/usr/bin/env kotlin

package derry.s6


data class ObjectClass1(val  name: String, val age: Int)
data class ObjectClass2(val  name: String, val age: Int)
data class ObjectClass3(val  name: String, val age: Int)

class KTBase112 {

    inline fun <reified T> randomOrDefault(defaultLambdaAction: () -> T ): T? {
       val objList: List<Any> = listOf(ObjectClass1("张三", 33), ObjectClass2("张四", 34), ObjectClass3("王五", 78))

        val randomObj: Any? = objList.shuffled().first()

//       return randomObj.takeIf { it is T } as T ?: null

        // 如果it随机产生的对象 等于T类型的，就会走 as T 直接返回
        // 如果 it随机产生的对象 不等于T类型 ，就会走下面备用环节
        return randomObj.takeIf { randomObj is T } as T? ?: defaultLambdaAction()
    }
}

// reified 关键字
// 1. 定义3个obj类
// 2. randomOrDefault 函数 备用机制lambda
// 3. lists.shuffled()
fun main () {
    val finalResult = KTBase112().randomOrDefault<ObjectClass1> {
        println("随机产生对象，和指定ObjectClass1 不一致，所以启动备用机制")
        ObjectClass1("Obj 李四", 22)
    }
    println("finalResult$finalResult")
}


