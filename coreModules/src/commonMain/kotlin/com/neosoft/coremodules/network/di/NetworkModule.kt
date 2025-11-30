package com.neosoft.coremodules.network.di

import com.neosoft.coremodules.network.HttpClientProvider
import com.neosoft.coremodules.network.config.NetworkConfig
import org.koin.dsl.module


val networkModule = module {

    // Configurable values (can be overridden later)
    single {
        NetworkConfig(
            baseUrl = "http://10.0.2.2:8090/api/",
            tokenProvider = { null },
            refreshTokenHandler = { false }
        )
    }

    // Shared HttpClient for whole app
    single {
        HttpClientProvider.create(get())
    }
}
