package com.example.numbersapp.API

import com.example.numbersapp.models.Fact
import com.example.numbersapp.models.TypeFact
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApiService {

    @GET("/{number}/{type}?json")
    fun getNumberFact(
        @Path("number") number: Int,
        @Path("type") type: TypeFact,
    ) : Call<Fact>

    @GET("/{mouth}/{year}/{type}?json")
    fun getDateFact(
        @Path("mouth") mouth: Int,
        @Path("year") year: Int,
        @Path("type") type: TypeFact = TypeFact.DATE
    ) : Call<Fact>


    @GET("/random/{type}?json")
    fun getRandomFact(
        @Path("type") type: String
    ) : Call<Fact>
}