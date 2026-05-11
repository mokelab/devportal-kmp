package com.mokelab.devportal.kmp.api

import androidx.navigation3.runtime.EntryProviderScope

interface DevPortalFeature {
    val name: String
    val installer: EntryProviderScope<Any>.() -> Unit
    val root: Any
}
