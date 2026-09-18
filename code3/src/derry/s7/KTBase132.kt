#!/usr/bin/env kotlin

package derry.s7


// 注解  @JvmField 与kotlin
class Person1 {
    val names = listOf("张三1","张三2","张三3")
}
/*
public final class Person {
    private static final List<String> names = new ArraryList();
    // val 只读 只有getNames
    public static final List<String> getNames() {
        return this.names;
    }
}

@JvmField 背后会剔除私有代码成员
public final class Person {
    @JvmField
    @NotNUll
    public final List Names = CollectionsKt.listOf(new String[]{ "张三1","张三2","张三3" })
}
*/

fun main () {

}


