package com.rujirakongsomran.jc_sketchbooklib

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.getstream.sketchbook.Sketchbook
import io.getstream.sketchbook.SketchbookController

@Composable
fun SketchbookScreen(
    modifier: Modifier,
    controller: SketchbookController
) {
    Box(modifier = modifier) {
        Sketchbook(
            modifier = Modifier
                .matchParentSize()
                .padding(
                    start = 30.dp,
                    top = 60.dp,
                    end = 30.dp,
                    bottom = 30.dp
                )
                .border(BorderStroke(2.dp, color = MaterialTheme.colors.onPrimary))
                .background(Color.White),
            controller = controller,
        )
    }
}