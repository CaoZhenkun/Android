package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }
}

@Composable
//该函数允许传入 modifier 形参。modifier 形参的默认值为 Modifier 对象，因此是方法签名的 = Modifier 部分。
// 借助某个形参的默认值，未来调用该方法的任何调用方都可以决定是否为该形参传递值。如果它们传递自己的 Modifier 对象，
// 则可以自定义界面的行为和装饰。如果它们选择不传递 Modifier 对象，系统会假定使用默认值，即普通的 Modifier 对象。
fun DiceWithButtonAndImage(modifier:Modifier=Modifier ) {
    //var result = 1//创建了一个 result 变量并将其硬编码为 1 值

    //var result by remember { mutableStateOf(1) } 是用来声明并初始化一个具有状态感知能力的变量 result，它的初始值是 1。
    //remember 确保 result 的值在重新组合期间不会被重置。
    //mutableStateOf 使得 result 变成一个状态变量，具有状态跟踪能力，一旦它的值被改变，Jetpack Compose 会重新组合（recompose）界面来反映新值。
    //by 关键字则简化了这个过程，使得你可以像使用普通变量一样直接读取和更新 result，但它实际背后有状态管理逻辑在运作。
    var result by remember { mutableStateOf(1) }
    //由于 result 是通过 remember { mutableStateOf(1) } 创建的状态变量，它具有状态感知能力，
    // 当它的值改变时，Jetpack Compose 会自动触发重新组合。

    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    //垂直布局Column
    Column (
        modifier = modifier,
        //将 horizontalAlignment 实参传递给 Column() 函数，然后将其值设为 Alignment.CenterHorizontally。
        //这可以确保列中的子项相对于宽度在设备屏幕上居中。
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResource),
            //每当您在应用中创建图片时，都应该提供所谓的“内容说明”。
            contentDescription = "1"
        )
        //Spacer 可组合函数，用于在布局中创建空白空间
        Spacer(modifier = Modifier.height(16.dp))//dp 尺寸以 4.dp 为增量进行更改
        //Button(onClick = { /*TODO*/ }) {
        //给按钮加入随机数
        Button(onClick = { result = (1..6).random() }) {
        Text(stringResource(R.string.roll))
        }
    }
}

@Preview
@Composable
fun DiceRollerApp() {
    //，既然有默认值，为什么还要传递 Modifier 实参。原因在于可组合函数可能会进行重组，
    // 这实质上意味着 @Composable 方法中的代码块会再次执行。
    DiceWithButtonAndImage(modifier = Modifier
        //将 fillMaxSize() 方法链接到 Modifier 对象，以便让布局填充整个屏幕。
        .fillMaxSize()
        //wrapContentSize() 方法会指定可用空间应至少与其内部组件一样大。
        // 但是，由于使用了 fillMaxSize() 方法，因此如果布局内的组件小于可用空间，
        // 则可以将 Alignment 对象传递到 wrapContentSize() 方法，以指定组件应如何在可用空间内对齐。
        .wrapContentSize(Alignment.Center)
    )
}

