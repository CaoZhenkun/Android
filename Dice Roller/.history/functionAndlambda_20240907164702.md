# 一、将函数直接存储在变量中
如需将函数作为值引用，您需要使用函数引用运算符 (::)。语法如下图所示：
![alt text](image-6.png)

    fun main() {
        val trickFunction = ::trick
    }

    fun trick() {
        println("No treats!")
    }

# 二、使用 lambda 表达式重新定义函数
lambda 表达式提供了简洁的语法来定义函数，无需使用 fun 关键字。您可以直接将 lambda 表达式存储在变量中，无需对其他函数进行函数引用。
在赋值运算符 (=) 前面，您要添加 val 或 var 关键字，后跟变量名称，以供您在调用函数时使用。赋值运算符 (=) 后面是 lambda 表达式，它由一对大括号构成，而大括号中的内容则构成函数正文。语法如下图所示：
![alt text](image-7.png)

