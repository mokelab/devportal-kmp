package com.mokelab.devportal.kmp

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(devPortalModule, appKoinModule)
    }
}

@Suppress("FunctionName")
fun MainViewController() = DevPortalViewController()
