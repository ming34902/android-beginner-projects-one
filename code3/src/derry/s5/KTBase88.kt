#!/usr/bin/env kotlin

package derry.s5

interface  RunnableKT {
   fun run()
}

open class KtBase88 {
   open  fun add(info: String) = println("KtBase88 add:$info")
   open  fun del(info: String) = println("KtBase88 del:$info")
}

//  对象表达式
// 1. add del println
// 2. 匿名对象表达式
// 3. 具名实现方式
// 4. 对java的接口，用对象表达式
fun main () {
   // 匿名函数表达式
   val p1 : KtBase88 = object : KtBase88() {
      override fun add(info: String) {
         println("匿名函数对象 add:$info")
      }

      override fun del(info: String) {
         println("匿名函数对象 del:$info")
      }
   }

   p1.add("王五")
   p1.del("王五")

   val p2 = KtBase88Impl()
   p2.add("王五")
   p2.del("王五")

   // java接口 用 java最简洁方式
   val p3 = object :Runnable {
      override  fun run() {
         println("RunnableKT run3")
      }
   }
   p3.run()

   // java接口 用 java最简洁方式
   val p4 = Runnable {
      println("Runnable run4")
   }
   p4.run()

   // object
   object : RunnableKT {
      override  fun run() {
         println("RunnableKT run2")
      }
   }.run()

}


// java接口 有两种方式， kotlin接口只有一种方式（ object:对象表达式）

// 具体实现   具体名字 == KtBase88Impl
class KtBase88Impl : KtBase88() {
    override fun add(info: String) {
         // super.add(info)
        println("具名实现类 add:$info")
    }

    override fun del(info: String) {
       // super.del(info)
        println("具名实现类 del:$info")
    }
}
