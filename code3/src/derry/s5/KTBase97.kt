#!/usr/bin/env kotlin

package derry.s5


enum class Exam {
    Fraction1,
    Fraction2,
    Fraction3,
    Fraction4
}

class Teacher (private val exam: Exam) {
    fun show() : String = when (exam) {
        Exam.Fraction1 -> "学生分数很差"
        Exam.Fraction2 -> "学生分数及格"
        Exam.Fraction3 -> "学生分数良好"
        Exam.Fraction4 -> "学生分数优秀"
        // else -> 由于show函数 使用的枚举类型做when判断， 代数据类型 写完枚举，就不需要写else
    }
}

// 代数据类型
// 1.定义枚举Exam类，四个级别分数情况
// 2.定义Teacher老师类，when使用枚举类
// 3. 得到优秀孩子的姓名
fun main () {
    Teacher(Exam.Fraction4).show()
}


