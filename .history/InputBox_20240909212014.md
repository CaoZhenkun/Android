# 一、状态
如果发生状态更改，Compose 会使用新状态重新执行受影响的可组合函数，从而创建更新后的界面。这一过程称为“重组”。
____
在 Compose 中，您可以使用 State 和 MutableState 类型让应用中的状态可被 Compose 观察或跟踪。State 类型不可变，因此您只能读取其中的值，而 MutableState 类型是可变的。您可以使用 mutableStateOf() 函数来创建可观察的 MutableState。它接受初始值作为封装在 State 对象中的形参，这样便可使其 value 变为可观察。
____
使用 `MutableState<String>` 类型（而非硬编码的 String 变量），以便 Compose 知道要跟踪 amountInput 状态，然后传入 "0" 字符串，该字符串是 amountInput 状态变量的初始默认值：

    var amountInput: MutableState<String> = mutableStateOf("0")

也可以使用类型推断编写 amountInput 初始化

    var amountInput = mutableStateOf("0")

____
在 TextField 可组合函数中，使用 amountInput.value 属性：

    TextField(
    value = amountInput.value,
    onValueChange = {},
    modifier = modifier
    )

Compose 会跟踪每个读取状态 value 属性的可组合项，并在其 value 更改时触发重组。
____
当文本框的输入更改时，系统会触发 onValueChange 回调。在 lambda 表达式中，it 变量包含新值。

在 onValueChange 具名形参的 lambda 表达式中，将 amountInput.value 属性设置为 it 变量。

当 TextField 通过 onValueChange 回调函数通知您文本发生更改时，您将更新 TextField 的状态（即 amountInput 变量）。

    //应用中的状态是指可以随时间变化的任何值。在该应用中，状态是指账单金额。
    var amountInput: MutableState<String> = mutableStateOf("0")
    //MutableState 用于保存和管理可以动态更新的状态（比如用户输入的值）。
    // 每当 amountInput.value 发生变化时，Compose 会自动重新组合相关的UI。
    TextField(
        value = amountInput.value,
        onValueChange = { amountInput.value = it },
        //it 是新输入的内容，当用户在 TextField 中输入新内容时，
        //amountInput.value 会被更新为新内容。这样，amountInput 也会动态更新，UI 会实时反映新的值。
        modifier = modifier
    )
____
用户在文本框中输入文本后，系统会调用 onValueChange 回调，并使用新值更新 amountInput 变量。Compose 跟踪 amountInput 状态，因此当其值更改时，系统会安排重组并再次执行 EditNumberField() 可组合函数。在该可组合函数中，amountInput 变量会重置为初始 0 值。因此，文本框会显示 0 值。
____
# 二、remember
可组合方法可能会因重组而被系统多次调用。如果不保存，可组合项就会在重组期间重置状态。<br>
初始组合期间，remember 函数计算的值会存储在组合中，而存储的值会在重组期间返回。remember 和 mutableStateOf 函数通常在可组合函数中一起使用，以使状态及其更新正确反映在界面中。
____
在 EditNumberField() 函数中，使用 remember 将对 mutableStateOf() 的调用括起来，以便使用 by remember Kotlin 属性委托来初始化 amountInput 变量。<br>
在 mutableStateOf() 函数中，传入一个空字符串（而非静态 "0" 字符串）

    var amountInput by remember { mutableStateOf("") }

现在，空字符串是 amountInput 变量的初始默认值。by 是 Kotlin 属性委托。amountInput 属性的默认 getter 和 setter 函数分别委托给 remember 类的 getter 和 setter 函数。
____
通过添加委托的 getter 和 setter 导入内容，您可以读取和设置 amountInput，而无需引用 MutableState 的 value 属性。

    @Composable
    fun EditNumberField(modifier: Modifier = Modifier) {
    var amountInput by remember { mutableStateOf("") }
    TextField(
        value = amountInput,
        onValueChange = { amountInput = it },
        modifier = modifier
    )
    }
____
用户在文本框中输入文本后，文本框会的值发生变化。

# 三、外观

## 标签
每个文本框都应包含一个标签，以便用户了解可以输入哪些信息。标签文本位于文本字段的中间，并与输入行对齐。当用户点击文本框以输入文本时，该标签会移到文本框中靠上的位置。

    label = { Text(stringResource(R.string.bill_amount)) }

##  singleLine
将文本框从多行压缩成可水平滚动的单行。

    singleLine = true

## KeyboardOptions
Android 提供了一个选项，用于配置屏幕上显示的键盘，以便输入数字、电子邮件地址、网址和密码等内容。
将键盘类型设置为数字键盘即可输入数字。向 KeyboardOptions 函数传递设置为 KeyboardType.Number 的 keyboardType 具名形参

    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

____

    TextField(
        value = amountInput,
        onValueChange = {
            amountInput = it
        },
        //it 是新输入的内容，当用户在 TextField 中输入新内容时，
        //amountInput.value 会被更新为新内容。这样，amountInput 也会动态更新，UI 会实时反映新的值。
        label = { Text(stringResource(R.string.bill_amount)) },
        singleLine = true,//这样可以将文本框从多行压缩成可水平滚动的单行
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )

# 四、计算小费

    @Composable
    fun EditNumberField(modifier: Modifier = Modifier) {
    var amountInput by remember { mutableStateOf("") }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount)

    TextField(
        value = amountInput,
        onValueChange = { amountInput = it },
        label = { Text(stringResource(R.string.bill_amount)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
    }

需要访问 TipTimeLayout() 函数中的 amountInput 变量来计算和显示小费金额，但 amountInput 变量是 EditNumberField() 可组合函数中定义的文本字段的状态，因此您还无法从 TipTimeLayout() 函数对其进行调用。相应代码的结构如下图所示：

![alt text](image-15.png)

该结构不允许您在新的 Text 可组合项中显示小费金额，因为 Text 可组合项需要访问根据 amountInput 变量计算得出的 amount 变量。您需要向 TipTimeLayout() 函数公开 amount 变量。所需代码结构（这会使 EditNumberField() 可组合项变为无状态）如下图所示：

![alt text](image-16.png)

这种模式称为“状态提升”。

# 五、状态提升

当您的应用变得越来越复杂并且其他可组合项需要访问 EditNumberField() 可组合项中的状态时，您需要考虑将 EditNumberField() 可组合函数中的状态提升或提取出来。

## 有状态和无状态可组合项

当您需要执行以下操作时，应该提升状态：

- 与多个可组合函数共享状态。
- 创建可在应用中重复使用的无状态可组合项。

在您从可组合函数中提取状态后，生成的可组合函数称为无状态函数。也就是说，通过从可组合函数中提取状态，可以将其变为无状态。

无状态可组合项是指没有状态的可组合项，这意味着它不会保存、定义或修改新状态。相反，有状态可组合项是指具有可以随时间变化的状态的可组合项。

状态提升是一种将状态移到其调用方以使组件变为无状态的模式。当应用于可组合项时，这通常意味着向可组合项引入以下两个形参：

- `value: T` 形参，即要显示的当前值。
- `onValueChange: (T) -> Unit` - 回调 lambda，会在值更改时触发，以便可以在其他位置更新状态（例如，当用户在文本框中输入一些文本时）。

## 在 EditNumberField() 函数中提升状态

1.更新 EditNumberField() 函数定义，以通过添加 value 和 onValueChange 形参来提升状态。

    @Composable
    fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
    )

value 形参的类型为 String，onValueChange 形参的类型为 (String) -> Unit，因此它是一个接受 String 值作为输入且没有返回值的函数。onValueChange 形参用作传入 TextField 可组合项的 onValueChange 回调。

2.在 EditNumberField() 函数中，更新 TextField() 可组合函数以使用传入的形参。

    TextField(
    value = value,
    onValueChange = onValueChange,
    )

3.提升状态，将记住的状态从 EditNumberField() 函数移至 TipTimeLayout() 函数。

    @Composable
    fun TipTimeLayout() {
    var amountInput by remember { mutableStateOf("") }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount)
    }

4.您已将状态提升到 TipTimeLayout()，现在将其传递到 EditNumberField()。在 TipTimeLayout() 函数中，更新 EditNumberField() 函数调用以使用提升的状态。

    EditNumberField(
    value = amountInput,
    onValueChange = { amountInput = it },
    modifier = Modifier
        .padding(bottom = 32.dp)
        .fillMaxWidth()
    )

这会使 EditNumberField 变为无状态。您已将界面状态提升到其祖先实体 TipTimeLayout()。TipTimeLayout() 现在是状态(amountInput) 所有者。

# 六、位置格式设置

通过位置格式设置，您可以使用字符串显示动态内容。例如，假设您希望 Tip amount 文本框显示一个 xx.xx 值，该值可以是在您的函数中计算并设置格式的任意金额。如需在 strings.xml 文件中完成此操作，您需要使用占位符实参定义字符串资源，如以下代码段所示：

    <string name="tip_amount">Tip Amount: %s</string>

在 Compose 代码中，您可以拥有多个任意类型的占位符实参。string 占位符为 %s。

    Text(
        text = stringResource(R.string.tip_amount, tip),
        // ...
    )