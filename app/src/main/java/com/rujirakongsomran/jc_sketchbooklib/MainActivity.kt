package com.rujirakongsomran.jc_sketchbooklib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.rujirakongsomran.jc_sketchbooklib.ui.theme.JC_SketchbookLibTheme
import io.getstream.sketchbook.Sketchbook
import io.getstream.sketchbook.rememberSketchbookController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC_SketchbookLibTheme {
                // A surface container using the 'background' color from the theme
                CreateSketchbook()
            }
        }
    }
}

@Composable
fun CreateSketchbook() {
    val sketchbookController = rememberSketchbookController()
    Sketchbook(
        modifier = Modifier.fillMaxSize(),
        controller = sketchbookController,
        backgroundColor = Color.White
    )
}


@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    JC_SketchbookLibTheme {
        CreateSketchbook()
    }
}