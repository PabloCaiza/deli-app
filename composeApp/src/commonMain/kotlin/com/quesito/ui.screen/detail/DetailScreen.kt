package com.quesito.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.quesito.data.Movie
import com.quesito.ui.common.LoadingIndicator
import com.quesito.ui.screen.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(vm:DetailViewModel, onBack: () -> Unit) {
    val state = vm.state;
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Screen {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = state.movie?.title?:"") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                })
        }) {
            LoadingIndicator(enabled = state.loading)
            state.movie?.let { movie->
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    AsyncImage(
                        model = movie.backdrop?:movie.poster,
                        contentDescription = movie.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                            .aspectRatio(16f / 9f)
                    )
                    Text(
                        text = movie.overview ,
                        modifier = Modifier.padding(16.dp),
                    )
                    Text(
                        text = movie.overview ,
                        modifier = Modifier.padding(16.dp),
                    )
                }
            }
        }

    }

}