package com.rujirakongsomran.jc_sketchbooklib

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import io.getstream.sketchbook.Sketchbook
import io.getstream.sketchbook.SketchbookController

@Composable
fun SketchbookScreen(
    modifier: Modifier,
    controller: SketchbookController
) {
    Box(modifier = modifier) {
        Image(
            bitmap = ImageBitmap.imageResource(id = R.drawable.sketchbook_bg),
            contentDescription = null,
            modifier = Modifier
                .matchParentSize()
                .padding(horizontal = 20.dp)
        )
        Sketchbook(
            modifier = Modifier
                .matchParentSize()
                .padding(
                    start = 30.dp,
                    top = 60.dp,
                    end = 30.dp,
                    bottom = 30.dp
                ),
            controller = controller,
            backgroundColor = Color.White
        )
    }
}