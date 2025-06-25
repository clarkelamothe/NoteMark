package com.clarkelamothe.notemark.feature_auth.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.clarkelamothe.notemark.R

@Composable
fun EyeIconToggle(
    isOn: Boolean,
    onClick: () -> Unit
) = IconButton(
    onClick = onClick
) {
    EyeIcon(isOn)
}

@Composable
fun EyeIcon(isOn: Boolean) {
    if (isOn) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_eye_off),
            contentDescription = null
        )
    } else {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_eye),
            contentDescription = null
        )
    }
}
