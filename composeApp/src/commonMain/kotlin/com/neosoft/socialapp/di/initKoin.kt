package com.neosoft.socialapp.di

import com.neosoft.coremodules.network.di.networkModule
import neosoft.chat.di.chatModule
import neosoft.lounge.di.loungeModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(networkModule,sharedModule, chatModule,
                loungeModule)
    }
}