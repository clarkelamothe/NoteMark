@file:OptIn(ExperimentalMaterial3Api::class)

package com.clarkelamothe.notemark.feature_note.presentation.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.R
import com.clarkelamothe.notemark.core.presentation.local.LocalOrientation
import com.clarkelamothe.notemark.core.presentation.local.Orientation
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme

@Composable
fun NotesScreenRoot(

) {
    val orientation = LocalOrientation.current

    NotesScreen(
        orientation = orientation
    )
}

@Composable
fun NotesScreen(
    orientation: Orientation?
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "NoteMark",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_eye),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(20.dp)
                    ),
                onClick = {}
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_plus),
                    contentDescription = null
                )
            }
        }
    ) {
        when (orientation) {
            Orientation.PHONE_PORTRAIT, Orientation.TABLET_PORTRAIT -> NotesScreenPortrait(
                modifier = Modifier.padding(top = it.calculateTopPadding()),
            )

            Orientation.PHONE_LANDSCAPE, Orientation.TABLET_LANDSCAPE -> {
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.padding(top = it.calculateTopPadding()),
                    contentPadding = PaddingValues(16.dp),
                    verticalItemSpacing = 16.dp,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    columns = StaggeredGridCells.Fixed(3)
                ) {
                    (1..10).map { nm ->
                        item {
                            Text(
                                text = nm.toString(),
                                modifier = Modifier
                                    .background(Color.LightGray)
                            )
                        }
                    }
                }
            }

            Orientation.DESKTOP, null -> TODO()
        }
    }
}

@Preview
@Composable
private fun NotesScreenPreview() {
    NoteMarkTheme {
        NotesScreenRoot()
    }
}
