# 一、泛型
类似C++模板

    fun main() {
        val question1 = Question<String>("Quoth the raven ___", "nevermore", "medium")
        val question2 = Question<Boolean>("The sky is green. True or false", false, "easy")
        val question3 = Question<Int>("How many days are there between full moons?", 28, "hard")
    }
    class Question<T>(
        val questionText: String,
        val answer: T,
        val difficulty: String
    )

# 二、枚举类
枚举类用于创建一组具有有限数量的可能值的类型。例如，在现实世界中，您可以使用一个枚举类来表示四个基本方向（北、南、东和西）。

![alt text](image-19.png)

使用点运算符来引用枚举常量。

![alt text](image-20.png)

1.定义了一个名为Difficulty的enum类。

    enum class Difficulty {
        EASY, MEDIUM, HARD
    }

2.在Question类中，将difficulty属性的数据类型String更改为Difficulty。

    class Question<T>(
        val questionText: String,
        val answer: T,
        val difficulty: Difficulty
    )

3.初始化这三个问题时，创建枚举常量来表示变量。

    val question1 = Question<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    val question2 = Question<Boolean>("The sky is green. True or false", false, Difficulty.EASY)
    val question3 = Question<Int>("How many days are there between full moons?", 28, Difficulty.HARD)

# 三、数据类

像Question这样的类只包含数据。它们没有任何用于执行操作的方法。这些类可以定义为“数据类”。

1.非数据类Question调用toString()等方法时

    fun main() {
        val question1 = Question<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
        println(question1.toString())
    }

输出结果仅显示类名称和对象的唯一标识符。

    Question@37f8bb67

2.使用data关键字将Question转换为数据类。

    data class Question<T>(
        val questionText: String,
        val answer: T,
        val difficulty: Difficulty
    )

3.再次运行代码

    fun main() {
        val question1 = Question<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
        println(question1.toString())
    }

输出结果仅显示类名称和对象的唯一标识符。

    Question(questionText=Quoth the raven ___, answer=nevermore, difficulty=MEDIUM)

# 四、单例对象
一个类只包含一个实例
使用object关键字（而不是class关键字）即可。单例对象不能包含构造函数，因为无法直接创建实例。

![alt text](image-21.png)

    object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
    }

## 访问单例对象

![alt text](image-22.png)

    fun main() {
        ...
        println("${StudentProgress.answered} of ${StudentProgress.total} answered.")
    }

## 伴生对象

可以在另一个类中定义单例对象中使用“伴生对象”。伴生对象允许您从类内部访问其属性和方法（如果对象的属性和方法属于相应类的话）
声明声明生成对象，只需在`object`前面添加`companion`关键字即可。

![alt text](image-23.png)

    class Quiz {
        companion object StudentProgress {
            var total: Int = 10
            var answered: Int = 3
        }
    }

    fun main() {
        println("${Quiz.answered} of ${Quiz.total} answered.")
    }

#  五、使用新的属性和方法来扩展类

## 扩展熟悉

1.在Quiz类之后，定义一个名为progressText且类型为String的Quiz.StudentProgress扩展属性。

    val Quiz.StudentProgress.progressText: String

2.为此扩展属性定义了一个 getter，用于返回在main()中使用之前的那个字符串。

    val Quiz.StudentProgress.progressText: String
        get() = "${answered} of ${total} answered"

3.将main()函数中的代码替换为用于输出progressText的代码。由于这是伴生对象的扩展属性，因此，您可以使用类名称Quiz通过点表示法来访问此属性。

    class Quiz {
        companion object StudentProgress {
            var total: Int = 10
            var answered: Int = 3
        }
    }

    val Quiz.StudentProgress.progressText: String
        get() = "${answered} of ${total} answered"

    fun main() {
        println(Quiz.progressText)
    }

## 扩展函数

![alt text](image-24.png)

    fun Quiz.StudentProgress.printProgressBar() {
        repeat(Quiz.answered) { print("▓") }
        repeat(Quiz.total - Quiz.answered) { print("▒") }
        println()
        println(Quiz.progressText)
    }


    class Quiz {
        companion object StudentProgress {
            var total: Int = 10
            var answered: Int = 3
        }
    }

    val Quiz.StudentProgress.progressText: String
        get() = "${answered} of ${total} answered"

    fun main() {
        Quiz.printProgressBar()
    }

# 六、接口

接口（Interface）是编程中的一种概念，通常用于定义一组功能或行为，而不关心这些功能是如何实现的。它提供了一种规范，用来约定类必须实现哪些方法，而具体的实现细节由实现接口的类来完成。

![alt text](image-25.png)

类可以声明它想用“冒号( :)后跟一个空格，再跟接口名称”的形式来扩展接口。

![alt text](image-26.png)

## 定义接口

    interface Animal {
        // 抽象方法（无默认实现）
        fun makeSound()

        // 属性，可以被子类实现
        val type: String

        // 具有默认实现的方法
        fun sleep() {
            println("The animal is sleeping.")
        }
    }

## 实现接口

    class Dog : Animal {
        override fun makeSound() {
            println("Woof!")
        }

        // 实现接口的属性
        override val type: String
            get() = "Dog"
    }


## 使用接口

    fun main() {
        val dog: Animal = Dog()

        dog.makeSound()  // 输出 "Woof!"

        // 使用接口的默认实现方法
        dog.sleep()      // 输出 "The animal is sleeping."

    }





## 使用接口重写扩展函数

1.定义了一个名为ProgressPrintable的接口

    interface ProgressPrintable {
        val progressText: String
    }

2.修改Quiz类的声明，以扩展ProgressPrintable接口。

    class Quiz : ProgressPrintable {
        ...
    }

3.在Quiz类中，添加一个名为progressText且类型为String的属性，如ProgressPrintable接口中所指定。由于该属性来自ProgressPrintable，因此请在val前面添加替换关键字。

    class Quiz : ProgressPrintable {
        override val progressText: String
            get() = "${answered} of ${total} answered"

            override fun printProgressBar() {
            repeat(Quiz.answered) { print("▓") }
            repeat(Quiz.total - Quiz.answered) { print("▒") }
            println()
            println(progressText)
            }

        companion object StudentProgress {
            var total: Int = 10
            var answered: Int = 3
        }
    }


4.从旧的progressText扩展属性中复制属性getter。

    override val progressText: String
        get() = "${answered} of ${total} answered"

5.移除旧的progressText已扩展属性。

6.在ProgressPrintable接口中，添加一个名为printProgressBar的方法，该方法不接受任何参数，也没有返回值。

    interface ProgressPrintable {
        val progressText: String
        fun printProgressBar()
    }

7.在Quiz类中，使用override关键字添加printProgressBar()方法。

    override fun printProgressBar() {
    }

8.将代码从旧的printProgressBar()扩展函数移至接口中的新变量printProgressBar()。通过删除对Quiz的引用，修改最后一行，以引用接口中的新progressText变量。

    override fun printProgressBar() {
        repeat(Quiz.answered) { print("▓") }
        repeat(Quiz.total - Quiz.answered) { print("▒") }
        println()
        println(progressText)
    }

9.删除扩展函数printProgressBar()。此功能现在属于扩展ProgressPrintable的Quiz类。

    fun Quiz.StudentProgress.printProgressBar() {
        repeat(Quiz.answered) { print("▓") }
        repeat(Quiz.total - Quiz.answered) { print("▒") }
        println()
        println(Quiz.progressText)
    }

10.更新main()中的代码。由于printProgressBar()函数现在是Quiz类的一个方法，因此需要先实例化Quiz对象，然后再调用printProgressBar()。

    fun main() {
        Quiz().printProgressBar()
    }

# 八、使用作用域函数来访问类属性和方法

# 使用let()替换过长的对象名称
借助let()函数，您可以使用标识符it来引用 lambda 表达式中的对象，而去掉使用对象的实际名称

    fun printQuiz() {
        println(question1.questionText)
        println(question1.answer)
        println(question1.difficulty)
        println()
        println(question2.questionText)
        println(question2.answer)
        println(question2.difficulty)
        println()
        println(question3.questionText)
        println(question3.answer)
        println(question3.difficulty)
        println()
    }

_____

    fun printQuiz() {
        question1.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
        question2.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
        question3.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
    }

## 使用apply( ) 在没有变量的情况下调用对象方法

作用域函数有一个函数的功能，那么即使尚未将某个对象赋值给变量，您也可以调用该对象调用作用域函数。例如，函数是一个扩展函数，apply()可以通过点表示法调用对象。apply()返回还会对相应对象的引用，以便将其存储在变量中。

    //不需要实例化Quiz()
    Quiz().apply {
        printQuiz()
    }