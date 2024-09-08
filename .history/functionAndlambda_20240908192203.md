# 一、将函数存储在变量中
## 1.将函数直接存储在变量中
如需将函数作为值引用，您需要使用函数引用运算符 (::)。语法如下图所示：

![alt text](image-6.png)

    fun main() {
        val trickFunction = ::trick
        trickFunction()
    }

    fun trick() {
        println("No treats!")
    }

## 2.使用 lambda 表达式重新定义函数
lambda 表达式提供了简洁的语法来定义函数，无需使用 fun 关键字。您可以直接将 lambda 表达式存储在变量中，无需对其他函数进行函数引用。
在赋值运算符 (=) 前面，您要添加 val 或 var 关键字，后跟变量名称，以供您在调用函数时使用。赋值运算符 (=) 后面是 lambda 表达式，它由一对大括号构成，而大括号中的内容则构成函数正文。语法如下图所示：

![alt text](image-7.png)

1.使用 lambda 表达式重写 trick() 函数。现在，名称 trick 将引用变量的名称。现在，大括号中的函数正文是 lambda 表达式。

2.在 main() 函数中，移除函数引用运算符 (::)，因为 trick 现在引用的是变量，而不是函数名称。

3.在 main() 函数中，将 trickFunction 变量视为函数进行调用。

    fun main() {
        val trickFunction = trick
        trickFunction()
    }

    val trick = {
        println("No treats!")
    }
输出：`No treats!`

# 二、将函数用作数据类型
函数类型由一组圆括号组成，其中包含可选的参数列表、-> 符号和返回值类型。语法如下图所示：
<br>
![alt text](image-8.png)

如果参数接受两个 Int 参数并返回 Int，则其数据类型为 (Int, Int) -> Int。

## 明确指定函数类型的 lambda 表达式声明另一个函数
    fun main() {
        val trickFunction = trick
        trickFunction()
        treat()
    }
    val trick = {
        println("No treats!")
    }
    val treat: () -> Unit = {
        println("Have a treat!")
    }