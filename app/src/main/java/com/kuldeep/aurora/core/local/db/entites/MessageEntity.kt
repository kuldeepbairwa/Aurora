package com.kuldeep.aurora.core.local.db.entites

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kuldeep.aurora.features.chat.domain.model.Message
import com.kuldeep.aurora.features.chat.domain.model.MessageOwner

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val senderId: String,
    val receiverId: String,
    val message: String,
    val timestamp: Long = System.currentTimeMillis(),
    val messageOwner: MessageOwner
)

fun MessageEntity.toMessage(): Message {
    return Message(
        senderId = senderId,
        receiverId = receiverId,
        message = message,
        messageOwner = messageOwner,
        timeStamp = timestamp
    )
}

fun Message.toMessageEntity(): MessageEntity {
    return MessageEntity(
        senderId = senderId,
        receiverId = receiverId,
        message = message,
        messageOwner = messageOwner,
        timestamp = timeStamp
    )
}
