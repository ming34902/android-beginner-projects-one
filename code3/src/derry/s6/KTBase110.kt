#!/usr/bin/env kotlin

package derry.s6

class ConsumerClass1 : Consumer<Animal> {
    override fun consumer(item: Animal) {
        println("消费者是 Animal ")
    }
}

class ConsumerClass2 : Consumer<Humanity> {
    override fun consumer(item: Humanity) {
        println("消费者是 Humanity ")
    }
}

class ConsumerClass3 : Consumer<Man> {
    override fun consumer(item: Man) {
        println("消费者是 Man ")
    }
}

class ConsumerClass4 : Consumer<Woman> {
    override fun consumer(item: Woman) {
        println("消费者是 Woman ")
    }
}
// in-逆变
fun main () {
    // ConsumerClass1 本来就是传递  Humanity，也可以，因为in
    val p1 : Consumer<Man> = ConsumerClass1()
    val p2 : Consumer<Woman> = ConsumerClass2()

    // 默认情况下： 泛型具体出的父类 是不可以赋值给  泛型声明处的子类
    // in 泛型具体出的服务类 是可以赋值给 泛型声明处的子类的

    // 逆变： 子类 泛型声明处 可以接收 父类 泛型具体处
    // 协变： 父类 = 子类
    // 逆变： 子类
}


