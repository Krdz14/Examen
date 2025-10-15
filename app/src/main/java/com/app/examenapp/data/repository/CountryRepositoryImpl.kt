package com.app.examenapp.data.repository

import android.util.Log
import com.app.examenapp.data.mapper.toDomain
import com.app.examenapp.data.remote.api.CountriesApi
import com.app.examenapp.domain.model.Country
import com.app.examenapp.domain.repository.CountryRepository
import com.app.examenapp.data.local.preferences.CountryPreferences
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class CountryRepositoryImpl
@Inject
constructor(
    private val api: CountriesApi,
    private val preferences: CountryPreferences,
) : CountryRepository {
    // Obtener todos los paise existentes trayendo su nombre y su bandera.
    override suspend fun getCountryList(): List<Country> {
        preferences.getCountryCache()?.let { cache ->
            if (preferences.isCacheValid()) {
                Log.d("CountryRepo", "Usando cache de países (resumen)")
                return cache.countryList
            }
        }

        return try {
            Log.d("CountryRepo", "Iniciando petición getCountryList")
            val response = api.getCountriesList()
            val countryList = response.map { dto -> dto.toDomain() } // resumen

            preferences.saveCountryList(
                countryList = countryList,
                offset = countryList.size,
                totalCount = response.count()
            )

            countryList
        } catch (e: Exception) {
            Log.e("CountryRepo", "Error en getCountryList: ${e.message}", e)
            preferences.getCountryCache()?.countryList ?: throw e
        }
    }

    // Detalle completo de un país incluyendo su captial y región
    override suspend fun getCountry(name: String): Country {
        // Intentar obtener del cache primero
        preferences.getCountryCache()?.let { cache ->
            cache.countryList.find { it.name.equals(name, ignoreCase = true) }?.let {
                if (preferences.isCacheValid() && it.capital != "Unknown" && it.region != "Unknown") {
                    Log.d("CountryRepo", "País encontrado en cache con detalles completos: $name")
                    return it
                }
            }
        }

        return try {
            Log.d("CountryRepo", "Buscando país completo: $name")
            val response = api.getCountry(name)
            val country = response.firstOrNull()?.toDomain() ?: throw Exception("Country not found")

            val currentCache = preferences.getCountryCache()
            val updatedList = if (currentCache != null) {
                val mutableList = currentCache.countryList.toMutableList()
                val index = mutableList.indexOfFirst { it.name.equals(country.name, ignoreCase = true) }
                if (index >= 0) {
                    val existing = mutableList[index]
                    mutableList[index] = country.copy(
                        capital = if (country.capital == "Unknown") existing.capital else country.capital,
                        region = if (country.region == "Unknown") existing.region else country.region,
                        flagUrl = if (country.flagUrl == "No flag") existing.flagUrl else country.flagUrl
                    )
                } else {
                    mutableList.add(country)
                }
                mutableList
            } else {
                listOf(country)
            }

            preferences.saveCountryList(
                countryList = updatedList,
                offset = updatedList.size,
                totalCount = updatedList.count()
            )

            country
        } catch (e: Exception) {
            Log.e("CountryRepo", "Error en getCountry: ${e.message}", e)
            preferences.getCountryCache()?.countryList
                ?.find { it.name.equals(name, ignoreCase = true) }
                ?: throw e
        }
    }
}
