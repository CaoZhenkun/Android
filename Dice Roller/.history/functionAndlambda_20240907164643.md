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