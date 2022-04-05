package com.rujirakongsomran.jc_sketchbooklib

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import io.getstream.sketchbook.SketchbookController

@Composable
fun SketchbookTools(
    controller: SketchbookController,
) {
    val context = LocalContext.current
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
                .clickable { controller.undo() }
                .size(30.dp),
            tint = if (controller.canUndo.value) {
                MaterialTheme.colors.primary
            } else {
                MaterialTheme.colors.onPrimary
            }
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_redo),
            contentDescription = null,
            modifier = Modifier
                .clickable { controller.redo() }
                .size(30.dp),
            tint = if (controller.canRedo.value) {
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
                    controller.setEraseMode(false)
                    controller.setRainbowShader()
                    Toast
                        .makeText(context, "Rainbow Shader", Toast.LENGTH_SHORT)
                        .show()
                }
                .size(30.dp),
            tint = MaterialTheme.colors.primary
        )
        Box {
            val expanded = remember { mutableStateOf(false) }
            val widths = listOf(10f, 20f, 30f, 40f, 50f)
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_line_weight),
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        expanded.value = true
                    }
                    .size(30.dp),
                tint = MaterialTheme.colors.onPrimary,
            )
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false })
            {
                widths.forEach { width ->
                    DropdownMenuItem(
                        onClick = {
                            controller.setPaintStrokeWidth(width)
                            expanded.value = false
                        })
                    {
                        Text(
                            text = width.toString(),
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }

        Box {
            val expanded = remember { mutableStateOf(false) }
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_line_style),
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        expanded.value = true
                    }
                    .size(30.dp),
                tint = MaterialTheme.colors.onPrimary
            )
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false })
            {
                DropdownMenuItem(
                    onClick = {
                        controller.setPathEffect(PathEffect.cornerPathEffect(60f))
                        expanded.value = false
                    }
                ) {
                    Text(text = "Normal", color = Color.DarkGray)
                }
                DropdownMenuItem(
                    onClick = {
                        controller.setPathEffect(
                            PathEffect.dashPathEffect(
                                floatArrayOf(20f, 40f),
                                40f
                            )
                        )
                    }
                ) {
                    Text(text = "Dash Effect", color = Color.DarkGray)
                }
            }
        }
        Image(
            bitmap = ImageBitmap.imageResource(id = R.drawable.ic_eraser),
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    controller.toggleEraseMode()
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
                    controller.clear()
                }
                .size(30.dp),
            tint = Color.LightGray
        )
    }
}
