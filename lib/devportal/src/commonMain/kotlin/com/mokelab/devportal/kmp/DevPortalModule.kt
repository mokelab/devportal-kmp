package com.mokelab.devportal.kmp

import com.mokelab.devportal.kmp.api.DevPortalNavigator
import org.koin.dsl.module

object DevPortal

val devPortalModule = module {
    single { DevPortalNavigator(startDestination = DevPortal) }
}
