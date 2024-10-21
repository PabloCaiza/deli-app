package com.quesito

import androidx.room.RoomDatabase
import com.quesito.data.MovieRepository
import com.quesito.data.MovieService
import com.quesito.data.RegionRepository
import com.quesito.data.database.MovieDao
import com.quesito.data.database.MoviesDatabase
import com.quesito.ui.screen.detail.DetailViewModel
import com.quesito.ui.screen.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    single<MovieDao> {
        val dbBuilder = get<RoomDatabase.Builder<MoviesDatabase>>()
        dbBuilder.build().moviesDao()
    }
}
val dataModule = module {
    factoryOf(::MovieRepository)
    factoryOf(::RegionRepository)
    factoryOf(::MovieService)
    single<HttpClient> {         HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.themoviedb.org"
                parameters.append("api_key", "6ba845aa35c8c2ff14e0f01dbe15385f")
            }
        }
    } }
}

val viewModelsModule = module{
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}

expect val nativeModule:Module

fun initKoin(config:KoinAppDeclaration?=null){
    startKoin{
        config?.invoke(this)
        modules(appModule, dataModule, viewModelsModule, nativeModule)
    }
}
