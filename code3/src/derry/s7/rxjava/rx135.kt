package derry.s7.rxjava


fun main() {
    create {
        "Derry"
        true
        "dssdd"
    }.map{
        // 此时 this 是 create最后一行的类型
    }.observer{
        println(this)
    }
    // create 输入源， 输出内容 是写的 create最后一行
    // map 输入 是create输出的 valueItem，输出的
    // observer 输入的 map存储的valueItem ，消费完成
}

// 中转站 保存记录
class RxJavaCoreObject<T>(var valueItem: T) {
    // 主构造函数，接收传递进来的信息，此消息最后一行就是 create 的返回
    // valueItem == create 操作符，最后一行返回值
}

inline fun <I>  RxJavaCoreObject<I>.observer( observerAction : I.() -> Unit) = observerAction(valueItem)

// I输入泛型， O输出泛型
//inline fun<I,O> RxJavaCoreObject<I>.map(mapAction: I.() -> O): RxJavaCoreObject<O>{
//    return RxJavaCoreObject(mapAction(this.valueItem))
//}

inline fun<I,O> RxJavaCoreObject<I>.map(mapAction: I.() -> O) = RxJavaCoreObject(mapAction(this.valueItem))


inline fun <OUTPUT> create(action: () -> OUTPUT) = RxJavaCoreObject<OUTPUT>(action())