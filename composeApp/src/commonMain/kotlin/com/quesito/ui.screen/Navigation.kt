package com.quesito.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.quesito.data.Movie
import com.quesito.data.MovieRepository
import com.quesito.data.MovieService
import com.quesito.data.database.MovieDao
import com.quesito.ui.screen.detail.DetailScreen
import com.quesito.ui.screen.detail.DetailViewModel
import com.quesito.ui.screen.home.HomeScreen
import com.quesito.ui.screen.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.http.parameters
import io.ktor.http.parametersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

@OptIn(KoinExperimentalAPI::class)
@Composable
fun Navigation( ) {
    val navController = rememberNavController()
    val apiKey = "6ba845aa35c8c2ff14e0f01dbe15385f";
    val client = remember {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.themoviedb.org"
                    parameters.append("api_key", apiKey)
                }
            }
        }
    }
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onMovieClick = { movie: Movie -> navController.navigate("detail/${movie.id}") },
                vm = koinViewModel()
            )
        }
        composable(
            route = "detail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId");
            DetailScreen(
                vm= koinViewModel(parameters = { parametersOf(movieId) }),
                onBack = { navController.popBackStack() })
        }
    }
}