package com.mokelab.devportal.kmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.mokelab.devportal.kmp.api.DevPortalFeature
import com.mokelab.devportal.kmp.api.DevPortalNavigator
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.getKoin

class DevPortalActivity : ComponentActivity() {
    private val navigator: DevPortalNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val features = getKoin().getAll<DevPortalFeature>()

        setContent {
            NavDisplay(
                backStack = navigator.backStack,
                onBack = { navigator.goBack() },
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator(),
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
}
