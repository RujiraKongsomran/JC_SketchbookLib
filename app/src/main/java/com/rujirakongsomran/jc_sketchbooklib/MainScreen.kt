package com.rujirakongsomran.jc_sketchbooklib

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.getstream.sketchbook.ColorPickerPaletteIcon
import io.getstream.sketchbook.PaintColorPalette
import io.getstream.sketchbook.PaintColorPaletteTheme
import io.getstream.sketchbook.rememberSketchbookController

@Composable
fun MainScreen() {
    val systemUiController = rememberSystemUiController()
    // hide status bar programmatically
    systemUiController.isStatusBarVisible = false

    val sketchbookController = rememberSketchbookController()

    Column {
        PhotoPickerIcon(controller = sketchbookController)
        SketchbookScreen(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f, fill = false),
            controller = sketchbookController
        )
        PaintColorPalette(
            modifier = Modifier.padding(6.dp),
            controller = sketchbookController,
            theme = PaintColorPaletteTheme(borderColor = MaterialTheme.colors.onPrimary),
            initialSelectedIndex = 3,
            onColorSelected = { _, _ -> sketchbookController.setPaintShader(null) },
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