package com.rujirakongsomran.jc_sketchbooklib

import android.annotation.SuppressLint
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.google.modernstorage.photopicker.PhotoPicker
import io.getstream.sketchbook.SketchbookController

@Composable
@SuppressLint("UnsafeOptInUsageError")
fun ColumnScope.PhotoPickerIcon(
    controller: SketchbookController
) {
    val context = LocalContext.current
    val photoPicker =
        rememberLauncherForActivityResult(PhotoPicker()) { uris ->
            val uri = uris.firstOrNull() ?: return@rememberLauncherForActivityResult

            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, uri))
            } else {
                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            }
            controller.setImageBitmap(bitmap.asImageBitmap())
        }
    Box(
        modifier = Modifier
            .padding(
                horizontal = 16.dp
            )
            .align(Alignment.End)
    ) {
        val expanded = remember {
            mutableStateOf(false)
        }
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_gallery),
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    expanded.value = true
                }
                .size(40.dp),
            tint = MaterialTheme.colors.onPrimary
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }) {
            DropdownMenuItem(
                onClick = {
                    photoPicker.launch(PhotoPicker.Args(PhotoPicker.Type.IMAGES_ONLY, 1))
                    expanded.value = false
                })
            {
                Text(text = "Select Photo", color = MaterialTheme.colors.onPrimary)
            }

            DropdownMenuItem(
                onClick = {
                    controller.setImageBitmap(null)
                    expanded.value = false
                }
            ) {
                Text(text = "Clear Photo", color = MaterialTheme.colors.onPrimary)
            }
        }
    }
}