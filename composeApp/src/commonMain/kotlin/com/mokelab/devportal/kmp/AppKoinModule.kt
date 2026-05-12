package com.mokelab.devportal.kmp

import com.mokelab.devportal.kmp.api.DevPortalFeature
import org.koin.dsl.module

val appKoinModule = module {
    single<DevPortalFeature> { SampleFeature() }
}
