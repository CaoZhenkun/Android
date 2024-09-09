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

# 三、将函数用作返回值类型
函数是一种数据类型，因此您可以像使用任何其他数据类型一样使用函数。您甚至可以从其他函数返回函数。语法如下图所示：
![alt text](image-9.png)

定义一个接受 Boolean 类型的 isTrick 参数的 trickOrTreat() 函数。

    fun main() {
        val treatFunction = trickOrTreat(false)
        val trickFunction = trickOrTreat(true)
        treatFunction()
        trickFunction()
    }
    fun trickOrTreat(isTrick: Boolean): () -> Unit {
        if (isTrick) {
            return trick
        } else {
            return treat
        }
    }
    val trick = {
        println("No treats!")
    }

    val treat = {
        println("Have a treat!")
    }

# 四、将一个函数作为参数传递到另一个函数
在 isTrick 参数后面，添加类型为 (Int) -> String 的 extraTreat 参数。

    fun trickOrTreat(isTrick: Boolean, extraTreat: (Int) -> String): () -> Unit {
        if (isTrick) {
            return trick
        } else {
            println(extraTreat(5))
            return treat
        }
    }

当调用 trickOrTreat() 函数时，需要使用 lambda 表达式定义一个函数并为 extraTreat 参数传入该函数。在 main() 函数中，在对 trickOrTreat() 函数的调用前面添加一个 coins() 函数。coins() 函数为 Int 参数指定名称 quantity 并返回 String。您可能会发现这里没有 return 关键字，lambda 表达式中无法使用该关键字。相反，函数中最后一个表达式的结果将成为返回值。

    val coins: (Int) -> String = { quantity ->
        "$quantity quarters"
    }



在 coins() 函数后面，添加一个 cupcake() 函数，如下所示。将 Int 参数命名为 quantity，并使用 -> 运算符将其与函数正文隔开。现在，您可以将 coins() 或 cupcake() 函数传入 trickOrTreat() 函数。

    val coins: (Int) -> String = { quantity ->
        "$quantity quarters"
    }

    val cupcake: (Int) -> String = { quantity ->
        "Have a cupcake!"
    }

调用函数

    fun main() {
        val coins: (Int) -> String = { quantity ->
            "$quantity quarters"
        }

        val cupcake: (Int) -> String = {
            "Have a cupcake!"
        }

        val treatFunction = trickOrTreat(false, coins)
        val trickFunction = trickOrTreat(true, cupcake)
        treatFunction()
        trickFunction()
    }
    fun trickOrTreat(isTrick: Boolean, extraTreat: (Int) -> String): () -> Unit {
        if (isTrick) {
            return trick
        } else {
            println(extraTreat(5))
            return treat
        }
    }
    val trick = {
        println("No treats!")
    }

    val treat = {
        println("Have a treat!")
    }

输出:

    5 quarters
    Have a treat!
    No treats!

# 五、可为 null 的函数类型
需将函数声明为可为 null，请用圆括号括住函数类型，并在右圆括号外后接 ? 符号。例如，如果您想让 () -> String 类型可为 null，则将其声明为 (() -> String)? 类型。语法如下图所示：
![alt text](image-10.png)