package com.app.examenapp.data.repository

import com.app.examenapp.data.mapper.toDomain
import com.app.examenapp.data.remote.api.CountriesApi
import com.app.examenapp.domain.model.Country
import com.app.examenapp.domain.repository.CountryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepositoryImpl
@Inject
constructor(
    private val api: CountriesApi,
) : CountryRepository {
     override fun getCountriesList(): List<Country> {
        val response = api.getCountriesList()
        return response.name.map { result ->
            val name =
                result.common
            // Hacemos la llamada para obtener detalles
            api.getCountry(name).toDomain()
        }
    }

    override suspend fun getCountry(name: String): Country = api.getCountry(name).toDomain()
}