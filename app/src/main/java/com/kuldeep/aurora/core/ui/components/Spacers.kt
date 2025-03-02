package com.kuldeep.aurora.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(size: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(size))
}
@Composable
fun HorizontalSpacer(size: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.width(size))
}

@Composable
fun VerticalDivider(thickness: Dp = 1.dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(thickness).fillMaxWidth().background(MaterialTheme.colorScheme.outline))
}
@Composable
fun HorizontalDivider(thickness: Dp = 1.dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.width(thickness).fillMaxHeight().background(MaterialTheme.colorScheme.outline))
}