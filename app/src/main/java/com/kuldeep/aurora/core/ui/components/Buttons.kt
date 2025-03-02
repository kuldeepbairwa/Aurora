package com.kuldeep.aurora.core.ui.components

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AccentButton(
    text:String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    ElevatedButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )

    ) {

        Text(
            text = text,
        )
    }

}