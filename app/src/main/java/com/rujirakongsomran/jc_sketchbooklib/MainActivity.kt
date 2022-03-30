package com.rujirakongsomran.jc_sketchbooklib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rujirakongsomran.jc_sketchbooklib.ui.theme.JC_SketchbookLibTheme
import io.getstream.sketchbook.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC_SketchbookLibTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    CreateSketchbook()
                }
            }
        }
    }
}

@Composable
fun CreateSketchbook() {
    val sketchbookController = rememberSketchbookController()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
                .weight(1f),
            elevation = 10.dp,
        ) {
            Sketchbook(
                imageBitmap = ImageBitmap.imageResource(R.drawable.nomsod),
                modifier = Modifier
                    .wrapContentSize()
                    .padding(all = 16.dp),
                controller = sketchbookController,
                backgroundColor = Color.White
            )
        }
        PaintColorPalette(
            controller = sketchbookController,
            header = {
                ColorPickerPaletteIcon(
                    modifier = Modifier
                        .size(60.dp)
                        .padding(6.dp),
                    controller = sketchbookController,
                    bitmap = ImageBitmap.imageResource(id = R.drawable.palette)
                )
            },
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(
                onClick = {
                    sketchbookController.undo()
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_undo),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            IconButton(
                onClick = {
                    sketchbookController.redo()
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_redo),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            IconButton(
                onClick = {
                    sketchbookController.setRainbowShader()
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_brush),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            IconButton(
                onClick = {
                    sketchbookController.toggleEraseMode()
                    val isEraseMode = sketchbookController.isEraseMode.value
                    sketchbookController.setEraseMode(isEraseMode)
                    sketchbookController.setEraseRadius(50f)
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_eraser),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            IconButton(
                onClick = {
                    sketchbookController.clearPaths()
                }) {
                Icon(
                    Icons.Rounded.Delete,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    JC_SketchbookLibTheme {
        CreateSketchbook()
    }
}