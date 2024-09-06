package com.example.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworld.ui.theme.HelloworldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloworldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //GreetingImage(message = "Happy Birthday Sam!", from = "From Tom")
                    //Extract string resource提取字符串
//                    GreetingImage(message = stringResource(R.string.happy_birthday_text),
//                    from = stringResource(R.string.signature_text)
//                    )
                    //Passage(title="Jetpack Compose tutorial",message1="Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
                    //   message2= "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.")
                    Finishing()
                }
            }
        }
    }
}

//**************************************************************************************//
//*******************************生日祝福文字*********************************************//
@Composable
fun GreetingText(message: String,from: String,modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.Center,//居中
        modifier = modifier.padding(8.dp)//内边距
    ){
        Text(
            text = message,
            fontSize = 95.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center//居中
        )
        Text(
            text = from,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)//内边距
                .align(alignment = Alignment.End)//右对齐
                //.align(alignment = Alignment.CenterHorizontally)//居中
        )
    }
}
//**************************************************************************************//

//**************************************************************************************//
//*******************************生日祝福卡片*********************************************//
@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.androidparty)
    Box (
        modifier
            .fillMaxSize()
            //两种都可以
            //.systemBarsPadding()//systemBarsPadding 会自动为内容添加状态栏和导航栏的内边距，避免内容遮盖系统栏。
            .padding(WindowInsets.systemBars.asPaddingValues()) // 使用 WindowInsets 手动处理状态栏
    ){
        Image(
            painter = image,
            contentDescription = null,
            //有多种 ContentScale 类型可供选择。
            //使用 ContentScale.Crop 形参进行缩放时，
            //系统会均匀缩放图片以保持宽高比不变，进而使图片的宽度和高度等于或大于屏幕的相应尺寸。
            contentScale = ContentScale.Crop,
            alpha = 0.5F//更改背景图片的不透明度
        )
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}
//**************************************************************************************//

//**************************************************************************************//
//*******************************文章****************************************************//
@Composable
fun Passage(title: String,message1: String,message2: String,modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.bg_compose_background)
    Column (
        modifier .padding(WindowInsets.systemBars.asPaddingValues()) // 使用 WindowInsets 手动处理状态栏
    ){
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth() // 使图片宽度占满屏幕
                //.height(250.dp) // 可根据需要调整高度
        )
        Text(
            text = title,
            fontSize = 24.sp,
            //textAlign = TextAlign.Center,//居中
            modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
        )
        Text(
            text = message1,
            modifier = modifier.padding(start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify//居中
        )
        Text(
            text = message2,
            modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
            textAlign = TextAlign.Justify//居中
        )
    }
}
//**************************************************************************************//

//**************************************************************************************//
//*******************************完成任务*************************************************//
@Composable
fun Finishing(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.ic_task_completed)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = image,
            contentDescription = null,
        )
        Text(
            text = "All tasks completed",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,//居中
            modifier = modifier.padding(top = 24.dp, bottom = 8.dp),
        )
        Text(
            text = "Nice work!",
            fontSize = 16.sp,
            modifier = modifier.padding(start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Center//居中
        )
    }
}
//**************************************************************************************//

//**************************************************************************************//
//*******************************象限***************************************************//
@Composable
fun QuadrantCard(
    title: String,
    description: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
        //注意Modifier与modifier，正确区分
        Column(
            modifier = modifier
                .fillMaxSize()
                //background()和padding交换顺序结果不同
                .background(backgroundColor)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(bottom = 16.dp),
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = description,
                textAlign = TextAlign.Justify
            )
        }
}
//**************************************************************************************//

//**************************************************************************************//
//*******************************Quadrant()示例数据***************************************//
var title="Text composable"
var description="Displays text and follows the recommended Material Design guidelines."
var color1=Color(0xFFEADDFF)
var color2=Color(0xFFD0BCFF)
var color3=Color(0xFFB69DF8)
var color4=Color(0xFFF6EDFF)
//**************************************************************************************//

@Composable
fun Quadrant() {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.weight(1f)) {
            QuadrantCard(
                title = title,
                description = description,
                backgroundColor = color1,
                modifier = Modifier.weight(1f)
            )
            QuadrantCard(
                title = title,
                description = description,
                backgroundColor = color2,
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            QuadrantCard(
                title = title,
                description = description,
                backgroundColor = color3,
                modifier = Modifier.weight(1f)
            )
            QuadrantCard(
                title = title,
                description = description,
                backgroundColor = color4,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

//**************************************************************************************//
//*******************************名片****************************************************//
@Composable
fun MyCard(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.android_logo)
    val image1 = painterResource(R.drawable.home_24dp_75fb4c)
    val image2 = painterResource(R.drawable.phone_24dp_75fb4c)
    val image3 = painterResource(R.drawable.question_answer_24dp_75fb4c)
    Column(
        Modifier
            .background(Color.Cyan)
            .fillMaxWidth(),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier.weight(3f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .padding(top = 100.dp),
            )
            Text(
                text = "Caozhenkun",
                fontSize = 50.sp,
            )
            Text(
                text = "A",
                fontSize = 40.sp,
            )
        }
        Column(
            Modifier
                .weight(1f)
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.Bottom, // 使内容靠底部对齐
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row (
                modifier = Modifier.align(Alignment.Start)//将子元素在水平方向上左对齐
            ){
                Image(
                    painter = image3,
                    contentDescription = null,
                )
                Text(
                    text = "29932332323@qq.com",
                    fontSize = 25.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(
                modifier = Modifier.align(Alignment.Start)//将子元素在水平方向上左对齐
            ) {
                Image(
                    painter = image2,
                    contentDescription = null,
                )
                Text(
                    text = "1923242424422",
                    fontSize = 25.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

        }
    }
}
//**************************************************************************************//




//@Preview(showBackground = true)
//@Composable
//fun BirthdayCardPreview() {
//    HelloworldTheme {
//        GreetingImage(message = "Happy Birthday Sam!",from = "From Emma")
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun PassagePreview() {
//    HelloworldTheme {
//        Passage(title="Jetpack Compose tutorial",message1="Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
//            message2= "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.")
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun FinishingPreview() {
//    HelloworldTheme {
//        Finishing()
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun QuadrantPreview() {
//    HelloworldTheme {
//        Quadrant()
//    }
//}

@Preview(showBackground = true)
@Composable
fun QuadrantPreview() {
    HelloworldTheme {
        MyCard()
    }
}