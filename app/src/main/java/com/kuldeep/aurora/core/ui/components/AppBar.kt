package com.kuldeep.aurora.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AuroraAppBar(
    text: String,
    navigationIcon: ImageVector,
    onNavigate: () -> Unit,
    actionIcon: ImageVector? = null,
    onActionClicked: () -> Unit ={}
) {
    TopAppBar(
       windowInsets = TopAppBarDefaults.windowInsets.only(WindowInsetsSides.Bottom),
        title = { Text(
            text = text,
            style = MaterialTheme.typography.titleLarge
        ) },
        navigationIcon = {
            Icon(
                imageVector = navigationIcon,
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 12.dp).clickable {
                    onNavigate()
                }
            )
        },
        actions = {
            if (actionIcon != null) {
                Icon(
                    imageVector = actionIcon,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 12.dp).clickable {onActionClicked()}
                )
            }
        },
        expandedHeight = TopAppBarDefaults.TopAppBarExpandedHeight,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )


)
}