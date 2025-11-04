package com.hitech.pickit.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.hitech.pickit.ui.theme.PickItTheme

@Composable
fun TEST(modifier: Modifier = Modifier) {
    val colorSurface = MaterialTheme.colorScheme.surface
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawWithCache {
                // (الجزء 1: يُنفذ فقط عند تغير الحجم)
                // إنشاء كائن الفرشاة الغالي وتخزينه
                val gradientBrush = Brush.verticalGradient(
                    colors = listOf(Color.Blue, Color.Green),
                    startY = 0f,
                    endY = size.height // يعتمد على الحجم
                )

                // (الجزء 2: هذا ما سيتم رسمه)
                // هذه الأوامر تُنفذ كل frame، لكنها تعيد استخدام 'gradientBrush'
                onDrawBehind {
                    drawRect(brush = gradientBrush)
                }
            }
    ) {
        Text("Hello", color = Color.White)
    }


}

@PreviewLightDark
@Composable
private fun testPreview() {
    PickItTheme {
        TEST()
    }

}