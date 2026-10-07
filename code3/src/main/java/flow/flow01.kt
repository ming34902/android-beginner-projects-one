package flow

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

// 循环调用10次网络请求，获取结果平方，然后过滤掉奇数，最后取前2个
fun main(): Unit = runBlocking {
    useFlow()
    // 冷流 基于消费者消费 才生产的

    // 热流，自己生产自己的，不管消费者
    val sharedFlow = MutableSharedFlow<Int>(
        replay = 3, // 重播
        extraBufferCapacity = 1, // 缓冲区
        onBufferOverflow = BufferOverflow.DROP_LATEST // DROP_OLDEST
    )
    sharedFlow.onEach {
        println("接收数据$it")
    }.launchIn(this)
    delay(1000)
    repeat(10) {
        println("发送次数${it + 1}")
        sharedFlow.emit(it)
    }

    // 共享数据流
//    val stateFlow = MutableSharedFlow(1)
//    stateFlow.emit(2)
//    launch {
//        stateFlow.collect {
//            println(it)
//        }
//    }
//    stateFlow.emit(3)

    // ViewModel
    val viewModel2 = ViewModel()
    launch {
        viewModel2.age.collect {
            println("ViewModel-it:$it")
        }
    }
    launch {
//        viewModel2.updateAge()
        while (isActive) { viewModel2.updateAge() }
    }
}

class ViewModel {
    private val _age = MutableStateFlow(0)
    val age = _age.asStateFlow()
    suspend fun updateAge() {
        _age.emit(request1())
    }
}

suspend fun useFlow() {
    // 流的上游 生产者
    val flow1 = flow {
        repeat(10) {
            emit(request1())
        }
    }
    flow1.map { it * it }
        .filter { (it % 2) == 0 }
        .take(2)
        .onEach { println(it) }
        .collect()
    // 流的下游 消费者 collect()
    //    flow1.collect {
    //        println(it)
    //    }
}

suspend fun notUseFlow() {
    var count = 0
    repeat(10) {
        val res = request1().let { it * it }
        if (count == 2) {
            return
        }
        if (res % 2 == 0) {
            if (count < 2) {
                ++count
                println("res$res")
            }
        }
    }

    // 第二种链式
//    val resList = (0 until 10).map{ request1() }
//    resList.map { it * it}
//        .filter { it % 2 == 0}
//        .take(2)
//        .onEach{ println(it)}
}

suspend fun request1(): Int {
    delay(Random.nextLong(1000))
    return Random.nextInt(100)
}

//public fun LongArray.asFlow(): Flow<Long> = flow {
//    forEach { value ->
//        emit(value)
//    }
//}
//
//public fun IntRange.asFlow(): Flow<Long> = flow {
//    forEach { value ->
//        emit(value)
//    }
//}
