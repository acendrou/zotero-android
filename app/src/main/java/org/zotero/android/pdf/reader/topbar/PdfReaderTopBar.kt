package org.zotero.android.pdf.reader.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.zotero.android.architecture.ui.CustomLayoutSize
import org.zotero.android.pdf.reader.PdfReaderVMInterface
import org.zotero.android.pdf.reader.PdfReaderViewState
import org.zotero.android.pdf.reader.pdfsearch.PdfReaderSearchViewModel
import org.zotero.android.pdf.reader.pdfsearch.PdfReaderSearchViewState
import org.zotero.android.pdf.reader.pdfsearch.popup.PdfReaderSearchPopup
import org.zotero.android.pdf.reader.share.PdfReaderSharePopup
import org.zotero.android.uicomponents.Drawables
import org.zotero.android.uicomponents.icon.IconWithPaddingM3

@Composable
internal fun PdfReaderTopBar(
    onBack: () -> Unit,
    onShowHideSideBar: () -> Unit,
    onShareButtonTapped: () -> Unit,
    toPdfSettings: () -> Unit,
    toPdfPlainReader: () -> Unit,
    onShowHidePdfSearch: () -> Unit,
    toggleToolbarButton: () -> Unit,
    isToolbarButtonSelected: Boolean,
    showSideBar: Boolean,
    showPdfSearch: Boolean,
    viewState: PdfReaderViewState,
    viewModel: PdfReaderVMInterface,
    pdfReaderSearchViewModel: PdfReaderSearchViewModel,
    pdfReaderSearchViewState: PdfReaderSearchViewState,
) {
    val isTablet = CustomLayoutSize.calculateLayoutType().isTablet()

    TopAppBar(
//        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        title = {},
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(Drawables.arrow_back_24dp),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        actions = {
            Spacer(modifier = Modifier.width(48.dp))
            IconWithPaddingM3(
                unselectedDrawableRes = Drawables.view_sidebar,
                selectedDrawableRes = Drawables.view_sidebar_filled,
                onToggle = {
                    onShowHideSideBar()
                },
                isSelected = showSideBar
            )
            IconButton(onClick = toPdfPlainReader) {
                Icon(
                    painter = painterResource(Drawables.reader),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconWithPaddingM3(
                unselectedDrawableRes = Drawables.draw,
                selectedDrawableRes = Drawables.draw_filled,
                onToggle = toggleToolbarButton,
                isSelected = isToolbarButtonSelected
            )

            if (isTablet) {
                Box {
                    IconWithPaddingM3(
                        unselectedDrawableRes = Drawables.search,
                        selectedDrawableRes = Drawables.search,
                        onToggle = onShowHidePdfSearch,
                        isSelected = true,
                    )
                    if (viewState.showPdfSearch) {
                        PdfReaderSearchPopup(
                            viewModel = viewModel,
                            pdfReaderSearchViewState = pdfReaderSearchViewState,
                            pdfReaderSearchViewModel = pdfReaderSearchViewModel,
                        )
                    }
                }
            } else {
                IconWithPaddingM3(
                    unselectedDrawableRes = Drawables.search,
                    selectedDrawableRes = Drawables.search,
                    onToggle = {
                        onShowHidePdfSearch()
                    },
                    isSelected = showPdfSearch
                )
            }
            Box {
                if (viewState.showSharePopup) {
                    PdfReaderSharePopup(
                        viewModel = viewModel,
                        viewState = viewState,
                    )
                }
                IconWithPaddingM3(
                    unselectedDrawableRes = Drawables.share,
                    selectedDrawableRes = Drawables.share,
                    onToggle = onShareButtonTapped,
                    isSelected = false
                )
            }

            IconWithPaddingM3(
                unselectedDrawableRes = Drawables.settings_24px,
                selectedDrawableRes = Drawables.settings_24px,
                onToggle = toPdfSettings,
                isSelected = false
            )
        },

        )

}
