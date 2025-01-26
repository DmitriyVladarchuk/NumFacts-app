package com.example.numbersapp.data.repository

import android.util.Log
import com.example.numbersapp.data.API.NumbersApiService
import com.example.numbersapp.domain.models.Fact
import com.example.numbersapp.domain.models.TypeFact
import com.example.numbersapp.domain.repository.FactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.HttpException
import javax.inject.Inject

private const val TAG_FACT_REPOSITORY = "com.example.numbersapp.data.repository"

class FactRepositoryImpl @Inject constructor(
    private val numbersApiService: NumbersApiService
) : FactRepository {

    override suspend fun getRandomFact(typeFact: TypeFact) : Fact? {
        return responseApiFact(numbersApiService.getRandomFact(typeFact.type))
    }

    suspend fun getTriviaFact(number: Int): Fact? {
        return responseApiFact(numbersApiService.getNumberFact(number, TypeFact.TRIVIA))
    }

    suspend fun getDateFact(month: Int, year: Int): Fact? {
        return responseApiFact(numbersApiService.getDateFact(month, year))
    }

    suspend fun getYearFact(year: Int): Fact? {
        return responseApiFact(numbersApiService.getNumberFact(year, TypeFact.YEAR))
    }

    suspend fun getMathFact(number: Int): Fact? {
        return responseApiFact(numbersApiService.getNumberFact(number, TypeFact.MATH))
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
                    Log.d(
                        TAG_FACT_REPOSITORY,
                        "Response unsuccessful: ${response.errorBody()?.string()}"
                    )
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