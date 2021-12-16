package com.jetpack.bitmapimage

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetpack.bitmapimage.ui.theme.BitmapImageTheme
import com.jetpack.bitmapimage.viewmodel.BitmapImageViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BitmapImageTheme {
                Surface(color = MaterialTheme.colors.background) {
                    BitmapImage()
                }
            }
        }
    }
}

@Composable
fun BitmapImage() {
    val viewModel: BitmapImageViewModel = viewModel()

    BitmapImageUI(onBitmapCreated = { bitmap ->
        viewModel.bitmapCreated(bitmap)
    })
}

@Composable
fun BitmapImageUI(
    onBitmapCreated: (bitmap: Bitmap?) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Bitmap Image",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Image below is a bitmap Image",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            AndroidView(
                factory = { context ->
                    val catImageView = CatImageView(context) { bitmap ->
                        onBitmapCreated(bitmap)
                    }
                    catImageView
                }
            )

            CatImageHandler()
        }
    }
}

@Composable
fun CatImageHandler() {
    val viewModel: BitmapImageViewModel = viewModel()
    val bitmap = viewModel.onBitmapGenerated.observeAsState().value

    CatImage(bitmap)
}

@Composable
fun CatImage(bitmap: Bitmap?) {
    if (bitmap != null) {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "Bitmap Image",
            contentScale = ContentScale.Fit
        )
    }
}






















