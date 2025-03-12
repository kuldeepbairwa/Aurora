package com.kuldeep.aurora.features.chatList.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kuldeep.aurora.features.newChat.domain.model.Contact

@Composable
fun ContactItem(contact:Contact, onClick:()->Unit) {

    ListItem(
        headlineContent = {
            Text(
                text = contact.name,
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium
            )
        },
        colors =
            ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLow
            ),
        leadingContent = {

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                    .padding(10.dp),
                tint = MaterialTheme.colorScheme.onSurface
            )

        },
        supportingContent = {
            Text(text = contact.phoneNumber, style = MaterialTheme.typography.bodySmall)
        },
        modifier = Modifier.clickable { onClick() }
    )

}