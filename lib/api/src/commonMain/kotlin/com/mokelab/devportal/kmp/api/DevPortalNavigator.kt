package com.mokelab.devportal.kmp.api

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class DevPortalNavigator(startDestination: Any) {
    val backStack: SnapshotStateList<Any> = mutableStateListOf(startDestination)

    fun goTo(destination: Any) {
        backStack.add(destination)
    }

    fun goBack() {
        backStack.removeLastOrNull()
    }
}
