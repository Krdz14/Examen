package com.app.examenapp.domain.usecase

import com.app.examenapp.domain.model.Country
import com.app.examenapp.domain.repository.CountryRepository
import com.app.examenapp.domain.common.Result
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCountryUseCase
@Inject
constructor(
    private val repository: CountryRepository,
) {
    operator fun invoke(name: String): Flow<Result<Country>> =
        flow {
            try {
                emit(Result.Loading)
                val country = repository.getCountry(name)
                emit(Result.Success(country))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
}
