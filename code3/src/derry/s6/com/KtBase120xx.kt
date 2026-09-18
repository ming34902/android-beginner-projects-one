package derry.s6.com


// 扩展文件 一般都是 public,如果是 private外界无法使用
// Iterable<E> 子类 set list 都可以用，所有用父类
// 本次扩展函数的作用是，随机取第一个元素返回
fun <E> Iterable<E>.randomItemValue() = this.shuffled().first()

fun <T> Iterable<T>.randomItemValuePrintln() = println(this.shuffled().first())
