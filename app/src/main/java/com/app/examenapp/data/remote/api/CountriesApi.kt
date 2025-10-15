package com.app.examenapp.data.remote.api

import com.app.examenapp.data.remote.dto.CountryDto
import com.app.examenapp.data.remote.dto.CountryListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountriesApi {
    @GET("v3.1/all")
    suspend fun getCountriesList(
        @Query("fields") fields: String = "name,flags"
    ): List<CountryListDto>

    @GET("v3.1/name/{name}")
    suspend fun getCountry(
        @Path("name") name: String,
        @Query("fullText") fullText: Boolean = true
    ): List<CountryDto>
}