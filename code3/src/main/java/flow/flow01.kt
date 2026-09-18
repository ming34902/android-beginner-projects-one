package flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

public fun LongArray.asFlow(): Flow<Long> = flow {
    forEach { value ->
        emit(value)
    }
}


fun main(): Unit = runBlocking {

}
