#!/usr/bin/env kotlin

package derry.s6


// 生产者是out T .协变 [ out T 此泛型能够获取 所以是out ]
interface Producer<out T> {
    // out T 代表整个生产者类里面这个T 只能被读取，不能被修改
    // fun consumer(item: T) {} // 不能被修改，无法编译

    fun producer() :T
}

// 消费 in T 逆变 【 int T 此泛型只能被修改  所以是in 】
interface Consumer<in T> {
    fun consumer(item: T) {}

    //    fun producer() :T  // 不能被读取，编译不通过
}

// 生产&消费 T
interface ProducerAndConsumer<T> {
    fun consumer(item: T) {}

    fun producer() :T
}

open class Animal
open class Humanity: Animal()
open class Man: Humanity()
open class Woman: Humanity()

class ProducerClass1 : Producer<Animal> {
    override fun producer(): Animal {
        println("生产者是 Animal ")
        return Animal()
    }
}

class ProducerClass2 : Producer<Humanity> {
    override fun producer(): Humanity {
        println("生产者是 Humanity ")
        return Humanity()
    }
}

class ProducerClass3 : Producer<Man> {
    override fun producer(): Man {
        println("生产者是 Man ")
        return Man()
    }
}

class ProducerClass4 : Producer<Woman> {
    override fun producer(): Woman {
        println("生产者是 Woman ")
        return Woman()
    }
}

// out-协变
// 1. Producer Consumer
// 2. ProducerClass
// 3. main 测试
fun main () {
    val p1 : Producer<Animal> = ProducerClass1()
    val p2 : Producer<Humanity> = ProducerClass2()
    val p3 : Producer<Man> = ProducerClass3()
    val p4 : Producer<Woman> = ProducerClass4()

    // 泛型默认情况下是: 泛型的子类对象 不可以赋值给 泛型的父类对象
    // 泛型默认情况下是:  泛型具体出的子类对象 不可以赋值给 泛型声明处的父类对象

    // out: 泛型的子类对象可以赋值给 泛型的父类对象
    // out: 泛型具体出的子类对象可以赋值给 泛型声明处的父类对象
}


