package com.kuldeep.aurora.core.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kuldeep.aurora.core.local.db.entites.MessageEntity

@Dao
interface MessageDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity)

    @Query("DELETE FROM messages WHERE senderId = :userId OR receiverId = :userId")
    suspend fun deleteAllMessagesForUser(userId: String)


}