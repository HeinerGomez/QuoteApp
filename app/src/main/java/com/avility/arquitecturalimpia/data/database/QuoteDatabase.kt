package com.avility.arquitecturalimpia.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.avility.arquitecturalimpia.data.database.dao.QuoteDao
import com.avility.arquitecturalimpia.data.database.entities.QuoteEntity

@Database(
    entities = [QuoteEntity::class],
    version = 1
)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun getQuoteDao(): QuoteDao
}