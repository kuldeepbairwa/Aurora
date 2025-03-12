package com.kuldeep.aurora.core.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kuldeep.aurora.core.local.db.dao.MessageDAO
import com.kuldeep.aurora.core.local.db.entites.MessageEntity

@Database(entities = [MessageEntity::class], version = 1, exportSchema = false)
@TypeConverters(AuroraConverters::class)
abstract class AuroraDatabase : RoomDatabase() {

    abstract fun messageDao(): MessageDAO

}