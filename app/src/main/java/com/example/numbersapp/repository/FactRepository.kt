package com.example.numbersapp.repository

import android.util.Log
import com.example.numbersapp.API.RetrofitClient
import com.example.numbersapp.models.Fact
import com.example.numbersapp.models.TypeFact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.HttpException

private const val TAG_FACT_REPOSITORY = "com.example.numbersapp.repository"

class FactRepository(private val retrofitClient: RetrofitClient = RetrofitClient) {

    suspend fun getTriviaFact(number: Int): Fact? {
        return responseApiFact(retrofitClient.numbersAPI.getNumberFact(number, TypeFact.TRIVIA.type))
    }

    suspend fun getDateFact(month: Int, year: Int): Fact? {
        return responseApiFact(retrofitClient.numbersAPI.getDateFact(month, year))
    }

    suspend fun getYearFact(year: Int): Fact? {
        return responseApiFact(retrofitClient.numbersAPI.getNumberFact(year, TypeFact.YEAR.type))
    }

    suspend fun getMathFact(number: Int): Fact? {
        return responseApiFact(retrofitClient.numbersAPI.getNumberFact(number, TypeFact.MATH.type))
    }

    private suspend fun responseApiFact(call: Call<Fact>): Fact? {
        return withContext(Dispatchers.IO) {
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    val fact: Fact? = response.body()
                    Log.d(TAG_FACT_REPOSITORY, fact.toString())
                    fact
                } else {
                    Log.d(TAG_FACT_REPOSITORY, "Response unsuccessful: ${response.errorBody()?.string()}")
                    null
                }
            } catch (e: HttpException) {
                Log.d(TAG_FACT_REPOSITORY, "HTTP Error: ${e.message}")
                null
            } catch (t: Throwable) {
                Log.d(TAG_FACT_REPOSITORY, "Error: ${t.message}")
                null
            }
        }
    }

}