package com.kuldeep.aurora.features.chat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kuldeep.aurora.features.chat.domain.model.Message
import com.kuldeep.aurora.features.chat.domain.model.MessageOwner

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
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .heightIn(min = 44.dp)
            .padding(horizontal = 40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalArrangement = Arrangement.End
    ) {

        Text(
            text = text,
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.End
        )
    }
}

@Composable
private fun ReceiverMessageItem(text: String) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .heightIn(min = 44.dp)
            .padding(horizontal = 40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer),
        horizontalArrangement = Arrangement.Start
    ) {

        Text(
            text = text,
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Start
        )
    }
}