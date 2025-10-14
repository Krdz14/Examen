package com.app.examenapp.data.remote.api

import com.app.examenapp.data.remote.dto.CountryDto
import com.app.examenapp.data.remote.dto.CountryListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountriesApi {
    @GET("all?/fields=name")
    suspend fun getCountriesList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ): CountryListDto

    @GET("name/{id}")
    suspend fun getCountry(
        @Path("name") name: String,
    ): CountryDto

}