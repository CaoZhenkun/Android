package com.example.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
                    GreetingImage(message = stringResource(R.string.happy_birthday_text),
                    from = stringResource(R.string.signature_text)
                    )
                }
            }
        }
    }
}

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

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    HelloworldTheme {
        GreetingImage(message = "Happy Birthday Sam!",from = "From Emma")
    }
}