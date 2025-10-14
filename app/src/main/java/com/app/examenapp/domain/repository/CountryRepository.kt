package com.app.examenapp.domain.repository

import com.app.examenapp.domain.model.Country

interface CountryRepository {
    suspend fun getCountryList(): List<Country>

    suspend fun getCountry(name: String): Country
}