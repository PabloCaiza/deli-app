package com.quesito

import androidx.compose.ui.window.ComposeUIViewController
import com.quesito.data.database.getDatabaseBuilder

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) {
    App()
}