package com.clarkelamothe.notemark.feature_note.presentation.note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.clarkelamothe.notemark.R
import com.clarkelamothe.notemark.core.presentation.designsystem.appbar.NoteMarkTopBar
import com.clarkelamothe.notemark.core.presentation.local.LocalOrientation
import com.clarkelamothe.notemark.core.presentation.local.Orientation
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme
import com.clarkelamothe.notemark.feature_note.presentation.component.NoteMarkDialog
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteScreenRoot(
    viewModel: NoteViewModel = koinViewModel()
) {
    val orientation = LocalOrientation.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    var showDialog by rememberSaveable { mutableStateOf(false) }

    NoteScreen(
        orientation = orientation,
        state = state,
        onAction = {

        }
    )

    NoteMarkDialog(
        show = showDialog,
        title = "Discard Changes?",
        text = "You have unsaved changes. If you discard now, all changes will be lost.",
        confirmText = "Discard",
        cancelText = "Keep Editing",
        onConfirm = { },
        onDismissRequest = { showDialog = false }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    orientation: Orientation?,
    state: NoteState,
    onAction: () -> Unit
) {
    Scaffold(
        containerColor = Color.Transparent,
        contentWindowInsets = WindowInsets(right = 16.dp, left = 16.dp, top = 16.dp),
        topBar = {
            NoteMarkTopBar(
                title = "",
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_close),
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                actions = {
                    TextButton(
                        onClick = {}
                    ) {
                        Text(
                            text = "SAVE NOTE",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        },
        floatingActionButton = {

        }
    ) {
        when (orientation) {
            Orientation.PHONE_PORTRAIT, Orientation.TABLET_PORTRAIT -> {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                        .padding(it)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(
                            end = 16.dp
                        ),
                        text = state.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    HorizontalDivider()
                    Text(
                        modifier = Modifier.padding(
                            end = 16.dp
                        ),
                        text = state.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Orientation.PHONE_LANDSCAPE, Orientation.TABLET_LANDSCAPE -> {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                        .padding(it)
                        .fillMaxSize(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(
                        modifier = Modifier
                            .width(540.dp)
                            .offset(y = (-24).dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(
                                    end = 16.dp
                                )
                                .zIndex(Float.MAX_VALUE),
                            text = state.title,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        HorizontalDivider()
                        Text(
                            text = state.description,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Orientation.DESKTOP, null -> TODO()
        }
    }
}

@Preview
@Composable
private fun NoteScreenPortraitPreview() {
    NoteMarkTheme {
        NoteScreen(
            orientation = Orientation.PHONE_PORTRAIT,
            state = NoteState(title = "Note Title", description = "Note Description"),
            onAction = {}
        )
    }
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun NoteScreenLandscapePreview() {
    NoteMarkTheme {
        NoteScreen(
            orientation = Orientation.PHONE_LANDSCAPE,
            state = NoteState(title = "Note Title", description = "Note Description"),
            onAction = {}
        )
    }
}
