package com.example.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
    //stringResourceId 表示存储在字符串资源中的自我肯定话语文本的 ID。
    //imageResourceId 表示存储在可绘制资源中的自我肯定话语图片的 ID。
)
