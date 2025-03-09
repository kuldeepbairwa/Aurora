package com.kuldeep.aurora.core.ui.components

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun PopupMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    items: List<PopupMenuItem>
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {
        items.forEach { menuItem ->
            DropdownMenuItem(
                text = { Text(text = menuItem.title) },
                onClick = {
                    menuItem.action()
                    onDismissRequest() // Close menu after clicking an item
                }
            )
        }
    }
}

data class PopupMenuItem(
    val title: String,
    val action: () -> Unit
)

@Preview(showBackground = true)
@Composable
private fun PopUpPreview() {
    PopupMenu(
        expanded = true,
        onDismissRequest = { },
        items = listOf(
            PopupMenuItem("Item 1") { },
            PopupMenuItem("Item 2") { }
        )
    )
}