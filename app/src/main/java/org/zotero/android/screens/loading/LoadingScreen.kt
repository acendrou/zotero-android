package org.zotero.android.screens.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.zotero.android.architecture.ui.CustomLayoutSize

@Composable
internal fun LoadingScreen(
) {
    val layoutType = CustomLayoutSize.calculateLayoutType()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.size(layoutType.calculateMainLoaderIconSize()),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 2.dp
        )
    }
}