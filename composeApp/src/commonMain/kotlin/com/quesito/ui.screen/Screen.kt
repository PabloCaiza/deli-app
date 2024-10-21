package com.quesito.ui.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Screen(modifier:Modifier =Modifier, content:@Composable ()-> Unit) {
    MaterialTheme {
        Surface(modifier = modifier,
            content = content
        )
    }
}