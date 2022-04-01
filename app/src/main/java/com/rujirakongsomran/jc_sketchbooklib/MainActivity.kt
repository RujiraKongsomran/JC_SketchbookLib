package com.rujirakongsomran.jc_sketchbooklib

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
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
    val context = LocalContext.current
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_undo),
                contentDescription = null,
                modifier = Modifier
                    .clickable { sketchbookController.undo() }
                    .size(30.dp),
                tint = if (sketchbookController.canUndo.value) {
                    MaterialTheme.colors.primary
                } else {
                    MaterialTheme.colors.onPrimary
                }
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_redo),
                contentDescription = null,
                modifier = Modifier
                    .clickable { sketchbookController.redo() }
                    .size(30.dp),
                tint = if (sketchbookController.canRedo.value) {
                    MaterialTheme.colors.primary
                } else {
                    MaterialTheme.colors.onPrimary
                }
            )

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_brush),
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        sketchbookController.setEraseMode(false)
                        sketchbookController.setRainbowShader()
                        Toast
                            .makeText(context, "Rainbow Shader", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .size(30.dp),
                tint = MaterialTheme.colors.primary
            )
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.ic_eraser),
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        sketchbookController.toggleEraseMode()
                        Toast
                            .makeText(context, "Eraser Mode", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .size(30.dp),
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_clear),
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        sketchbookController.clear()
                    }
                    .size(30.dp),
                tint = Color.LightGray
            )
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