#!/usr/bin/env kotlin

package derry.s5

// 普通类  一般 set get 构造函数
class ResponseResultBean1(var msg: String, var code: Int, var data: String) {

}

// 数据类    解构操作， copy, toString ,hashCode, equals
data class ResponseResultBean2(var msg: String, var code: Int, var data: String) {

}

// 数据类
//在 Kotlin 中，使用 == 进行比较时，背后调用的其实是 .equals() 方法。
// 1. 普通类 与 数据类的toString 背后原理
// 2. == 与 ===
// 3. 普通类的 == 背后原理
// 普通类默认比较的是“内存地址”（是不是同一个东西），而数据类（data class）默认比较的是“内容值”（里面的数据是不是一样）
// 4. 数据类的 == 背后原理
//
fun main () {
   println(ResponseResultBean1("loginSuccess", 200, "登录成功"))
   // 普通类 Any() toString windows 实现打印了 com.derry.s5.ResponseResultBean1@266474c2

   println(ResponseResultBean2("loginSuccess", 200, "登录成功"))
   // 数据类 Any() 默认重写了 父类的 toString 打印子类toString详情， ResponseResultBean2(msg=loginSuccess,code=200,data=登录成功)


   // 推理 两个普通类的值是一样，应该是true,但实际背后并不是这样的 返回false
   // 第一次 new 了一个对象，放在内存地址 A；
   // 第二次又 new 了一个对象，放在内存地址 B；
   //equals 比较的是 地址 A 是否等于 地址 B，结果自然是 false。
   println(
      ResponseResultBean1("loginSuccess", 200, "登录成功") ==
              ResponseResultBean1("loginSuccess", 200, "登录成功")
   )
   // Any父类的 equals 实现 ResponseResultBean1对象引用 比较 ResponseResultBean1对象引用

   println(
      ResponseResultBean2("loginSuccess", 200, "登录成功") ==
              ResponseResultBean2("loginSuccess", 200, "登录成功")
   )
   // Any父类的 equals 被 数据类 重写了 equals 会调用子类的 equals函数（对值的比较）
   // 编译器调用了上面这个自动生成的 equals()；
   // 它拿第一个对象的 msg 与第二个对象的 msg 比，code 与 code 比，data 与 data 比；
   // 因为所有字段的值都完全相同，所以最终返回 true。
}


