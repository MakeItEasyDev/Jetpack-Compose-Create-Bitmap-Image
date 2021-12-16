package com.jetpack.bitmapimage

import android.content.Context
import android.graphics.Bitmap
import android.view.ViewTreeObserver
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource

class CatImageView(
    context: Context,
    onBitmapCreated: (bitmap: Bitmap) -> Unit
): LinearLayoutCompat(context) {
    init {
        val width = 600
        val height = 670
        val view = ComposeView(context)
        view.visibility = GONE
        view.layoutParams = LayoutParams(width, height)
        this.addView(view)

        view.setContent {
            CatInfo()
        }

        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val graphicUtils = GraphicUtils()
                val bitmap = graphicUtils.createBitmapFromView(view, width, height)
                onBitmapCreated(bitmap)
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}

@Composable
fun CatInfo() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.cat),
            contentDescription = "Bitmap Image",
            contentScale = ContentScale.Fit
        )
    }
}




















