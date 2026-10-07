package flow

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {

    runBlocking {
//        testChannel()
        // asFlowExample()
        // Flow 默认是cold 生产者和消费者的通信是 同步非阻塞的，也就是 生产和消费 会顺序交替进行
        runBlocking {
//            productor2().collect {
//                delay(100)
//                println("custom $it")
//            }
        }
    }
}

private fun testChannel() {
    runBlocking {
        val channel = Channel<Int>()
        // 这里是 消耗大量 cpu 运算的异步逻辑，用5次整数的平方 并发送
        launch {
            for (x in 1..10) {
                channel.send(x * x)
                println("do send")
            }
        }
        repeat(5) {
            val receive = channel.receive()
            println(receive)
        }
        println("done")
    }
}

// Channel 与 flow 的交互
private fun asFlowExample() {
    val subject = Channel<Int>()
    val channelFlow = subject.receiveAsFlow() // 转换成flow
    runBlocking {
        launch {
            // 消费
            channelFlow.collect {
                println("subject:$it")
            }
        }
        repeat(2) {
            // 发送
            subject.send(it)
        }
        // 注意只有 Channel 关闭了 runBlocking  协程才能结束
        subject.close()
    }
}

//suspend fun productor2() = channelFlow<Int> {
//    for (i in 1..10) {
//        delay(100)
//        send(i)
//        println(" produce $i")
//    }
//}
//
//suspend fun productor3() = flow<Int> {
//    for (i in 1..10) {
//        delay(100)
//        emit(i)
//        println(" produce $i")
//    }
//}
