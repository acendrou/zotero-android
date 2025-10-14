package org.zotero.android.architecture.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.lang.Integer.min

object CustomLayoutSize {
    enum class LayoutType {
        big, medium, small;

        fun calculateItemCreatorDeleteStartPadding(): Dp {
            return when (this) {
                big, medium -> 18.dp
                small -> 11.dp
            }
        }

        fun calculateIconSize(): Dp {
            return when (this) {
                big, medium -> 30.dp
                small -> 22.dp
            }
        }

        fun calculateItemFieldLabelWidth(): Dp {
            return when (this) {
                big, medium -> 160.dp
                small -> 120.dp
            }
        }

        fun calculateMainLoaderIconSize(): Dp {
            return when (this) {
                big, medium -> 100.dp
                small -> 60.dp
            }
        }

        fun calculateItemsRowTextSize(): TextUnit {
            return when (this) {
                big, medium -> 20.sp
                small -> 14.sp
            }
        }

        fun calculatePdfSettingsOptionTextSize(): TextUnit {
            return when (this) {
                big, medium -> 14.sp
                small -> 14.sp
            }
        }

        fun calculateAllItemsBottomPanelHeight(): Dp {
            return 48.dp
        }

        fun calculateSelectorHeight(): Dp {
            return when (this) {
                big, medium -> 50.dp
                small -> 36.dp
            }
        }
        fun isTablet(): Boolean {
            return when (this) {
                big, medium -> true
                small -> false
            }
        }

    }

    @Composable
    fun calculateLayoutType(): LayoutType {
        val configuration = LocalConfiguration.current

        val screenWidth = configuration.screenWidthDp
        val screenHeight = configuration.screenHeightDp

        val size = min(screenWidth, screenHeight).dp
        if (size >= 834.dp) {
            return LayoutType.big
        } else if (size >= 680.dp) {
            return LayoutType.medium
        }
        return LayoutType.small
    }
}
