package com.mokelab.devportal.kmp

import androidx.compose.ui.window.ComposeUIViewController
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.mokelab.devportal.kmp.api.DevPortalFeature
import com.mokelab.devportal.kmp.api.DevPortalNavigator
import org.koin.mp.KoinPlatform
import platform.UIKit.UIViewController

@Suppress("FunctionName")
fun DevPortalViewController(): UIViewController {
    val koin = KoinPlatform.getKoin()
    val navigator = koin.get<DevPortalNavigator>()
    val features = koin.getAll<DevPortalFeature>()

    return ComposeUIViewController {
        NavDisplay(
            backStack = navigator.backStack,
            onBack = { navigator.goBack() },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
            ),
            entryProvider = entryProvider {
                entry<DevPortal> {
                    DevPortalScreen(
                        navigator = navigator,
                        features = features,
                    )
                }
                features.forEach { feature -> feature.installer.invoke(this) }
            }
        )
    }
}
