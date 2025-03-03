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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuldeep.aurora.features.chatList.domain.ChatRoom

@Composable
fun ChatRoomItem(chatRoom: ChatRoom,onClick:()->Unit) {

    ListItem(
        headlineContent = {
            Text(
                text = chatRoom.receiverPhone,
                maxLines = 1
            )
        },
        colors =
        ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        leadingContent = {

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .padding(10.dp),
                tint = MaterialTheme.colorScheme.onTertiaryContainer
            )

        },
        modifier = Modifier.clickable { onClick() }
    )

}

@Preview
@Composable
private fun ChatRoomItemPreview() {
    ChatRoomItem(
        ChatRoom("123","1234456567"),
        {}
    )
}