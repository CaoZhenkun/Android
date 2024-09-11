
    package com.example.affirmations

    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.WindowInsets
    import androidx.compose.foundation.layout.asPaddingValues
    import androidx.compose.foundation.layout.calculateEndPadding
    import androidx.compose.foundation.layout.calculateStartPadding
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.safeDrawing
    import androidx.compose.foundation.layout.statusBarsPadding
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.items
    import androidx.compose.material3.Card
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Surface
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.platform.LocalLayoutDirection
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.res.stringResource
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import com.example.affirmations.data.Datasource
    import com.example.affirmations.model.Affirmation
    import com.example.affirmations.ui.theme.AffirmationsTheme

    class MainActivity : ComponentActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                AffirmationsTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        AffirmationsApp()
                    }
                }
            }
        }
    }

    @Composable
    fun AffirmationsApp() {
        //在 AffirmationsApp 可组合项中，检索当前的布局方向并将其保存在变量中。它们将用于稍后配置内边距。
        val layoutDirection = LocalLayoutDirection.current
        //创建一个 Surface 可组合项。此可组合项将设置 AffirmationsList 可组合项的内边距。
        Surface(
            modifier = Modifier
                .fillMaxSize()
                //Modifier.statusBarsPadding()：为 Surface 添加状态栏的内边距，确保内容不被状态栏遮挡。
                .statusBarsPadding()
                //Modifier.padding(...)：设置左右内边距，计算方式基于 WindowInsets（窗口安全区域）。
                .padding(
                    start = WindowInsets.safeDrawing.asPaddingValues()
                        .calculateStartPadding(layoutDirection),
                    end = WindowInsets.safeDrawing.asPaddingValues()
                        .calculateEndPadding(layoutDirection),
                ),
        ) {
            AffirmationList(
                affirmationList = Datasource().loadAffirmations()
            )
        }
    }

    @Composable
    fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
        Card(modifier = modifier) {
            Column {
                Image(
                    painter = painterResource(affirmation.imageResourceId),
                    contentDescription = stringResource(affirmation.stringResourceId),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(194.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = LocalContext.current.getString(affirmation.stringResourceId),
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }

    @Composable
    fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
        //在 Jetpack Compose 中，可以使用 LazyColumn 可组合项创建可滚动列表。LazyColumn 和 Column 之间的区别在于，
        // 当要显示的项数量较少时，应使用 Column，因为 Compose 会一次性加载所有项。Column 只能保存预定义或固定数量的可组合项。
        // LazyColumn 可以按需添加内容，因此非常适合较长的列表，尤其是当列表长度未知时。LazyColumn 还提供默认滚动行为，无需添加其他代码。
        LazyColumn(modifier = modifier) {
            //items() 方法用于向 LazyColumn 添加项
            items(affirmationList) { affirmation ->
                //调用 items() 方法需要 lambda 函数。在该函数中，指定一个 affirmation 形参，它代表 affirmationList 中的一个项目（即每个 affirmation 项）。
                AffirmationCard(
                    affirmation = affirmation,
                    modifier = Modifier.padding(8.dp)
                )

            }
        }
    }



    @Preview
    @Composable
    private fun AffirmationCardPreview() {
        AffirmationsApp()

    }