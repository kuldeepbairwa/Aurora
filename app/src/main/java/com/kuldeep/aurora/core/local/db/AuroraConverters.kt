package com.kuldeep.aurora.core.local.db

import androidx.room.TypeConverter
import com.kuldeep.aurora.features.chat.domain.model.MessageOwner

class AuroraConverters {

    @TypeConverter
    fun toHealth(value: String) = enumValueOf<MessageOwner>(value)

    @TypeConverter
    fun fromHealth(value: MessageOwner) = value.name
}