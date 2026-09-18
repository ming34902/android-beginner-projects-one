#!/usr/bin/env kotlin

package derry.s5


sealed class Exams {
    object Fraction1: Exams()
    object Fraction2: Exams()
    object Fraction3: Exams()
    class Fraction4(val studentName: String): Exams()
}

class Teacher2 (private val exam: Exams) {
    fun show()  =
        when (exam) {
            is Exams.Fraction1 -> "学生分数很差"
            is Exams.Fraction2 -> "学生分数及格"
            is Exams.Fraction3 -> "学生分数良好"
            is Exams.Fraction4 -> "学生分数优秀,该优秀学生姓名${(this.exam as Exams.Fraction4).studentName}"
        // else -> 由于show函数 使用的枚举类型做when判断， 代数据类型 写完枚举，就不需要写else
    }
}

// 代数据类型
// 1.定义枚举Exam类，四个级别分数情况
// 2.定义Teacher老师类，when使用枚举类
// 3. 得到优秀孩子的姓名
fun main () {
    Teacher2(Exams.Fraction1).show()


    Teacher2(Exams.Fraction4("赵四")).show()

//    Exams.Fraction1 === Exams.Fraction1   // true , === 必须对象引用，object是单例 只会实例化一次

    Exams.Fraction4("赵四") === Exams.Fraction4("赵四") // class 有两个不同的对象，所以是false
}


