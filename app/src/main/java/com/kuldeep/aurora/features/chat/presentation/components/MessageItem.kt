package com.kuldeep.aurora.features.chat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kuldeep.aurora.core.domain.model.Message
import com.kuldeep.aurora.core.domain.model.MessageOwner

@Composable
fun MessageItem(
    message: Message,
    modifier: Modifier = Modifier
) {

    when (message.messageOwner) {
        MessageOwner.SENDER -> SenderMessageItem(message.message)

        MessageOwner.RECEIVER -> ReceiverMessageItem(message.message)
    }

}


@Composable
private fun SenderMessageItem(text: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(end = 40.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(MaterialTheme.colorScheme.primaryContainer)
        , horizontalArrangement = Arrangement.End) {

        Text(text = text,
            modifier = Modifier.padding(4.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun ReceiverMessageItem(text: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 40.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(MaterialTheme.colorScheme.secondaryContainer)
        , horizontalArrangement = Arrangement.Start) {

        Text(text = text,
            modifier = Modifier.padding(4.dp),
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}