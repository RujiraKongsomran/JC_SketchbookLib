package com.rujirakongsomran.jc_sketchbooklib

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import io.getstream.sketchbook.*

@Composable
fun MainScreen() {
    val sketchbookController = rememberSketchbookController()
    Column(
        modifier = Modifier
            .background(Color.Black)
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
            modifier = Modifier.padding(6.dp),
            controller = sketchbookController,
            initialSelectedIndex = 3,
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
        Spacer(modifier = Modifier.height(10.dp))
        SketchbookTools(controller = sketchbookController)
        Spacer(modifier = Modifier.height(20.dp))
    }
}