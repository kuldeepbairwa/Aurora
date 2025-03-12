package com.kuldeep.aurora.features.chat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = text,
            modifier = Modifier
                .padding(start = 20.dp, end = 4.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp, bottomEnd = 0.dp, topEnd = 12.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(4.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.End,
        )
    }
}

@Composable
private fun ReceiverMessageItem(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Text(
            text = text,
            modifier = Modifier
                .padding(start = 4.dp, end = 20.dp)
                .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 0.dp, bottomEnd = 8.dp, topEnd = 8.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(4.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.End,
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

//@Preview(showBackground = true, showSystemUi = false)
//@Composable
//private fun MessageItemPreview() {
//    MessageItem(Message("1234567890", "1234567890", "Hello", MessageOwner.SENDER))
//}