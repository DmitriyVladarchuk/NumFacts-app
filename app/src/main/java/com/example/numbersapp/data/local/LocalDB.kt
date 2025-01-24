package com.example.numbersapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.numbersapp.domain.models.Fact

@Database(
    entities = [Fact::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class LocalDB : RoomDatabase() {
    abstract fun dao(): FactDAO

    companion object{
        @Volatile
        private var INSTANCE: LocalDB? = null

        fun getBataBase(context: Context): LocalDB {
            return INSTANCE ?: synchronized(this){
                buildDataBase(context).also{ INSTANCE = it }
            }
        }

        private fun buildDataBase(context: Context) = Room
            .databaseBuilder(context, LocalDB::class.java, "FactDB")
            .fallbackToDestructiveMigration()
            .build()

    }
}