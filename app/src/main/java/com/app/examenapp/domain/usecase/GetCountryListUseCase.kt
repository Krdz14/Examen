package com.app.examenapp.domain.usecase

import com.app.examenapp.domain.model.Country
import com.app.examenapp.domain.common.Result
import com.app.examenapp.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCountryListUseCase
@Inject
constructor(
    private val repository: CountryRepository,
) {
    operator fun invoke(): Flow<Result<List<Country>>> =
        flow {
            try {
                emit(Result.Loading)
                val countryList = repository.getCountryList()
                emit(Result.Success(countryList))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
}