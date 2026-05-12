package com.mokelab.devportal.kmp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.EntryProviderScope
import com.mokelab.devportal.kmp.api.DevPortalFeature

object SampleRoute

class SampleFeature : DevPortalFeature {
    override val name: String = "Sample Feature"
    override val root: Any = SampleRoute
    override val installer: EntryProviderScope<Any>.() -> Unit = {
        entry<SampleRoute> {
            SampleScreen()
        }
    }
}

@Composable
private fun SampleScreen() {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            Text("Hello from Sample Feature!")
        }
    }
}
