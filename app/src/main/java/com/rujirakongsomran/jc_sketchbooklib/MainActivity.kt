package com.rujirakongsomran.jc_sketchbooklib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rujirakongsomran.jc_sketchbooklib.ui.theme.JC_SketchbookLibTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JC_SketchbookLibTheme {
                MainScreen()
            }
        }
    }
}