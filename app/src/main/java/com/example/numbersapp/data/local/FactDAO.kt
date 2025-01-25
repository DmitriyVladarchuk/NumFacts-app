package com.example.numbersapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.numbersapp.domain.models.Fact
import kotlinx.coroutines.flow.Flow

@Dao
interface FactDAO {

    @Query("SELECT * FROM facts")
    fun getAllFacts(): Flow<List<Fact>>

    @Query("SELECT * FROM facts WHERE text=:value")
    fun getFactFromText(value: String): Flow<Fact>

    @Query("SELECT COUNT(*) FROM facts WHERE text=:value")
    suspend fun getFactCountByText(value: String): Int

    @Insert(entity = Fact::class)
    fun saveFact(fact: Fact)

    @Delete(entity = Fact::class)
    fun deleteFact(fact: Fact)

    @Query("DELETE FROM facts")
    fun clearFacts()

}